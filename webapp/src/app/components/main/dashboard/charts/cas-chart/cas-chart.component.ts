import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../../models/metrics.model';
import * as moment from 'moment';

@Component({
  selector: 'app-cas-chart',
  templateUrl: './cas-chart.component.html',
  styleUrls: ['./cas-chart.component.scss']
})
export class CasChartComponent implements OnInit {
  single: any[];
  multi: any[];

  @Input() view: any[] = [500, 300];
  @Input() data: any[];

  // options
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Jour';
  showYAxisLabel = true;
  yAxisLabel = 'Nombre de cas';

  colorScheme = {
    domain: ['#2f58d6', '#d43151', '#27bc97', '#8966cf', '#3fb4ec', '#e05343']
  };

  constructor() {}

  ngOnInit(): void {
    Object.assign(this, {single: this.cleanForChart(this.data)});
  }

  onSelect(event): void {
    console.log(event);
  }

  cleanForChart(metrics: Metrics[]): any[] {
    const result = [];
    metrics.forEach(m => {
      result.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.conf_j1
      });
    });
    return result;
  }
}
