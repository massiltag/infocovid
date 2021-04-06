import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ApiLinksEnum} from '../enums/api-links.enum';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {Prevision_confinement} from '../models/prevision_confinement.model';
import {Prevision_immunite} from '../models/prevision_immunite.model';

@Injectable({
  providedIn: 'root'
})
export class Prevision_confinementService {

  constructor(private http: HttpClient) { }

  prevision_confinement(): Observable<Prevision_confinement> {
    const url = ApiLinksEnum.PREVISION_CONFINEMENT;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams()
   
    return this.http.get<Prevision_confinement>(url, {headers, params});
  }

  prevision_immunite(): Observable<Prevision_immunite> {
    const url = ApiLinksEnum.PREVISION_IMMUNITE;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams();

    return this.http.get<Prevision_immunite>(url, {headers, params});
  }

}
