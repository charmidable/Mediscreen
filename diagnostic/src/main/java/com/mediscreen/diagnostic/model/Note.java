package com.mediscreen.diagnostic.model;

import java.time.LocalDate;

public record Note(String id, LocalDate date, String note, Integer patientId) {}