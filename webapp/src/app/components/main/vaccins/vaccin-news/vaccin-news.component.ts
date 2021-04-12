import {Component, Input, OnInit} from '@angular/core';
import {News} from '../../../../models/news.model';

@Component({
  selector: 'app-vaccin-news',
  templateUrl: './vaccin-news.component.html',
  styleUrls: ['./vaccin-news.component.scss']
})
export class VaccinNewsComponent implements OnInit {
  @Input() article: News;

  constructor() { }

  ngOnInit(): void {
  }

}
