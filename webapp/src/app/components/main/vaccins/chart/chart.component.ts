import {Component, Input, OnInit} from '@angular/core';
import { single } from './data';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
  single: any[];
  multi: any[];

  @Input() view: any[] = [500, 300];

  // options
  showXAxis = true;
  showYAxis = true;
  gradient = true;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Jour';
  showYAxisLabel = true;
  yAxisLabel = 'Nombre de vaccinations';

  colorScheme = {
    domain: ['#2f58d6', '#d43151', '#27bc97', '#8966cf']
  };

  constructor() {
    Object.assign(this, { single });
  }

  ngOnInit(): void {
  }

  onSelect(event): void {
    console.log(event);
  }
}
