import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
import {CanActivate, Router} from '@angular/router';
import {RoutePathEnum} from '../../enums/route-path.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private authService: AuthService) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    }
    // If not authenticated
    this.router.navigate([RoutePathEnum.HOME]);
    return false;
  }


}
