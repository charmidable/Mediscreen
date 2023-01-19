package com.mediscreen.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.mediscreen.model.Patient;

import java.time.LocalDate;
import java.util.List;


@FeignClient(name="mediscreen-patient", url="http://localhost:8081/patient")
public interface PatientProxy
{

    @GetMapping
    List<Patient> getAllPatients();


    @PostMapping
    Patient createPatient(Patient patient);


    @PutMapping
    boolean updatePatient(Patient patient);


    @DeleteMapping(value = "{id}")
    boolean deletePatientById(Integer id);
}


//    @GetMapping(value = "/byfirstname/{firstname}")
//    List<Patient> getPatientByFirstname(@RequestParam(value = "firstname")String firstname);
//
//
//    @GetMapping(value = "/bylastname/{lastname}")
//    List<Patient> getPatientByLastname(@RequestParam(value = "lastname")String lastname);
//
//
//    @GetMapping(value = "/bynames")
//    List<Patient> getPatientByFirstnameAndLastname(@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname);

