package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "evento")
public class Evento extends PanacheEntity {
    
    @Column(name = "acao_tomada", length = 100)
    public String acaoTomada;

}
