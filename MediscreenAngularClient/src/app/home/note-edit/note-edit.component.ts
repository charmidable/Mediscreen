import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router}   from "@angular/router";
import {DataService}              from "../../data.service";
import {Patient}                  from "../../Model/Patient";
import {Note}                     from "../../Model/Note";


@Component
(
  {
    selector: 'app-note-edit',
    templateUrl: './note-edit.component.html',
    styleUrls: ['./note-edit.component.css']
  }
)
export class NoteEditComponent implements OnInit
{

  //==========================
  //=      Attributes        =
  //==========================

  @Input()
  patient       !: Patient;
  action        !: string;

  formNote      !: Note;
  selectedNote  !: Note;
  editedNoteId  !: string;
  noteIsValid   =  false;



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
    this.activatedRoute.queryParams.subscribe(
                                          params => {
                                                            this.action       = params['action'];
                                                            this.editedNoteId = params['noteid'];
                                                         }
                                             );

    if(this.editedNoteId)
    {
      this.selectedNote = this.patient.notes.find( note => note.id === this.editedNoteId) as Note;
    }

    this.formNote = Object.assign({}, this.selectedNote);

    this.checkIfNoteIsValid();
  }


  //==========================
  //=    Business Methods    =
  //==========================

  isEditedNoteId(noteId :string) : boolean
  {
    return this.editedNoteId === noteId;
  }


  onSubmit()
  {
      if(this.editedNoteId == null)
      {
        this.dataService.addNote(this.formNote, this.patient)
                        .subscribe(
                                patient => {
                                                  Patient.replaceInPatientArray(this.dataService.patients, patient);
                                                  this.router.navigate([], { queryParams: { action: 'view', patientid: +this.patient.id } });
                                                }
                                  );
      }
      else
      {
        this.dataService.updateNote(this.formNote)
                        .subscribe(
                                    patient => {
                                                      Patient.replaceInPatientArray(this.dataService.patients, patient);
                                                      this.router.navigate([], { queryParams: { action: 'view', patientid: +this.patient.id } });
                                                    }
                                  );
      }
  }


  checkIfNoteIsValid() : boolean
  {
    if(this.formNote.note != null && this.formNote.note.trim().length === 0)
    {
      this.formNote.note = "";
      return false;
    }
    return true;
  }
}
