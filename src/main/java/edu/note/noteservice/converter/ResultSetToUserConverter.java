package edu.note.noteservice.converter;

import edu.note.noteservice.security.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToUserConverter {
    public static User convert(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setCode(resultSet.getString("code"));
        return user;
    }
}
