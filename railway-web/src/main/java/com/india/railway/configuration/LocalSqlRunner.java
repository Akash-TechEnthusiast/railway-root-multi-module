package com.india.railway.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LocalSqlRunner {

    private final SqlFileExecutorService service;

    @Bean
    CommandLineRunner runLocalSql() {
        return args -> service.executeSqlFile("sql/init_auto_generation.sql");
    }
}
