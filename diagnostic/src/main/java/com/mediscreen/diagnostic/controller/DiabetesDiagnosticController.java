package com.mediscreen.diagnostic.controller;

import com.mediscreen.diagnostic.model.Patient;
import com.mediscreen.diagnostic.service.DiabetesDiagnosticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnostic")
public class DiabetesDiagnosticController
{
    //=========================
    //=      Attributes       =
    //=========================

    private final DiabetesDiagnosticService service;

    //=========================
    //=     Constructors      =
    //=========================

    public DiabetesDiagnosticController(DiabetesDiagnosticService diagnosticService)
    {
        this.service = diagnosticService;
    }

    //=========================
    //=  Controller Methods   =
    //=========================

    @GetMapping(value = "{patientId}")
    public Patient getDiagnosedPatientById(@PathVariable int patientId)
    {
        return service.getDiagnosedPatientById(patientId);
    }
}