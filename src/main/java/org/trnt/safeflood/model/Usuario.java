package org.trnt.safeflood.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntity {
    
    @Column(name = "nome_usuario", nullable = false, length = 100)
    public String nomeUsuario;
    
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    public Date dataCadastro;
    
    @Column(name = "email", unique = true, length = 50)
    public String email;
    
    @Column(name = "tipo_usuario", nullable = false, length = 50)
    public String tipoUsuario;
    
    
    @OneToMany(mappedBy = "usuario")
    public List<Alerta> alertas;
    
    @ManyToMany
    @JoinTable(
        name = "usu_not",
        joinColumns = @JoinColumn(name = "fk_id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "fk_id_notificacao")
    )
    public List<Notificacao> notificacoes;
}
