package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "are_eve")
@IdClass(AreaEventoId.class)
public class AreaEvento extends PanacheEntityBase {
    
    @Id
    @Column(name = "fk_area_eve")
    public Long idAreaRisco;
    
    @Id
    @Column(name = "fk_evento_eve")
    public Long idEvento;
    
    @ManyToOne
    @JoinColumn(name = "fk_area_eve", insertable = false, updatable = false)
    public AreaRisco areaRisco;
    
    @ManyToOne
    @JoinColumn(name = "fk_evento_eve", insertable = false, updatable = false)
    public Evento evento;
}
