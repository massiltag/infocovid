import {Component, OnInit} from '@angular/core';
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})

export class MapComponent implements OnInit {
  options: any;
  overlays: any[];

  lat = 46.662253328373495;
  lng = 2.4410404427084402;
  constructor() {
    this.options = {
      center: {lat: this.lat, lng: this.lng},
      zoom: 5.9
    };

    this.overlays = [
      new google.maps.Marker({position: {lat: 48.81027, lng: 2.35366}, title: 'CHU Kremlin-Bicêtre'}),
      new google.maps.Marker({position: {lat: 48.83685, lng: 2.36555}, title: 'Pitié Salpétrière'}),
      new google.maps.Marker({position: {lat: 45.70177, lng: 4.80643}, title: 'Hôpital Lyon Sud'}),
      new google.maps.Marker({position: {lat: 45.78489, lng: 3.10988}, title: 'CHU Clermont-Ferrand'}),
      new google.maps.Marker({position: {lat: 44.82741, lng: -0.60834}, title: 'Hôpital Pellegrin Bordeaux'}),
      new google.maps.Marker({position: {lat: 43.27520, lng: 5.39300}, title: 'Hôpital Saint Joseph Marseille'}),
      new google.maps.Marker({position: {lat: 49.11430, lng: 6.18020}, title: 'Hôpital Sainte Blandine Metz'}),
    ];
  }

  ngOnInit(): void {
  }

}
