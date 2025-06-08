package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;


import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Ruolo;

public class RuoloDao implements DaoGeneral<Ruolo>{
	
	private EntityManager manager;
	
	public RuoloDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public Ruolo create(Ruolo entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public List<Ruolo> read() {
		return manager.createQuery("select r from Ruolo r", Ruolo.class).getResultList();
	}

	@Override
	public Ruolo update(Ruolo entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Ruolo entity) {
		manager.remove(entity);
	}
	
	public Ruolo getRuoloByName(String nome) {
		return manager.createQuery("select r from Ruolo r where r.nome= :parNome",Ruolo.class)
				.setParameter("parNome", nome).getSingleResult();
	}
}
