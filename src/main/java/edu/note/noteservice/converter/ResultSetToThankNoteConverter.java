package edu.note.noteservice.converter;

import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import edu.note.noteservice.statuses.NoteType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToThankNoteConverter {
    public static Note convert(ResultSet rs) throws SQLException {
        ThankNote note = new ThankNote();
        note.setId(rs.getLong("id"));
        note.setType(NoteType.THANK.getNoteType());
        note.setWhoThankTo(rs.getString("whothank"));
        return note;
    }
}
