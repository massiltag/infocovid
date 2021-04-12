import {Component, HostListener, OnInit} from '@angular/core';
import {DataService} from '../../../services/data.service';
import {Metrics} from '../../../models/metrics.model';
import {MetricsService} from '../../../services/metrics.service';

@Component({
    selector: 'app-vaccin',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    searching: number;

    today: Metrics;

    twoWeeksAgo: Metrics;

    fiveDays: Metrics[];

    public screenWidth: any;
    public screenHeight: any;

    public txPos: number;
    public txIncid: number;
    public r: number;

    constructor(private dataService: DataService, private metricsService: MetricsService) { }

    ngOnInit(): void {
        this.screenWidth = window.innerWidth;
        this.screenHeight = window.innerHeight;
        this.onResize();
        this.searching = 0;

        this.metricsService.getMetricsForDate(new Date(Date.now() - 86400000))
            .subscribe(m => { // 1 DAY
                this.searching++;
                this.today = m;
            });

        this.metricsService.getMetricsForDate(new Date(Date.now() - (14 * 24 * 60 * 60 * 1000)))
            .subscribe(m => {
                this.searching++;
                this.twoWeeksAgo = m;
            });

        this.metricsService.getMetricsForRange(new Date(Date.now() - (6 * 24 * 60 * 60 * 1000)), new Date(Date.now()))
            .subscribe(t => { // 5 DAYS
                this.searching++;
                this.fiveDays = t;
                if (this.fiveDays[-1].recap.conf_j1 === 0) { this.fiveDays.pop(); }
            });

    }

    cleanForView(input: number): string {
        return (input > 1000) ? (input / 1000).toFixed(1) + '' : Math.round(input * 10) / 10 + '';
    }

    getUnity(input: number): string {
        return (input > 1000) ? 'k' : '';
    }


    @HostListener('window:resize', ['$event'])
    onResize(): void {
        this.screenWidth = window.innerWidth;
        this.screenHeight = window.innerHeight;
    }

    isXs(): boolean {
        return this.screenHeight < 850 && this.screenWidth < 400;
    }
}
