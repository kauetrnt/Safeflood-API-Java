package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.AreaRisco;
import java.util.List;

@ApplicationScoped
public class AreaRiscoService {

    public List<AreaRisco> listAll() {
        return AreaRisco.listAll();
    }

    public AreaRisco findById(Long id) {
        return AreaRisco.findById(id);
    }

    @Transactional
    public AreaRisco create(AreaRisco areaRisco) {
        areaRisco.persist();
        return areaRisco;
    }

    @Transactional
    public AreaRisco update(Long id, AreaRisco areaRisco) {
        AreaRisco entity = AreaRisco.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Area de risco com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        
        entity.nomeArea = areaRisco.nomeArea;
        entity.alcance = areaRisco.alcance;
        entity.localAreaRisco = areaRisco.localAreaRisco;
        entity.nivelRisco = areaRisco.nivelRisco;
        entity.tipoRisco = areaRisco.tipoRisco;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        AreaRisco entity = AreaRisco.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Area de risco com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 