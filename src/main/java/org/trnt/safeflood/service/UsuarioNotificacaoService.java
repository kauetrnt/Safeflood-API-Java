package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.model.UsuarioNotificacao;
import org.trnt.safeflood.model.UsuarioNotificacaoId;
import java.util.List;

@ApplicationScoped
public class UsuarioNotificacaoService {

    public List<UsuarioNotificacao> listAll() {
        return UsuarioNotificacao.listAll();
    }

    public UsuarioNotificacao findById(UsuarioNotificacaoId id) {
        UsuarioNotificacao usuarioNotificacao = UsuarioNotificacao.findById(id);
        if (usuarioNotificacao == null) {
            throw new ResourceNotFoundException("Relação Usuário-Notificação não encontrada");
        }
        return usuarioNotificacao;
    }

    public List<UsuarioNotificacao> findByUsuario(Long usuarioId) {
        return UsuarioNotificacao.find("usuario.id", usuarioId).list();
    }

    public List<UsuarioNotificacao> findByNotificacao(Long notificacaoId) {
        return UsuarioNotificacao.find("notificacao.id", notificacaoId).list();
    }

    @Transactional
    public UsuarioNotificacao create(UsuarioNotificacao usuarioNotificacao) {
        if (usuarioNotificacao.usuario == null) {
            throw new BusinessException("Usuário é obrigatório para criar uma relação de notificação");
        }
        if (usuarioNotificacao.notificacao == null) {
            throw new BusinessException("Notificação é obrigatória para criar uma relação de notificação");
        }
        
        // Verifica se já existe uma relação entre o usuário e a notificação
        UsuarioNotificacaoId id = new UsuarioNotificacaoId();
        id.idUsuario = usuarioNotificacao.usuario.id;
        id.idNotificacao = usuarioNotificacao.notificacao.id;
        
        if (UsuarioNotificacao.findById(id) != null) {
            throw new BusinessException("Relação entre usuário e notificação já existe");
        }
        
        usuarioNotificacao.persist();
        return usuarioNotificacao;
    }

    @Transactional
    public void delete(UsuarioNotificacaoId id) {
        UsuarioNotificacao entity = UsuarioNotificacao.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Relação Usuário-Notificação não encontrada");
        }
        entity.delete();
    }
} 