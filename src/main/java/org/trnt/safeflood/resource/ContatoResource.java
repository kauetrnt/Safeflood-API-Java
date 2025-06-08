package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Contato;
import org.trnt.safeflood.service.ContatoService;
import java.util.List;

@Path("/contatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContatoResource {

    @Inject
    ContatoService service;

    @GET
    public List<Contato> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Contato findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/email/{email}")
    public List<Contato> findByEmail(@PathParam("email") String email) {
        return service.findByEmail(email);
    }

    @POST
    @Transactional
    public Response create(Contato contato) {
        Contato created = service.create(contato);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Contato update(@PathParam("id") Long id, Contato contato) {
        return service.update(id, contato);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 