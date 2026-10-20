package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class V2__criptografar_senhas extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        var encoder = new BCryptPasswordEncoder();
        try (var consulta = context.getConnection().prepareStatement("SELECT id, senha FROM tb_usuarios");
             var usuarios = consulta.executeQuery();
             var atualizacao = context.getConnection().prepareStatement("UPDATE tb_usuarios SET senha = ? WHERE id = ?")) {
            while (usuarios.next()) {
                String senha = usuarios.getString("senha");
                if (senha.matches("\\$2[aby]\\$\\d{2}\\$[./A-Za-z0-9]{53}")) {
                    continue;
                }
                atualizacao.setString(1, encoder.encode(senha));
                atualizacao.setLong(2, usuarios.getLong("id"));
                atualizacao.executeUpdate();
            }
        }
    }
}
