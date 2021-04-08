import {Component, Input, OnInit} from '@angular/core';
import {Metrics} from '../../../../models/metrics.model';
import {Prevision_immunite} from '../../../../models/prevision_immunite.model';
import * as moment from 'moment';

@Component({
  selector: 'app-predictions-vaccination',
  templateUrl: './predictions-vaccination.component.html',
  styleUrls: ['./predictions-vaccination.component.scss']
})

export class PredictionsVaccinationComponent implements OnInit {
  multi: any[];
  @Input() data: any[];
  @Input() view: any[];
  @Input() prevision_immunite: any = {};
  
  // Chart option
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

  // Line color
  colorScheme = {
     domain: [ '#aae3f5', '#a8385d']
  };

  // line, area
  autoScale = false;

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

  /* Comparer les variables pour recup le nombre de vaccination du dernier jour qui se trouve en bdd
  *  Afin de pouvoir relier la ligne de vaccination réelle avec la ligne de vaccination prédit 
  *  yesterday = la date d'hier
  *  m_day = date en bdd 
  *  Lorsque yesterday = m_day, on recup la valeur et on la stocke dans cum_dose1_prev
  */
  yesterday: string;
  m_day: string;
  // Autres variables 
  i: number;
  buff: number;
  cpt: number = 0;
  cleanForChart(metrics: Metrics[]): any[] {
    const cum_dose1 = [];
    const cum_dose1_prev = [];
    
    metrics.forEach(m => {
      // Ligne de vaccination réelle 
      cum_dose1.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.vaccineStats.n_cum_dose1
      });
      
      this.yesterday = moment(new Date(Date.now()), 'YYYY-MM-DD').subtract(1, "days").format('DD MMM');
      this.m_day = moment(m.date, 'YYYY-MM-DD').format('DD MMM');
      //console.log("YESTERDAY : "+this.yesterday);
      //console.log("M_DAY : "+this.m_day);
   
      if (this.yesterday == this.m_day) {
        //console.log("Is equal");

        // Ligne de vaccination prédit, ajout de la valeur du dernier jour réelle 
        cum_dose1_prev.push({
            //name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
            name: this.m_day,
            value: m.vaccineStats.n_cum_dose1
        });
      }
    });

    const temp: number = this.prevision_immunite.nbVaccinQuotidien.length;
 
    for(this.i = 0; this.i < temp; this.i++) {
        this.buff = this.prevision_immunite.nbVaccinQuotidien[this.i];
        //console.log("TEST 2 : "+this.buff);

        // Ligne de vaccination prédit, ajout des valeurs prédit 
        cum_dose1_prev.push({
            name: moment(new Date(Date.now()), 'YYYY-MM-DD').add(this.cpt, "days").format('DD MMM'),
            value: this.buff
        });
        this.cpt++;
    }
 
    return [
      {
        name: 'Total vaccin dose 1',
        series: cum_dose1
      },
      {
        name: 'Prevision dose 1',
        series: cum_dose1_prev
      }
    ];
  }
}