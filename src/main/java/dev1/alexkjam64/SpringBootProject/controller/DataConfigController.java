package dev1.alexkjam64.SpringBootProject.controller;


import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataConfigController {

    // @Bean
    public DataSource dataConfig(){
        return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .build();
    }

    @Bean
    public HikariDataSource dataSource(){
        System.out.println("HELLO NAME SERVICE!");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver"); 
        // jdbc:postgresql://@pellefant.db.elephantsql.com:5432/cwkqmdql?user=cwkqmdql&password=SsVqwdLxQObgaJAYu68O-8gTY1VmS9LX
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/NintendoAccount?user=postgres&password=u2r94970203");
        return new HikariDataSource(config);    
    }
}
