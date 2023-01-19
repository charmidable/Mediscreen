package com.mediscreen.patient.proxies;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.mediscreen.patient.model.Note;


@FeignClient(name="mediscreen-note", url="http://localhost:8082/note")
public interface NotesProxy
{
    @GetMapping(value = "/patient/{patientId}")
    List<Note> getNotesByPatientId(@PathVariable Integer patientId);
}