package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.*;
import br.edu.ifpb.combinado.service.JwtService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDate;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
    "spring.datasource.url=jdbc:h2:mem:gestao;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
    "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
abstract class GestaoFixture {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired CombinadoRepository combinados;
    @Autowired ConviteRepository convites;
    @Autowired TarefaRepository tarefas;
    @Autowired EntityManager em;
    @Autowired JwtService jwt;
    MockMvc mvc;
    Usuario criador, participante, externo;
    Combinado combinado;
    String bearer;
    @BeforeEach void prepararGestao() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        criador = usuario("Criador", "criador");
        participante = usuario("Ana", "ana");
        externo = usuario("Externo", "externo");
        combinado = new Combinado();
        combinado.setTitulo("Combinado de teste");
        combinado.setCategoria(CategoriaCombinado.OUTRO);
        combinado.setCriador(criador);
        combinado.setCriadorNome("Nome antigo");
        combinado.setParticipanteNome(participante.getNome());
        combinado.getParticipantes().add(participante);
        combinado.setDataPrazo(LocalDate.now().plusDays(10));
        combinado.setStatus(StatusCombinado.AGUARDANDO_CONFIRMACAO);
        combinados.saveAndFlush(combinado);
        bearer = token(criador);
    }
    Usuario usuario(String nome, String email) {
        return usuarios.saveAndFlush(new Usuario(nome, email + "-gestao@combinado.app", "segredo", PerfilUsuario.ROLE_USUARIO));
    }
    String token(Usuario usuario) { return "Bearer " + jwt.gerarToken(usuario); }
    Convite convite(Usuario destino, StatusConvite status) {
        Convite convite = new Convite();
        convite.setCombinado(combinado);
        convite.setRemetente(criador);
        convite.setDestinatario(destino);
        convite.setStatus(status);
        return convites.saveAndFlush(convite);
    }
}
