import {Injectable}     from '@angular/core';
import {formatDate}     from "@angular/common";
import {Observable, of} from "rxjs";

import {Patient}        from "./Model/Patient";
import {Note}           from "./Model/Note";


@Injectable
(
  {
    providedIn: 'root'
  }
)
export class DataService
{
  //==========================
  //=       Attributes       =
  //==========================
  private patients !: Array<Patient>;
  private notes    !: Array<Note>;

  //==========================
  //=        getters         =
  //==========================

  getPatients(): Observable<Array<Patient>>
  {
    return of(this.patients);
  }

  //==========================
  //=      Constructor       =
  //==========================
  constructor()
  {
    this.patients               = new Array<Patient>();
    this.notes                  = new Array<Note>();
    // NOTES
    const note1                 = new Note;
    note1.id                    = "1";
    note1.patientId             = 1;
    note1.date                  = formatDate(new Date("2021-02-04"), 'yyy-MM-dd', 'en-GB' );
    note1.note                  = "Patient states that they are 'feeling terrific' Weight at or below recommended level";

    const note2                 = new Note;
    note2.id                    = "2";
    note2.patientId             = 2;
    note2.date                  = formatDate(new Date("2021-05-10"), 'yyy-MM-dd', 'en-GB' );
    note2.note                  = "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late";

    const note3                 = new Note;
    note3.id                    = "3";
    note3.patientId             = 2;
    note3.date                  = formatDate(new Date("2021-08-01"), 'yyy-MM-dd', 'en-GB' );
    note3.note                  = "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic";

    const note4                 = new Note;
    note4.id                    = "4";
    note4.patientId             = 3;
    note4.date                  = formatDate(new Date("2021-10-27"), 'yyy-MM-dd', 'en-GB' );
    note4.note                  = "Patient states that they are short term Smoker";

    const note5                 = new Note;
    note5.id                    = "5";
    note5.patientId             = 3;
    note5.date                  = formatDate(new Date("2021-10-27"), 'yyy-MM-dd', 'en-GB' );
    note5.note                  = "Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high";

    const note6                 = new Note;
    note6.id                    = "6";
    note6.patientId             = 4;
    note6.date                  = formatDate(new Date("2021-11-03"), 'yyy-MM-dd', 'en-GB' );
    note6.note                  = "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication";

    const note7                 = new Note;
    note7.id                    = "7";
    note7.patientId             = 4;
    note7.date                  = formatDate(new Date("2021-11-15"), 'yyy-MM-dd', 'en-GB' );
    note7.note                  = "Patient states that they are experiencing back pain when seated for a long time";

    const note8                 = new Note;
    note8.id                    = "8";
    note8.patientId             = 4;
    note8.date                  = formatDate(new Date("2021-12-04"), 'yyy-MM-dd', 'en-GB' );
    note8.note                  = "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level";

    const note9                 = new Note;
    note9.id                    = "9";
    note9.patientId             = 4;
    note9.date                  = formatDate(new Date("2021-11-20"), 'yyy-MM-dd', 'en-GB' );
    note9.note                  = "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction";

    this.notes.push(note1, note2, note3, note4, note5, note6, note7, note8, note9);


    // PATIENTS
    const patient1              = new Patient();
    patient1.id                 = 1;
    patient1.firstname          = "Lucas";
    patient1.lastname           = "Ferguson";
    patient1.address            = "2 Warren Street";
    patient1.phone              = "387-866-1399";
    patient1.birthdate          = formatDate(new Date("1968-06-22"), 'yyy-MM-dd', 'en-GB' );
    patient1.sex                = "male";
    patient1.diabetesRiskLevel  = "None";
    patient1.notes.push(note1);

    const patient2              = new Patient();
    patient2.id                 = 2;
    patient2.firstname          = "Pippa";
    patient2.lastname           = "Rees";
    patient2.address            = "745 West Valley Farms Drive";
    patient2.phone              = "628-423-0993";
    patient2.birthdate          = formatDate(new Date("1952-09-27"), 'yyy-MM-dd', 'en-GB' );
    patient2.sex                = "female";
    patient2.diabetesRiskLevel  = "None";
    patient2.notes.push(note2, note3);

    const patient3              = new Patient();
    patient3.id                 = 3;
    patient3.firstname          = "Edward";
    patient3.lastname           = "Arnold";
    patient3.address            = "599 East Garden Ave";
    patient3.phone              = "123-727-2779";
    patient3.birthdate          = formatDate(new Date("1952-11-11"), 'yyy-MM-dd', 'en-GB' );
    patient3.sex                = "male";
    patient3.diabetesRiskLevel  = "None";
    patient3.notes.push(note4, note5);

    const patient4              = new Patient();
    patient4.id                 = 4;
    patient4.firstname          = "Anthony";
    patient4.lastname           = "Sharp";
    patient4.address            = "894 Hall Street";
    patient4.phone              = "451-761-8383";
    patient4.birthdate          = formatDate(new Date("1946-11-26"), 'yyy-MM-dd', 'en-GB' );
    patient4.sex                = "male";
    patient4.diabetesRiskLevel  = "Borderline";
    patient4.notes.push(note6, note7, note8, note9);

    const patient5              = new Patient();
    patient5.id                 = 5;
    patient5.firstname          = "Wendy";
    patient5.lastname           = "Ince";
    patient5.address            = "4 Southampton Road";
    patient5.phone              = "802-911-9975";
    patient5.birthdate          = formatDate(new Date("1958-06-29"), 'yyy-MM-dd', 'en-GB' );
    patient5.sex                = "female";
    patient5.diabetesRiskLevel  = "Borderline";

    const patient6              = new Patient();
    patient6.id                 = 6;
    patient6.firstname          = "Tracey";
    patient6.lastname           = "Ross";
    patient6.address            = "40 Sulphur Springs Dr";
    patient6.phone              = "131-396-5049";
    patient6.birthdate          = formatDate(new Date("1949-12-07"), 'yyy-MM-dd', 'en-GB' );
    patient6.sex                = "female";
    patient6.diabetesRiskLevel  = "Borderline";

    const patient7              = new Patient();
    patient7.id                 = 7;
    patient7.firstname          = "Claire";
    patient7.lastname           = "Wilson";
    patient7.address            = "12 Cobblestone St";
    patient7.phone              = "300-452-1091";
    patient7.birthdate          = formatDate(new Date("1966-12-31"), 'yyy-MM-dd', 'en-GB' );
    patient7.sex                = "female";
    patient7.diabetesRiskLevel  = "In Danger";

    const patient8              = new Patient();
    patient8.id                 = 8;
    patient8.firstname          = "Max";
    patient8.lastname           = "Buckland";
    patient8.address            = "193 Vale St";
    patient8.phone              = "833-534-0864";
    patient8.birthdate          = formatDate(new Date("1945-06-24"), 'yyy-MM-dd', 'en-GB' );
    patient8.sex                = "male";
    patient8.diabetesRiskLevel  = "In Danger";

    const patient9              = new Patient();
    patient9.id                 = 9;
    patient9.firstname          = "Natalie";
    patient9.lastname           = "Clark";
    patient9.address            = "12 Beechwood Road";
    patient9.phone              = "241-467-9197";
    patient9.birthdate          = formatDate(new Date("1964-06-18"), 'yyy-MM-dd', 'en-GB' );
    patient9.sex                = "female";
    patient9.diabetesRiskLevel  = "In Danger";

    const patient10 = new Patient();
    patient10.id                = 10;
    patient10.firstname         = "Piers";
    patient10.lastname          = "Bailey";
    patient10.address           = "1202 Bumble Dr";
    patient10.phone             = "747-815-0557";
    patient10.birthdate         = formatDate(new Date("1959-06-28"), 'yyy-MM-dd', 'en-GB' );
    patient10.sex               = "male";
    patient10.diabetesRiskLevel  = "Early onset";

    this.patients.push(patient1, patient2, patient3, patient4, patient5, patient6, patient7, patient8, patient9, patient10);
  }


