package com.vknyazeva.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

// данный класс заменяет applicationContext.xml, бины прописываются как методы
@Configuration
@ComponentScan(basePackages = "com.vknyazeva.spring.rest") //аналог <context:component-scan.../> в applCon.xml
@EnableWebMvc // аналог <mvc:annotation-driven/>
@EnableTransactionManagement // <tx:annotation-driven transaction-manager="transactionManager"/>
public class MyConfig {

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5433/empl");
            dataSource.setUser("postgres");
            dataSource.setPassword("1121");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource()); //создаем бин dataSource с пом. метода dataSource()
        sessionFactoryBean.setPackagesToScan("com.vknyazeva.spring.rest.entity");

        Properties properties = new Properties(); // чтобы добавить к sessionFactory пропертис, нужно их создать
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");

         sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject()); // getObgect() исп-ся для
                        // получения экземпляра sessionFactory

        return transactionManager;
    }




}
