import {Component, HostListener, Input, OnInit} from '@angular/core';
import {RoutePathEnum} from '../../../enums/route-path.enum';
import {AuthService} from '../../../services/auth/auth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {
  @Input() opened: boolean;
  routePath = RoutePathEnum;

  public screenWidth: any;
  public screenHeight: any;


  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    this.opened = true;
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

  toggle(): void {
    this.opened = !this.opened;
  }

}
