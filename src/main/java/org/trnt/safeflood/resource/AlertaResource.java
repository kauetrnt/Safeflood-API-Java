package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Alerta;
import org.trnt.safeflood.service.AlertaService;
import java.util.List;

@Path("/alertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertaResource {

    @Inject
    AlertaService service;

    @GET
    public List<Alerta> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Alerta findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public List<Alerta> findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return service.findByUsuario(usuarioId);
    }

    @POST
    @Transactional
    public Response create(Alerta alerta) {
        Alerta created = service.create(alerta);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Alerta update(@PathParam("id") Long id, Alerta alerta) {
        return service.update(id, alerta);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 