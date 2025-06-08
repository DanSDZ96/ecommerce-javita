package com.azienda.progettoCatalogoProdotto.dao;

import java.util.List;

public interface DaoGeneral <T> {
	public T create(T entity);
	public List<T> read();
	public T update(T entity);
	public void delete (T entity);
}
