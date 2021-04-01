import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {News} from '../../../models/news.model';
import {VaccineStats} from '../../../models/vaccine-stats.model';
import {MetricsService} from '../../../services/metrics.service';

@Component({
  selector: 'app-vaccins',
  templateUrl: './vaccins.component.html',
  styleUrls: ['./vaccins.component.scss']
})
export class VaccinsComponent implements OnInit {

  public news: News[];

  public vstats: VaccineStats;

  public searching: number;

  public searchingNews: boolean;

  constructor(private dataService: DataService, private metricsService: MetricsService) { }

  ngOnInit(): void {
    this.getNews();
  }

  getNews(): void {
    this.searching = 0;
    this.searchingNews = true;
    this.dataService.getVaccineNews().subscribe(n => {
      this.searching++;
      this.searchingNews = false;
      this.news = n;
    });
    this.metricsService.getMetricsForRange(new Date(Date.now() - (5 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
        .subscribe(m => {
          this.searching++;
          this.vstats = m[0].vaccineStats;
        });

  }

  cleanForView(input: number): string {
    return input > 1000000
        ? (input / 1000000).toFixed(1) + ''
        : (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
  }

  getUnity(input: number): string {
    return input > 1000000 ? 'm' : (input > 1000 ? 'k' : '');
  }

}
