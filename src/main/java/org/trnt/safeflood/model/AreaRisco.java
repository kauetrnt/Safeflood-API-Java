package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "area_risco")
public class AreaRisco extends PanacheEntity {
    
    @Column(name = "alcance", nullable = false)
    public Double alcance;
    
    @Column(name = "nome_area", nullable = false, length = 100)
    public String nomeArea;
    
    @Column(name = "local_area_risco", nullable = false)
    public String localAreaRisco; // SDO_GEOMETRY type
    
    @Column(name = "nivel_risco", nullable = false)
    public Integer nivelRisco;
    
    @Column(name = "tipo_risco", nullable = false, length = 40)
    public String tipoRisco;

    
    @OneToMany(mappedBy = "areaRisco")
    public List<RotaFuga> rotasFuga;
    
    @ManyToMany
    @JoinTable(
        name = "are_eve",
        joinColumns = @JoinColumn(name = "fk_area_eve"),
        inverseJoinColumns = @JoinColumn(name = "fk_evento_eve")
    )
    public List<Evento> eventos;
}
