package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;


import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Categoria;

public class CategoriaDao implements DaoGeneral<Categoria>{
	
	private EntityManager manager;
	
	public CategoriaDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public Categoria create(Categoria entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public List<Categoria> read() {
		return manager.createQuery("select cat from Categoria cat", Categoria.class).getResultList();
	}

	@Override
	public Categoria update(Categoria entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Categoria entity) {
		manager.remove(entity);
	}
	
	public Categoria getCategoriaByName(String nome) {
		return manager.createQuery("select c from Categoria c where c.nome= :parNome",Categoria.class)
				.setParameter("parNome", nome).getSingleResult();
	}
	
	public Categoria getCategoriaById(int id) {
		return manager.createQuery("select c from Categoria c where c.id= :parId",Categoria.class)
				.setParameter("parId", id).getSingleResult();
	}
	
}
