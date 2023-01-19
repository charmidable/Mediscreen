package com.mediscreen.proxy;


import com.mediscreen.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name="mediscreen-note", url="http://localhost:8082/note")
public interface NoteProxy
{
    @PostMapping
    boolean createNote(@RequestBody Note note);

    @PutMapping
    boolean updateNote(@RequestBody Note note);

    @DeleteMapping(value = "/{id}")
    Integer deleteNoteById(@PathVariable String id);
}