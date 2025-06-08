package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Evento;
import java.util.List;

@ApplicationScoped
public class EventoService {

    public List<Evento> listAll() {
        return Evento.listAll();
    }

    public Evento findById(Long id) {
        return Evento.findById(id);
    }

    @Transactional
    public Evento create(Evento evento) {
        evento.persist();
        return evento;
    }

    @Transactional
    public Evento update(Long id, Evento evento) {
        Evento entity = Evento.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Evento com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        
        entity.acaoTomada = evento.acaoTomada;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Evento entity = Evento.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Evento com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 