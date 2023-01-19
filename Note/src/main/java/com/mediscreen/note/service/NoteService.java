package com.mediscreen.note.service;

import com.mediscreen.note.exception.NoteDoesNotExistException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.Set;


@Service
public class NoteService
{
    //=========================
    //=      Attributes       =
    //=========================

    private final NoteRepository repository;


    //===========================
    //=      Constructors       =
    //===========================

    public NoteService(NoteRepository repository)
    {
        this.repository = repository;
    }


    //===========================
    //=     Service Methods     =
    //===========================

    public List<Note> getNoteByPatientId(long patientId)
    {
        return StreamSupport.stream(repository.findByPatientIdOrderByDate(patientId).spliterator(), true)
                            .sorted()
                            .toList();
    }


    public int countTermInNotesByPatientId(long patientId, Set<String> terms)
    {
        return (int)terms.stream()
                         .filter(term -> repository.existsByPatientIdAndNoteContains(patientId, term))
                         .count();
    }


    public boolean createNote(Note note)
    {
        note.setDate(LocalDate.now());
        return repository.insert(note) != null;
    }


    public boolean updateNote(Note note)
    {
        getNoteById(note.getId());
        return repository.save(note) != null;
    }


    public Integer deleteNoteById(String id)
    {
        Integer patientId = getNoteById(id).getPatientId();
        repository.deleteById(id);
        return patientId;
    }


    //===========================
    //=      Tool Methods       =
    //===========================

    private Note getNoteById(String id)
    {
        return repository.findById(id)
                         .orElseThrow( () -> new NoteDoesNotExistException("Note id " + id + " does not exist") );
    }
}