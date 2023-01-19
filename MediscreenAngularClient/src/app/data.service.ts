import {HttpClient}       from "@angular/common/http";
import {environment}      from "../environments/environment";
import {Injectable}       from '@angular/core';
import {map, Observable}  from "rxjs";
import {Patient}          from "./Model/Patient";
import {Note}             from "./Model/Note";

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

  //==========================
  //=        getters         =
  //==========================

  patients !: Array<Patient>;

  //==========================
  //=      Constructor       =
  //==========================
  constructor(private http: HttpClient)
  {

  }


  //==========================
  //=   Diagnostic Methods   =
  //==========================

  getPatients(): Observable<Array<Patient>>
  {
    return this.http.get<Array<Patient>>(environment.restUrl + '/mediscreen/patient')
      .pipe(
        map(data => {
                              const patients = new Array<Patient>();
                              data.forEach((patient:Patient )=> patients.push(Patient.fromHttp(patient)));
                              return patients;
                            }
        )
      );
  }


  //==========================
  //=    Patient Methods     =
  //==========================

  getDiagnosedPatientById(patientid: number): Observable<Patient>
  {
    return this.http.get<Patient>(environment.restUrl + '/mediscreen/diagnotic/' + patientid)
                    .pipe(
                            map(patient => { return Patient.fromHttp(patient); }  )
                         );
  }

  updatePatient(updatedPatient: Patient): Observable<Patient>
  {
    return this.http.put<Patient>(environment.restUrl + '/mediscreen/patient', updatedPatient)
                .pipe(map(patient => { return Patient.fromHttp(patient); }));
  }


  addPatient(newPatient: Patient): Observable<Patient>
  {
    return this.http.post<Patient>(environment.restUrl + '/mediscreen/patient', newPatient)
               .pipe(map(patient => { return Patient.fromHttp(patient); }));
  }


  //==========================
  //=     Note Methods       =
  //==========================

  addNote(formNote: Note, patient: Patient): Observable<Patient>
  {
    formNote.patientId = patient.id;
    return this.http.post<Patient>(environment.restUrl + '/mediscreen/note', formNote)
                    .pipe(map(patient => { return Patient.fromHttp(patient); }));
  }


  updateNote(formNote: Note): Observable<Patient>
  {
    return this.http.put<Patient>(environment.restUrl + '/mediscreen/note', formNote)
                    .pipe(map(patient => { return Patient.fromHttp(patient); }));
  }


  deletePatientNote(note: Note) : Observable<Patient>
  {
    return this.http.delete<Patient>(environment.restUrl + '/mediscreen/note/' + note.id)
                    .pipe(map(patient => { return Patient.fromHttp(patient); }));
  }

}
