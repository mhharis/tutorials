/*
 * @date: 21-05-2013
 * @author: Muhammad Haris (mh.haris@gmail.com)
 */
package org.baeldung.spring.persistence.dao;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional(propagation = Propagation.MANDATORY)
public class GenericHibernateDAO<T extends Serializable> extends
		AbstractHibernateDAO<T> implements IGenericDAO<T> {

}
