package org.trnt.safeflood.model;


import java.io.Serializable;
import java.util.Objects;

public class UsuarioNotificacaoId implements Serializable {
    
    public Long idUsuario;
    public Long idNotificacao;
    
    public UsuarioNotificacaoId() {}
    
    public UsuarioNotificacaoId(Long idUsuario, Long idNotificacao) {
        this.idUsuario = idUsuario;
        this.idNotificacao = idNotificacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioNotificacaoId that = (UsuarioNotificacaoId) o;
        return Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idNotificacao, that.idNotificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idNotificacao);
    }
}
