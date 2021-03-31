import {Component, OnInit} from '@angular/core';
import {LiveData} from '../../../models/live-data.model';
import {DataService} from '../../../services/data.service';
import {Metrics} from '../../../models/metrics.model';
import {MetricsService} from '../../../services/metrics.service';

@Component({
  selector: 'app-vaccin',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  liveData: LiveData;

  searching: boolean;

  today: Metrics;

  twoWeeksAgo: Metrics;

  fiveDays: Metrics[];

  constructor(private dataService: DataService, private metricsService: MetricsService) { }

  ngOnInit(): void {
    this.searching = true;
    this.dataService.getLiveData().subscribe(d => {
      this.searching = false;
      this.liveData = d;
    });

    this.metricsService.getMetricsForDate(new Date(Date.now() - 86400000))
        .subscribe(m => { // 1 DAY
          this.today = m;
        });

    this.metricsService.getMetricsForDate(new Date(Date.now() - (14 * 24 * 60 * 60 * 1000)))
        .subscribe(m => { // 1 DAY
          this.twoWeeksAgo = m;
        });

    this.metricsService.getMetricsForRange(new Date(Date.now() - (6 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
        .subscribe(t => { // 5 DAYS
          this.fiveDays = t;
        });

  }

  cleanForView(input: number): string {
    return (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
  }

  getUnity(input: number): string {
    return (input > 1000) ? 'k' : '';
  }
}
