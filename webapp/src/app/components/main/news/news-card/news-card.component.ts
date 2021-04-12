import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.scss']
})
export class NewsCardComponent implements OnInit {
  @Input() title: string;
  @Input() subtitle: string;
  @Input() imgLink: string;
  @Input() content: string;
  @Input() url: string;

  constructor() { }

  ngOnInit(): void {
  }

}
