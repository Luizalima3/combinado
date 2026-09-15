package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.ConviteRequestDTO;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.NotificacaoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConviteService {

    private final ConviteRepository conviteRepository;
    private final CombinadoRepository combinadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacaoRepository notificacaoRepository;

    public ConviteService(ConviteRepository conviteRepository,
                          CombinadoRepository combinadoRepository,
                          UsuarioRepository usuarioRepository,
                          NotificacaoRepository notificacaoRepository) {
        this.conviteRepository = conviteRepository;
        this.combinadoRepository = combinadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.notificacaoRepository = notificacaoRepository;
    }

    @Transactional
    public Convite enviarConvite(Long combinadoId, ConviteRequestDTO dto) {
        Combinado combinado = combinadoRepository.findById(combinadoId)
                .orElseThrow(() -> new IllegalArgumentException("Combinado não encontrado: " + combinadoId));

        Usuario remetente = usuarioRepository.findById(dto.remetenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remetente não encontrado: " + dto.remetenteId()));

        Usuario destinatario = usuarioRepository.findById(dto.destinatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado: " + dto.destinatarioId()));

        if (remetente.getId().equals(destinatario.getId())) {
            throw new IllegalArgumentException("O criador não pode enviar convite para si mesmo.");
        }

        Convite convite = new Convite();
        convite.setCombinado(combinado);
        convite.setRemetente(remetente);
        convite.setDestinatario(destinatario);
        convite.setMensagem(dto.mensagem());

        Convite salvo = conviteRepository.save(convite);

        // Gera notificação automática para o participante convidado
        String conteudo = String.format("Você foi convidado por %s para o combinado '%s'.", remetente.getNome(), combinado.getTitulo());
        Notificacao notificacao = new Notificacao(destinatario, "Novo Convite de Combinado", conteudo);
        notificacaoRepository.save(notificacao);

        return salvo;
    }
}
