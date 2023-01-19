import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router}   from "@angular/router";
import {DataService}              from "../../data.service";
import {Patient}                  from "../../Model/Patient";


@Component
(
  {
    selector: 'app-patient-edit',
    templateUrl: './patient-edit.component.html',
    styleUrls: ['./patient-edit.component.css']
  }
)
export class PatientEditComponent implements OnInit
{
  //==========================
  //=      Attributes        =
  //==========================
  @Input()
  patient     !: Patient;
  formPatient !: Patient;
  action      !: string;


  //==========================
  //=      Constructor       =
  //==========================

  constructor(
                private activatedRoute: ActivatedRoute,
                private dataService   : DataService,
                private router        : Router
             )
  { }


  //==========================
  //=   Life Cycle Methods   =
  //==========================

  ngOnInit(): void
  {
    this.formPatient = Object.assign({}, this.patient);

    this.activatedRoute.queryParams.subscribe(params => this.action = params['action']);

    this.checkIfFirstnameIsValid();
    this.checkIfLastnameIsValid();
  }


  //==========================
  //=      Form Methods      =
  //==========================

  onSubmit() : void
  {
      if (this.formPatient.id == null)
      {
        this.dataService.addPatient(this.formPatient)
                        .subscribe(patient => {
                                                      this.dataService.patients.push(patient);
                                                      this.router.navigate([], {queryParams: {action: 'view', patientid: +patient.id}})
                                                   }
                                  )

      }
      else
      {
        this.dataService.updatePatient(this.formPatient)
                        .subscribe(patient => {
                                                    Patient.replaceInPatientArray(this.dataService.patients, patient);
                                                    this.router.navigate([], {queryParams: {action: 'view', patientid: +patient.id}})
                                                   }
                                  )
      }
  }


  checkIfFirstnameIsValid() : boolean
  {
    if(this.formPatient.firstname != null && this.formPatient.firstname.trim().length == 0)
    {
      this.formPatient.firstname = "";
      return false;
    }
    return true;
  }


  checkIfLastnameIsValid(): boolean
  {
    if(this.formPatient.lastname != null  && this.formPatient.lastname.trim().length == 0)
    {
      this.formPatient.lastname = "";
      return false;
    }
    return true;
  }
}
