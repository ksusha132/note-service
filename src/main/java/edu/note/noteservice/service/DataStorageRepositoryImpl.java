package edu.note.noteservice.service;

import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataStorageRepositoryImpl implements DataStorageRepository {

    private final JdbcTemplate template;

    public DataStorageRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public void saveCompliance(ComplianceNote note) {
        template.update(
                "insert into notes (id, type, cause, comment, place) values(?,?,?,?,?)",
                note.getId(), note.getType(), note.getCause(), note.getComment(), note.getPlace());
    }

    @Override
    public void saveThank(ThankNote note) {
        template.update(
                "insert into notes (id, type, whothank) values(?,?,?)",
                note.getId(), note.getType(), note.getWhoThankTo());
    }

    @Override
    public Note getById(Long id) {
//        return template.queryForObject(
//                "select * from notes where id = :id",
//                new MapSqlParameterSource("id", id),
//                (rs, rowNum) ->
//                        Optional.of(new Note(
//                                rs.getLong("id")
//                        ))
//        );
        // get all fields than make an object
        return null;
    }
}
