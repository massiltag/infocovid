import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {Metrics} from '../../../models/metrics.model';
import {MetricsService} from '../../../services/metrics.service';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.scss']
})
export class PredictionsComponent implements OnInit {
  confinement: string;
  immunite_collectif: string;
  predictions_confinement: string;

  searching: number;
  
  today: Metrics;
  twoWeeksAgo: Metrics;
  fiveDays: Metrics[];

  constructor(private dataService: DataService, private metricsService: MetricsService) { }

  ngOnInit(): void {
    this.searching = 0;

    this.metricsService.getMetricsForDate(new Date(Date.now() - 86400000))
        .subscribe(m => { // 1 DAY
          this.searching++;
          this.today = m;

          if (m.recap.conf_j1 > 47000) {
                this.confinement = "sup";
          }
        });

    this.metricsService.getMetricsForDate(new Date(Date.now() - (14 * 24 * 60 * 60 * 1000)))
        .subscribe(m => {
          this.searching++;
          this.twoWeeksAgo = m;
        });

    this.metricsService.getMetricsForRange(new Date(Date.now() - (31 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
        .subscribe(t => { // 5 DAYS
          this.searching++;
          this.fiveDays = t;
          if (this.fiveDays[-1].recap.conf_j1 === 0) { this.fiveDays.pop(); }
        });
  }

  cleanForView(input: number): string {
    return (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
  }//return (input > 1000) ? (input / 1000).toFixed(1) + '' : input + '';

  getUnity(input: number): string {
    return (input > 1000) ? 'k' : '';
  }
}
