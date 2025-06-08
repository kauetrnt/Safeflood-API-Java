package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.RotaFuga;
import java.util.List;

@ApplicationScoped
public class RotaFugaService {

    public List<RotaFuga> listAll() {
        return RotaFuga.listAll();
    }

    public RotaFuga findById(Long id) {
        return RotaFuga.findById(id);
    }

    public List<RotaFuga> findByAreaRisco(Long areaRiscoId) {
        return RotaFuga.find("areaRisco.id", areaRiscoId).list();
    }

    @Transactional
    public RotaFuga create(RotaFuga rotaFuga) {
        rotaFuga.persist();
        return rotaFuga;
    }

    @Transactional
    public RotaFuga update(Long id, RotaFuga rotaFuga) {
        RotaFuga entity = RotaFuga.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Rota de fuga com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        
        entity.origem = rotaFuga.origem;
        entity.destino = rotaFuga.destino;
        entity.areaRisco = rotaFuga.areaRisco;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        RotaFuga entity = RotaFuga.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Rota de fuga com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 