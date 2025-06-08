package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rota_de_fuga")
public class RotaFuga extends PanacheEntity {

    
    @Column(name = "origem", nullable = false)
    public String origem; // SDO_GEOMETRY type
    
    @Column(name = "destino", nullable = false)
    public String destino; // SDO_GEOMETRY type
    
    @Column(name = "rota_criado_em")
    @Temporal(TemporalType.DATE)
    public Date rotaCriadoEm;
    
    @ManyToOne
    @JoinColumn(name = "fk_area_risco_rota")
    public AreaRisco areaRisco;
}
