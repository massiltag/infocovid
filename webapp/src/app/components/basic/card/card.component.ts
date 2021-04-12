import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  @Input()
  public headerTitle: string;

  @Input()
  public headerIcon: string;

  @Input()
  public iconLink = '';

  @Input()
  public contentCss: string;

  constructor() { }

  ngOnInit(): void {
  }

}
