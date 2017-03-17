package kr.pe.jady.wordict.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 12. 8..
 */
@Configuration
public class DataSourceTestConfig {
    @Bean(destroyMethod = "shutdown")
    public DataSource embeddedDataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema/schema.sql")
                .build();
    }

    public static void buildNamingContext() throws NamingException {
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("jdbc/word", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
    }
}
