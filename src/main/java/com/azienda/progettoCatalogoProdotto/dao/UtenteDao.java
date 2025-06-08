package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;


import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Utente;

public class UtenteDao implements DaoGeneral<Utente>{
	
	private EntityManager manager;
	
	public UtenteDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public Utente create(Utente entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public List<Utente> read() {
		return manager.createQuery("select u from Utente u", Utente.class).getResultList();
	}

	@Override
	public Utente update(Utente entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Utente entity) {
		manager.remove(entity);
	}
	
	public Utente selectByEmail(String email) {
		return manager.createQuery("select u from Utente u where u.email= :parEmail",Utente.class)
				.setParameter("parEmail", email).getSingleResult();
	}
}
