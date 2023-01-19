package com.mediscreen.controller;

import com.mediscreen.model.Patient;
import com.mediscreen.proxy.DiagnosticProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mediscreen/diagnotic")
public class DiagnosticController
{
    //=========================
    //=      Attributes       =
    //=========================

    private final DiagnosticProxy diagnosticProxy;


    //=========================
    //=     Constructors      =
    //=========================

    public DiagnosticController(DiagnosticProxy diagnosticProxy)
    {
        this.diagnosticProxy = diagnosticProxy;
    }


    //=========================
    //=  Controller Methods   =
    //=========================

    @GetMapping(value = "/{patientId}")
    public Patient getDiagnosedPatientById(@PathVariable int patientId)
    {
        return diagnosticProxy.getDiagnosedPatientById(patientId);
    }
}