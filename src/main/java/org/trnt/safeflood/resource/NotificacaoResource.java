package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Notificacao;
import org.trnt.safeflood.service.NotificacaoService;
import java.util.List;

@Path("/notificacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificacaoResource {

    @Inject
    NotificacaoService service;

    @GET
    public List<Notificacao> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Notificacao findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/tipo/{tipo}")
    public List<Notificacao> findByTipo(@PathParam("tipo") String tipo) {
        return service.findByTipo(tipo);
    }

    @POST
    @Transactional
    public Response create(Notificacao notificacao) {
        Notificacao created = service.create(notificacao);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Notificacao update(@PathParam("id") Long id, Notificacao notificacao) {
        return service.update(id, notificacao);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 