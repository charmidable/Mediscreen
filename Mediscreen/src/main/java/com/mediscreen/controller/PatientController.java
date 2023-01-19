package com.mediscreen.controller;

import com.mediscreen.model.Patient;
import com.mediscreen.proxy.DiagnosticProxy;
import com.mediscreen.proxy.PatientProxy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mediscreen/patient")
public class PatientController
{

    //=========================
    //=      Attributes       =
    //=========================

    private final PatientProxy     patientProxy;
    private final DiagnosticProxy  diagnosticProxy;


    //=========================
    //=     Constructors      =
    //=========================

    public PatientController(PatientProxy patientProxy, DiagnosticProxy diagnosticProxy)
    {
        this.patientProxy = patientProxy;
        this.diagnosticProxy = diagnosticProxy;
    }


    //=========================
    //=  Controller Methods   =
    //=========================

    @GetMapping
    public List<Patient> getAllPatients()
    {
        return patientProxy.getAllPatients();
    }


    @PostMapping
    public Patient createPatient(@Valid @RequestBody Patient patient)
    {
        Patient newPatient = patientProxy.createPatient(patient);
        return diagnosticProxy.getDiagnosedPatientById(newPatient.id());
    }


    @PutMapping
    public Patient updatePatient(@Valid @RequestBody Patient patient)
    {
        patientProxy.updatePatient(patient);

        return diagnosticProxy.getDiagnosedPatientById(patient.id());
    }


    @DeleteMapping(value = "/{id}")
    public boolean deletePatientById(@PathVariable Integer id)
    {
        return patientProxy.deletePatientById(id);
    }
}

//    @GetMapping(value = "/byfirstname/{firstname}")
//    public List<Patient> getPatientByFirstname(@PathVariable String firstname)
//    {
//        return patientProxy.getPatientByFirstname(firstname);
//    }
//
//
//    @GetMapping(value = "/bylastname/{lastname}")
//    public List<Patient> getPatientByLastname(@PathVariable String lastname)
//    {
//        return patientProxy.getPatientByLastname(lastname);
//    }
//
//
//    @GetMapping(value = "/bynames")
//    public List<Patient> getPatientByFirstNameAndLastName(@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname)
//    {
//        return patientProxy.getPatientByFirstnameAndLastname(firstname, lastname);
//    }