package edu.note.noteservice.mapper;

import edu.note.noteservice.converter.ResultSetToComplianceNoteConverter;
import edu.note.noteservice.converter.ResultSetToThankNoteConverter;
import edu.note.noteservice.note.Note;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRowMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int arg1) throws SQLException {

        String noteType = rs.getString("type").trim();
        Note note = null;

        switch (noteType) {
            case "compliance":
                note = ResultSetToComplianceNoteConverter.convert(rs);
                break;
            case "thank":
                note = ResultSetToThankNoteConverter.convert(rs);
                break;
        }
        return note;
    }
}
