package org.trnt.safeflood.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.trnt.safeflood.model.Contato;
import java.util.List;

@ApplicationScoped
public class ContatoService {

    public List<Contato> listAll() {
        return Contato.listAll();
    }

    public Contato findById(Long id) {
        return Contato.findById(id);
    }

    public List<Contato> findByEmail(String email) {
        return Contato.find("email", email).list();
    }

    @Transactional
    public Contato create(Contato contato) {
        contato.persist();
        return contato;
    }

    @Transactional
    public Contato update(Long id, Contato contato) {
        Contato entity = Contato.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Contato com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        
        entity.nome = contato.nome;
        entity.email = contato.email;
        entity.telefone = contato.telefone;
        entity.mensagem = contato.mensagem;
        
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        Contato entity = Contato.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Contato com id " + id + " não encontrado.", Response.Status.NOT_FOUND);
        }
        entity.delete();
    }
} 