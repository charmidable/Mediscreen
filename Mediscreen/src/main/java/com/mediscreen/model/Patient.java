package com.mediscreen.model;

import java.time.LocalDate;

public record Patient(Integer id, String firstname, String lastname, LocalDate birthdate, String sex, Iterable<Note> notes, String diabetesRiskLevel, String address, String phone) {}
