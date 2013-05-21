/*
 * @date: 21-05-2013
 * @author: Muhammad Haris (mh.haris@gmail.com)
 */
package org.baeldung.spring.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDAO<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	SessionFactory sessionFactory;

	public void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	public T getById(final Long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	public void save(final T entity) {
		getCurrentSession().persist(entity);
	}

	public void update(final T entity) {
		getCurrentSession().merge(entity);
	}

	public void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(final Long entityId) {
		final T entity = getById(entityId);
		delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
