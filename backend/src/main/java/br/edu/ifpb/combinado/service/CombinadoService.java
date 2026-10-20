package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoDTO;
import br.edu.ifpb.combinado.dto.CombinadoResumoDTO;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private static final tools.jackson.databind.json.JsonMapper JSON =
            tools.jackson.databind.json.JsonMapper.builder().build();

    private final CombinadoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public CombinadoService(CombinadoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    public List<CombinadoResumoDTO> listarResumos() {
        return listarTodos().stream().map(CombinadoResumoDTO::de).toList();
    }

    @PreAuthorize("isAuthenticated()")
    public List<Combinado> listarTodos() {
        var principal = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.listarVinculados(principal.usuario().getId());
    }

    @PreAuthorize("@combinadoAcesso.podeAcessar(#id, authentication)")
    @Transactional(readOnly = true)
    public CombinadoResumoDTO buscarDetalhes(Long id) {
        return CombinadoResumoDTO.de(buscarPorId(id));
    }

    @PreAuthorize("@combinadoAcesso.podeAcessar(#id, authentication)")
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
    public Combinado criarCombinado(CombinadoDTO dto, Usuario criador) {
        var participantes = new java.util.HashSet<Usuario>();
        if (dto.participantesIds() != null) {
            for (Long participanteId : dto.participantesIds()) {
                if (participanteId == null) {
                    throw new IllegalArgumentException("ID de participante é obrigatório.");
                }
                participantes.add(usuarioRepository.findById(participanteId)
                        .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado: " + participanteId)));
            }
        }
        String nomeParticipantes = participantes.isEmpty() ? dto.participanteNome()
                : participantes.stream().map(Usuario::getNome).sorted().collect(java.util.stream.Collectors.joining(", "));
        var dados = new CombinadoDTO(null, dto.titulo(), dto.descricao(), dto.categoria(), dto.dataPrazo(),
                criador.getNome(), nomeParticipantes, dto.responsabilidades(), dto.status(), dto.participantesIds());
        Combinado combinado = criarCombinado(dados);
        combinado.setCriador(criador);
        combinado.setParticipantes(participantes);
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
    @PreAuthorize("@combinadoAcesso.podeAcessar(#id, authentication)")
    public Combinado aceitar(Long id, LocalDateTime respondidoEm) {
        Combinado combinado = buscarPorId(id);
        combinado.getStatus().validarTransicao(StatusCombinado.ATIVO);
        combinado.setStatus(StatusCombinado.ATIVO);
        combinado.setAceitoEm(respondidoEm != null ? respondidoEm : LocalDateTime.now());
        combinado.setRecusadoEm(null);
        return repository.save(combinado);
    }

    @Transactional
    @PreAuthorize("@combinadoAcesso.podeAcessar(#id, authentication)")
    public Combinado recusar(Long id, LocalDateTime respondidoEm) {
        Combinado combinado = buscarPorId(id);
        combinado.getStatus().validarTransicao(StatusCombinado.CANCELADO);
        combinado.setStatus(StatusCombinado.CANCELADO);
        combinado.setRecusadoEm(respondidoEm != null ? respondidoEm : LocalDateTime.now());
        combinado.setAceitoEm(null);
        return repository.save(combinado);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public List<CombinadoResumoDTO> listarMeusRascunhos() {
        var principal = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByCriadorIdAndStatusOrderByIdDesc(principal.usuario().getId(), StatusCombinado.RASCUNHO)
                .stream().map(CombinadoResumoDTO::de).toList();
    }

    @Transactional
    @PreAuthorize("@combinadoAcesso.eCriador(#id, authentication)")
    public CombinadoResumoDTO publicar(Long id, CombinadoDTO dados) {
        Combinado combinado = buscarPorId(id);
        if (combinado.getStatus() != StatusCombinado.RASCUNHO) {
            throw new IllegalArgumentException("Somente rascunhos podem ser publicados.");
        }
        combinado.getStatus().validarTransicao(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        String titulo = dados == null ? combinado.getTitulo() : dados.titulo();
        var categoria = dados == null ? combinado.getCategoria() : dados.categoria();
        var prazo = dados == null ? combinado.getDataPrazo() : dados.dataPrazo();
        var participantes = new java.util.HashSet<>(combinado.getParticipantes());
        if (dados != null && dados.participantesIds() != null) {
            participantes.clear();
            for (Long usuarioId : dados.participantesIds()) {
                if (usuarioId == null) throw new IllegalArgumentException("ID de participante é obrigatório.");
                participantes.add(usuarioRepository.findById(usuarioId)
                        .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado: " + usuarioId)));
            }
        }
        participantes.removeIf(u -> u.getId().equals(combinado.getCriador().getId()));
        if (participantes.isEmpty()) {
            throw new IllegalArgumentException("Informe ao menos um participante cadastrado diferente do criador.");
        }
        String nomes = participantes.stream().map(Usuario::getNome).sorted()
                .collect(java.util.stream.Collectors.joining(", "));
        validarCamposObrigatorios(titulo, categoria, combinado.getCriador().getNome(), nomes);
        validarPrazo(prazo);
        String responsabilidades = dados == null ? combinado.getResponsabilidades() : dados.responsabilidades();
        responsabilidades = removerMarcadorRascunho(responsabilidades);
        // Todas as validações precedem a alteração do registro gerenciado.
        combinado.setTitulo(titulo.trim());
        combinado.setCategoria(categoria);
        combinado.setDataPrazo(prazo);
        if (dados != null) combinado.setDescricao(dados.descricao());
        combinado.setResponsabilidades(responsabilidades);
        combinado.setCriadorNome(combinado.getCriador().getNome());
        combinado.setParticipanteNome(nomes);
        combinado.setParticipantes(participantes);
        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        return CombinadoResumoDTO.de(repository.save(combinado));
    }

    private String removerMarcadorRascunho(String responsabilidades) {
        if (responsabilidades == null || !responsabilidades.stripLeading().startsWith("{")) return responsabilidades;
        try {
            var json = JSON.readTree(responsabilidades);
            if (json instanceof tools.jackson.databind.node.ObjectNode objeto && objeto.has("rascunho")) {
                objeto.put("rascunho", false);
                return JSON.writeValueAsString(objeto);
            }
        } catch (tools.jackson.core.JacksonException ignored) {
            // Texto legado livre não é convertido nem descartado.
        }
        return responsabilidades;
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
