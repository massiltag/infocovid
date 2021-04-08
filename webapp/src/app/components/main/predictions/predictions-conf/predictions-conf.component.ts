import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../models/metrics.model';
import {Prevision_confinement} from '../../../../models/prevision_confinement.model';
import * as moment from 'moment';

@Component({
  selector: 'app-predictions-conf',
  templateUrl: './predictions-conf.component.html',
  styleUrls: ['./predictions-conf.component.scss']
})

export class PredictionsConfComponent implements OnInit {
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
  yAxisLabel = 'Nombre';
  timeline = true;
  roundDomains = true;

  colorScheme = {
     domain: ['#7aa3e5', '#a8385d', '#aae3f5']
  };

  // line, area
  autoScale = true;

  constructor() {
  }

  ngOnInit(): void {
    Object.assign(this, {multi: this.cleanForChart(this.data)});
    //this.prediction_function(this.prevision);
    console.log(this.multi);
  }
  /*
  prediction_function(p_confinement: Prevision_confinement): void {
    this.predictions_confinement = p_confinement.confinement;
    console.log("Prediction : "+p_confinement.nombre_cas);
  }
  */
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
    const conf = [];

    metrics.forEach(m => {
      conf.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.conf
      });
    });
 
    return [
      {
        name: 'Total Cas positif',
        series: conf
      }
    ];
  }
}