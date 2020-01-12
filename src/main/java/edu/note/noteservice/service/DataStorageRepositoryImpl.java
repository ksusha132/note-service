package edu.note.noteservice.service;

import edu.note.noteservice.mapper.NoteRowMapper;
import edu.note.noteservice.note.ComplianceNote;
import edu.note.noteservice.note.Note;
import edu.note.noteservice.note.ThankNote;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class DataStorageRepositoryImpl implements DataStorageRepository {

    private final JdbcTemplate template;
    private AtomicInteger id = new AtomicInteger(1);

    public DataStorageRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public void saveCompliance(ComplianceNote note) {
        template.update(
                "insert into notes (id, comment, cause, place, whothank, type) values(?,?,?,?,?,?)",
                (note.getId() + id.incrementAndGet()), note.getComment(), note.getCause(), note.getPlace(), null, note.getType());
    }

    @Override
    public void saveThank(ThankNote note) {
        template.update(
                "insert into notes (id, comment, cause, place, whothank, type) values(?,?,?,?,?,?)",
                (note.getId() + id.incrementAndGet()), null, null, null, note.getWhoThankTo(), note.getType());
    }

    @Override
    public Note getById(Long id) {
        return template.queryForObject("select * from notes where id = ?", new Object[]{id}, new NoteRowMapper());
    }
}
