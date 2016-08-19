package ca.tklab.config;

import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ca.tklab.core.utils.ObjUtils;
@EnableJpaRepositories(basePackages = {
        "ca.tklab.secret.repository"
})	
@Configuration
@EnableTransactionManagement
public class PersistenceContext {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
    private static final String[] PROPERTY_PACKAGES_TO_SCAN = {
            "ca.tklab.secret.model",
            "ca.tklab.secret.repository"
    };
    
    private static final String HIBERNATE_DIALECT = org.hibernate.cfg.AvailableSettings.DIALECT;
    private static final String HIBERNATE_FORMAT_SQL = org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
    private static final String HIBERNATE_HBM2DDL_AUTO = org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO; 
    private static final String HIBERNATE_NAMING_STRATEGY = org.hibernate.jpa.AvailableSettings.NAMING_STRATEGY;//"hibernate.ejb.naming_strategy";
    private static final String HIBERNATE_SHOW_SQL = org.hibernate.cfg.AvailableSettings.SHOW_SQL; //"hibernate.show_sql";
    private static final String HIBERNATE_IMPORT_FILES = org.hibernate.cfg.AvailableSettings.HBM2DDL_IMPORT_FILES; //"hibernate.show_sql";
    private static final String HIBERNATE_IMPORT_FILES_SQL_EXTRACTOR = org.hibernate.cfg.AvailableSettings.HBM2DDL_IMPORT_FILES_SQL_EXTRACTOR; //"hibernate.show_sql";

	@Bean
	 public DataSource dataSource() {
		 DriverManagerDataSource driver = new DriverManagerDataSource();
		 logger.debug("DB.DRIVER:{}", env.getProperty("db.driver"));
		 driver.setDriverClassName(env.getProperty("db.driver", "org.postgresql.Driver")); 
		 driver.setUrl(env.getProperty("db.url", "jdbc:postgresql://localhost/secret")); 
		 driver.setUsername(env.getProperty("db.username", "test")); 
		 driver.setPassword(env.getProperty("db.password", "testpassword")); 
		 return driver;
	 }


    @Bean
    public EntityManagerFactory  entityManagerFactory() {
    	
        Properties jpaProperties = new Properties();
        jpaProperties.put(HIBERNATE_DIALECT, 		env.getProperty(HIBERNATE_DIALECT, "org.hibernate.dialect.PostgreSQLDialect"));
        jpaProperties.put(HIBERNATE_SHOW_SQL, 		env.getProperty(HIBERNATE_SHOW_SQL, "true"));
        jpaProperties.put(HIBERNATE_FORMAT_SQL, 	env.getProperty(HIBERNATE_FORMAT_SQL, "true"));
        jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, 	env.getProperty(HIBERNATE_HBM2DDL_AUTO,"update"));
        jpaProperties.put(HIBERNATE_NAMING_STRATEGY, env.getProperty(HIBERNATE_NAMING_STRATEGY, "org.hibernate.cfg.ImprovedNamingStrategy"));
        jpaProperties.put(HIBERNATE_IMPORT_FILES_SQL_EXTRACTOR, "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor"); 
        jpaProperties.put("hibernate.id.new_generator_mappings", "true");        
//        jpaProperties.put("jadira.usertype.autoRegisterUserTypes", "true");
        
        
        String importFiles = env.getProperty(HIBERNATE_IMPORT_FILES);
        if(!ObjUtils.isEmpty(importFiles)) {
        	jpaProperties.put(HIBERNATE_IMPORT_FILES, importFiles);
        }
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }
	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory());
	    return txManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
