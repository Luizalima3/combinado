package br.edu.ifpb.combinado.service;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class PerfilMigrationTest {
    @Test
    void deveConverterPerfisAntigosEPreservarIdentidadeDoUsuario() throws Exception {
        String url = "jdbc:h2:mem:migracao-perfis;MODE=PostgreSQL;DB_CLOSE_DELAY=-1";
        Flyway.configure().dataSource(url, "sa", "").target("3").load().migrate();
        try (var connection = DriverManager.getConnection(url, "sa", ""); var sql = connection.createStatement()) {
            sql.executeUpdate("INSERT INTO tb_usuarios (nome,email,senha,perfil) VALUES ('Comum','comum@combinado.app','hash','USUARIO'), ('Admin','admin@combinado.app','hash','ADMIN')");
            sql.executeUpdate("ALTER TABLE tb_usuarios ADD CONSTRAINT perfil_legado CHECK (perfil IN ('USUARIO','ADMIN'))");
        }
        Flyway.configure().dataSource(url, "sa", "").load().migrate();
        try (var connection = DriverManager.getConnection(url, "sa", ""); var sql = connection.createStatement()) {
            try (var rows = sql.executeQuery("SELECT perfil FROM tb_usuarios ORDER BY id")) {
                assertTrue(rows.next());
                assertEquals("ROLE_USUARIO", rows.getString(1));
                assertTrue(rows.next());
                assertEquals("ROLE_ADMIN", rows.getString(1));
            }
            try (var defaults = sql.executeQuery("SELECT COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'TB_USUARIOS' AND COLUMN_NAME = 'PERFIL'")) {
                assertTrue(defaults.next());
                assertEquals("'ROLE_USUARIO'", defaults.getString(1));
            }
            sql.executeUpdate("INSERT INTO tb_usuarios (nome,email,senha) VALUES ('Padrao','padrao@combinado.app','hash')");
            try (var rows = sql.executeQuery("SELECT perfil FROM tb_usuarios WHERE email = 'padrao@combinado.app'")) {
                assertTrue(rows.next());
                assertEquals("ROLE_USUARIO", rows.getString(1));
            }
        }
    }
}
