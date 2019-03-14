package net.codejava.javaee.bookstore.SpringConfig;
import net.codejava.javaee.bookstore.Utils.PropertyReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import java.io.IOException;
import java.util.Properties;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.codejava.javaee.bookstore")
public class BeanConfig {




    @Bean
         public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("net.codejava.javaee.bookstore.model");
            sessionFactory.setHibernateProperties(hibernateProperties());
        try {
            sessionFactory.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
        }

       @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            dataSource.setUsername("root");
            dataSource.setPassword("Qweqwe123");
            return dataSource;
        }



     private Properties hibernateProperties() {
            String url = PropertyReader.getPropertyStr("url");
            String username = PropertyReader.getPropertyStr("username");
            String password = PropertyReader.getPropertyStr("password");
            return new Properties() {
                {
                    setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                  //  setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                   // setProperty("hibernate.connection.url", url);
                   // setProperty("hibernate.connection.username", username);
                  //  setProperty("hibernate.connection.password", password);
                    setProperty("hibernate.show_sql", "true");
                    setProperty("hibernate.hbm2ddl.auto", "none");
                }
            };
        }


    }

