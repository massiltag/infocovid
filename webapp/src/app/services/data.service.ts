import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {ApiLinksEnum} from '../enums/api-links.enum';
import {CookieService} from 'ngx-cookie-service';
import {LiveData} from '../models/live-data.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient,
              private cookies: CookieService) { }

  // GET
  getLiveData(): Observable<LiveData> {
    // const url = ApiLinksEnum.insertParam(ApiLinksEnum.SAMPLE_LINK, input, 1);
    const url = ApiLinksEnum.LIVE_DATA;

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const params = new HttpParams(); // .set(Properties.SAMPLE, this.cookies.get(Properties.SAMPLE));

    return this.http.get<LiveData>(url, {headers, params});
  }


}
