package edu.note.noteservice.converter;

import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.statuses.NoteType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToComplianceNoteConverter {
    public static Note convert(ResultSet rs) throws SQLException {
        ComplianceNote note = new ComplianceNote();
        note.setId(rs.getLong("id"));
        note.setType(NoteType.COMPLIANCE.getNoteType());
        note.setComment(rs.getString("comment"));
        note.setPlace(rs.getString("place"));
        note.setCause(rs.getString("cause"));
        return note;
    }
}
