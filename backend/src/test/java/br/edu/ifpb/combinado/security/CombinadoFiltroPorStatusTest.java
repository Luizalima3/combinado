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

class CombinadoFiltroPorStatusTest extends GestaoFixture {

    @Test
    @DisplayName("Deve filtrar combinados por cada status do ciclo de vida (#32314)")
    void deveFiltrarCombinadosPorCadaStatus() throws Exception {
        for (StatusCombinado st : StatusCombinado.values()) {
            if (st == StatusCombinado.AGUARDANDO_CONFIRMACAO) continue;
            Combinado novo = new Combinado();
            novo.setTitulo("Combinado " + st.name());
            novo.setCategoria(CategoriaCombinado.OUTRO);
            novo.setCriador(criador);
            novo.setCriadorNome(criador.getNome());
            novo.setParticipanteNome(participante.getNome());
            novo.adicionarParticipante(participante);
            novo.setDataPrazo(LocalDate.now().plusDays(10));
            novo.setStatus(st);
            combinados.saveAndFlush(novo);
        }

        for (StatusCombinado st : StatusCombinado.values()) {
            mvc.perform(get("/api/combinados")
                            .header("Authorization", bearer)
                            .param("status", st.name()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].status").value(st.name()));
        }
    }
}
