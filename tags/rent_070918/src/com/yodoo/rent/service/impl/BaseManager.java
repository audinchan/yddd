package com.yodoo.rent.service.impl;
// Generated 2007-8-6 20:27:00 by Hibernate Tools 3.2.0.b9 with mintgen


import java.io.Serializable;
import com.yodoo.rent.service.IBaseManager;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class BaseManager<T, K extends Serializable> extends RootManager<T, K> implements IBaseManager<T, K> {
	private Class<T> entityClass;
	
	public BaseManager() {
		entityClass = getGenericClass(getClass());
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T instance) {
		getHibernateTemplate().saveOrUpdate(instance);
	}

	public List<T> findAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public T get(K id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}
	
	public void remove(T instance) {
		getHibernateTemplate().delete(instance);
	}

	public void removeById(K id) {
		getHibernateTemplate().delete(get(id));
	}

	public List<T> findByExample(T instance) {
		return getHibernateTemplate().findByExample(instance);
	}

	public List<T> findByExample(T instance, int firstResult, int maxResults) {
		return getHibernateTemplate().findByExample(instance, firstResult,
				maxResults);
	}
}
