package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alerta")
public class Alerta extends PanacheEntity {
    
    @Column(name = "titulo", nullable = false, length = 100)
    public String titulo;
    
    @Column(name = "descricao", nullable = false, length = 500)
    public String descricao;
    
    @Column(name = "nivel_risco", nullable = false, length = 20)
    public String nivelRisco;
    
    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date dataInicio;
    
    @Column(name = "data_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date dataFim;
    
    @Column(name = "alerta_criado_em")
    @Temporal(TemporalType.DATE)
    public Date alertaCriadoEm;
    
    @Column(name = "latitude", nullable = false)
    public Double latitude;
    
    @Column(name = "longitude", nullable = false)
    public Double longitude;
    
    @Column(name = "uf", nullable = false, length = 2)
    public String uf;
    
    @Column(name = "municipio", nullable = false, length = 100)
    public String municipio;
    
    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    public Usuario usuario;
}
