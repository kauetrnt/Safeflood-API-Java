package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.AreaEvento;
import org.trnt.safeflood.model.AreaEventoId;
import java.util.List;

@ApplicationScoped
public class AreaEventoService {

    public List<AreaEvento> listAll() {
        return AreaEvento.listAll();
    }

    public AreaEvento findById(AreaEventoId id) {
        return AreaEvento.findById(id);
    }

    public List<AreaEvento> findByAreaRisco(Long areaRiscoId) {
        return AreaEvento.find("areaRisco.id", areaRiscoId).list();
    }

    public List<AreaEvento> findByEvento(Long eventoId) {
        return AreaEvento.find("evento.id", eventoId).list();
    }

    @Transactional
    public AreaEvento create(AreaEvento areaEvento) {
        areaEvento.persist();
        return areaEvento;
    }

    @Transactional
    public void delete(AreaEventoId id) {
        AreaEvento entity = AreaEvento.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Relação Área de Risco-Evento não encontrada.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 