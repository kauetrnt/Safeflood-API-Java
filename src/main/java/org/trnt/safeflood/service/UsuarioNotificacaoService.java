package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.UsuarioNotificacao;
import org.trnt.safeflood.model.UsuarioNotificacaoId;
import java.util.List;

@ApplicationScoped
public class UsuarioNotificacaoService {

    public List<UsuarioNotificacao> listAll() {
        return UsuarioNotificacao.listAll();
    }

    public UsuarioNotificacao findById(UsuarioNotificacaoId id) {
        return UsuarioNotificacao.findById(id);
    }

    public List<UsuarioNotificacao> findByUsuario(Long usuarioId) {
        return UsuarioNotificacao.find("usuario.id", usuarioId).list();
    }

    public List<UsuarioNotificacao> findByNotificacao(Long notificacaoId) {
        return UsuarioNotificacao.find("notificacao.id", notificacaoId).list();
    }

    @Transactional
    public UsuarioNotificacao create(UsuarioNotificacao usuarioNotificacao) {
        usuarioNotificacao.persist();
        return usuarioNotificacao;
    }

    @Transactional
    public void delete(UsuarioNotificacaoId id) {
        UsuarioNotificacao entity = UsuarioNotificacao.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Relação Usuário-Notificação não encontrada.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 