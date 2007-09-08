package com.yodoo.rent.service.impl;
// Generated 2007-8-6 20:27:00 by Hibernate Tools 3.2.0.b9 with mintgen

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.nestframework.commons.hibernate.IQueryProvider;
import com.yodoo.rent.service.IRootManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("unchecked")
public abstract class RootManager<T, K extends Serializable> extends HibernateDaoSupport implements IRootManager<T, K> {
	public static Class getGenericClass(Class clazz) {
		return getGenericClass(clazz, 0);
	}

	public static Class getGenericClass(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();

			if ((params != null) && (params.length >= (index - 1))) {
				return (Class) params[index];
			}
		}
		return null;
	}
    
    /**
     * Dynamic HQL provider.
     */
    protected IQueryProvider queryProvider;

    public void setQueryProvider(IQueryProvider queryProvider)
    {
        this.queryProvider = queryProvider;
    }
    
    /**
     * Get dynamic HQL template.
     * @param name HQL's name.
     * @return HQL template.
     */
    protected String _(String name) {
        return queryProvider.getQuery(name);
    }
}