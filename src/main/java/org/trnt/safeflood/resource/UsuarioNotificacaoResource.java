package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.UsuarioNotificacao;
import org.trnt.safeflood.model.UsuarioNotificacaoId;
import org.trnt.safeflood.service.UsuarioNotificacaoService;
import java.util.List;

@Path("/usuario-notificacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioNotificacaoResource {

    @Inject
    UsuarioNotificacaoService service;

    @GET
    public List<UsuarioNotificacao> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{usuarioId}/{notificacaoId}")
    public UsuarioNotificacao findById(
            @PathParam("usuarioId") Long usuarioId,
            @PathParam("notificacaoId") Long notificacaoId) {
        UsuarioNotificacaoId id = new UsuarioNotificacaoId();
        id.idUsuario = usuarioId;
        id.idNotificacao = notificacaoId;
        return service.findById(id);
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public List<UsuarioNotificacao> findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return service.findByUsuario(usuarioId);
    }

    @GET
    @Path("/notificacao/{notificacaoId}")
    public List<UsuarioNotificacao> findByNotificacao(@PathParam("notificacaoId") Long notificacaoId) {
        return service.findByNotificacao(notificacaoId);
    }

    @POST
    @Transactional
    public Response create(UsuarioNotificacao usuarioNotificacao) {
        UsuarioNotificacao created = service.create(usuarioNotificacao);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("/{usuarioId}/{notificacaoId}")
    @Transactional
    public Response delete(
            @PathParam("usuarioId") Long usuarioId,
            @PathParam("notificacaoId") Long notificacaoId) {
        UsuarioNotificacaoId id = new UsuarioNotificacaoId();
        id.idUsuario = usuarioId;
        id.idNotificacao = notificacaoId;
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 