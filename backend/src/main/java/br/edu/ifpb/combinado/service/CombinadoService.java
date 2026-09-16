package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CombinadoService {

    private final CombinadoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CombinadoService(CombinadoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Combinado> listarTodos() {
        return repository.findAll();
    }

    public Combinado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Combinado não encontrado com o id: " + id));
    }

    @Transactional
    public Combinado criar(Combinado combinado) {
        validarCamposObrigatorios(combinado.getTitulo(), combinado.getCategoria(), combinado.getCriadorNome(), combinado.getParticipanteNome());
        validarPrazo(combinado.getDataPrazo());
        return repository.save(combinado);
    }

    @Transactional
    public Combinado criarCombinado(CombinadoDTO dto) {
        // Task #31058: Se for salvar como RASCUNHO, exige apenas o título
        if (dto.status() == StatusCombinado.RASCUNHO) {
            if (!dto.isTituloValido()) {
                throw new IllegalArgumentException("Para salvar como rascunho, o título é obrigatório.");
            }
            Combinado rascunho = new Combinado();
            rascunho.setTitulo(dto.titulo().trim());
            rascunho.setDescricao(dto.descricao());
            rascunho.setCategoria(dto.categoria());
            rascunho.setDataPrazo(dto.dataPrazo());
            rascunho.setCriadorNome(dto.criadorNome() != null ? dto.criadorNome().trim() : "Criador");
            rascunho.setParticipanteNome(dto.participanteNome() != null ? dto.participanteNome().trim() : "");
            rascunho.setResponsabilidades(dto.responsabilidades());
            rascunho.setStatus(StatusCombinado.RASCUNHO);
            return repository.save(rascunho);
        }

        // Validação completa para combinados normais
        validarCamposObrigatorios(dto.titulo(), dto.categoria(), dto.criadorNome(), dto.participanteNome());
        validarPrazo(dto.dataPrazo());

        // Task #31049: Validar integridade dos IDs de participantes, se enviados
        if (dto.participantesIds() != null && !dto.participantesIds().isEmpty()) {
            for (Long idUsuario : dto.participantesIds()) {
                if (!usuarioRepository.existsById(idUsuario)) {
                    throw new IllegalArgumentException("Participante com ID " + idUsuario + " não encontrado.");
                }
            }
        }

        Combinado combinado = new Combinado();
        combinado.setTitulo(dto.titulo().trim());
        combinado.setDescricao(dto.descricao());
        combinado.setCategoria(dto.categoria());
        combinado.setDataPrazo(dto.dataPrazo());
        combinado.setCriadorNome(dto.criadorNome().trim());
        combinado.setParticipanteNome(dto.participanteNome().trim());
        combinado.setResponsabilidades(dto.responsabilidades());
        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);

        return repository.save(combinado);
    }

    @Transactional
    public Combinado aceitar(Long id, LocalDateTime respondidoEm) {
        Combinado combinado = buscarPorId(id);
        combinado.setStatus(StatusCombinado.ATIVO);
        combinado.setAceitoEm(respondidoEm != null ? respondidoEm : LocalDateTime.now());
        combinado.setRecusadoEm(null);
        return repository.save(combinado);
    }

    @Transactional
    public Combinado recusar(Long id, LocalDateTime respondidoEm) {
        Combinado combinado = buscarPorId(id);
        combinado.setStatus(StatusCombinado.CANCELADO);
        combinado.setRecusadoEm(respondidoEm != null ? respondidoEm : LocalDateTime.now());
        combinado.setAceitoEm(null);
        return repository.save(combinado);
    }

    private void validarCamposObrigatorios(String titulo, Object categoria, String criador, String participante) {
        if (titulo == null || titulo.trim().isBlank()) {
            throw new IllegalArgumentException("O título do combinado é obrigatório.");
        }
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria do combinado é obrigatória.");
        }
        if (criador == null || criador.trim().isBlank()) {
            throw new IllegalArgumentException("O nome do criador é obrigatório.");
        }
        if (participante == null || participante.trim().isBlank()) {
            throw new IllegalArgumentException("O nome do participante é obrigatório.");
        }
    }

    private void validarPrazo(LocalDate prazo) {
        if (prazo == null) {
            throw new IllegalArgumentException("O prazo do combinado é obrigatório.");
        }
        if (prazo.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O prazo do combinado deve ser uma data presente ou futura.");
        }
    }
}