import {Component, Input, OnInit} from '@angular/core';
import {multi} from './data';
import {MetricsService} from '../../../../services/metrics.service';
import {Metrics} from '../../../../models/metrics.model';
import * as moment from 'moment';

@Component({
  selector: 'app-deaths-chart',
  templateUrl: './deaths-chart.component.html',
  styleUrls: ['./deaths-chart.component.scss']
})
export class DeathsChartComponent implements OnInit {
  multi: any[];
  @Input() view: any[] = [500, 300];

  // options
  legend = true;
  showLabels = true;
  animations = true;
  xAxis = true;
  yAxis = true;
  showYAxisLabel = true;
  showXAxisLabel = true;
  xAxisLabel = 'Year';
  yAxisLabel = 'Population';
  timeline = true;

  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  constructor(private metricsService: MetricsService) {
    this.metricsService.getMetricsForRange(new Date(Date.now() - 518400000), new Date(Date.now()))
        .subscribe(t => {
          console.log({ multi });
          console.log({ multi: this.cleanForChart(t) });
          Object.assign(this, { multi: this.cleanForChart(t) });
        });
  }

  ngOnInit(): void {}

  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }

  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }

  cleanForChart(metrics: Metrics[]): any[] {
    const deathsSeries = []; // Total cumulé des morts
    const confSeries = []; // Total cumulé des cas confirmés
    metrics.forEach(m => {
      deathsSeries.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.dchosp + m.recap.esms_dc
      });
      confSeries.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.conf
      });
    });

    return [
      {
        name: 'Cas confirmés',
        series: confSeries
      },
      {
        name: 'Décès',
        series: deathsSeries
      }
    ];
  }

}
