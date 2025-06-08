package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Usuario;
import org.trnt.safeflood.service.UsuarioService;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @GET
    public List<Usuario> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public Usuario findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/cpf/{cpf}")
    public Usuario findByCpf(@PathParam("cpf") String cpf) {
        return service.findByCpf(cpf);
    }

    @GET
    @Path("/email/{email}")
    public Usuario findByEmail(@PathParam("email") String email) {
        return service.findByEmail(email);
    }

    @POST
    @Transactional
    public Response create(Usuario usuario) {
        Usuario created = service.create(usuario);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario update(@PathParam("id") Long id, Usuario usuario) {
        return service.update(id, usuario);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 