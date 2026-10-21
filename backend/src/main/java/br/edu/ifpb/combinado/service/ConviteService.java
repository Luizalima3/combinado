package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.ConviteRequestDTO;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.NotificacaoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.repository.ConviteHistoricoRepository;
import br.edu.ifpb.combinado.dto.ConviteRespostaDTO;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConviteService {

    private final ConviteRepository conviteRepository;
    private final CombinadoRepository combinadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacaoRepository notificacaoRepository;
    private final ConviteHistoricoRepository historico;

    public ConviteService(ConviteRepository conviteRepository,
                          CombinadoRepository combinadoRepository,
                          UsuarioRepository usuarioRepository,
                          NotificacaoRepository notificacaoRepository,
                          ConviteHistoricoRepository historico) {
        this.conviteRepository = conviteRepository;
        this.combinadoRepository = combinadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.notificacaoRepository = notificacaoRepository;
        this.historico = historico;
    }

    @Transactional
    @PreAuthorize("@combinadoAcesso.eCriador(#combinadoId, authentication)")
    public Convite enviarConvite(Long combinadoId, ConviteRequestDTO dto) {
        Combinado combinado = combinadoRepository.buscarParaConvite(combinadoId)
                .orElseThrow(() -> new IllegalArgumentException("Combinado não encontrado: " + combinadoId));

        Usuario remetente = usuarioRepository.findById(dto.remetenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remetente não encontrado: " + dto.remetenteId()));

        Usuario destinatario = usuarioRepository.findById(dto.destinatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado: " + dto.destinatarioId()));

        if (remetente.getId().equals(destinatario.getId())) {
            throw new IllegalArgumentException("O criador não pode enviar convite para si mesmo.");
        }

        if (combinado.getCriador() == null || !remetente.getId().equals(combinado.getCriador().getId())) {
            throw new AccessDeniedException("Somente o criador pode enviar convites.");
        }
        validarEstado(combinado);
        if (conviteRepository.existsByCombinadoIdAndDestinatarioIdAndStatus(combinadoId,
                destinatario.getId(), StatusConvite.PENDENTE)) {
            throw new ConviteConflitoException("Já existe um convite pendente para este participante.");
        }
        if (conviteRepository.findFirstByCombinadoIdAndDestinatarioIdOrderByDataEnvioDescIdDesc(
                combinadoId, destinatario.getId()).filter(v -> v.getStatus() == StatusConvite.ACEITO).isPresent()) {
            throw new ConviteConflitoException("O participante já aceitou o convite.");
        }

        Convite convite = new Convite();
        convite.setCombinado(combinado);
        convite.setRemetente(remetente);
        convite.setDestinatario(destinatario);
        convite.setMensagem(dto.mensagem());

        Convite salvo = conviteRepository.save(convite);
        historico.save(new ConviteHistorico(salvo, remetente, AcaoConvite.ENVIO, Instant.now()));

        // Gera notificação automática para o participante convidado
        String conteudo = String.format("Você foi convidado por %s para o combinado '%s'.", remetente.getNome(), combinado.getTitulo());
        Notificacao notificacao = new Notificacao(destinatario, "Novo Convite de Combinado", conteudo);
        notificacaoRepository.save(notificacao);

        return salvo;
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ConviteRespostaDTO aceitar(Long id) { return responder(id, StatusConvite.ACEITO); }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ConviteRespostaDTO recusar(Long id) { return responder(id, StatusConvite.RECUSADO); }

    private ConviteRespostaDTO responder(Long id, StatusConvite resposta) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UsuarioPrincipal principal)) {
            throw new AccessDeniedException("Autenticação necessária.");
        }
        Convite convite = conviteRepository.buscarParaResponder(id)
                .orElseThrow(() -> new AccessDeniedException("Convite indisponível."));
        if (!convite.getDestinatario().getId().equals(principal.usuario().getId())) {
            throw new AccessDeniedException("Somente o destinatário pode responder ao convite.");
        }
        validarEstado(convite.getCombinado());
        if (convite.getStatus() != StatusConvite.PENDENTE) {
            throw new ConviteConflitoException("Este convite já foi respondido.");
        }
        var recente = conviteRepository.findFirstByCombinadoIdAndDestinatarioIdOrderByDataEnvioDescIdDesc(
                convite.getCombinado().getId(), convite.getDestinatario().getId()).orElseThrow();
        if (!recente.getId().equals(convite.getId())) {
            throw new ConviteConflitoException("Este convite foi substituído por um convite mais recente.");
        }
        Instant agora = Instant.now();
        convite.setStatus(resposta);
        convite.setDataResposta(agora);
        conviteRepository.save(convite);
        historico.save(new ConviteHistorico(convite, convite.getDestinatario(),
                resposta == StatusConvite.ACEITO ? AcaoConvite.ACEITE : AcaoConvite.RECUSA, agora));
        return new ConviteRespostaDTO(convite.getId(), convite.getCombinado().getId(), resposta, agora);
    }

    private void validarEstado(Combinado combinado) {
        if (combinado.getStatus() != StatusCombinado.AGUARDANDO_CONFIRMACAO
                && combinado.getStatus() != StatusCombinado.ATIVO) {
            throw new ConviteConflitoException("O combinado não permite envio ou resposta de convites neste estado.");
        }
    }
}
