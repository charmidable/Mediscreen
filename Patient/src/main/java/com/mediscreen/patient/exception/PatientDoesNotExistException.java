package com.mediscreen.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientDoesNotExistException extends RuntimeException
{
    public PatientDoesNotExistException(String message)
    {
        super(message);
    }
}