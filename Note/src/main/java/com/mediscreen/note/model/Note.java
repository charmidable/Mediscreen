package com.mediscreen.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Comparator;

@Component
@Document(collection = "notes")
public class Note implements Comparable<Note>
{
    //=========================
    //=      Attributes       =
    //=========================

    @Id
    private String id;
    @NotBlank(message = "Note is mandatory")
    private String note;
//    @NotBlank(message = "Patient Id is mandatory")
    @NotNull
    private Integer patientId;
    @NotNull
    private LocalDate date;

    //===========================
    //=      Constructors       =
    //===========================

    public Note()
    {
    }

    public Note(String id, LocalDate date, String note, Integer patientId)
    {
        this.id         = id;
        this.date       = date;
        this.note       = note;
        this.patientId  = patientId;
    }


    //===========================
    //=    Getters & Setters    =
    //===========================

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public Integer getPatientId()
    {
        return patientId;
    }

    public void setPatientId(Integer patientId)
    {
        this.patientId = patientId;
    }


    //===========================
    //=     Object Methods      =
    //===========================

    @Override
    public int compareTo(Note o)
    {
        return Comparator.comparing(Note::getDate)
                         .compare(this, o);
    }
}