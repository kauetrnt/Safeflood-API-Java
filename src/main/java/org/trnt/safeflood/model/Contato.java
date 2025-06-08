package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contato")
public class Contato extends PanacheEntity {

    @Column(name = "nome", nullable = false, length = 100)
    public String nome;
    
    @Column(name = "email", nullable = false, length = 50)
    public String email;
    
    @Column(name = "telefone", nullable = false, length = 11)
    public String telefone;
    
    @Column(name = "mensagem", nullable = false, length = 1000)
    public String mensagem;
    
    @Column(name = "data_envio")
    @Temporal(TemporalType.DATE)
    public Date dataEnvio;
}
