package com.example.repository;

import com.example.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void save(Student student) {
        String sql = "INSERT INTO students (name, score) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getScore());
    }
    
    public List<Student> findAll() {
        String sql = "SELECT name, score FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
    
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM students");
    }
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                    "name VARCHAR(50) PRIMARY KEY, " +
                    "score INT NOT NULL)";
        jdbcTemplate.execute(sql);
    }
    
    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(rs.getString("name"), rs.getInt("score"));
        }
    }
}