import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../models/metrics.model';
import {Prevision_confinement} from '../../../../models/prevision_confinement.model';
import * as moment from 'moment';

@Component({
  selector: 'app-line-chart',
  templateUrl: './predictions-chart.component.html',
  styleUrls: ['./predictions-chart.component.scss']
})

export class PredictionsChartComponent implements OnInit {
  multi: any[];
  @Input() data: any[];
  @Input() prevision: any = {};
  @Input() view: any[];
  @Input() nombre_cas: number;
  prevision_date_confinement:string;

  // option
  legend = true;
  showLabels = true;
  animations = true;
  xAxis = true;
  yAxis = true;
  showYAxisLabel = true;
  showXAxisLabel = true;
  xAxisLabel = 'Jour';
  yAxisLabel = 'Nombre';
  timeline = true;
  roundDomains = true;

  colorScheme = {
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

  date_confinement_notfind: boolean = true;
  cleanForChart(metrics: Metrics[]): any[] {
    const dchosp = []; // Total cumul� des morts
    const hosp = []; // Nombre de cas total

    metrics.forEach(m => {
      dchosp.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.dchosp
      });
      
      hosp.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.hosp
      });

      if(m.recap.hosp >= this.nombre_cas && this.date_confinement_notfind) {
            console.log("Date de confinement possible : "+moment(m.date, 'YYYY-MM-DD').format('DD MMM'));
            this.prevision_date_confinement = "Prevision : Risque de confinement a partir de "+moment(m.date, 'YYYY-MM-DD').format('DD MMM');
            this.date_confinement_notfind = false;
      } 
    });

    if (this.date_confinement_notfind) {
            this.prevision_date_confinement = "Prévision : Actuellement pas de risque de confinement";
    }

    return [
      {
        name: 'Total Décès',
        series: dchosp
      },
      {
        name: 'Total Hospitalisations',
        series: hosp
      }
    ];
  }
}