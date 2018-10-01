package com.niit.JobBack;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = "com.niit.JobBack")
@EnableTransactionManagement

public class DBConfig {

	@Bean(name = "mydatasource")
	DataSource m1() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/Pro2");
		ds.setUsername("sa");
		ds.setPassword("suja");
		return ds;
	}

	@Bean(name = "myprop")
	Properties m2() {
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		p.setProperty("hibernate.show_sql", "true");
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		return p;
	}

	@Autowired
	@Bean(name = "sf")
	LocalSessionFactoryBean m3(DataSource mydatasource, Properties myprop) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(mydatasource);
		lsfb.setHibernateProperties(myprop);
		lsfb.setPackagesToScan("com.niit.JobBack");
		return lsfb;
	}

	@Bean(name = "mytrans")
	@Autowired
	HibernateTransactionManager m4(SessionFactory sf) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		return htm;

	}
	
	
}
