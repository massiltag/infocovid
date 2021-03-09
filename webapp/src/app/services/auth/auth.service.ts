import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {RoutePathEnum} from '../../enums/route-path.enum';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public authenticated: boolean;
  public username: string;

  constructor(private http: HttpClient,
              private router: Router,
              private cookies: CookieService) {
    /*
    if (this.cookies.check(Properties.TOKEN_COOKIE)) {
      this.authenticated = true;
      this.username = this.cookies.get(Properties.USERNAME_COOKIE);
    } else {
      this.authenticated = false;
    }
     */
  }

  login(login: string, password: string, ifValid: (o: AuthResponse) => any, orElse: (o: AuthResponse) => any): void {
    const url = ''; // ApiLinksEnum.LOGIN;

    const headers = new HttpHeaders(); const params = new HttpParams();
    headers.append('Content-Type', 'application/json');

    this.http.post<any>(url, { login, password }).subscribe(r => {
      this.authenticated = true;
      if (r.access_token !== 'INVALID') {
        ifValid(r);
        this.authenticated = true;
        this.username = r.username;
        // this.cookies.set(Properties.TOKEN_COOKIE, r.access_token);
        // this.cookies.set(Properties.USERNAME_COOKIE, r.username);
        // this.router.navigate([RoutePathEnum.GROUPS]);
      } else {
        orElse(r);
        this.authenticated = false;
        this.username = null;
      }
    });
  }

  isAuthenticated(): boolean {
    // return this.authenticated;
    return true; // uncomment line before for authentication
  }

  logoff(): void {
    this.username = null;
    this.authenticated = false;
    // this.cookies.delete(Properties.TOKEN_COOKIE);
    // this.cookies.delete(Properties.USERNAME_COOKIE);
    this.router.navigate([RoutePathEnum.DASHBOARD]);
  }

  // Use with NgStyle
  displayWhenLogged(): any { return {  display: this.isAuthenticated() ? '' : 'none' }; }
  hideWhenLogged(): any { return { display: this.isAuthenticated() ? 'none' : '' }; }
}

export interface AuthResponse {

  username: string;

  scope: string;

  access_token: string;

  refresh_token: string;

}
