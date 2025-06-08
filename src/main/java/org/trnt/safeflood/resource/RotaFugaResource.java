package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.RotaFuga;
import org.trnt.safeflood.service.RotaFugaService;
import java.util.List;

@Path("/rotas-fuga")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RotaFugaResource {

    @Inject
    RotaFugaService service;

    @GET
    public List<RotaFuga> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public RotaFuga findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/area-risco/{areaRiscoId}")
    public List<RotaFuga> findByAreaRisco(@PathParam("areaRiscoId") Long areaRiscoId) {
        return service.findByAreaRisco(areaRiscoId);
    }

    @POST
    @Transactional
    public Response create(RotaFuga rotaFuga) {
        RotaFuga created = service.create(rotaFuga);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RotaFuga update(@PathParam("id") Long id, RotaFuga rotaFuga) {
        return service.update(id, rotaFuga);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 