import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {RoutePathEnum} from '../../enums/route-path.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public authenticated: boolean;
  public username: string;

  constructor(private http: HttpClient,
              private router: Router,) {
  }

  login(login: string, password: string, ifValid: (o: AuthResponse) => any, orElse: (o: AuthResponse) => any): void {
    // login code (après)
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
