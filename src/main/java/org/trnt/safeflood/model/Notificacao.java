package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notificacao")
public class Notificacao extends PanacheEntity {

    @Column(name = "mensagem_notificacao", nullable = false, length = 200)
    public String mensagemNotificacao;
    
    @Column(name = "tipo_notificacao", nullable = false, length = 40)
    public String tipoNotificacao;
    
    @Column(name = "data_envio")
    @Temporal(TemporalType.DATE)
    public Date dataEnvio;
    
    @ManyToMany(mappedBy = "notificacoes")
    public List<Usuario> usuarios;
}
