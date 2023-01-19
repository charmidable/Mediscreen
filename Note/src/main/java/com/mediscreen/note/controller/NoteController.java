package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/note")
public class NoteController
{
    //=========================
    //=      Attributes       =
    //=========================

    private final NoteService service;


    //=========================
    //=     Constructors      =
    //=========================

    public NoteController(NoteService service)
    {
        this.service = service;
    }


    //=========================
    //=  Controller Methods   =
    //=========================

    @GetMapping(value = "/patient/{patientId}")
    public List<Note> getNotesByPatientId(@PathVariable Long patientId)
    {
        return service.getNoteByPatientId(patientId);
    }


    @GetMapping(value = "/existingtermlistbypatientid")
    public int countTermInNotesByPatientId(@RequestParam(value = "patientid")Long patientId, @RequestParam(value = "termlist")Set<String> termlist)
    {
        return service.countTermInNotesByPatientId(patientId, termlist);
    }


    @PostMapping
    public boolean createNote(@RequestBody Note note)
    {
        return service.createNote(note);
    }


    @PutMapping
    public boolean updateNote(@Valid @RequestBody Note note)
    {
        return service.updateNote(note);
    }


    @DeleteMapping(value = "/{noteId}")
    public Integer deleteNoteById(@PathVariable String noteId)
    {
        return service.deleteNoteById(noteId);
    }
}