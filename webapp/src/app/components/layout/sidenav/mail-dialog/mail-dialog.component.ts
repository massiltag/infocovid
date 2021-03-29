import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MailService} from '../../../../services/mail.service';
import {Properties} from '../../../../enums/properties';

@Component({
  selector: 'app-mail-dialog',
  templateUrl: './mail-dialog.component.html',
  styleUrls: ['./mail-dialog.component.scss']
})
export class MailDialogComponent implements OnInit {

  constructor(public mailService: MailService,
              private snack: MatSnackBar) { }

  public mailForm: FormGroup;

  ngOnInit(): void {
    this.mailForm = new FormGroup({
      email: new FormControl(undefined, Validators.required),
    });
  }

  onButtonClick($event): void {
    if (this.isMailValid()) {
      this.mailService.subscribeUser(this.mailForm.controls.email.value).subscribe(r => {
        this.snack.open(r.message,
            'OK',
            {
              duration: 4000,
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
            });
      });
    }
  }


  // CONDITIONS
  isMailValid(): boolean {
    const mail = this.mailForm.controls.email.value;
    if (!mail) { return false; }
    else { return (mail.match(Properties.EMAIL_REGEX)); }
  }
}
