package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;

import com.mediscreen.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest
{
    private HashSet<String> diabeteDiagnoticTerms = new HashSet<>(Arrays.stream("Abnormal;Antibodies;Cholesterol;Dizziness;Height;Hemoglobin a1c;Microalbumin;Reaction;Relapse;Smoker;Weight".split(";")).toList());
    private final Note note1 = new Note("1", LocalDate.now(), "diabetes symptoms", 10);
    private final Note note2 = new Note("2", LocalDate.now(), "Abnormal Antibodies Cholesterol Dizziness Height", 10);
    private final List<Note> noteList = List.of(note1, note2);

    @InjectMocks
    NoteService noteService;

    @Mock
    NoteRepository noteRepository;

    @Test
    void getNoteByPatientId()
    {
        when(noteRepository.findByPatientIdOrderByDate(10)).thenReturn(noteList);
        assertThat(noteService.getNoteByPatientId(10)).containsAll(noteList);
    }

    @Test
    void countTermInNotesByPatientId()
    {
        Integer patientId = 100;
        String noteNote = "Abnormal Antibodies Cholesterol Dizziness";
        Note note = new Note();
        note.setNote(noteNote);
        note.setPatientId(patientId);
        assertThat(noteService.getNoteByPatientId(100)).isEmpty();
        assertThat(noteService.createNote(note));
        assertThat(noteService.countTermInNotesByPatientId(100, diabeteDiagnoticTerms)).isEqualTo(0);
    }


    @Test
    void updateNote()
    {
        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        noteService.updateNote(note1);
        verify(noteRepository, times(1)).save(note1);
    }

    @Test
    void deleteNoteById()
    {
        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        noteService.deleteNoteById("1");
        verify(noteRepository, times(1)).deleteById("1");
    }
}