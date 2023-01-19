package com.mediscreen.note.repository;

import com.mediscreen.note.model.Note;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


@Repository
public interface NoteRepository extends MongoRepository<Note, String>
{
    List<Note> findByPatientIdOrderByDate(long patientId);

    boolean existsByPatientIdAndNoteContains(long patientId, String term);
}