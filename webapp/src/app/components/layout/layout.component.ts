import {Component, HostListener, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  public screenWidth: any;
  public screenHeight: any;

  constructor(public auth: AuthService) { }

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    this.screenHeight = window.innerHeight;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event): void {
    this.screenWidth = window.innerWidth;
    this.screenHeight = window.innerHeight;
  }

  isXs(): boolean {
    return this.screenHeight < 850 && this.screenWidth < 400;
  }

}
