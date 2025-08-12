package com.example.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    // 데이터소스 설정
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/rest_api_example;AUTO_SERVER=true;MODE=MySQL"); // 새로운 호환 DB
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    // H2 웹 콘솔 서버 활성화 - 이 부분 추가
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws Exception {
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8083");
    }

    // JdbcTemplate 설정
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // 테이블 생성 및 초기 데이터 삽입
        initializeDatabase(jdbcTemplate);

        return jdbcTemplate;
    }

    // 트랜잭션 매니저 설정
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private void initializeDatabase(JdbcTemplate jdbcTemplate) {
        // students 테이블 보장 (기존 유지)
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            System.out.println("[DB 정상 동작] users 테이블 확인");
        } catch (Exception e) {
            // users 테이블 재생성 (요청된 스키마 적용)
            System.out.println("users 테이블을 재생성합니다...");
            jdbcTemplate.execute("CREATE TABLE users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            // 초기 데이터 삽입
            System.out.println("초기 데이터를 삽입합니다...");
            jdbcTemplate.execute("INSERT INTO users (name, email) VALUES ('김철수', 'kim@example.com')");
            jdbcTemplate.execute("INSERT INTO users (name, email) VALUES ('이영희', 'lee@example.com')");
            jdbcTemplate.execute("INSERT INTO users (name, email) VALUES ('박민수', 'park@example.com')");

            // user_profiles
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user_profiles (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id BIGINT NOT NULL, " +
                    "nickname VARCHAR(100), " +
                    "bio VARCHAR(255))");

            // welcome_messages
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS welcome_messages (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id BIGINT NOT NULL, " +
                    "message VARCHAR(255), " +
                    "created_at TIMESTAMP)");
        }

    }
}
