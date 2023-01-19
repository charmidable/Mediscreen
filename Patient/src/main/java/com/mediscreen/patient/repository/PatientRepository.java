package com.mediscreen.patient.repository;

import com.mediscreen.patient.model.Patient;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface PatientRepository extends CrudRepository<Patient, Integer>
{
    List<Patient> findAll();

    List<Patient> findPatientByLastname(String lastname);

    List<Patient> findPatientByFirstname(String firstname);

    List<Patient> findPatientByBirthdate(LocalDate birthdate);

    List<Patient> findPatientByFirstnameAndLastname(String lastName, String firstName);

    List<Patient> findPatientByFirstnameAndLastnameAndBirthdate(String lastName, String firstName, LocalDate birthDate);
}