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

class CombinadoFiltroPorCategoriaTest extends GestaoFixture {

    @Test
    @DisplayName("Deve filtrar combinados por cada categoria cadastrada (#32311)")
    void deveFiltrarCombinadosPorCadaCategoria() throws Exception {
        for (CategoriaCombinado cat : CategoriaCombinado.values()) {
            if (cat == CategoriaCombinado.OUTRO) continue;
            Combinado novo = new Combinado();
            novo.setTitulo("Combinado " + cat.name());
            novo.setCategoria(cat);
            novo.setCriador(criador);
            novo.setCriadorNome(criador.getNome());
            novo.setParticipanteNome(participante.getNome());
            novo.adicionarParticipante(participante);
            novo.setDataPrazo(LocalDate.now().plusDays(10));
            novo.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
            combinados.saveAndFlush(novo);
        }

        for (CategoriaCombinado cat : CategoriaCombinado.values()) {
            mvc.perform(get("/api/combinados")
                            .header("Authorization", bearer)
                            .param("categoria", cat.name()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].categoria").value(cat.name()));
        }
    }
}
