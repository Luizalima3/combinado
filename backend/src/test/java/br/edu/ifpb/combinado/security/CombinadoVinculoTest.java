package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.CombinadoApplication;
import br.edu.ifpb.combinado.model.Combinado;
import br.edu.ifpb.combinado.model.Convite;
import br.edu.ifpb.combinado.model.Usuario;
import br.edu.ifpb.combinado.model.enums.*;
import br.edu.ifpb.combinado.repository.CombinadoRepository;
import br.edu.ifpb.combinado.repository.CombinadoHistoricoRepository;
import br.edu.ifpb.combinado.repository.ConviteRepository;
import br.edu.ifpb.combinado.repository.UsuarioRepository;
import br.edu.ifpb.combinado.service.CombinadoService;
import br.edu.ifpb.combinado.service.JwtService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CombinadoApplication.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:vinculos;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa",
        "spring.datasource.password=", "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=update", "spring.jpa.show-sql=false"
})
@Transactional
class CombinadoVinculoTest {
    @Autowired WebApplicationContext context;
    @Autowired UsuarioRepository usuarios;
    @Autowired CombinadoRepository combinados;
    @Autowired CombinadoHistoricoRepository historicos;
    @Autowired ConviteRepository convites;
    @Autowired CombinadoService service;
    @Autowired JwtService jwt;
    private MockMvc mvc;
    private Usuario criador, participante, intruso, admin;

