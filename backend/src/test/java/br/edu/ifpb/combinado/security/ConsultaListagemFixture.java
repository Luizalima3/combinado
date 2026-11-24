package br.edu.ifpb.combinado.security;

import br.edu.ifpb.combinado.model.*;
import br.edu.ifpb.combinado.model.enums.*;
import java.time.*;

abstract class ConsultaListagemFixture extends GestaoFixture {
    Combinado criarLista(Usuario autor, String titulo, StatusCombinado estado, LocalDateTime criado, LocalDate prazo) {
        Combinado c = new Combinado();
        c.setCriador(autor);
        c.setCriadorNome(autor.getNome());
        c.setParticipanteNome(participante.getNome());
        c.setTitulo(titulo);
        c.setCategoria(CategoriaCombinado.OUTRO);
        c.setStatus(estado);
        c.setDataPrazo(prazo);
        combinados.saveAndFlush(c);
        c.setCriadoEm(criado);
        return combinados.saveAndFlush(c);
    }
    Convite convidar(Combinado c, Usuario destinatario, StatusConvite estado, LocalDateTime dataEnvio) {
        Convite v = new Convite();
        v.setCombinado(c);
        v.setRemetente(c.getCriador());
        v.setDestinatario(destinatario);
        v.setStatus(estado);
        v.setDataEnvio(dataEnvio);
        return convites.saveAndFlush(v);
    }
}
