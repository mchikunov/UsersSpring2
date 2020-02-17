package net.codejava.javaee.bookstore.SpringConfig;

import net.codejava.javaee.bookstore.Utils.PropertyReader;
import net.codejava.javaee.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath://confDb.properties")
public class PersistenceJPAConfig{

    @Autowired
    private Environment env;


@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
em.setDataSource(dataSource());
em.setPackagesToScan("net.codejava.javaee.bookstore.model");
JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
em.setJpaVendorAdapter(vendorAdapter);
em.setJpaProperties(additionalProperties());
return em;
}


@Bean
public DataSource dataSource(){
DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("drv"));
    dataSource.setUrl(env.getProperty("url"));
    dataSource.setUsername(env.getProperty("username1"));
    dataSource.setPassword(env.getProperty("password1"));
return dataSource;
}

@Bean
public PlatformTransactionManager transactionManager(
EntityManagerFactory emf){
JpaTransactionManager transactionManager = new JpaTransactionManager();
transactionManager.setEntityManagerFactory(emf);

return transactionManager;
}
@Bean
public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
return new PersistenceExceptionTranslationPostProcessor();
}


Properties additionalProperties() {
    String url = PropertyReader.getPropertyStr("url");
    String username = PropertyReader.getPropertyStr("username");
    String password = PropertyReader.getPropertyStr("password");
    return new Properties() {
        {
            setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            setProperty("hibernate.show_sql", "true");
            setProperty("hibernate.hbm2ddl.auto", "none");
        }
    };



}
}
