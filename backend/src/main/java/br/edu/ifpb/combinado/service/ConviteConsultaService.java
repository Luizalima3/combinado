package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.*;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConviteConsultaService {
    private final ConviteRepository convites;
    private final ParticipanteConsultaService participantes;
    private final br.edu.ifpb.combinado.repository.ConviteHistoricoRepository historico;
    public ConviteConsultaService(ConviteRepository convites, ParticipanteConsultaService participantes,
            br.edu.ifpb.combinado.repository.ConviteHistoricoRepository historico) {
        this.convites = convites;
        this.participantes = participantes;
        this.historico = historico;
    }
    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public ConviteDetalheDTO buscar(Long id) {
        // Mesma resposta para ID inexistente e convite privado de outra pessoa.
        var convite = convites.findById(id).orElseThrow(() -> new AccessDeniedException("Convite indisponível."));
        Long usuarioId = ((UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).usuario().getId();
        var combinado = convite.getCombinado();
        boolean criador = combinado.getCriador() != null && usuarioId.equals(combinado.getCriador().getId());
        if (!criador && !usuarioId.equals(convite.getDestinatario().getId())) {
            throw new AccessDeniedException("Convite indisponível.");
        }
        var confirmados = participantes.listar(combinado.getId()).stream()
                .filter(p -> p.status() == StatusConvite.ACEITO).toList();
        return new ConviteDetalheDTO(convite.getId(), combinado.getId(), combinado.getTitulo(),
                combinado.getCategoria(), convite.getStatus(), convite.getMensagem(), convite.getDataEnvio(),
                resumo(convite.getRemetente()), resumo(convite.getDestinatario()), confirmados,
                convite.getDataResposta(), historico.findByConviteIdOrderByOcorridoEmAscIdAsc(id)
                        .stream().map(ConviteHistoricoDTO::de).toList());
    }
    private UsuarioResumoDTO resumo(Usuario u) { return new UsuarioResumoDTO(u.getId(), u.getNome(), u.getEmail()); }
}
