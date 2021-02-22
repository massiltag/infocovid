import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-raised-button',
  templateUrl: './raised-button.component.html',
  styleUrls: ['./raised-button.component.scss']
})
export class RaisedButtonComponent implements OnInit {
  @Input() icon: string;
  @Input() disabled = false;
  @Input() color: string;

  constructor() { }

  ngOnInit(): void {
  }

}
