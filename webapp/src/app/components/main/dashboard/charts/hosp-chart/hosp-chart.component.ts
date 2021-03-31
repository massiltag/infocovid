import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../../models/metrics.model';
import * as moment from 'moment';

@Component({
  selector: 'app-hosp-chart',
  templateUrl: './hosp-chart.component.html',
  styleUrls: ['./hosp-chart.component.scss']
})
export class HospChartComponent implements OnInit {
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
  yAxisLabel = 'Hospitalisations';
  timeline = true;

  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
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
    const hospSeries = []; // Total cumulé des morts
    metrics.forEach(m => {
      hospSeries.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.hosp
      });
    });

    return [
      {
        name: 'Hospitalisations',
        series: hospSeries
      }
    ];
  }

}
