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

class CombinadoMultiplosCriteriosOrdenacaoTest extends GestaoFixture {

    @Test
    @DisplayName("Deve ordenar combinados aplicando múltiplos critérios de ordenação via parâmetro sort (#32287)")
    void deveOrdenarPorMultiplosCriterios() throws Exception {
        Combinado segundo = new Combinado();
        segundo.setTitulo("Segundo combinado com prazo menor");
        segundo.setCategoria(CategoriaCombinado.OUTRO);
        segundo.setCriador(criador);
        segundo.setCriadorNome(criador.getNome());
        segundo.setParticipanteNome(participante.getNome());
        segundo.adicionarParticipante(participante);
        segundo.setDataPrazo(LocalDate.now().plusDays(2));
        segundo.setStatus(StatusCombinado.ATIVO);
        combinados.saveAndFlush(segundo);

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("sort", "status,asc")
                        .param("sort", "prazo,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(combinado.getId().intValue()))
                .andExpect(jsonPath("$[1].id").value(segundo.getId().intValue()));

        mvc.perform(get("/api/combinados")
                        .header("Authorization", bearer)
                        .param("sort", "prazo,asc")
                        .param("sort", "status,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(segundo.getId().intValue()))
                .andExpect(jsonPath("$[1].id").value(combinado.getId().intValue()));
    }
}
