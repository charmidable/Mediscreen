package com.mediscreen.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.patient.exception.PatientDoesNotExistException;
import com.mediscreen.patient.repository.PatientRepository;
import com.mediscreen.patient.proxies.NotesProxy;
import com.mediscreen.patient.model.Patient;


@Service
public class PatientService
{
    //=========================
    //=      Attributes       =
    //=========================

    private final PatientRepository repository;
    private final NotesProxy notesProxy;


    //===========================
    //=      Constructors       =
    //===========================

    public PatientService(PatientRepository repository, NotesProxy notesProxy)
    {
        this.repository = repository;
        this.notesProxy = notesProxy;
    }


    //===========================
    //=     Service Methods     =
    //===========================

    public List<Patient> getAllPatients()
    {
        return repository.findAll()
                         .stream()
                         .sorted()
                         .toList();
    }


    public Patient getPatientById(Integer id)
    {
        Patient patient;

        patient = repository.findById(id)
                            .orElseThrow( () -> new PatientDoesNotExistException("Patient id " + id + " does not exist") );

        patient.setNotes(notesProxy.getNotesByPatientId(id));

        return patient;
    }


    public Patient newPatient(Patient patient)
    {
        return repository.save(patient);
    }


    public boolean updatePatient(Patient updatedPatient)
    {
        getPatientById(updatedPatient.getId());

        repository.save(updatedPatient);

        return true;
    }


    public boolean deletePatientById(Integer id)
    {
        repository.delete(getPatientById(id));

        return true;
    }
}




//    public List<Patient> getPatientByLastName(String lastName)
//    {
//        return repository.findPatientByLastname(lastName)
//                         .stream()
//                         .sorted()
//                         .toList();
//    }
//
//
//    public List<Patient> getPatientByFirstName(String firstName)
//    {
//        return repository.findPatientByFirstname(firstName)
//                         .stream()
//                         .sorted()
//                         .toList();
//    }
//
//
//    public List<Patient> getPatientByFirstNameAndLastName(String firstName, String lastName)
//    {
//        return repository.findPatientByFirstnameAndLastname(firstName, lastName)
//                         .stream()
//                         .sorted()
//                         .toList();
//    }