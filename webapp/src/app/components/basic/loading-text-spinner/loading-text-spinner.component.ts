import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-loading-text-spinner',
  templateUrl: './loading-text-spinner.component.html',
  styleUrls: ['./loading-text-spinner.component.scss']
})
export class LoadingTextSpinnerComponent implements OnInit {
  @Input()
  input: string;

  constructor() { }

  ngOnInit(): void {
  }

}
