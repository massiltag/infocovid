import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../../models/metrics.model';
import * as moment from 'moment';

@Component({
  selector: 'app-to-chart',
  templateUrl: './to-chart.component.html',
  styleUrls: ['./to-chart.component.scss']
})
export class ToChartComponent implements OnInit {
  multi: any[];

  @Input() view: any[] = [500, 300];
  @Input() data: any[];

  // options
  legend = true;
  showLabels = true;
  animations = true;
  xAxis = true;
  yAxis = true;
  showYAxisLabel = true;
  showXAxisLabel = true;
  xAxisLabel = 'Jour';
  yAxisLabel = 'Tension en réanimation';
  timeline = true;

  colorScheme = {
    domain: ['#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  constructor() {
  }

  ngOnInit(): void {
    Object.assign(this, {multi: this.cleanForChart(this.data)});
  }

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
    const toSeries = []; // Total cumulé des morts
    metrics.forEach(m => {
      toSeries.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.to
      });
    });

    return [
      {
        name: 'Tension Réa',
        series: toSeries
      }
    ];
  }

}
