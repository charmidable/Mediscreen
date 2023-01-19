package com.mediscreen.patient.model;

import java.time.LocalDate;
import java.util.Comparator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;


@Entity
@Table(name="patient")
public class Patient implements Comparable<Patient>
{
    //=========================
    //=      Attributes       =
    //=========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @NonNull
    private LocalDate birthdate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Transient
    private Iterable<Note> notes;

    private String address;

    private String phone;


    //===========================
    //=      Constructors       =
    //===========================

    public Patient()
    {
    }

    public Patient(Integer id, String firstname, String lastname, @NonNull LocalDate birthdate, @NonNull Sex sex, String address, String phone)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
        this.notes = notes;
        this.address = address;
        this.phone = phone;
    }

    //===========================
    //=    Getters & Setters    =
    //===========================

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstName)
    {
        this.firstname = firstName;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastName)
    {
        this.lastname = lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public LocalDate getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthDate)
    {
        this.birthdate = birthDate;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public Iterable<Note> getNotes()
    {
        return notes;
    }

    public void setNotes(Iterable<Note> notes)
    {
        this.notes = notes;
    }


    //===========================
    //=     Object Methods      =
    //===========================

    @Override
    public int compareTo(Patient o)
    {
       return Comparator.comparing    (Patient::getLastname)
                        .thenComparing(Patient::getFirstname)
                        .thenComparing(Patient::getBirthdate)
                        .thenComparing(Patient::getId)
                        .compare(this, o);
    }
}