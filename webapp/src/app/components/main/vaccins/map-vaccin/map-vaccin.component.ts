import {Component, OnInit} from '@angular/core';

declare var require: any

import * as L from 'leaflet';

@Component({
  selector: 'app-map-vaccin',
  templateUrl: './map-vaccin.component.html',
  styleUrls: ['./map-vaccin.component.scss']
})
export class MapVaccinComponent implements OnInit {
 markerClusterGroup: L.MarkerClusterGroup;
  constructor() { }

  ngOnInit(): void {
    
    const data = require('../map-vaccin/centres-vaccination.json');

    

    console.log(data.features[0].properties.c_nom);
      // Déclaration de la carte avec les coordonnées du centre et le niveau de zoom.
      const myMap = L.map('map').setView([46.8, 3], 5);
  
      L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: 'myMap'
      }).addTo(myMap);
  
      const myIcon = L.icon({
        iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png'
      });
  
      var i;
      var clusterGroup = new L.MarkerClusterGroup();
      for (i = 0; i < data.features.length; i++) {
        var nom = data.features[i].properties.c_nom;
        var latitude = data.features[i].properties.c_lat_coor1;
        var longitude = data.features[i].properties.c_long_coor1;
       
        var content =
            '<div>' +
            '<h3>' + nom + '</h3>'
            '</div>';
        var marker = L.marker([latitude, longitude]);
        //marker.bindPopup(content).addTo(myMap);
        marker.bindPopup(content);
        clusterGroup.addLayer(marker);
    }
    myMap.addLayer(clusterGroup);
    
  }

}
