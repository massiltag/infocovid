import { Component, OnInit, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AgmCoreModule } from '@agm/core';

@Component({
  
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})

export class MapComponent implements OnInit {
  title = 'Carte des centres de vaccination en France';
  lat = 48.85661;
  lng = 2.35222;
  constructor() { }

  ngOnInit(): void {
  }

}
