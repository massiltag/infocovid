import { Component, OnInit } from '@angular/core';

import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})

export class MapComponent implements OnInit {
  markerClusterGroup: L.MarkerClusterGroup;


  constructor() { }



  ngOnInit() {
    // Déclaration de la carte avec les coordonnées du centre et le niveau de zoom.
    const myMap = L.map('map').setView([46.8, 3], 5);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: 'myMap'
    }).addTo(myMap);

    const myIcon = L.icon({
      iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png'
    });


    let firstLayer = L.marker([48.856614, 2.3522219],
      { icon: myIcon }).bindPopup('centre de vaccination').addTo(myMap);
    let secondLayer = L.marker([50, 2.3522219],
      { icon: myIcon }).bindPopup('centre de vaccination').addTo(myMap);


  }


}



