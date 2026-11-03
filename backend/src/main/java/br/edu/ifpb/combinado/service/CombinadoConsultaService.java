package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.dto.CombinadoResumoDTO;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.security.UsuarioPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.domain.Specification;
import java.util.*;

@Service
public class CombinadoConsultaService {
    private final CombinadoRepository combinados;
    public CombinadoConsultaService(CombinadoRepository combinados) { this.combinados = combinados; }

    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public List<CombinadoResumoDTO> listar(boolean apenasCriados, String titulo, List<String> ordenacoes) {
        var principal = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usuarioId = principal.usuario().getId();
        boolean admin = principal.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        String busca = TextoConsulta.titulo(titulo);
        var ordem = OrdenacaoConsulta.combinados(ordenacoes);
        Specification<Combinado> filtro = (raiz, consulta, cb) -> {
            var autor = cb.equal(raiz.get("criador").get("id"), usuarioId);
            var acesso = cb.conjunction();
            if (apenasCriados) {
                acesso = cb.and(autor);
            } else if (!admin) {
                var participantes = consulta.subquery(Long.class);
                var p = participantes.from(Participante.class);
                participantes.select(cb.literal(1L)).where(
                        cb.equal(p.get("combinado").get("id"), raiz.get("id")),
                        cb.equal(p.get("usuario").get("id"), usuarioId));
                var convites = consulta.subquery(Long.class);
                var v = convites.from(Convite.class);
                convites.select(cb.literal(1L)).where(
                        cb.equal(v.get("combinado").get("id"), raiz.get("id")),
                        cb.equal(v.get("destinatario").get("id"), usuarioId));
                acesso = cb.or(autor, cb.and(cb.notEqual(raiz.get("status"), StatusCombinado.RASCUNHO),
                        cb.or(cb.exists(participantes), cb.exists(convites))));
            }
            return cb.and(acesso, cb.like(cb.lower(raiz.get("titulo")), busca, '!'));
        };
        return combinados.findAll(filtro, ordem).stream().map(CombinadoResumoDTO::de).toList();
    }
}
