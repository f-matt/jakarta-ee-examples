import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IndexService {

  URL : string = "/api/index";

  constructor(private httpClient : HttpClient) { }

  index() : Observable<string> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'text/html, application/xhtml+xml, */*',
        'Content-Type': 'text/plain; charset=utf-8' 
      }),
      responseType: 'text' as 'json'};
    return this.httpClient.get<string>(`${this.URL}`, httpOptions);
  }

}
