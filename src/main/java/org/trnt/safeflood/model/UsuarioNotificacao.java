package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "usu_not")
@IdClass(UsuarioNotificacaoId.class)
public class UsuarioNotificacao extends PanacheEntityBase {
    
    @Id
    @Column(name = "fk_id_usuario")
    public Long idUsuario;
    
    @Id
    @Column(name = "fk_id_notificacao")
    public Long idNotificacao;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", insertable = false, updatable = false)
    public Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_notificacao", insertable = false, updatable = false)
    public Notificacao notificacao;
}
