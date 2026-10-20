package br.edu.ifpb.combinado.service;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class SenhaMigrationTest {
    @Test
    void deveMigrarSenhaLegadaEPreservarHashExistente() throws Exception {
        String url = "jdbc:h2:mem:senhas;MODE=PostgreSQL;DB_CLOSE_DELAY=-1";
        var flyway = Flyway.configure().dataSource(url, "sa", "").target("1").load();
        flyway.migrate();
        var encoder = new BCryptPasswordEncoder(4);
        String existente = encoder.encode("senha456");
        try (var connection = DriverManager.getConnection(url, "sa", "");
             var insert = connection.prepareStatement("INSERT INTO tb_usuarios (nome,email,senha) VALUES (?,?,?)")) {
            insert.setString(1, "Legado");
            insert.setString(2, "legado@combinado.app");
            insert.setString(3, "senha123");
            insert.executeUpdate();
            insert.setString(2, "hash@combinado.app");
            insert.setString(3, existente);
            insert.executeUpdate();
        }
        Flyway.configure().dataSource(url, "sa", "").load().migrate();
        try (var connection = DriverManager.getConnection(url, "sa", "");
             var query = connection.createStatement();
             var result = query.executeQuery("SELECT email, senha FROM tb_usuarios ORDER BY id")) {
            assertTrue(result.next());
            assertTrue(encoder.matches("senha123", result.getString("senha")));
            assertNotEquals("senha123", result.getString("senha"));
            assertTrue(result.next());
            assertEquals(existente, result.getString("senha"));
        }
    }
}
