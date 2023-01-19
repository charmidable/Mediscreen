package com.mediscreen.proxy;

import com.mediscreen.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="mediscreen-diagnotic", url="http://localhost:8083/diagnostic")
public interface DiagnosticProxy
{
    @GetMapping(value = "{patientId}")
    Patient getDiagnosedPatientById(@PathVariable int patientId);
}