package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController
{
    //=========================
    //=      Attributes       =
    //=========================

    private final PatientService service;


    //=========================
    //=     Constructors      =
    //=========================

    public PatientController(PatientService service)
    {
        this.service = service;
    }


    //=========================
    //=  Controller Methods   =
    //=========================


    @GetMapping(value = "/{id}")
    public Patient getPatientById(@PathVariable Integer id)
    {
        return service.getPatientById(id);
    }


    @GetMapping
    public List<Patient> getAllPatients()
    {
        return service.getAllPatients();
    }


    @PostMapping
    public Patient createPatient(@Valid @RequestBody Patient patient)
    {
        return service.newPatient(patient);
    }


    @PutMapping
    public boolean updatePatient(@Valid @RequestBody Patient patient)
    {
        return service.updatePatient(patient);
    }


    @DeleteMapping(value = "{id}")
    public boolean deletePatientById(@PathVariable Integer id)
    {
        return service.deletePatientById(id);
    }
}


//    @GetMapping(value = "/byfirstname/{firstname}")
//    public List<Patient> getPatientByFirstname(@PathVariable String firstname)
//    {
//        return service.getPatientByFirstName(firstname);
//    }
//
//
//    @GetMapping(value = "/bylastname/{lastname}")
//    public List<Patient> getPatientByLastname(@PathVariable String lastname)
//    {
//        return service.getPatientByLastName(lastname);
//    }
//
//
//    @GetMapping(value = "/bynames")
//    public List<Patient> getPatientByFirstnameAndLastname(@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname)
//    {
//        return service.getPatientByFirstNameAndLastName(firstname, lastname);
//    }