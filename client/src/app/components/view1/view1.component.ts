import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FileuploadService } from 'src/app/services/fileupload.service';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component {


  fileData!:File;

  form!: FormGroup;

  constructor(private fb: FormBuilder, private fileUploadSvc:FileuploadService) { }

  ngOnInit() {
    this.createForm();
  }

  private createForm(){
    this.form = this.fb.group({
      name: this.fb.control<string>('',[Validators.required]),
      title: this.fb.control<string>('',[Validators.required]),
      comments: this.fb.control<string>(''),
      file: this.fb.control<File>(new File([], ''),[Validators.required])
    });
  }

  formValid(){
    return this.form.invalid;
  }

  upload(){
    const formData = this.form.value;
    this.fileUploadSvc.upload(formData)
  }

}
