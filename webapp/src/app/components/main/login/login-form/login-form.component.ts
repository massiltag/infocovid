import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  @Input() public spinnerEnabled: boolean;
  @Output() public loginEvent = new EventEmitter();

  public loginForm: FormGroup;

  constructor() {
    this.loginForm = new FormGroup({
      login: new FormControl(),
      password: new FormControl()
    });
  }

  ngOnInit(): void {
  }

  onLoginClick(event: any): void {
    const controls = this.loginForm.controls;
    const output = {
      login: controls.login.value,
      password: controls.password.value
    };
    this.loginEvent.emit(output);
  }

}
