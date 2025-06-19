package com.example.spring_project1.dao;

import com.example.spring_project1.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper =  new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("username"),
                    rs.getString("password"),rs.getString("email"));
        }
    };

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    public void create(User user) {
         jdbcTemplate.update("INSERT INTO users(id,username, email,password) VALUES (?,?, ?,?)",
                user.getId(),user.getName(), user.getEmail(),user.getPassword());
    }
}
