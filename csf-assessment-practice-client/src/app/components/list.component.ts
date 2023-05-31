import { Component, OnInit, Output, inject } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { BundleMongoResponse } from '../models';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  router = inject(Router)
  allBundlesResponse$!: Observable<BundleMongoResponse[]>
  uploadSvc = inject(UploadService)


  ngOnInit(): void {
    // this.bundleId$ = this.uploadSvc.onRequest

    this.allBundlesResponse$ = this.uploadSvc.getAllBundles()


  


  }


}
