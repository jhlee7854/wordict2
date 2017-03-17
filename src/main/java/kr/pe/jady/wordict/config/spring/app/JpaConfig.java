package kr.pe.jady.wordict.config.spring.app;

import org.hibernate.jpa.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * JPA Configuration
 * @author jhlee7854
 */
@Configuration
@EnableJpaRepositories(basePackages = {"kr.pe.jady.wordict"})
@Import(DataSourceConfig.class)
public class JpaConfig {
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_ACTION, "drop-and-create");
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_DROP_TARGET, "build/getnerated-script/jpa/schema.sql");
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_CREATE_TARGET, "build/getnerated-script/jpa/schema.sql");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaProperties(properties);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPersistenceUnitName("word");
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("kr.pe.jady.wordict.model");
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
