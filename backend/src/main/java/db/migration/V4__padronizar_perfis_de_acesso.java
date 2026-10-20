package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.util.ArrayList;
import java.util.Locale;

public class V4__padronizar_perfis_de_acesso extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        // Bancos criados pelo Hibernate podem restringir o enum aos nomes antigos.
        var constraints = new ArrayList<String>();
        try (var query = context.getConnection().prepareStatement("""
                SELECT tc.constraint_name, cc.check_clause
                FROM information_schema.table_constraints tc
                JOIN information_schema.check_constraints cc
                  ON cc.constraint_name = tc.constraint_name AND cc.constraint_schema = tc.constraint_schema
                WHERE LOWER(tc.table_name) = 'tb_usuarios'
                  AND tc.constraint_schema = CURRENT_SCHEMA() AND tc.constraint_type = 'CHECK'
                """); var rows = query.executeQuery()) {
            while (rows.next()) {
                String clause = rows.getString(2);
                if (clause.toLowerCase(Locale.ROOT).contains("perfil")
                        && (clause.contains("'USUARIO'") || clause.contains("'ADMIN'"))) {
                    constraints.add(rows.getString(1));
                }
            }
        }
        try (var sql = context.getConnection().createStatement()) {
            for (String constraint : constraints) {
                sql.executeUpdate("ALTER TABLE tb_usuarios DROP CONSTRAINT \"" + constraint.replace("\"", "\"\"") + "\"");
            }
            sql.executeUpdate("UPDATE tb_usuarios SET perfil = 'ROLE_USUARIO' WHERE perfil = 'USUARIO'");
            sql.executeUpdate("UPDATE tb_usuarios SET perfil = 'ROLE_ADMIN' WHERE perfil = 'ADMIN'");
            sql.executeUpdate("ALTER TABLE tb_usuarios ALTER COLUMN perfil SET DEFAULT 'ROLE_USUARIO'");
        }
    }
}
