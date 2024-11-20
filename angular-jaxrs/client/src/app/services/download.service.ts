import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  URL : string = "/api/zip";

  constructor(private httpClient : HttpClient) { }

  download() : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'application/zip, */*',
        'Content-Type': 'application/zip' 
      }),
      responseType: 'blob' as 'json'};
    return this.httpClient.get<string>(`${this.URL}`, httpOptions);
  }

}
