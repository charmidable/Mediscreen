import {Note} from "./Note";

export class Patient
{
  id                !:  number;
  firstname         !:  string;
  lastname          !:  string;
  address           !:  string;
  phone             !:  string;
  birthdate         !:  string;
  sex               !:  string;
  notes             =   new Array<Note>();
  diabetesRiskLevel !:  string;

  static fromHttp(httpPatient: Patient) : Patient
  {
    const patient = new Patient();
    patient.id        = httpPatient.id;
    patient.sex       = httpPatient.sex;
    patient.phone     = httpPatient.phone;
    patient.address   = httpPatient.address;
    patient.lastname  = httpPatient.lastname;
    patient.firstname = httpPatient.firstname;
    patient.birthdate = httpPatient.birthdate;

    if(httpPatient.diabetesRiskLevel != null)
    {
      patient.diabetesRiskLevel = httpPatient.diabetesRiskLevel;
    }

    if(httpPatient.notes != null)
    {
      httpPatient.notes.forEach(note => patient.notes.push(Note.fromHttp(note)));
    }

    return patient;
  }

  // getBirthdateNoteAsDate()
  // {
  //   return new Date(this.birthdate);
  // }

  removeNote(note: Note)
  {
    const index = this.notes.indexOf(note, 0);
    if (index > -1) this.notes.splice(index, 1);
  }

  // removeNoteById(noteId: string)
  // {
  //   const note = this.notes.find(note => note.id === noteId) as Note;
  //   this.notes.splice(this.notes.indexOf(note), 1);
  // }

  static replaceInPatientArray(patientArray: Array<Patient>, newPatient: Patient)
  {
    const patientToReplace = patientArray.find(patient => patient.id === newPatient.id) as Patient;
    patientArray.splice(patientArray.indexOf(patientToReplace), 1);
    patientArray.push(newPatient);
  }
}
