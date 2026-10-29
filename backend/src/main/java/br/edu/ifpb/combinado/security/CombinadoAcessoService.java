package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.enums.StatusConvite;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("combinadoAcesso")
public class CombinadoAcessoService {
    private final CombinadoRepository combinadoRepository;
    private final ConviteRepository conviteRepository;

    public CombinadoAcessoService(CombinadoRepository combinadoRepository, ConviteRepository conviteRepository) {
        this.combinadoRepository = combinadoRepository;
        this.conviteRepository = conviteRepository;
    }

    public boolean podeAcessar(Long id, Authentication authentication) {
        return id != null && authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UsuarioPrincipal principal
                && combinadoRepository.temVinculo(id, principal.usuario().getId());
    }

    public boolean eCriador(Long id, Authentication authentication) {
        return id != null && authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UsuarioPrincipal principal
                && combinadoRepository.existsByIdAndCriadorId(id, principal.usuario().getId());
    }

    /**
     * US71 / Task #31697 — Regra de autorização da conclusão:
     * aceita qualquer PARTICIPANTE (criador ou participante com convite ACEITO).
     *
     * BDD:
     * DADO que o combinado está ATIVO
n     * QUANDO um participante (não necessariamente o criador) tenta concluí-lo
     * ENTÃO o sistema deve aceitar a conclusão
     * DADO que o usuário não é participante do combinado
     * QUANDO ele tenta concluí-lo
     * ENTÃO o sistema deve recusar a conclusão (403)
     */
    public boolean podeConcluir(Long id, Authentication authentication) {
        if (id == null || authentication == null || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof UsuarioPrincipal principal)) {
            return false;
        }
        Long usuarioId = principal.usuario().getId();
        // Criador sempre pode concluir.
        if (combinadoRepository.existsByIdAndCriadorId(id, usuarioId)) {
            return true;
        }
        // US71: qualquer participante com convite ACEITO também pode concluir.
        return conviteRepository.existsByCombinadoIdAndDestinatarioIdAndStatus(
                id, usuarioId, StatusConvite.ACEITO);
    }
}
