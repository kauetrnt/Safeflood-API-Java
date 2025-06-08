package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Alerta;
import java.util.List;

@ApplicationScoped
public class AlertaService {

    public List<Alerta> listAll() {
        return Alerta.listAll();
    }

    public Alerta findById(Long id) {
        return Alerta.findById(id);
    }

    public List<Alerta> findByUsuario(Long usuarioId) {
        return Alerta.find("usuario.id", usuarioId).list();
    }

    @Transactional
    public Alerta create(Alerta alerta) {
        alerta.persist();
        return alerta;
    }

    @Transactional
    public Alerta update(Long id, Alerta alerta) {
        Alerta entity = Alerta.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Alerta com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
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
            throw new WebApplicationException("Alerta com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 