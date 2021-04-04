import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../models/metrics.model';
import * as moment from 'moment';

@Component({
  selector: 'app-line-chart',
  templateUrl: './predictions-chart.component.html',
  styleUrls: ['./predictions-chart.component.scss']
})

export class PredictionsChartComponent implements OnInit {
  multi: any[];
  @Input() data: any[];
  @Input() view: any[];

  // option
  legend = true;
  showLabels = true;
  animations = true;
  xAxis = true;
  yAxis = true;
  showYAxisLabel = true;
  showXAxisLabel = true;
  xAxisLabel = 'Jour';
  yAxisLabel = 'Nb cas';
  timeline = true;


  colorScheme = {
    //domain: ['#5AA454', '#8966cf', '#d43151']
     domain: ['#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };

  // line, area
  autoScale = true;

  constructor() {
  }

  ngOnInit(): void {
    Object.assign(this, {multi: this.cleanForChart(this.data)});
    console.log(this.multi);
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
    const tauxOccup = []; // Nombre de cas total
    metrics.forEach(m => {
      toSeries.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.dchosp
      });
      tauxOccup.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.rea
      });
    });

    return [
      {
        name: 'Nb cas',
        series: toSeries
      },
      {
        name: 'Tx occup',
        series: tauxOccup
      }
    ];
  }
}