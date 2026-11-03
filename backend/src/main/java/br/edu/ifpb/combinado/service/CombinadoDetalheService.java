package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.*;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.security.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CombinadoDetalheService {
    private final CombinadoRepository combinados;
    private final ParticipanteConsultaService participantes;
    private final TarefaService tarefas;
    private final CombinadoAcessoService acesso;

    public CombinadoDetalheService(CombinadoRepository combinados, ParticipanteConsultaService participantes,
            TarefaService tarefas, CombinadoAcessoService acesso) {
        this.combinados = combinados;
        this.participantes = participantes;
        this.tarefas = tarefas;
        this.acesso = acesso;
    }

    @Transactional(readOnly = true)
    @PreAuthorize("@combinadoAcesso.podeConsultar(#id, authentication)")
    public CombinadoDetalheDTO buscar(Long id) {
        var combinado = combinados.findById(id).orElseThrow(() -> new IllegalArgumentException("Combinado não encontrado."));
        var pessoas = participantes.listar(id);
        var atividades = tarefas.listar(id);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (UsuarioPrincipal) authentication.getPrincipal();
        boolean criador = combinado.getCriador() != null
                && combinado.getCriador().getId().equals(principal.usuario().getId());
        var estado = combinado.getStatus();
        List<AcaoPermitidaCombinado> acoes = new ArrayList<>();
        if (criador && (estado == StatusCombinado.RASCUNHO || estado == StatusCombinado.AGUARDANDO_CONFIRMACAO)) {
            acoes.add(AcaoPermitidaCombinado.EDITAR);
            acoes.add(AcaoPermitidaCombinado.ADICIONAR_TAREFAS);
        }
        if (criador && estado == StatusCombinado.RASCUNHO) acoes.add(AcaoPermitidaCombinado.PUBLICAR);
        if (criador && estado.podeTransicionarPara(StatusCombinado.CANCELADO)) acoes.add(AcaoPermitidaCombinado.CANCELAR);
        if (estado == StatusCombinado.ATIVO && acesso.podeConcluir(id, authentication)) {
            var estados = pessoas.stream().collect(Collectors.toMap(ParticipanteStatusDTO::id, ParticipanteStatusDTO::status));
            if (ConfirmacaoCombinado.pendentes(combinado, usuarioId -> Optional.ofNullable(estados.get(usuarioId))).isEmpty()) {
                acoes.add(AcaoPermitidaCombinado.CONCLUIR);
            }
        }
        acoes.add(AcaoPermitidaCombinado.VER_HISTORICO);
        return CombinadoDetalheDTO.de(CombinadoResumoDTO.de(combinado), pessoas, atividades, acoes);
    }
}
