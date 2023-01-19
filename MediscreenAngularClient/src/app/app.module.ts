import { NgModule }               from '@angular/core';
import { FormsModule }            from "@angular/forms";
import { AppComponent }           from './app.component';
import { BrowserModule }          from '@angular/platform-browser';
import { HttpClientModule }       from "@angular/common/http";
import { RouterModule, Routes }   from "@angular/router";


import { HomeComponent }          from './home/home.component';
import { NoteEditComponent }      from './home/note-edit/note-edit.component';
import { PatientEditComponent }   from './home/patient-edit/patient-edit.component';
import { PatientDetailComponent } from './home/patient-detail/patient-detail.component';



const routes: Routes =  [
                          {path : '', component : HomeComponent}
                        ];

@NgModule
(
  {
    declarations: [
                    AppComponent,
                    HomeComponent,
                    PatientDetailComponent,
                    PatientEditComponent,
                    NoteEditComponent
                  ],

    imports:      [
                    FormsModule,
                    BrowserModule,
                    HttpClientModule,
                    RouterModule.forRoot(routes)
                  ],

    providers:    [

                  ],

    bootstrap:    [
                    AppComponent
                  ]
  }
)
export class AppModule { }
