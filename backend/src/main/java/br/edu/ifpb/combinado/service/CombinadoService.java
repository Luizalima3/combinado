package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CombinadoService {

    private final CombinadoRepository repository;

    public CombinadoService(CombinadoRepository repository) {
        this.repository = repository;
    }

    public Combinado criar(Combinado combinado) {
        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        return repository.save(combinado);
    }

    public List<Combinado> listarTodos() {
        return repository.findAll();
    }

    public Combinado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Combinado não encontrado: " + id));
    }

    public Combinado aceitar(Long id) {
        Combinado combinado = buscarPorId(id);
        combinado.setStatus(StatusCombinado.ATIVO);
        return repository.save(combinado);
    }

    public Combinado recusar(Long id) {
        Combinado combinado = buscarPorId(id);
        combinado.setStatus(StatusCombinado.CANCELADO);
        return repository.save(combinado);
    }
}