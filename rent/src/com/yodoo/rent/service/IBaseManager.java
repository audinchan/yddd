package com.yodoo.rent.service;
// Generated 2007-8-6 20:26:59 by Hibernate Tools 3.2.0.b9 with mintgen


import java.io.Serializable;
import java.util.List;

public interface IBaseManager<T, K extends Serializable> extends IRootManager<T, K> {
	public T get(K id);
	public void save(T instance);
	public void remove(T instance);
	public void removeById(K id);
	public List<T> findAll();
	public List<T> findByExample(T instance);
	public List<T> findByExample(T instance, int firstResult, int maxResults);
}
