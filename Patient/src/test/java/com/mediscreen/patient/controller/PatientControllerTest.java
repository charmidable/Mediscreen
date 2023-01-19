package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.model.Sex;
import com.mediscreen.patient.proxies.NotesProxy;
import com.mediscreen.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest
{
    private final Patient patient1 = new Patient(1, "fn1", "ln1", LocalDate.now(), Sex.male, "add1", "phone1");
    private final Patient patient2 = new Patient(2, "fn2", "ln2", LocalDate.now(), Sex.female, "add2", "phone2");
    private final List<Patient> patients = List.of(patient1, patient2);

    @InjectMocks
    PatientController patientController;

    @Mock
    PatientService patientService;

    @Mock
    NotesProxy notesProxy;

    @Test
    void getPatientById()
    {
        when(patientService.getPatientById(1)).thenReturn(patient1);
        patientController.getPatientById(1);
        verify(patientService, times(1)).getPatientById(1);
    }

    @Test
    void getAllPatients()
    {
        patientController.getAllPatients();
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void createPatient()
    {
        when(patientService.newPatient(patient1)).thenReturn(patient1);
        patientController.createPatient(patient1);
        verify(patientService, times(1)).newPatient(patient1);
    }

    @Test
    void updatePatient()
    {
        when(patientService.updatePatient(any())).thenReturn(true);
        patientController.updatePatient(patient1);
        verify(patientService, times(1)).updatePatient(patient1);
    }

    @Test
    void deletePatientById()
    {
        patientController.deletePatientById(1);
        verify(patientService, times(1)).deletePatientById(1);
    }
}