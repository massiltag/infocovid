import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../services/auth/auth.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  requesting: boolean;

  constructor(public authService: AuthService,
              private snackbar: MatSnackBar) {
    this.requesting = false;
  }

  ngOnInit(): void {
  }

  login({login, password}): void {
    this.requesting = true;
    const finalLogin: string = login;
    this.authService.login(finalLogin, password,
        response => { this.snack('Bienvenue, ' + response.username); this.requesting = false; },
        () => { this.snack('Identifiants invalides'); this.requesting = false; }
        );
  }

  // Util
  snack(message: string): void {
    this.snackbar.open(message, 'OK', {
      duration: 5000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }
}
