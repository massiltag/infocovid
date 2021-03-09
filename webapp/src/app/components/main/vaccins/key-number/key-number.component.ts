import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-key-number',
  templateUrl: './key-number.component.html',
  styleUrls: ['./key-number.component.scss']
})
export class KeyNumberComponent implements OnInit {
  @Input() number: any;
  @Input() unity = '';
  @Input() description: string;

  constructor() { }

  ngOnInit(): void {
  }

}
