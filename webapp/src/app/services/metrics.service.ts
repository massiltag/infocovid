import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Metrics} from '../models/metrics.model';
import {ApiLinksEnum} from '../enums/api-links.enum';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {

  constructor(private http: HttpClient, public datePipe: DatePipe) { }

  getMetricsForDate(date: Date): Observable<Metrics> {
    const url = ApiLinksEnum.METRICS;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams()
        .set('date', this.datePipe.transform(date, 'yyyy-MM-dd'));

    return this.http.get<Metrics>(url, {headers, params});
  }

  getMetricsForRange(from: Date, to: Date): Observable<Metrics[]> {
    const url = ApiLinksEnum.METRICS_RANGE;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams()
        .set('from', this.datePipe.transform(from, 'yyyy-MM-dd'))
        .set('to', this.datePipe.transform(to, 'yyyy-MM-dd'));

    return this.http.get<Metrics[]>(url, {headers, params});
  }

}
