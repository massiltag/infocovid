import {Component, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {Metrics} from '../../../models/metrics.model';
import {MetricsService} from '../../../services/metrics.service';
import {Prevision_confinement} from '../../../models/prevision_confinement.model';
import {Prevision_immunite} from '../../../models/prevision_immunite.model';
import {Prevision_confinementService} from '../../../services/prevision.service';

@Component({
  selector: 'app-predictions',
  templateUrl: './predictions.component.html',
  styleUrls: ['./predictions.component.scss']
})
export class PredictionsComponent implements OnInit {
  searching: number;
  prevision: any = {};
  prevision_immunite: any = {};
  nombre_cas: number;
  date_confinement: string;  
  temp_number: number;

  // Affichage des r�sultats dans la partie de droite
  result_prevision_confinement: string;
  result_prevision_immunite: string;

  fiveDays: Metrics[];
  today: Metrics;
  twoWeeksAgo: Metrics;

  constructor(private dataService: DataService, private metricsService: MetricsService, private prevision_service: Prevision_confinementService) { }

  ngOnInit(): void {
    this.searching = 0;
    this.prevision = this.prevision_service.prevision_confinement();
    
    // Affichage des r�sultats des pr�visions dans la partie de droite 
    this.prevision_service.prevision_confinement().subscribe(statData => {
        this.prevision = statData;
        //this.searching++;
        console.log("Result this.prevision.confinenement : "+this.prevision.confinement);
        this.nombre_cas = this.prevision.nombre_cas;
        if (this.prevision.confinement) {
            this.result_prevision_confinement = "Risque élevé de confinement, lorsque le nombre de "+this.prevision.type_cas+" est sup a "+this.nombre_cas;
        } else {
            this.result_prevision_confinement = "Actuellement, risque peu élevé de confinement";
        }
    });

    this.prevision_immunite = this.prevision_service.prevision_immunite();
    
    this.prevision_service.prevision_immunite().subscribe(statData => {
        this.prevision_immunite = statData;
        //this.searching++;
        console.log("Result this.prevision_immunite.immunite : "+this.prevision_immunite.nbVaccinAtteint);
        if (this.prevision_immunite.immunite) {
            this.result_prevision_immunite = "L'immunité collective a été atteinte, le nombre  atteindre : "+this.prevision_immunite.nbVaccinAtteint;
        } else {
            this.result_prevision_immunite = "Actuellement, l'immunité collective n'a toujours pas été atteinte.";

            if (this.prevision_immunite.nbVaccinAtteint == -1) {
                this.result_prevision_immunite += " Et ne sera potentiellement pas atteinte dans les 2 prochaines semaines.";
            } else {
                this.result_prevision_immunite += " Et sera potentiellement atteinte dans les 2 prochaines semaines, lorsque le nombre de vaccination aura atteint : "+this.prevision_immunite.nbVaccinAtteint;
            }
        }
    });
   
    this.metricsService.getMetricsForDate(new Date(Date.now() - 86400000))
        .subscribe(m => { // 1 DAY
          this.searching++;
          this.today = m;
        });

    this.metricsService.getMetricsForDate(new Date(Date.now() - (2 * 24 * 60 * 60 * 1000)))
        .subscribe(m => {
          this.searching++;
          this.twoWeeksAgo = m;
        });

    this.metricsService.getMetricsForRange(new Date(Date.now() - (15 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
        .subscribe(t => { // 5 DAYS
          this.searching++;
          this.fiveDays = t;
          if (this.fiveDays[0].recap.conf_j1 === 0) { 
            this.fiveDays.pop(); 
          }
          // Test recup date de confinement selon nombre_cas
          this.temp_number = Number(this.fiveDays[0].recap.hosp);
          
          if(Number(this.temp_number) > this.nombre_cas) {
            console.log("Temp_number : "+this.temp_number+" , Nombre_cas : "+this.nombre_cas);
            this.date_confinement = this.fiveDays[0].recap.date;
            console.log("Date de confinement : "+this.date_confinement);
          }
          // Fin test
        });
  }

  cleanForView(input: number): string {
    return (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
  }

  getUnity(input: number): string {
    return (input > 1000) ? 'k' : '';
  }
}