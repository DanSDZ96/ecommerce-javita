package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;


import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Ordine;

public class OrdineDao implements DaoGeneral<Ordine>{
	
	private EntityManager manager;
	
	public OrdineDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public Ordine create(Ordine entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public List<Ordine> read() {
		return manager.createQuery("select o from Ordine o", Ordine.class).getResultList();
	}

	@Override
	public Ordine update(Ordine entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Ordine entity) {
		manager.remove(entity);
	}
	
	public List<Ordine> getAllByUserId(int id){
		return manager.createQuery("select o from Ordine o where o.utente.id = :utenteId", Ordine.class)
				.setParameter("utenteId", id).getResultList();
	}
}
