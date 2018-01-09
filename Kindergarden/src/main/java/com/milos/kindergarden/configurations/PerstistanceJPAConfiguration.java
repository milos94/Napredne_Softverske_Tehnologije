package com.milos.kindergarden.configurations;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PerstistanceJPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
	    emf.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
	    emf.setPackagesToScan("com.milos.kindergarden.models"); 
	    emf.setPersistenceUnitName("unitName");
	    emf.setJpaVendorAdapter(jpaVendorAdapter());
	    emf.setJpaProperties(properties());
	    
	    return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public Properties properties() {
		Properties properties = new Properties();
		
		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver" );
	    properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/kindergarden");
	    properties.put("javax.persistence.jdbc.user", "root");
	    properties.put("javax.persistence.jdbc.password", "root");
	    
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.put("hibernate.ddl_auto", "validate");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.physical_naming_strategy",
	    				"com.milos.kindergarden.configurations.PhysicalNamingStrategyImpl");
	    
	    properties.put("spring.datasource.dbcp2.test-while-idle", "true"); 
	    properties.put("spring.datasource.dbcp2.validation-query", "SELECT 1");
	    
	     
	    
	    return properties;
	}
	
}
