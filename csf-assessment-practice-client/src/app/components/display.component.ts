import { Component, OnInit, inject } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { BundleIdResponse, BundleMongoResponse } from '../models';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {

  uploadSvc = inject(UploadService)

 //obtained from tap .next()
  bundleId$!: Observable<BundleIdResponse>
  bundleMongoResponse$!: Observable<BundleMongoResponse>

  bundleId!: string

  bundleLinks!: string[]


  //use subscription to obtain value of bundleId

  bundleIdSubscription!: Subscription
  bundleLinksSubscription!: Subscription

  ngOnInit(): void {
    this.bundleId$ = this.uploadSvc.onRequest

    //obtain value of bundleId using subscription
    this.bundleIdSubscription = this.bundleId$.subscribe((response) => {
      this.bundleId = response.bundleId;
      console.log('>>>>>>>The bundleId in ngOnInit is>>>>>> :', this.bundleId);
      this.bundleMongoResponse$ = this.uploadSvc.getBundle(this.bundleId)
      this.bundleLinksSubscription = this.bundleMongoResponse$.subscribe((response) => {
        this.bundleLinks = response.urls;
        console.log('The urls are :', this.bundleLinks);
      });
    });

    // this.bundleMongoResponse$ = this.uploadSvc.getBundle(this.bundleId)
    // this.bundleLinksSubscription = this.bundleMongoResponse$.subscribe((response) => {
    //   this.bundleLinks = response.urls;
    //   console.log('The urls are :', this.bundleLinks);
    // });
  }

  ngAfterViewInit(): void{
    // this.bundleMongoResponse$ = this.uploadSvc.getBundle(this.bundleId)
    // this.bundleLinksSubscription = this.bundleMongoResponse$.subscribe((response) => {
    //   this.bundleLinks = response.urls;
    //   console.log('The urls are :', this.bundleLinks);
    // });
  }


  ngOnDestroy(): void {
    this.bundleIdSubscription.unsubscribe();
    this.bundleLinksSubscription.unsubscribe();
  }

}
