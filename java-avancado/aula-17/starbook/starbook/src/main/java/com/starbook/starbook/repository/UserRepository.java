package com.starbook.starbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.starbook.starbook.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    default Long saveUser(User user, JdbcClient jdbcClient) {
        var insertQuery = """
                INSERT INTO users(username, password, email, role) 
                VALUES(?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(insertQuery)
                .param(1, user.getUsername())
                .param(2, user.getPassword())
                .param(3, user.getEmail())
                .param(4, user.getRole())
                .update(keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Transactional(readOnly = true)
    default Optional<User> findByUsername(String username, JdbcClient jdbcClient) {
        var findQuery = "SELECT id, username, password, role, email FROM users WHERE username=:username";
        return jdbcClient.sql(findQuery).param("username", username).query(User.class).optional();
    }
}
