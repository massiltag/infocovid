import {Component, OnInit} from '@angular/core';
import {LiveData} from '../../../models/live-data.model';
import {DataService} from '../../../services/data.service';

@Component({
  selector: 'app-vaccin',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  liveData: LiveData;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.getLiveData().subscribe(d => {
      this.liveData = d;
    });
  }

  cleanForView(input: number): string {
    return (input > 1000) ? (input / 1000).toFixed(1) + '' : input + '';
  }

  getUnity(input: number): string {
    return (input > 1000) ? 'k' : '';
  }
}
