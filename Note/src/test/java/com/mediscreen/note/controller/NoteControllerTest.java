package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest
{
    final Note note = new Note("1", LocalDate.now(), "symptom dibetes", 10 );
    final Note note2 = new Note("2", LocalDate.now(), "symptom dibetes", 10 );
    final List<Note>  notes = Arrays.asList(note, note2);

    @InjectMocks
    NoteController noteController;
    @Mock
    NoteService noteService;

    @Test
    void getNotesByPatientId()
    {
        when(noteService.getNoteByPatientId(10l)).thenReturn(notes);
        noteController.getNotesByPatientId(10l);
        verify(noteService, times(1)).getNoteByPatientId(10l);
    }

    @Test
    void createNote()
    {
        when(noteService.createNote(note)).thenReturn(true);
        noteController.createNote(note);
        verify(noteService, times(1)).createNote(note);
    }

    @Test
    void updateNote()
    {
        when(noteService.updateNote(any())).thenReturn(true);
        noteController.updateNote(note);
        verify(noteService, times(1)).updateNote(note);
    }

}