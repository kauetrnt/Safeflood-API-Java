package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.trnt.safeflood.exception.BusinessException;
import org.trnt.safeflood.exception.ResourceNotFoundException;
import org.trnt.safeflood.model.Usuario;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    public List<Usuario> listAll() {
        return Usuario.listAll();
    }

    public Usuario findById(Long id) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado");
        }
        return usuario;
    }

    public Usuario findByCpf(String cpf) {
        Usuario usuario = Usuario.find("cpf", cpf).firstResult();
        if (usuario == null) {
            throw new ResourceNotFoundException("Usuário com CPF " + cpf + " não encontrado");
        }
        return usuario;
    }

    public Usuario findByEmail(String email) {
        return Usuario.find("email", email).firstResult();
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        if (usuario.email != null && findByEmail(usuario.email) != null) {
            throw new BusinessException("Email já cadastrado");
        }
        usuario.persist();
        return usuario;
    }

    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        Usuario entity = Usuario.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado");
        }
        
        if (usuario.email != null && !usuario.email.equals(entity.email)) {
            Usuario existingUser = Usuario.find("email", usuario.email).firstResult();
            if (existingUser != null) {
                throw new BusinessException("Email já cadastrado");
            }
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
            throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado");
        }
        entity.delete();
    }
} 