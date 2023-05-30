import { Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit{

  form!: FormGroup
  fb=inject(FormBuilder)
  uploadSvc = inject(UploadService)

  @ViewChild('uploadFile')
  uploadFile!: ElementRef

  ngOnInit(): void{
    this.form = this.fb.group({
      name: this.fb.control<string>('', [Validators.required] ),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>(''),
      file: this.fb.control<File | null>(null, [Validators.required])
    })

  }

  upload(){
    const data = this.form.value
    const f: File = this.uploadFile.nativeElement.files[0]
    console.info('>>> data: ', data)
    console.info('>>> file: ', f)

    //use promise
    firstValueFrom(this.uploadSvc.upload(data['name'],data['title'],data['comments'], f))
      .then(result=>{
        alert('uploaded')
        this.form.reset
      })
      .catch(err =>{
        alert(JSON.stringify(err))
      })
  }






}
