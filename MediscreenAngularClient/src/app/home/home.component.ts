import { Component, OnInit }    from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DataService}            from "../data.service";
import {Patient}                from "../Model/Patient";


@Component
(
  {
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  }
)
export class HomeComponent implements OnInit
{
  //==========================
  //=      Attributes        =
  //==========================

  patients        !: Array<Patient>;
  selectedPatient !: Patient;
  action          !: string;


  //==========================
  //=      Constructor       =
  //==========================

  constructor(
                private dataService:  DataService,
                private route:        ActivatedRoute,
                private router:       Router
             )
  {}


  //==========================
  //=   Life Cycle Methods   =
  //==========================

  ngOnInit(): void
  {
    this.dataService.getPatients().subscribe(
                                              next => {
                                                              this.patients = next;
                                                              this.dataService.patients = this.patients;
                                                           }

                                            );

    this.route.queryParams.subscribe(
                                  params => {
                                                    const patientId = params['patientid'];
                                                    this.action     = params['action'];

                                                    if(patientId)
                                                    {
                                                      this.selectedPatient = this.patients.find( patient => patient.id === +patientId) as Patient;
                                                    }
                                                 }
                                    );
  }


  //==========================
  //=    Business Methods    =
  //==========================

  setPatient(patientId: number) : void
  {
    this.dataService.getDiagnosedPatientById(patientId)
                    .subscribe(
                                patient => {
                                                  this.selectedPatient = patient;
                                                  Patient.replaceInPatientArray(this.patients, patient);
                                                }
                              );

    this.router.navigate(
                  [],
                      { queryParams: {
                                              patientid:  patientId,
                                              action:     'view'
                                           }
                            }
                        )
  }

  addPatient() : void
  {
    this.selectedPatient = new Patient();

    this.router.navigate(
                  [],
                      { queryParams: {
                                              action: 'addPatient'
                                           }
                            }
                        )
  }

  setNote(patientId: number, noteId: string)
  {
    this.router.navigate(
                          [],
                          {
                                  queryParams: {
                                                  patientid:  patientId,
                                                  noteid:     noteId,
                                                  action:     'editNote'
                                               }
                                }
                        )
  }
}
