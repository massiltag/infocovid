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
  confinement_Risque: number = 0;
  tx_progress_immunite_collectif: number = 0;
  predictions_confinement: string;
  nb_litDeRea_dispo: number = 5000;
  croissance_rea: number = 0;
  nb_hab_France: number = 65000000;

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
        });


    this.metricsService.getMetricsForDate(new Date(Date.now() - (2 * 24 * 60 * 60 * 1000)))
        .subscribe(m => {
          this.searching++;
          this.twoWeeksAgo = m;
          this.tx_progress_immunite_collectif = (this.twoWeeksAgo.vaccineStats.n_cum_dose1*100)/this.nb_hab_France;
        });

    this.metricsService.getMetricsForRange(new Date(Date.now() - (14 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
        .subscribe(t => { // 5 DAYS
          this.searching++;
          this.fiveDays = t;
          if (this.fiveDays[0].recap.conf_j1 === 0) { 
            console.log("yes");
            this.fiveDays.pop(); 
          }

          if (this.fiveDays[0].recap.r > 2) {
            this.confinement_Risque += 1;
          } 
          if (this.fiveDays[0].recap.rea > this.nb_litDeRea_dispo) {
            this.confinement_Risque += 5; 
          }

          console.log("Confinement risque : "+this.confinement_Risque);

          if (this.fiveDays[0].recap.rea - this.fiveDays[-1].recap.rea > 10) { 
            this.croissance_rea += 1;
          }
          console.log("Croissance de réa dans les 2 dernières semaines : "+this.croissance_rea);
        });
  }

  cleanForView(input: number): string {
    return (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
  }//return (input > 1000) ? (input / 1000).toFixed(1) + '' : input + '';

  getUnity(input: number): string {
    return (input > 1000) ? 'k' : '';
  }
}
