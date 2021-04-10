import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-progress-spinner',
  templateUrl: './progress-spinner.component.html',
  styleUrls: ['./progress-spinner.component.scss']
})
export class ProgressSpinnerComponent implements OnInit {
  @Input() diameter = 110;

  constructor() { }

  ngOnInit(): void {
  }

}