  //==========================
  //=    Patient Methods     =
  //==========================

  getNewUserId() : number
  {
    let id = 0;

    for(const user of this.patients)
    {
      if(user.id > id)
      {
        id = user.id;
      }
    }
    return ++id;
  }


  updatePatient(formPatient: Patient): Observable<Patient>
  {
    const originalPatient = this.patients.find( user => user.id === formPatient.id ) as Patient;

    originalPatient.birthdate = formPatient.birthdate;
    originalPatient.firstname = formPatient.firstname;
    originalPatient.lastname  = formPatient.lastname;
    originalPatient.address   = formPatient.address;
    originalPatient.phone     = formPatient.phone;
    originalPatient.sex       = formPatient.sex;

    return of(originalPatient) as Observable<Patient>;
  }


  addPatient(formPatient: Patient): Observable<Patient>
  {
    const newPatient = new Patient();

    newPatient.birthdate  = formPatient.birthdate;
    newPatient.firstname  = formPatient.firstname;
    newPatient.lastname   = formPatient.lastname;
    newPatient.address    = formPatient.address;
    newPatient.phone      = formPatient.phone;
    newPatient.sex        = formPatient.sex;
    newPatient.id         = this.getNewUserId();

    this.patients.push(newPatient);
    return of(newPatient);
  }


  //==========================
  //=     Note Methods       =
  //==========================

  getNewNoteId() : string
  {
    let stringId = "0";

    for(const note of this.notes)
    {
      if(note.id > stringId)
      {
        stringId = note.id;
      }
    }
    let id: number = +stringId;

    return String(++id);
  }


  addNote(formNote: Note, patient: Patient): Observable<Note>
  {
    const newNote = new Note();
    newNote.id   = this.getNewNoteId();
    newNote.date = formatDate(new Date(), 'yyy-MM-dd', 'en-GB');
    newNote.note = formNote.note;
    this.notes.push(newNote);
    patient.notes.push(newNote);
    return of(newNote);
  }


  updateNote(formNote: Note): Observable<Note>
  {
    const originalNote = this.notes.find( note => note.id === formNote.id ) as Note;
    originalNote.note = formNote.note;
    return of(originalNote);
  }


  deletePatientNote(note: Note, patient: Patient) : Observable<any>
  {
    const index1 = this.notes.indexOf(note, 0);
    if (index1 > -1) this.notes.splice(index1, 1);

    patient.removeNote(note);
    this.deleteNote(note)

    return of(null);
  }


  private deleteNote(note: Note) : Observable<any>
  {
    const index = this.notes.indexOf(note, 0);
    if (index > -1) this.notes.splice(index, 1);

    return of(null);
  }
}
