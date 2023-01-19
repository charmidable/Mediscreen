package com.mediscreen.diagnostic.proxy;

import com.mediscreen.diagnostic.model.Patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="mediscreen-patient", url="http://localhost:8081/patient")
public interface PatientProxy
{
    @GetMapping(value = "{id}")
    Patient getPatientById(@PathVariable Integer id);
}