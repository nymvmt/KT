package com.example.repository;

import com.example.model.WelcomeMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository
public class WelcomeMessageRepository {
    private final JdbcTemplate jdbcTemplate;

    public WelcomeMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public WelcomeMessage save(WelcomeMessage wm) {
        // null check for welcome message object
        if (wm == null) {
            throw new RuntimeException("WelcomeMessage 객체가 null입니다!");
        }
        
        if (wm.getId() == null) {
            // null checks for required fields
            if (wm.getUserId() == null) {
                throw new RuntimeException("사용자 ID는 필수입니다!");
            }
            if (wm.getMessage() == null || wm.getMessage().trim().isEmpty()) {
                throw new RuntimeException("메시지는 필수입니다!");
            }
            if (wm.getCreatedAt() == null) {
                wm.setCreatedAt(java.time.LocalDateTime.now());
            }
            
            String sql = "INSERT INTO welcome_messages(user_id, message, created_at) VALUES(?, ?, ?)";
            KeyHolder kh = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, wm.getUserId());
                ps.setString(2, wm.getMessage());
                ps.setTimestamp(3, Timestamp.valueOf(wm.getCreatedAt()));
                return ps;
            }, kh);
            Number key = kh.getKey();
            if (key != null)
                wm.setId(key.longValue());
        } else {
            // null checks for update
            if (wm.getUserId() == null) {
                throw new RuntimeException("사용자 ID는 필수입니다!");
            }
            if (wm.getMessage() == null || wm.getMessage().trim().isEmpty()) {
                throw new RuntimeException("메시지는 필수입니다!");
            }
            if (wm.getCreatedAt() == null) {
                wm.setCreatedAt(java.time.LocalDateTime.now());
            }
            
            jdbcTemplate.update("UPDATE welcome_messages SET user_id=?, message=?, created_at=? WHERE id=?",
                    wm.getUserId(), wm.getMessage(), Timestamp.valueOf(wm.getCreatedAt()), wm.getId());
        }
        return wm;
    }
}