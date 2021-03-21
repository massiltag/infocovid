import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {LiveData} from '../models/live-data.model';
import {ApiLinksEnum} from '../enums/api-links.enum';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {News} from '../models/news.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  // GET
  getLiveData(): Observable<LiveData> {
    // const url = ApiLinksEnum.insertParam(ApiLinksEnum.SAMPLE_LINK, input, 1);
    const url = ApiLinksEnum.LIVE_DATA;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams(); // .set(Properties.SAMPLE, this.cookies.get(Properties.SAMPLE));

    return this.http.get<LiveData>(url, {headers, params});
  }

  getNews(): Observable<News[]> {
    // const url = ApiLinksEnum.insertParam(ApiLinksEnum.SAMPLE_LINK, input, 1);
    const url = ApiLinksEnum.NEWS;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams(); // .set(Properties.SAMPLE, this.cookies.get(Properties.SAMPLE));

    return this.http.get<News[]>(url, {headers, params});
  }

}
