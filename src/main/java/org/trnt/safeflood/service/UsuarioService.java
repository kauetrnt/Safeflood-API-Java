package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Usuario;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    public List<Usuario> listAll() {
        return Usuario.listAll();
    }

    public Usuario findById(Long id) {
        return Usuario.findById(id);
    }

    public Usuario findByCpf(String cpf) {
        return Usuario.find("cpf", cpf).firstResult();
    }

    public Usuario findByEmail(String email) {
        return Usuario.find("email", email).firstResult();
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        if (usuario.email != null && findByEmail(usuario.email) != null) {
            throw new WebApplicationException("Email já cadastrado.", Response.Status.CONFLICT);
        }
        usuario.persist();
        return usuario;
    }

    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        Usuario entity = Usuario.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        
        entity.nomeUsuario = usuario.nomeUsuario;
        entity.email = usuario.email;
        entity.tipoUsuario = usuario.tipoUsuario;

        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Usuario entity = Usuario.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 