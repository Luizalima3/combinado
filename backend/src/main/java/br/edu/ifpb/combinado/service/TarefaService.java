package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.*;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class TarefaService {
    private final TarefaRepository tarefas;
    private final CombinadoRepository combinados;
    private final UsuarioRepository usuarios;
    public TarefaService(TarefaRepository tarefas, CombinadoRepository combinados, UsuarioRepository usuarios) {
        this.tarefas = tarefas; this.combinados = combinados; this.usuarios = usuarios;
    }
    @Transactional(readOnly = true)
    @PreAuthorize("@combinadoAcesso.podeConsultar(#combinadoId, authentication)")
    public List<TarefaResumoDTO> listar(Long combinadoId) {
        return tarefas.findByCombinadoIdOrderByIdAsc(combinadoId).stream().map(TarefaResumoDTO::de).toList();
    }
    @Transactional
    @PreAuthorize("@combinadoAcesso.eCriador(#combinadoId, authentication)")
    public List<TarefaResumoDTO> adicionar(Long combinadoId, List<TarefaInputDTO> dados) {
        if (dados == null || dados.isEmpty() || dados.size() > 100) {
            throw new IllegalArgumentException("Informe de 1 a 100 tarefas.");
        }
        var combinado = combinados.buscarParaConvite(combinadoId).orElseThrow();
        if (combinado.getStatus() == StatusCombinado.CONCLUIDO || combinado.getStatus() == StatusCombinado.CANCELADO) {
            throw new IllegalArgumentException("O combinado finalizado não permite novas tarefas.");
        }
        List<Tarefa> novas = new ArrayList<>();
        for (var dto : dados) {
            if (dto == null || dto.descricao() == null || dto.descricao().isBlank()) {
                throw new IllegalArgumentException("A descrição da tarefa é obrigatória.");
            }
            Set<Long> ids = new LinkedHashSet<>();
            if (dto.usuarioId() != null) ids.add(dto.usuarioId());
            if (dto.responsaveisIds() != null) ids.addAll(dto.responsaveisIds());
            if (ids.isEmpty() || ids.contains(null)) throw new IllegalArgumentException("Informe responsáveis válidos.");
            var tarefa = new Tarefa();
            tarefa.setCombinado(combinado);
            tarefa.setDescricao(dto.descricao().strip());
            for (Long id : ids) {
                var usuario = usuarios.findById(id).orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado: " + id));
                boolean vinculado = combinado.getCriador().getId().equals(id)
                        || combinado.getParticipantes().stream().anyMatch(u -> u.getId().equals(id))
                        || combinados.temVinculo(combinadoId, id);
                if (!vinculado) throw new IllegalArgumentException("O responsável deve estar vinculado ao combinado.");
                tarefa.getResponsaveis().add(usuario);
            }
            novas.add(tarefa);
        }
        return tarefas.saveAll(novas).stream().map(TarefaResumoDTO::de).toList();
    }
}
