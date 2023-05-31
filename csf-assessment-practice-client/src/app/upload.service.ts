import { HttpClient, HttpParams } from "@angular/common/http"
import { Injectable, inject } from "@angular/core"
import { Observable, Subject, filter, tap } from "rxjs"
import { BundleIdResponse, BundleMongoResponse } from "./models"


@Injectable()
export class UploadService{

    http=inject(HttpClient)
    onRequest = new Subject<BundleIdResponse>()

    upload(name: string, title: string, comments: string, file: File): Observable<BundleIdResponse>{
        const formData = new FormData()
         // @RequestPart String title
        formData.set('name', name)
        formData.set('title', title)
        // @RequestPart MultipartFile myFile
        formData.set('comments', comments)
        formData.set('myFile', file)
       

        return this.http.post<BundleIdResponse>('http://localhost:8080/upload', formData)
        .pipe(
            filter((response) => response !== null), // Filter out null responses
            tap(response => this.onRequest.next(response))
        )
    }


    //create the method to get weather data from server
    getBundle(bundleId: string): Observable<BundleMongoResponse>{
        const params = new HttpParams()
            .set("bundleId", bundleId)
   
        console.info('>>>>>>>the bundleId in getBundle is >>>>>>>>', bundleId)
        return this.http.get<BundleMongoResponse>('http://localhost:8080/bundle/'+`${bundleId}`, {params})
        // return this.http.get<BundleMongoResponse>('http://localhost:8080/bundle', {params})
    }

    getAllBundles(): Observable<BundleMongoResponse[]>{
   
        console.info('>>>>>>>In getAllBundles() >>>>>>>>')
        // return this.http.get<BundleMongoResponse>('http://localhost:8080/bundle/'+`${bundleId}`, {params})
        return this.http.get<BundleMongoResponse[]>('http://localhost:8080/bundles')
    }

}