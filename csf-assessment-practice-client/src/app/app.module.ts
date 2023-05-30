import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormComponent } from './components/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { DisplayComponent } from './components/display.component';
import { ListComponent } from './components/list.component';
import { HttpClientModule } from '@angular/common/http'
import { UploadService } from './upload.service';

const appRoutes: Routes = [
  { path: '', component: ListComponent, title: 'Photo Storage' },
  { path: 'form', component: FormComponent, title: 'Form'},
  { path: 'display', component: DisplayComponent, title: 'Display'},
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    FormComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true})
  ],
  providers: [UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
