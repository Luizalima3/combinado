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
    private final ResponsabilidadeRepository tarefas;
    private final CombinadoRepository combinados;
    private final UsuarioRepository usuarios;
    public TarefaService(ResponsabilidadeRepository tarefas, CombinadoRepository combinados, UsuarioRepository usuarios) {
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
        var combinado = combinadoEditavel(combinadoId);
        List<Responsabilidade> novas = new ArrayList<>();
        for (var dto : dados) {
            var validado = validar(combinado, dto);
            var tarefa = new Responsabilidade();
            tarefa.setCombinado(combinado);
            tarefa.setDescricao(validado.descricao());
            tarefa.getResponsaveis().addAll(validado.responsaveis());
            novas.add(tarefa);
        }
        return tarefas.saveAll(novas).stream().map(TarefaResumoDTO::de).toList();
    }

    @Transactional
    @PreAuthorize("@combinadoAcesso.eCriador(#combinadoId, authentication)")
    public TarefaResumoDTO editar(Long combinadoId, Long tarefaId, TarefaInputDTO dados) {
        var combinado = combinadoEditavel(combinadoId);
        var tarefa = tarefas.findByIdAndCombinadoId(tarefaId, combinadoId)
                .orElseThrow(TarefaNaoEncontradaException::new);
        var validado = validar(combinado, dados);
        // Validação completa antes de alterar a coleção gerenciada.
        tarefa.setDescricao(validado.descricao());
        tarefa.getResponsaveis().clear();
        tarefa.getResponsaveis().addAll(validado.responsaveis());
        return TarefaResumoDTO.de(tarefas.saveAndFlush(tarefa));
    }

    private Combinado combinadoEditavel(Long combinadoId) {
        var combinado = combinados.buscarParaConvite(combinadoId).orElseThrow();
        if (combinado.getStatus() != StatusCombinado.RASCUNHO
                && combinado.getStatus() != StatusCombinado.AGUARDANDO_CONFIRMACAO) {
            throw new IllegalArgumentException("O combinado não permite alterar tarefas neste estado.");
        }
        return combinado;
    }

    private DadosValidados validar(Combinado combinado, TarefaInputDTO dto) {
        if (dto == null || dto.descricao() == null || dto.descricao().isBlank()) {
            throw new IllegalArgumentException("A descrição da tarefa é obrigatória.");
        }
        Set<Long> ids = new LinkedHashSet<>();
        if (dto.usuarioId() != null) ids.add(dto.usuarioId());
        if (dto.responsaveisIds() != null) ids.addAll(dto.responsaveisIds());
        if (ids.isEmpty() || ids.contains(null)) throw new IllegalArgumentException("Informe responsáveis válidos.");
        Set<Usuario> responsaveis = new LinkedHashSet<>();
        for (Long id : ids) {
            var usuario = usuarios.findById(id).orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado: " + id));
            boolean vinculado = combinado.getCriador().getId().equals(id)
                    || combinado.getUsuariosParticipantes().stream().anyMatch(u -> u.getId().equals(id))
                    || combinados.temVinculo(combinado.getId(), id);
            if (!vinculado) throw new IllegalArgumentException("O responsável deve estar vinculado ao combinado.");
            responsaveis.add(usuario);
        }
        return new DadosValidados(dto.descricao().strip(), responsaveis);
    }

    private record DadosValidados(String descricao, Set<Usuario> responsaveis) {}
}
