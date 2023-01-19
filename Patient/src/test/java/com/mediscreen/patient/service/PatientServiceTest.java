package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.model.Sex;
import com.mediscreen.patient.proxies.NotesProxy;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest
{
    private final Patient patient1 = new Patient(1, "fn1", "ln1", LocalDate.now(), Sex.male, "add1", "phone1");
    private final Patient patient2 = new Patient(2, "fn2", "ln2", LocalDate.now(), Sex.female, "add2", "phone2");
    private final List<Patient> patients = List.of(patient1, patient2);

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;

    @Mock
    NotesProxy notesProxy;

    @Test
    void getAllPatients()
    {
        when(patientRepository.findAll()).thenReturn(patients);
        assertThat(patientService.getAllPatients()).isEqualTo(patients);
    }

    @Test
    void getPatientById()
    {
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
        assertThat(patientService.getPatientById(1)).isEqualTo(patient1);
    }

    @Test
    void newPatient()
    {
        patientService.newPatient(patient1);
        verify(patientRepository, times(1)).save(any());
    }

    @Test
    void updatePatient()
    {
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));
        patientService.updatePatient(patient1);
        verify(patientRepository, times(1)).save(patient1);
    }
}