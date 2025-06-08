package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.azienda.progettoCatalogoProdotto.model.Carrello;

public class CarrelloDao implements DaoGeneral<Carrello> {

    private EntityManager manager;

    public CarrelloDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Carrello create(Carrello entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public List<Carrello> read() {
        return manager.createQuery("SELECT c FROM Carrello c", Carrello.class).getResultList();
    }

    @Override
    public Carrello update(Carrello entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Carrello entity) {
        manager.remove(entity);
    }

    public Carrello getByUtenteId(Integer utenteId) {
        try {
            return manager.createQuery(
                "SELECT c FROM Carrello c WHERE c.utente.id = :utenteId", Carrello.class)
                .setParameter("utenteId", utenteId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
