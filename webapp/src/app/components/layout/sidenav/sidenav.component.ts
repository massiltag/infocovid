import {Component, Input, OnInit} from '@angular/core';
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

  constructor(public auth: AuthService) {}

  ngOnInit(): void {
    this.opened = true;
  }

  toggle(): void {
    this.opened = !this.opened;
  }

}
