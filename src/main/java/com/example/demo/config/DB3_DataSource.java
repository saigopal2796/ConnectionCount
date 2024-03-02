package com.example.demo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJpaRepositories(basePackages = {"com.example.demo.dao2"},
        entityManagerFactoryRef = "db3EntityManager",
        transactionManagerRef = "db3TransactionManager")
public class DB3_DataSource {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean db3EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db3Datasource());
        em.setPackagesToScan(
                new String[]{"com.example.demo.beans"});
        em.setPersistenceUnitName("db3EntityManager");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show-sql",
                env.getProperty("jdbc.show-sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource db3Datasource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("db3.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db3.datasource.url"));
        dataSource.setUsername(env.getProperty("db3.datasource.username"));
        dataSource.setPassword(env.getProperty("db3.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager db3TransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                db3EntityManager().getObject());
        return transactionManager;
    }
}
