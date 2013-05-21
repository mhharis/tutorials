/*
 * @date: 21-05-2013
 * @author: Muhammad Haris (mh.haris@gmail.com)
 */
package org.baeldung.spring.persistence.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T extends Serializable> {

	public void setClazz(final Class<T> clazzToSet);

	public T getById(final Long id);

	public List<T> findAll();

	public void save(final T entity);

	public void update(final T entity);

	public void delete(final T entity);

	public void deleteById(final Long entityId);
}
