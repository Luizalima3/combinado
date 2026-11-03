package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoHistoricoDTO;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.AcaoCombinadoHistorico;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.List;

@Service
public class HistoricoService {
    private final CombinadoHistoricoRepository historicos;
    public HistoricoService(CombinadoHistoricoRepository historicos) { this.historicos = historicos; }

    @Transactional(propagation = Propagation.MANDATORY)
    public void registrar(Combinado combinado, Usuario autor, String descricao, AcaoCombinadoHistorico acao) {
        registrar(combinado, autor, descricao, null, acao);
    }

    /** Uso interno: o autor é fornecido pelos serviços a partir da identidade autenticada. */
    @Transactional(propagation = Propagation.MANDATORY)
    public void registrar(Combinado combinado, Usuario autor, String descricao, String motivo, AcaoCombinadoHistorico acao) {
        if (combinado == null || combinado.getId() == null || autor == null || autor.getId() == null || acao == null) {
            throw new IllegalArgumentException("Histórico exige combinado, autor e ação válidos.");
        }
        if (descricao == null || descricao.isBlank() || descricao.length() > 500) {
            throw new IllegalArgumentException("O evento exige descrição de até 500 caracteres.");
        }
        if (motivo != null && motivo.length() > 500) throw new IllegalArgumentException("O motivo deve ter até 500 caracteres.");
        String motivoNormalizado = motivo == null || motivo.isBlank() ? null : motivo.strip();
        historicos.save(CombinadoHistorico.de(combinado, autor, descricao.strip(), motivoNormalizado, acao));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("@combinadoAcesso.podeConsultar(#id, authentication)")
    public List<CombinadoHistoricoDTO> listar(Long id) {
        return historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(id).stream().map(CombinadoHistoricoDTO::de).toList();
    }
    @Transactional(readOnly = true)
    @PreAuthorize("@combinadoAcesso.podeConsultar(#id, authentication)")
    public List<CombinadoHistoricoDTO> listar(Long id, java.time.LocalDate dataInicio, java.time.LocalDate dataFim,
            Long autorId, AcaoCombinadoHistorico tipo) {
        if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("dataInicio não pode ser posterior a dataFim.");
        }
        var inicio = dataInicio == null ? null : dataInicio.atStartOfDay().toInstant(java.time.ZoneOffset.UTC);
        var fim = dataFim == null ? null : dataFim.atTime(java.time.LocalTime.MAX).toInstant(java.time.ZoneOffset.UTC);
        return historicos.buscarPorCombinadoPeriodoAutorETipo(id, autorId, tipo, inicio, fim)
                .stream().map(CombinadoHistoricoDTO::de).toList();
    }
}
