package com.mediscreen.patient.model;

import java.time.LocalDate;

public record Note(String id, LocalDate date, String note, Integer patientId)
{
}