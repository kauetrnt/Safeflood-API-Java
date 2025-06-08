package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.model.RotaFuga;
import java.util.List;

@ApplicationScoped
public class RotaFugaService {

    public List<RotaFuga> listAll() {
        return RotaFuga.listAll();
    }

    public RotaFuga findById(Long id) {
        RotaFuga rotaFuga = RotaFuga.findById(id);
        if (rotaFuga == null) {
            throw new ResourceNotFoundException("Rota de fuga com id " + id + " não encontrada");
        }
        return rotaFuga;
    }

    public List<RotaFuga> findByAreaRisco(Long areaRiscoId) {
        return RotaFuga.find("areaRisco.id", areaRiscoId).list();
    }

    @Transactional
    public RotaFuga create(RotaFuga rotaFuga) {
        if (rotaFuga.areaRisco == null) {
            throw new BusinessException("Área de risco é obrigatória para criar uma rota de fuga");
        }
        if (rotaFuga.origem == null || rotaFuga.origem.trim().isEmpty()) {
            throw new BusinessException("Origem é obrigatória para criar uma rota de fuga");
        }
        if (rotaFuga.destino == null || rotaFuga.destino.trim().isEmpty()) {
            throw new BusinessException("Destino é obrigatório para criar uma rota de fuga");
        }
        rotaFuga.persist();
        return rotaFuga;
    }

    @Transactional
    public RotaFuga update(Long id, RotaFuga rotaFuga) {
        RotaFuga entity = RotaFuga.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Rota de fuga com id " + id + " não encontrada");
        }
        
        if (rotaFuga.areaRisco == null) {
            throw new BusinessException("Área de risco é obrigatória para atualizar uma rota de fuga");
        }
        if (rotaFuga.origem == null || rotaFuga.origem.trim().isEmpty()) {
            throw new BusinessException("Origem é obrigatória para atualizar uma rota de fuga");
        }
        if (rotaFuga.destino == null || rotaFuga.destino.trim().isEmpty()) {
            throw new BusinessException("Destino é obrigatório para atualizar uma rota de fuga");
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
            throw new ResourceNotFoundException("Rota de fuga com id " + id + " não encontrada");
        }
        entity.delete();
    }
} 