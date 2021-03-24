import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {News} from '../../../models/news.model';

@Component({
  selector: 'app-vaccins',
  templateUrl: './vaccins.component.html',
  styleUrls: ['./vaccins.component.scss']
})
export class VaccinsComponent implements OnInit {

  public news: News[];

  public searching: boolean;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.getNews();
  }

  getNews(): void {
    this.searching = true;
    this.dataService.getVaccineNews().subscribe(n => {
      this.searching = false;
      this.news = n;
    });
  }

}
