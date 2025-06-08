package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;


import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Acquisto;
import com.azienda.progettoCatalogoProdotto.model.ProdottoNelCarrello;

public class AcquistoDao implements DaoGeneral<Acquisto>{
	private EntityManager manager;
	
	
	public AcquistoDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Acquisto create(Acquisto entity) {
		manager.persist(entity);
		return entity;
	}
	
	public List<Acquisto> createList(List<Acquisto> entity){
		for(Acquisto a: entity) {
			create(a);
		}		
		return entity;
	}
	
	@Override
	public List<Acquisto> read() {
		return manager.createQuery("select a from Acquisto a", Acquisto.class).getResultList();
	}

	@Override
	public Acquisto update(Acquisto entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Acquisto entity) {
		manager.remove(entity);
	}
	
	public List<Acquisto> getAcquistiByIdProdotto(int prodottoId){
   	 return manager.createQuery(
   	            "SELECT p FROM Acquisto p WHERE p.prodotto.id = :prodottoId", 
   	         Acquisto.class)
   	            .setParameter("prodottoId", prodottoId)
   	            .getResultList();
    }
	
	public List<Acquisto> getAllByOrdineId(int ordineId){
		return manager.createQuery(
   	            "SELECT p FROM Acquisto p WHERE p.ordine.id = :ordineId", 
   	         Acquisto.class)
   	            .setParameter("ordineId", ordineId)
   	            .getResultList();
	}

}
