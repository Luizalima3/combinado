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

class CombinadoListagemRecebidosTest extends GestaoFixture {

    @Test
    @DisplayName("Deve listar combinados recebidos pelo participante autenticado e ocultar rascunhos de terceiros")
    void deveListarCombinadosRecebidosPeloParticipante() throws Exception {
        Combinado rascunhoDoCriador = new Combinado();
        rascunhoDoCriador.setTitulo("Rascunho privado do criador");
        rascunhoDoCriador.setCategoria(CategoriaCombinado.OUTRO);
        rascunhoDoCriador.setCriador(criador);
        rascunhoDoCriador.setCriadorNome(criador.getNome());
        rascunhoDoCriador.setParticipanteNome(participante.getNome());
        rascunhoDoCriador.adicionarParticipante(participante);
        rascunhoDoCriador.setDataPrazo(LocalDate.now().plusDays(15));
        rascunhoDoCriador.setStatus(StatusCombinado.RASCUNHO);
        combinados.saveAndFlush(rascunhoDoCriador);

        mvc.perform(get("/api/combinados").header("Authorization", token(participante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(combinado.getId().intValue()))
                .andExpect(jsonPath("$[0].titulo").value(combinado.getTitulo()))
                .andExpect(jsonPath("$[0].criadorNome").value(criador.getNome()));
    }
}
