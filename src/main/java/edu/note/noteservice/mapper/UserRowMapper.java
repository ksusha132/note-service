package edu.note.noteservice.mapper;

import edu.note.noteservice.converter.ResultSetToUserConverter;
import edu.note.noteservice.security.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return ResultSetToUserConverter.convert(resultSet);
    }
}
