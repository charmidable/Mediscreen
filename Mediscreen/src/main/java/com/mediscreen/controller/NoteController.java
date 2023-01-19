package com.mediscreen.controller;

import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.proxy.DiagnosticProxy;
import com.mediscreen.proxy.NoteProxy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mediscreen/note")
public class NoteController
{
    //=========================
    //=      Attributes       =
    //=========================

    private final NoteProxy         noteProxy;
    private final DiagnosticProxy   diagnosticProxy;


    //=========================
    //=     Constructors      =
    //=========================

    public NoteController(NoteProxy noteProxy, DiagnosticProxy diagnosticProxy)
    {
        this.noteProxy = noteProxy;
        this.diagnosticProxy = diagnosticProxy;
    }


    //=========================
    //=  Controller Methods   =
    //=========================

    @PostMapping
    public Patient create(@RequestBody Note note)
    {
        noteProxy.createNote(note);

        return diagnosticProxy.getDiagnosedPatientById(note.patientId());
    }


    @PutMapping
    public Patient update(@Valid @RequestBody Note note)
    {
        noteProxy.updateNote(note);
        return diagnosticProxy.getDiagnosedPatientById(note.patientId());
    }


    @DeleteMapping(value = "/{id}")
    public Patient deleteNoteById(@PathVariable String id)
    {
        Integer patientId = noteProxy.deleteNoteById(id);
        return diagnosticProxy.getDiagnosedPatientById(patientId);
    }
}