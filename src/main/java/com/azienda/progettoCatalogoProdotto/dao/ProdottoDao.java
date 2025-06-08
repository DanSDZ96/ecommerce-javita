package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;



import javax.persistence.EntityManager;

import com.azienda.progettoCatalogoProdotto.model.Prodotto;

public class ProdottoDao implements DaoGeneral<Prodotto>{
	
	private EntityManager manager;
	
	public ProdottoDao(EntityManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	public Prodotto create(Prodotto entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public List<Prodotto> read() {
		return manager.createQuery("select p from Prodotto p", Prodotto.class).getResultList();
	}

	@Override
	public Prodotto update(Prodotto entity) {
		manager.persist(entity);
		return entity;
	}

	@Override
	public void delete(Prodotto entity) {
		manager.remove(entity);
	}
	
	public void deleteById(int id) {
		manager.createQuery("delete from Prodotto p where p.id =:parId").
				setParameter("parId", id).executeUpdate();
	}
	
	public List<Prodotto> findByNameLike(String string) {
		return manager.createQuery("select p from Prodotto p where p.nome like :parNome", Prodotto.class)
				.setParameter("parNome", string).getResultList();
	}
	
	public Prodotto getById(int id) {
		return manager.createQuery("select p from Prodotto p where p.id =:parId", Prodotto.class)
				.setParameter("parId", id).getSingleResult();
	}
	
	public List<Prodotto> cerca(String keywords) {
		return manager.createQuery("select p from Prodotto p where lower(p.nome) like :parNome",Prodotto.class)
		.setParameter("parNome", "%" + keywords.toLowerCase().trim() + "%").getResultList();
	}
	
	public void updateProduct(Prodotto p) {
		manager.createQuery("UPDATE Prodotto p SET p.nome = :parNome, p.prezzo = :parPrezzo, p.disponibilita = :parDisponibilita WHERE p.id = :parId")
	               .setParameter("parNome", p.getNome())
	               .setParameter("parPrezzo", p.getPrezzo())
	               .setParameter("parDisponibilita", p.getDisponibilita())
	               .setParameter("parId", p.getId())
	               .executeUpdate();
	}
}
