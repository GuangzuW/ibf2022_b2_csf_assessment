import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileuploadService {

constructor(private http: HttpClient) {}

  upload(formData: FormData) {
    return this.http.post('/upload', formData);
  }
}
