package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.AreaRisco;
import org.trnt.safeflood.service.AreaRiscoService;
import java.util.List;

@Path("/areas-risco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AreaRiscoResource {

    @Inject
    AreaRiscoService service;

    @GET
    public List<AreaRisco> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public AreaRisco findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response create(AreaRisco areaRisco) {
        AreaRisco created = service.create(areaRisco);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public AreaRisco update(@PathParam("id") Long id, AreaRisco areaRisco) {
        return service.update(id, areaRisco);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 