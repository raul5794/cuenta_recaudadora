package bcp.cuenta.recaudadora.databases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = {"bcp.cuenta.recaudadora.Repository.primary"},
        entityManagerFactoryRef = "KairaEntityManager",
        transactionManagerRef = "KairaTransactionManager"
)
public class KairaDataSourceConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean KairaEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(KairaDataSource());
        em.setPackagesToScan(
                "bcp.cuenta.recaudadora.Entity.primary");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto-primary"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect-primary"));
        properties.put("hibernate.show_sql",true);
        em.setJpaPropertyMap(properties);


        return em;
    }

    @Primary
    @Bean
    //@ConfigurationProperties(prefix ="spring.datasource")
    public DataSource KairaDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name-primary")));
        dataSource.setUrl(env.getProperty("spring.datasource.url-primary"));
        dataSource.setUsername(env.getProperty("spring.datasource.username-primary"));
        dataSource.setPassword(env.getProperty("spring.datasource.password-primary"));
        //return DataSourceBuilder.create().build();

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager KairaTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                KairaEntityManager().getObject());
        return transactionManager;
    }
}