    private Usuario usuario(String nome, String email, PerfilUsuario perfil) {
        return usuarios.saveAndFlush(new Usuario(nome, email, "hash-teste", perfil));
    }

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        criador = usuario("Nome igual", "criador@combinado.app", PerfilUsuario.ROLE_USUARIO);
        participante = usuario("Participante", "participante@combinado.app", PerfilUsuario.ROLE_USUARIO);
        intruso = usuario("Nome igual", "intruso@combinado.app", PerfilUsuario.ROLE_USUARIO);
        admin = usuario("Administrador", "admin-vinculo@combinado.app", PerfilUsuario.ROLE_ADMIN);
    }

    private String bearer(Usuario usuario) { return "Bearer " + jwt.gerarToken(usuario); }

    private Long criar(Usuario autor, String participantesIds) throws Exception {
        String body = """
                {"titulo":"Acordo privado","categoria":"OUTRO","dataPrazo":"%s",
                 "criadorNome":"Nome forjado","criadorId":%d,"participanteNome":"Participante",
                 "participantesIds":%s,"status":"AGUARDANDO_CONFIRMACAO"}
                """.formatted(LocalDate.now().plusDays(7), intruso.getId(), participantesIds);
        var resposta = mvc.perform(post("/api/combinados").header("Authorization", bearer(autor))
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        return ((Number) JsonPath.read(resposta, "$.id")).longValue();
    }

    @Test
    void deveAssociarCriadorAoUsuarioAutenticadoIgnorandoIdentidadeEnviada() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");
        Combinado salvo = combinados.findById(id).orElseThrow();
        assertEquals(criador.getId(), salvo.getCriador().getId());
        assertEquals(criador.getNome(), salvo.getCriadorNome());
        assertEquals(Set.of(participante), salvo.getUsuariosParticipantes());
        assertEquals(1, salvo.getParticipantes().size());
    }

    @Test
    void deveRegistrarCriacaoDoCombinadoComAutorEDataNoHistorico() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");

        var eventos = historicos.findByCombinadoIdOrderByOcorridoEmAscIdAsc(id);
        assertEquals(1, eventos.size());
        assertEquals(AcaoCombinadoHistorico.CRIACAO, eventos.getFirst().getAcao());
        assertEquals(criador.getId(), eventos.getFirst().getAutor().getId());
        assertEquals(criador.getNome(), eventos.getFirst().getAutorNome());
        assertEquals("Combinado criado.", eventos.getFirst().getDescricao());
        assertNotNull(eventos.getFirst().getOcorridoEm());
    }

    @Test
    void devePermitirDetalhesAoCriadorEAoParticipante() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");
        for (Usuario usuario : new Usuario[]{criador, participante}) {
            mvc.perform(get("/api/combinados/" + id).header("Authorization", bearer(usuario)))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Acordo privado"))
                    .andExpect(jsonPath("$.criador").doesNotExist()).andExpect(jsonPath("$.participantes").doesNotExist());
        }
    }

    @Test
    void deveNegarUsuarioSemVinculoMesmoComNomeIgualOuPerfilAdmin() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");
        for (Usuario usuario : new Usuario[]{intruso, admin}) {
            mvc.perform(get("/api/combinados/" + id).header("Authorization", bearer(usuario)))
                    .andExpect(status().isForbidden()).andExpect(jsonPath("$.titulo").doesNotExist());
        }
        mvc.perform(get("/api/combinados/" + id)).andExpect(status().isUnauthorized());
    }

    @Test
    void listagemDeveRetornarSomenteCombinadosVinculados() throws Exception {
        Long proprio = criar(criador, "[" + participante.getId() + "]");
        Long alheio = criar(intruso, "[]");
        mvc.perform(get("/api/combinados").header("Authorization", bearer(criador)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].id", contains(proprio.intValue())))
                .andExpect(jsonPath("$[*].id", not(hasItem(alheio.intValue()))));
        mvc.perform(get("/api/combinados").header("Authorization", bearer(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].id", contains(proprio.intValue())));
    }

    @Test
    void deveBloquearAceiteRecusaEConvitesDeUsuarioSemVinculo() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");
        for (String acao : new String[]{"aceitar", "recusar"}) {
            mvc.perform(patch("/api/combinados/" + id + "/" + acao).header("Authorization", bearer(intruso)))
                    .andExpect(status().isForbidden());
        }
        mvc.perform(post("/api/combinados/" + id + "/convites").header("Authorization", bearer(intruso))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"remetenteId\":" + criador.getId() + ",\"destinatarioId\":" + intruso.getId() + "}"))
                .andExpect(status().isForbidden());
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO, combinados.findById(id).orElseThrow().getStatus());
    }

    @Test
    void somenteCriadorPodeCancelarCombinado() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");

        mvc.perform(patch("/api/combinados/{id}/recusar", id).header("Authorization", bearer(participante)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(containsString("Somente o criador")));
        mvc.perform(patch("/api/combinados/{id}/cancelar", id).header("Authorization", bearer(participante)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Somente o criador do combinado pode cancelá-lo."));
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(id).orElseThrow().getStatus());

        mvc.perform(patch("/api/combinados/{id}/cancelar", id).header("Authorization", bearer(criador)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("CANCELADO"));
        assertEquals(StatusCombinado.CANCELADO, combinados.findById(id).orElseThrow().getStatus());
    }

    @Test
    void somenteCriadorPodeConcluirCombinadoAtivoComTodosAceites() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");
        Combinado combinado = combinados.findById(id).orElseThrow();
        combinado.setStatus(StatusCombinado.ATIVO);
        combinados.saveAndFlush(combinado);

        // US62: com convite ainda pendente/recusado, a conclusão deve ser bloqueada (422)
        mvc.perform(patch("/api/combinados/{id}/concluir", id).header("Authorization", bearer(criador)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value(containsString("ainda não confirmaram")));
        assertEquals(StatusCombinado.ATIVO, combinados.findById(id).orElseThrow().getStatus());

        mvc.perform(patch("/api/combinados/{id}/concluir", id).header("Authorization", bearer(participante)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Somente o criador do combinado pode concluí-lo."));

        // Todos aceitaram: conclusão permitida ao criador
        Convite aceite = new Convite();
        aceite.setCombinado(combinados.findById(id).orElseThrow());
        aceite.setRemetente(criador);
        aceite.setDestinatario(participante);
        aceite.setStatus(StatusConvite.ACEITO);
        convites.saveAndFlush(aceite);

        mvc.perform(patch("/api/combinados/{id}/concluir", id).header("Authorization", bearer(criador)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.status").value("CONCLUIDO"));
        assertEquals(StatusCombinado.CONCLUIDO, combinados.findById(id).orElseThrow().getStatus());
    }

    @Test
    void naoDeveConcluirCombinadoQueAindaAguardaConfirmacao() throws Exception {
        Long id = criar(criador, "[" + participante.getId() + "]");

        mvc.perform(patch("/api/combinados/{id}/concluir", id).header("Authorization", bearer(criador)))
                .andExpect(status().isBadRequest());
        assertEquals(StatusCombinado.AGUARDANDO_CONFIRMACAO,
                combinados.findById(id).orElseThrow().getStatus());
    }

    @Test
    void conviteDeveConcederAcessoAoDestinatarioSemExporSenhas() throws Exception {
        Long id = criar(criador, "[]");
        mvc.perform(post("/api/combinados/" + id + "/convites").header("Authorization", bearer(criador))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"remetenteId\":" + intruso.getId() + ",\"destinatarioId\":" + participante.getId() + "}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.remetente.id").value(criador.getId().intValue()))
                .andExpect(jsonPath("$.remetente.senha").doesNotExist())
                .andExpect(jsonPath("$.destinatario.senha").doesNotExist());
        mvc.perform(get("/api/combinados/" + id).header("Authorization", bearer(participante))).andExpect(status().isOk());
    }

    @Test
    void legadoSomenteComNomesNaoDeveConcederPermissao() throws Exception {
        var legado = new Combinado();
        legado.setTitulo("Legado");
        legado.setCriadorNome(criador.getNome());
        legado.setParticipanteNome(participante.getNome());
        legado.setCategoria(CategoriaCombinado.OUTRO);
        legado.setDataPrazo(LocalDate.now().plusDays(7));
        combinados.saveAndFlush(legado);
        mvc.perform(get("/api/combinados/" + legado.getId()).header("Authorization", bearer(criador)))
                .andExpect(status().isForbidden());
    }

    @Test
    void conviteExistenteDevePermitirConsultaMesmoEmLegadoSemCriadorIdentificado() throws Exception {
        var legado = new Combinado();
        legado.setTitulo("Legado com convite");
        legado.setCriadorNome(criador.getNome());
        legado.setParticipanteNome(participante.getNome());
        legado.setCategoria(CategoriaCombinado.OUTRO);
        legado.setDataPrazo(LocalDate.now().plusDays(7));
        combinados.saveAndFlush(legado);
        var convite = new Convite();
        convite.setCombinado(legado);
        convite.setRemetente(criador);
        convite.setDestinatario(participante);
        convites.saveAndFlush(convite);
        mvc.perform(get("/api/combinados/" + legado.getId()).header("Authorization", bearer(participante)))
                .andExpect(status().isOk());
        mvc.perform(get("/api/combinados").header("Authorization", bearer(participante)))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].id", contains(legado.getId().intValue())));
    }

    @Test
    void deveRejeitarIdDeParticipanteInexistente() throws Exception {
        mvc.perform(post("/api/combinados").header("Authorization", bearer(criador))
                        .contentType(MediaType.APPLICATION_JSON).content("{\"titulo\":\"Teste\",\"participantesIds\":[999999]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void protecaoDeveValerTambemAoChamarServiceDiretamente() throws Exception {
        Long id = criar(criador, "[]");
        var principal = new UsuarioPrincipal(intruso);
        SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(
                principal, null, principal.getAuthorities()));
        try {
            assertThrows(AccessDeniedException.class, () -> service.buscarPorId(id));
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
