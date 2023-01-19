import {Component, Input, OnInit} from '@angular/core';
import {Patient} from "../../Model/Patient";
import {DataService} from "../../data.service";
import {Router} from "@angular/router";
import {Note} from "../../Model/Note";

@Component
(
  {
    selector: 'app-patient-detail',
    templateUrl: './patient-detail.component.html',
    styleUrls: ['./patient-detail.component.css']
  }
)
export class PatientDetailComponent implements OnInit
{
  //==========================
  //=      Attributes        =
  //==========================
  @Input()
  patient     !: Patient;

  //==========================
  //=      Constructor       =
  //==========================
  constructor(
                private dataService: DataService,
                private router:      Router
             )
  { }


  //==========================
  //=   Life Cycle Methods   =
  //==========================
  ngOnInit(): void
  {

  }


  //==========================
  //=    Business Methods    =
  //==========================

  editPatient()
  {
    this.router.navigate([],
                            { queryParams: {
                                                    patientid:  this.patient.id,
                                                    action:     'editPatient'
                                                 }
                                  }
                        )
  }

  editNote(noteId :string)
  {
    this.router.navigate([],
                        { queryParams: {
                                                patientid:  this.patient.id,
                                                noteid:     noteId,
                                                action:     'editNote'
                                             }
                              }
                        );
  }


  addNote() : void
  {
    this.router.navigate(
                        [],
                            { queryParams: {
                                                    action: 'addNote'
                                                 }
                                  }
                        );
  }



  deleteNote(note: Note)
  {
    this.dataService.deletePatientNote(note)
                    .subscribe(
                            patient => {
                                              this.patient.removeNote(note);
                                              this.patient  = patient
                                              Patient.replaceInPatientArray(this.dataService.patients, patient);
                                              this.router.navigate([], { queryParams: { action: 'view', patientid: +this.patient.id } });
                                            }
                              );
  }
}
