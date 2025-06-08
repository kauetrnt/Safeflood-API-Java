package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Notificacao;
import java.util.List;

@ApplicationScoped
public class NotificacaoService {

    public List<Notificacao> listAll() {
        return Notificacao.listAll();
    }

    public Notificacao findById(Long id) {
        return Notificacao.findById(id);
    }

    public List<Notificacao> findByTipo(String tipo) {
        return Notificacao.find("tipoNotificacao", tipo).list();
    }

    @Transactional
    public Notificacao create(Notificacao notificacao) {
        notificacao.persist();
        return notificacao;
    }

    @Transactional
    public Notificacao update(Long id, Notificacao notificacao) {
        Notificacao entity = Notificacao.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Notificação com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        
        entity.mensagemNotificacao = notificacao.mensagemNotificacao;
        entity.tipoNotificacao = notificacao.tipoNotificacao;
        entity.dataEnvio = notificacao.dataEnvio;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Notificacao entity = Notificacao.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Notificação com id " + id + " não encontrada.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 