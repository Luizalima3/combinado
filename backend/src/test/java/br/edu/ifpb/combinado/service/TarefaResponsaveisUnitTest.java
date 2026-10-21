package br.edu.ifpb.combinado.service;

import br.edu.ifpb.combinado.model.Tarefa;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.PerfilUsuario;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarefaResponsaveisUnitTest {
    @Test
    void deveManterTodosOsResponsaveisEImpedirIdsDuplicados() {
        var tarefa = new Tarefa();
        var primeiro = usuario(1L, "ana@combinado.app");
        var segundo = usuario(2L, "bruno@combinado.app");
        var mesmoPrimeiro = usuario(1L, "ana@combinado.app");

        tarefa.getResponsaveis().add(primeiro);
        tarefa.getResponsaveis().add(segundo);
        tarefa.getResponsaveis().add(mesmoPrimeiro);

        assertEquals(Set.of(1L, 2L), tarefa.getResponsaveis().stream()
                .map(Usuario::getId)
                .collect(java.util.stream.Collectors.toSet()));
    }

    private Usuario usuario(Long id, String email) {
        var usuario = new Usuario(email, email, "senha-hash", PerfilUsuario.ROLE_USUARIO);
        usuario.setId(id);
        return usuario;
    }
}