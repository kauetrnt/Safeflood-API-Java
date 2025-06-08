package org.trnt.safeflood.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.AreaEvento;
import org.trnt.safeflood.model.AreaEventoId;
import org.trnt.safeflood.service.AreaEventoService;
import java.util.List;

@Path("/area-eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AreaEventoResource {

    @Inject
    AreaEventoService service;

    @GET
    public List<AreaEvento> listAll() {
        return service.listAll();
    }

    @GET
    @Path("/{areaRiscoId}/{eventoId}")
    public AreaEvento findById(
            @PathParam("areaRiscoId") Long areaRiscoId,
            @PathParam("eventoId") Long eventoId) {
        AreaEventoId id = new AreaEventoId();
        id.idAreaRisco = areaRiscoId;
        id.idEvento = eventoId;
        return service.findById(id);
    }

    @GET
    @Path("/area-risco/{areaRiscoId}")
    public List<AreaEvento> findByAreaRisco(@PathParam("areaRiscoId") Long areaRiscoId) {
        return service.findByAreaRisco(areaRiscoId);
    }

    @GET
    @Path("/evento/{eventoId}")
    public List<AreaEvento> findByEvento(@PathParam("eventoId") Long eventoId) {
        return service.findByEvento(eventoId);
    }

    @POST
    @Transactional
    public Response create(AreaEvento areaEvento) {
        AreaEvento created = service.create(areaEvento);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @DELETE
    @Path("/{areaRiscoId}/{eventoId}")
    @Transactional
    public Response delete(
            @PathParam("areaRiscoId") Long areaRiscoId,
            @PathParam("eventoId") Long eventoId) {
        AreaEventoId id = new AreaEventoId();
        id.idAreaRisco = areaRiscoId;
        id.idEvento = eventoId;
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
} 