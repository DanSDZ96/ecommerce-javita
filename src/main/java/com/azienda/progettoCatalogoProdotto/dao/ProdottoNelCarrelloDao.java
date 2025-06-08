package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.azienda.progettoCatalogoProdotto.model.ProdottoNelCarrello;

public class ProdottoNelCarrelloDao implements DaoGeneral<ProdottoNelCarrello> {

    private EntityManager manager;

    public ProdottoNelCarrelloDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public ProdottoNelCarrello create(ProdottoNelCarrello entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public List<ProdottoNelCarrello> read() {
        return manager.createQuery("SELECT p FROM ProdottoNelCarrello p", ProdottoNelCarrello.class).getResultList();
    }

    @Override
    public ProdottoNelCarrello update(ProdottoNelCarrello entity) {
    	manager.persist(entity);
        return entity;
    }

    @Override
    public void delete(ProdottoNelCarrello entity) {
    	manager.remove(entity);
    }

    public ProdottoNelCarrello findByCarrelloAndProdotto(Integer carrelloId, Integer prodottoId) {
        try {
            return manager.createQuery(
                "SELECT p FROM ProdottoNelCarrello p WHERE p.carrello.id = :cid AND p.prodotto.id = :pid", 
                ProdottoNelCarrello.class)
                .setParameter("cid", carrelloId)
                .setParameter("pid", prodottoId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ProdottoNelCarrello> getByCarrelloId(Integer carrelloId) {
        return manager.createQuery(
            "SELECT p FROM ProdottoNelCarrello p WHERE p.carrello.id = :carrelloId", 
            ProdottoNelCarrello.class)
            .setParameter("carrelloId", carrelloId)
            .getResultList();
    }

    public void deleteByCarrelloId(Integer carrelloId) {
        manager.createQuery("DELETE FROM ProdottoNelCarrello p WHERE p.carrello.id = :cid")
               .setParameter("cid", carrelloId)
               .executeUpdate();
    }
    
    public void deleteByIdFromCarrello(int id) {
		manager.createQuery("delete from ProdottoNelCarrello pnc where pnc.id =:parId").
				setParameter("parId", id).executeUpdate();
	}
    
    public List<ProdottoNelCarrello> getCarrelloByIdProdotto(int prodottoId){
    	 return manager.createQuery(
    	            "SELECT p FROM ProdottoNelCarrello p WHERE p.prodotto.id = :prodottoId", 
    	            ProdottoNelCarrello.class)
    	            .setParameter("prodottoId", prodottoId)
    	            .getResultList();
    }
}
