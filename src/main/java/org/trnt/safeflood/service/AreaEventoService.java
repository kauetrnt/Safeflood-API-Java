package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.model.AreaEvento;
import org.trnt.safeflood.model.AreaEventoId;
import java.util.List;

@ApplicationScoped
public class AreaEventoService {

    public List<AreaEvento> listAll() {
        return AreaEvento.listAll();
    }

    public AreaEvento findById(AreaEventoId id) {
        AreaEvento areaEvento = AreaEvento.findById(id);
        if (areaEvento == null) {
            throw new ResourceNotFoundException("Relação Área de Risco-Evento não encontrada");
        }
        return areaEvento;
    }

    public List<AreaEvento> findByAreaRisco(Long areaRiscoId) {
        return AreaEvento.find("areaRisco.id", areaRiscoId).list();
    }

    public List<AreaEvento> findByEvento(Long eventoId) {
        return AreaEvento.find("evento.id", eventoId).list();
    }

    @Transactional
    public AreaEvento create(AreaEvento areaEvento) {
        if (areaEvento.areaRisco == null) {
            throw new BusinessException("Área de risco é obrigatória para criar uma relação");
        }
        if (areaEvento.evento == null) {
            throw new BusinessException("Evento é obrigatório para criar uma relação");
        }
        
        // Verifica se já existe uma relação entre a área de risco e o evento
        AreaEventoId id = new AreaEventoId();
        id.idAreaRisco = areaEvento.areaRisco.id;
        id.idEvento = areaEvento.evento.id;
        
        if (AreaEvento.findById(id) != null) {
            throw new BusinessException("Relação entre área de risco e evento já existe");
        }
        
        areaEvento.persist();
        return areaEvento;
    }

    @Transactional
    public void delete(AreaEventoId id) {
        AreaEvento entity = AreaEvento.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Relação Área de Risco-Evento não encontrada");
        }
        entity.delete();
    }
} 