package edu.note.noteservice.service;

import edu.note.noteservice.mapper.UserRowMapper;
import edu.note.noteservice.security.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserStorageRepositoryImpl implements UserStorageRepository {


    private JdbcTemplate jdbcTemplate;

    public UserStorageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, new UserRowMapper());
    }
}
