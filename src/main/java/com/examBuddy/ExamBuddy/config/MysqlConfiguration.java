package com.examBuddy.ExamBuddy.config;

import com.examBuddy.ExamBuddy.utils.ConfigUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "db1roEntityManager", basePackages = "com.examBuddy.ExamBuddy.database.mysql.dao", transactionManagerRef = "db1roTransactionManager")
public class MysqlConfiguration {

	@Autowired
	private ConfigUtils configUtils;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.db1a.datasource")
	public DataSourceProperties dataSourceProperties1() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.db1a.datasource")
	public HikariDataSource dataSource(DataSourceProperties properties) {
		HikariDataSource hikari = properties.initializeDataSourceBuilder()
				.url(configUtils.getStringValue("spring.db1a.datasource.url")).type(HikariDataSource.class).build();
		hikari.addDataSourceProperty("autoReconnect", true);
		hikari.addDataSourceProperty("cachePrepStmts", true);
		hikari.addDataSourceProperty("prepStmtCacheSize", 250);
		hikari.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		hikari.addDataSourceProperty("useServerPrepStmts", true);
		hikari.addDataSourceProperty("cacheResultSetMetadata", true);
		hikari.addDataSourceProperty("maxReconnects", 5);
		hikari.addDataSourceProperty("tcpKeepAlive", true);
		hikari.setDriverClassName("com.mysql.jdbc.Driver");
		hikari.setConnectionTestQuery("SELECT 1");
		hikari.setMaximumPoolSize(configUtils.getIntValue("spring.dba.maxpoolsize"));
		hikari.setMinimumIdle(20);
		hikari.setIdleTimeout(30000);
		return hikari;
	}

	@Primary
	@Bean(name = "db1roEntityManager")
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource(dataSourceProperties1()));
		em.setPackagesToScan(new String[] { "com.examBuddy.ExamBuddy.database.mysql.entity" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", configUtils.getStringValue("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", configUtils.getStringValue("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.current_session_context_class",
				configUtils.getStringValue("spring.jpa.properties.hibernate.current_session_context_class"));
		em.setJpaPropertyMap(properties);
		em.setPersistenceUnitName("db1");
		return em;
	}

	@Primary
	@Bean(name = "db1roTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("db1roEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
