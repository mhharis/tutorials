package org.baeldung.spring.persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "org.baeldung.spring.persistence.dao",
"org.baeldung.spring.persistence.service" })
public class PersistenceConfig {

	@Autowired
	private Environment env;

	public PersistenceConfig() {
		super();
	}

	@Bean
	public AnnotationSessionFactoryBean sessionFactory() {
		final AnnotationSessionFactoryBean sessionFactory = new AnnotationSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory
		.setPackagesToScan(new String[] { "org.baeldung.spring.persistence.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(env
				.getProperty("jdbc.driverClassName")));
		dataSource.setUrl(Preconditions.checkNotNull(env
				.getProperty("jdbc.url")));
		dataSource.setUsername(Preconditions.checkNotNull(env
				.getProperty("jdbc.user")));
		dataSource.setPassword(Preconditions.checkNotNull(env
				.getProperty("jdbc.pass")));

		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	final Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto",
						env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect",
						env.getProperty("hibernate.dialect"));

				// setProperty("hibernate.globally_quoted_identifiers", "true");
				// note: necessary in launchpad-storage, but causing problems
				// here
			}
		};
	}
}