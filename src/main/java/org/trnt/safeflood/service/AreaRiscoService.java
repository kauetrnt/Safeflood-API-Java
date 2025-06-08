package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.model.AreaRisco;
import java.util.List;

@ApplicationScoped
public class AreaRiscoService {

    public List<AreaRisco> listAll() {
        return AreaRisco.listAll();
    }

    public AreaRisco findById(Long id) {
        AreaRisco areaRisco = AreaRisco.findById(id);
        if (areaRisco == null) {
            throw new ResourceNotFoundException("Área de risco com id " + id + " não encontrada");
        }
        return areaRisco;
    }

    @Transactional
    public AreaRisco create(AreaRisco areaRisco) {
        if (areaRisco.nomeArea == null || areaRisco.nomeArea.trim().isEmpty()) {
            throw new BusinessException("Nome da área é obrigatório");
        }
        if (areaRisco.localAreaRisco == null || areaRisco.localAreaRisco.trim().isEmpty()) {
            throw new BusinessException("Local da área de risco é obrigatório");
        }
        if (areaRisco.nivelRisco == null) {
            throw new BusinessException("Nível de risco é obrigatório");
        }
        if (areaRisco.tipoRisco == null || areaRisco.tipoRisco.trim().isEmpty()) {
            throw new BusinessException("Tipo de risco é obrigatório");
        }
        if (areaRisco.alcance == null) {
            throw new BusinessException("Alcance é obrigatório");
        }
        areaRisco.persist();
        return areaRisco;
    }

    @Transactional
    public AreaRisco update(Long id, AreaRisco areaRisco) {
        AreaRisco entity = AreaRisco.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Área de risco com id " + id + " não encontrada");
        }
        
        if (areaRisco.nomeArea == null || areaRisco.nomeArea.trim().isEmpty()) {
            throw new BusinessException("Nome da área é obrigatório");
        }
        if (areaRisco.localAreaRisco == null || areaRisco.localAreaRisco.trim().isEmpty()) {
            throw new BusinessException("Local da área de risco é obrigatório");
        }
        if (areaRisco.nivelRisco == null) {
            throw new BusinessException("Nível de risco é obrigatório");
        }
        if (areaRisco.tipoRisco == null || areaRisco.tipoRisco.trim().isEmpty()) {
            throw new BusinessException("Tipo de risco é obrigatório");
        }
        if (areaRisco.alcance == null) {
            throw new BusinessException("Alcance é obrigatório");
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
            throw new ResourceNotFoundException("Área de risco com id " + id + " não encontrada");
        }
        entity.delete();
    }
} 