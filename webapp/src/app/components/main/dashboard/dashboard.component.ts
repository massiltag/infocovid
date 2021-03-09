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

  searching: boolean;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.searching = true;
    this.dataService.getLiveData().subscribe(d => {
      this.searching = false;
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
