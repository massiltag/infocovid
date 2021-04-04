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
  predictions_confinement: string;
  // rea predicitons
  ecart: number = 0; 
  nb_lit_rea_stable: number = 6000;
  nb_lit_rea_danger: number = 7000;
  nb_lit_rea_prevision: number = 0;
  date_prevision_rea_danger: number = 0;

  //Immunitee collectif
  prevision_immunitee_collectif: string;
  nb_population_france: number = 9000000;
  cum_dose1: number;
  pourcentage_vaccinee: number;

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
    const dchosp = []; // Total cumulé des morts
    const tauxOccup = []; // Nombre de cas total
    const n_dose1 = [];
    metrics.forEach(m => {
      dchosp.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.dchosp
      });
      
      tauxOccup.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.recap.rea
      });

      n_dose1.push({
        name: moment(m.date, 'YYYY-MM-DD').format('DD MMM'),
        value: m.vaccineStats.n_cum_dose1
      });
    });

    // Rea
    this.ecart = tauxOccup[tauxOccup.length-2].value - tauxOccup[tauxOccup.length-3].value;
    console.log("Ecart : "+this.ecart);

    if (tauxOccup[tauxOccup.length-2].value < this.nb_lit_rea_stable && this.ecart < 200) {
        this.nb_lit_rea_prevision = tauxOccup[tauxOccup.length-2].value;
        do {
            this.nb_lit_rea_prevision += this.ecart*1.3;
            this.date_prevision_rea_danger += 1;
        } while (this.nb_lit_rea_prevision < this.nb_lit_rea_danger);

        this.predictions_confinement= "Risque de confinement dans "+this.date_prevision_rea_danger+" jour(s) environ.";

    } else if (tauxOccup[tauxOccup.length-2].value > this.nb_lit_rea_danger || this.ecart > 200) {
        this.predictions_confinement= "Confinement immediat !";
    } else {
        this.predictions_confinement= "Situation stable";
    }

    // immunitee collectif
    this.cum_dose1 = n_dose1[n_dose1.length-3].value;
    this.pourcentage_vaccinee = Math.floor((this.cum_dose1*100)/this.nb_population_france);
    console.log(this.pourcentage_vaccinee);
    if (this.cum_dose1 > 2*this.nb_population_france/3) {
        this.prevision_immunitee_collectif = "Immunitee collective atteinte, Environ "+this.pourcentage_vaccinee+"% de la population francaise vaccinee";
    } else {
        this.prevision_immunitee_collectif = "Non atteinte, Environ "+this.pourcentage_vaccinee+"% de la population francaise actuellement vaccinee";
    }

    return [
      {
        name: 'Deces',
        series: dchosp
      },
      {
        name: 'Tx occup',
        series: tauxOccup
      }
    ];
  }
}