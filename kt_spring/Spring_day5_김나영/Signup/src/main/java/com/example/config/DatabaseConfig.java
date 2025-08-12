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
// import org.springframework.context.annotation.Profile;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    // 데이터소스 설정
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // transaction_example DB를 새로 사용하겠다!
        dataSource.setUrl("jdbc:h2:~/transaction_example;AUTO_SERVER=true;MODE=MySQL"); // 새로운 호환 DB
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    // H2 웹 콘솔 서버 활성화
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
        // users 테이블 생성
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            System.out.println("[DB 정상 동작] users 테이블 확인");
        } catch (Exception e) {
            System.out.println("users 테이블을 생성합니다...");
            String createUsersSql = "CREATE TABLE users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL)";
            jdbcTemplate.execute(createUsersSql);
        }

        // user_profiles 테이블 생성
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_profiles", Integer.class);
            System.out.println("[DB 정상 동작] user_profiles 테이블 확인");
        } catch (Exception e) {
            System.out.println("user_profiles 테이블을 생성합니다...");
            String createUserProfilesSql = "CREATE TABLE user_profiles (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id BIGINT NOT NULL, " +
                    "nickname VARCHAR(50) NOT NULL, " +
                    "bio TEXT, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))";
            jdbcTemplate.execute(createUserProfilesSql);
        }

        // welcome_messages 테이블 생성
        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM welcome_messages", Integer.class);
            System.out.println("[DB 정상 동작] welcome_messages 테이블 확인");
        } catch (Exception e) {
            System.out.println("welcome_messages 테이블을 생성합니다...");
            String createWelcomeMessagesSql = "CREATE TABLE welcome_messages (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id BIGINT NOT NULL, " +
                    "message TEXT NOT NULL, " +
                    "created_at TIMESTAMP NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))";
            jdbcTemplate.execute(createWelcomeMessagesSql);
        }
    }
}