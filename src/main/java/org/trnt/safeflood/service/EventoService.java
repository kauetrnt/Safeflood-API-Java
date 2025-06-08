package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.model.Evento;
import java.util.List;

@ApplicationScoped
public class EventoService {

    public List<Evento> listAll() {
        return Evento.listAll();
    }

    public Evento findById(Long id) {
        Evento evento = Evento.findById(id);
        if (evento == null) {
            throw new ResourceNotFoundException("Evento com id " + id + " não encontrado");
        }
        return evento;
    }

    @Transactional
    public Evento create(Evento evento) {
        if (evento.acaoTomada == null || evento.acaoTomada.trim().isEmpty()) {
            throw new BusinessException("Ação tomada é obrigatória");
        }
        evento.persist();
        return evento;
    }

    @Transactional
    public Evento update(Long id, Evento evento) {
        Evento entity = Evento.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Evento com id " + id + " não encontrado");
        }
        
        if (evento.acaoTomada == null || evento.acaoTomada.trim().isEmpty()) {
            throw new BusinessException("Ação tomada é obrigatória");
        }
        
        entity.acaoTomada = evento.acaoTomada;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Evento entity = Evento.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Evento com id " + id + " não encontrado");
        }
        entity.delete();
    }
} 