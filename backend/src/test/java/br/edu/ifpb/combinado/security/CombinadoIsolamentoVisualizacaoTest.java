package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.enums.CategoriaCombinado;
import br.edu.ifpb.combinado.model.enums.StatusCombinado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CombinadoIsolamentoVisualizacaoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve garantir isolamento completo de visualização entre combinados de outros usuários (#31407)")
    void deveGarantirIsolamentoDeVisualizacaoEntreCombinadosDeOutrosUsuarios() throws Exception {
        Combinado combinadoExterno = new Combinado();
        combinadoExterno.setTitulo("Combinado Exclusivo Externo");
        combinadoExterno.setCategoria(CategoriaCombinado.OUTRO);
        combinadoExterno.setCriador(externo);
        combinadoExterno.setCriadorNome(externo.getNome());
        combinadoExterno.setParticipanteNome("Outro");
        combinadoExterno.setDataPrazo(LocalDate.now().plusDays(10));
        combinadoExterno.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        combinados.saveAndFlush(combinadoExterno);

        mvc.perform(get("/api/combinados").header("Authorization", bearer))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(combinado.getId().intValue()));

        mvc.perform(get("/api/combinados/{id}", combinadoExterno.getId()).header("Authorization", bearer))
                .andExpect(status().isForbidden());
    }
}
