import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {ApiLinksEnum} from '../enums/api-links.enum';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  constructor(private http: HttpClient) { }

  subscribeUser(email: string): Observable<{ message: string }> {
    const url = ApiLinksEnum.SUBSCRIBE;

    const headers = new HttpHeaders();
    const params = new HttpParams().set('email', email);

    return this.http.post<{ message: string }>(url, null, {headers, params});
  }

}
