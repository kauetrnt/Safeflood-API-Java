package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.model.Alerta;
import java.util.List;

@ApplicationScoped
public class AlertaService {

    public List<Alerta> listAll() {
        return Alerta.listAll();
    }

    public Alerta findById(Long id) {
        Alerta alerta = Alerta.findById(id);
        if (alerta == null) {
            throw new ResourceNotFoundException("Alerta com id " + id + " não encontrado");
        }
        return alerta;
    }

    public List<Alerta> findByUsuario(Long usuarioId) {
        return Alerta.find("usuario.id", usuarioId).list();
    }

    @Transactional
    public Alerta create(Alerta alerta) {
        if (alerta.usuario == null) {
            throw new BusinessException("Usuário é obrigatório para criar um alerta");
        }
        alerta.persist();
        return alerta;
    }

    @Transactional
    public Alerta update(Long id, Alerta alerta) {
        Alerta entity = Alerta.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Alerta com id " + id + " não encontrado");
        }
        
        if (alerta.usuario == null) {
            throw new BusinessException("Usuário é obrigatório para atualizar um alerta");
        }
        
        entity.titulo = alerta.titulo;
        entity.descricao = alerta.descricao;
        entity.nivelRisco = alerta.nivelRisco;
        entity.dataInicio = alerta.dataInicio;
        entity.dataFim = alerta.dataFim;
        entity.latitude = alerta.latitude;
        entity.longitude = alerta.longitude;
        entity.uf = alerta.uf;
        entity.municipio = alerta.municipio;
        entity.usuario = alerta.usuario;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Alerta entity = Alerta.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Alerta com id " + id + " não encontrado");
        }
        entity.delete();
    }
} 