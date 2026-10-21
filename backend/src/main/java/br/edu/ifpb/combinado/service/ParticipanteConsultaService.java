package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.ParticipanteStatusDTO;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class ParticipanteConsultaService {
    private final CombinadoRepository combinados;
    private final ConviteRepository convites;
    public ParticipanteConsultaService(CombinadoRepository combinados, ConviteRepository convites) {
        this.combinados = combinados;
        this.convites = convites;
    }
    @Transactional(readOnly = true)
    @PreAuthorize("@combinadoAcesso.podeAcessar(#combinadoId, authentication)")
    public List<ParticipanteStatusDTO> listar(Long combinadoId) {
        var combinado = combinados.findById(combinadoId)
                .orElseThrow(() -> new IllegalArgumentException("Combinado não encontrado."));
        Map<Long, ParticipanteStatusDTO> participantes = new HashMap<>();
        // O convite mais recente determina o status quando houve reenvio.
        for (var convite : convites.findByCombinadoIdOrderByDataEnvioDescIdDesc(combinadoId)) {
            Usuario usuario = convite.getDestinatario();
            participantes.putIfAbsent(usuario.getId(), resumo(usuario, convite.getStatus()));
        }
        for (Usuario usuario : combinado.getUsuariosParticipantes()) {
            participantes.putIfAbsent(usuario.getId(), resumo(usuario, StatusConvite.PENDENTE));
        }
        return participantes.values().stream().sorted(Comparator.comparing(ParticipanteStatusDTO::nome)
                .thenComparing(ParticipanteStatusDTO::id)).toList();
    }
    private ParticipanteStatusDTO resumo(Usuario usuario, StatusConvite status) {
        return new ParticipanteStatusDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), status);
    }
}
