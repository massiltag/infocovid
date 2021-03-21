import {Component, OnInit} from '@angular/core';
import {News} from '../../../models/news.model';
import {DataService} from '../../../services/data.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  searching: boolean;
  news: News[];

  breakpoint: number;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.searching = true;
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 4;
    this.dataService.getNews().subscribe(n => {
      this.news = n;
      this.searching = false;
      console.log(n);
    });
  }

  isoToFrenchDate(s: string): string {
    const d = new Date(s);
    return d.toLocaleDateString('fr');
  }
}
