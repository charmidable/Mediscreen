package com.mediscreen.diagnostic.service;

import com.mediscreen.diagnostic.model.DiabetesRiskLevel;
import com.mediscreen.diagnostic.model.Patient;
import com.mediscreen.diagnostic.proxy.NoteProxy;
import com.mediscreen.diagnostic.proxy.PatientProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

@Service
public class DiabetesDiagnosticService
{
    //=========================
    //=      Attributes       =
    //=========================

    @Value("#{'${diabeteDiagnosticTerms}'.split(';')}")
    private       HashSet<String> diabeteDiagnoticTerms;

    private final PatientProxy    patientProxy;
    private final NoteProxy       noteProxy;


    //===========================
    //=      Constructors       =
    //===========================

    public DiabetesDiagnosticService(NoteProxy noteProxy, PatientProxy patientProxy)
    {
        this.noteProxy    = noteProxy;
        this.patientProxy = patientProxy;
    }


    //===========================
    //=     Service Methods     =
    //===========================


    public Patient getDiagnosedPatientById(Integer patientId)
    {
        var patientCF        = CompletableFuture.supplyAsync( () -> patientProxy.getPatientById(patientId) );

        var nbrOfSymptomsCF  = CompletableFuture.supplyAsync( () -> noteProxy.countTermInNotesByPatientId(patientId, diabeteDiagnoticTerms) );

        return patientCF.thenCombine( nbrOfSymptomsCF, (patient, nbrOfSymptoms) ->  new Patient(
                                                                                                patient.id(),
                                                                                                patient.firstname(),
                                                                                                patient.lastname(),
                                                                                                patient.birthdate(),
                                                                                                patient.sex(),
                                                                                                patient.notes(),
                                                                                                getDiabetesRiskLevel(patient, nbrOfSymptoms),
                                                                                                patient.address(),
                                                                                                patient.phone()
                                                                                              )
                                    ).join();
    }



    private String getDiabetesRiskLevel(Patient patient, int numberOfSymptoms)
    {
        final boolean isUnder30Years =  LocalDate.now().getYear() - patient.birthdate().getYear() <= 30;

        final boolean isMale         = patient.sex().equals("male");

        switch (numberOfSymptoms)
        {
            case 0, 1:                        return DiabetesRiskLevel.NONE.toString();

            case 2:     if (isUnder30Years)   return DiabetesRiskLevel.NONE.toString();
                        else                  return DiabetesRiskLevel.BORDERLINE.toString();

            case 3:     if (isUnder30Years)
                        {
                            if (isMale)       return DiabetesRiskLevel.IN_DANGER.toString();
                            else              return DiabetesRiskLevel.BORDERLINE.toString();
                        }
                        else                  return DiabetesRiskLevel.BORDERLINE.toString();

            case 4:     if (isUnder30Years)   return DiabetesRiskLevel.IN_DANGER.toString();
                        else                  return DiabetesRiskLevel.BORDERLINE.toString();

            case 5:     if (isUnder30Years)
                        {
                            if (isMale)       return DiabetesRiskLevel.EARLY_ONSET.toString();
                            else              return DiabetesRiskLevel.IN_DANGER.toString();
                        }
                        else                  return DiabetesRiskLevel.BORDERLINE.toString();

            case 6:  if (isUnder30Years)
                        {
                            if (isMale)       return DiabetesRiskLevel.EARLY_ONSET.toString();
                            else              return DiabetesRiskLevel.IN_DANGER.toString();
                        }
                        else                  return DiabetesRiskLevel.IN_DANGER.toString();


            default:    if (isUnder30Years)   return DiabetesRiskLevel.EARLY_ONSET.toString();
                        else                  return DiabetesRiskLevel.IN_DANGER.toString();
        }
    }
}

//    public Patient getDiagnosedPatientById(Integer patientId)
//    {
//        Patient patient      = patientProxy.getPatientById(patientId);
//
//        int numberOfSymptoms = noteProxy.countTermInNotesByPatientId(patientId, diabeteDiagnoticTerms);
//
//        return new Patient(
//                            patient.id(),
//                            patient.firstname(),
//                            patient.lastname(),
//                            patient.birthdate(),
//                            patient.sex(),
//                            patient.notes(),
//                            getDiabetesRiskLevel(patient, numberOfSymptoms),
//                            patient.address(),
//                            patient.phone()
//                          );
//    }




//    public Patient getDiagnosedPatientById(Integer patientId)
//    {
//        var patientCF        = CompletableFuture.supplyAsync( () -> patientProxy.getPatientById(patientId) );
//        var nbrOfSymptomsCF  = CompletableFuture.supplyAsync( () -> noteProxy.countTermInNotesByPatientId(patientId, diabeteDiagnoticTerms) );
//
//        Patient patient = patientCF.join();             // méthode bloquante
//        int nbrOfSymptoms = nbrOfSymptomsCF.join();     // méthode bloquante
//
//        return  new Patient(
//                              patient.id(),
//                              patient.firstname(),
//                              patient.lastname(),
//                              patient.birthdate(),
//                              patient.sex(),
//                              patient.notes(),
//                              getDiabetesRiskLevel(patient, nbrOfSymptoms),
//                              patient.address(),
//                              patient.phone()
//                           );
//    }