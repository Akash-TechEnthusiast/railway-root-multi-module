package com.india.railway.configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SqlFileExecutorService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void executeSqlFile(String filePath) throws IOException {

        Resource resource = new ClassPathResource(filePath);

        String sql = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        String[] queries = sql.split(";");

        for (String query : queries) {
            if (!query.trim().isEmpty()) {
                jdbcTemplate.execute(query);
            }
        }
    }
}
