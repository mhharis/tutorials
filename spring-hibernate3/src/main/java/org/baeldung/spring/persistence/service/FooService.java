/*
 * @date: 21-05-2013
 * @author: Muhammad Haris (mh.haris@gmail.com)
 */
package org.baeldung.spring.persistence.service;

import java.util.List;

import org.baeldung.spring.persistence.dao.IGenericDAO;
import org.baeldung.spring.persistence.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class FooService {

	IGenericDAO<Foo> dao;

	@Autowired
	public void setDao(final IGenericDAO<Foo> daoToSet) {
		dao = daoToSet;
		dao.setClazz(Foo.class);
	}

	public List<Foo> getAllFoos() throws Exception {
		return dao.findAll();
	}
}
