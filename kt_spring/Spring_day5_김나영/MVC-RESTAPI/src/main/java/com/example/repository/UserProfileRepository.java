package com.example.repository;

import com.example.model.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserProfileRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserProfile save(UserProfile profile) {
        // null check for profile object
        if (profile == null) {
            throw new RuntimeException("UserProfile 객체가 null입니다!");
        }
        
        if (profile.getId() == null) {
            // null checks for required fields
            if (profile.getUserId() == null) {
                throw new RuntimeException("사용자 ID는 필수입니다!");
            }
            
            String sql = "INSERT INTO user_profiles(user_id, nickname, bio) VALUES(?, ?, ?)";
            KeyHolder kh = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, profile.getUserId());
                ps.setString(2, profile.getNickname());
                ps.setString(3, profile.getBio());
                return ps;
            }, kh);
            Number key = kh.getKey();
            if (key != null)
                profile.setId(key.longValue());
        } else {
            // null checks for update
            if (profile.getUserId() == null) {
                throw new RuntimeException("사용자 ID는 필수입니다!");
            }
            
            jdbcTemplate.update(
              "UPDATE user_profiles SET user_id=?, nickname=?, bio=? WHERE id=?",
              profile.getUserId(), profile.getNickname(), profile.getBio(), profile.getId());
        }
        return profile;
    }
}