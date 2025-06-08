package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.model.Notificacao;
import java.util.List;

@ApplicationScoped
public class NotificacaoService {

    public List<Notificacao> listAll() {
        return Notificacao.listAll();
    }

    public Notificacao findById(Long id) {
        Notificacao notificacao = Notificacao.findById(id);
        if (notificacao == null) {
            throw new ResourceNotFoundException("Notificação com id " + id + " não encontrada");
        }
        return notificacao;
    }

    public List<Notificacao> findByTipo(String tipo) {
        return Notificacao.find("tipoNotificacao", tipo).list();
    }

    @Transactional
    public Notificacao create(Notificacao notificacao) {
        if (notificacao.tipoNotificacao == null || notificacao.tipoNotificacao.trim().isEmpty()) {
            throw new BusinessException("Tipo de notificação é obrigatório");
        }
        if (notificacao.mensagemNotificacao == null || notificacao.mensagemNotificacao.trim().isEmpty()) {
            throw new BusinessException("Mensagem da notificação é obrigatória");
        }
        notificacao.persist();
        return notificacao;
    }

    @Transactional
    public Notificacao update(Long id, Notificacao notificacao) {
        Notificacao entity = Notificacao.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Notificação com id " + id + " não encontrada");
        }
        
        if (notificacao.tipoNotificacao == null || notificacao.tipoNotificacao.trim().isEmpty()) {
            throw new BusinessException("Tipo de notificação é obrigatório");
        }
        if (notificacao.mensagemNotificacao == null || notificacao.mensagemNotificacao.trim().isEmpty()) {
            throw new BusinessException("Mensagem da notificação é obrigatória");
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
            throw new ResourceNotFoundException("Notificação com id " + id + " não encontrada");
        }
        entity.delete();
    }
} 