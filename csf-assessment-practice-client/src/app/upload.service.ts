import { HttpClient } from "@angular/common/http"
import { Injectable, inject } from "@angular/core"
import { Observable } from "rxjs"


@Injectable()
export class UploadService{

    http=inject(HttpClient)

    upload(name: string, title: string, comments: string, file: File): Observable<any>{
        const formData = new FormData()
         // @RequestPart String title
        formData.set('name', name)
        formData.set('title', title)
        // @RequestPart MultipartFile myFile
        formData.set('comments', comments)
        formData.set('myFile', file)
       

        return this.http.post<any>('http://localhost:8080/upload', formData)
    }

}