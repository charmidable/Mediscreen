package com.mediscreen.diagnostic.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@FeignClient(name="mediscreen-note", url="http://localhost:8082/note")
public interface NoteProxy
{
    @GetMapping(value = "/existingtermlistbypatientid")
    int countTermInNotesByPatientId(
                                      @RequestParam(value = "patientid") Integer     patientId,
                                      @RequestParam(value = "termlist")  Set<String> termlist
                                   );
}

