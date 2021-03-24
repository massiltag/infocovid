import {Component, OnInit} from '@angular/core';
import * as L from 'leaflet';

declare var require: any;

@Component({
  selector: 'app-map-vaccin',
  templateUrl: './map-vaccin.component.html',
  styleUrls: ['./map-vaccin.component.scss']
})
export class MapVaccinComponent implements OnInit {
 markerClusterGroup: L.MarkerClusterGroup;
  constructor() { }

  files: {
      nom: string,
      lon: string,
      lat: string,
      addr: string
  }[] = [];

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

    let i;
    const clusterGroup = new L.MarkerClusterGroup();
    for (i = 0; i < data.features.length; i++) {
        const nom = data.features[i].properties.c_nom;
        const latitude = data.features[i].properties.c_lat_coor1;
        const longitude = data.features[i].properties.c_long_coor1;

        const content =
            '<div>' +
            ' <h3>' + nom + '</h3>' +
            '</div>';
        const marker = L.marker([latitude, longitude], { icon: myIcon });

        // marker.bindPopup(content).addTo(myMap);
        marker.bindPopup(content);
        clusterGroup.addLayer(marker);

        const name = data.features[i].properties.c_nom;
        const lt = data.features[i].properties.c_lat_coor1;
        const lg = data.features[i].properties.c_long_coor1;

        const p = data.features[i].properties;

        this.files.push(
            {
                nom: name,
                lon: lg,
                lat: lt,
                // tslint:disable-next-line:max-line-length
                addr: (this.clean(p.c_adr_num) + ' ' + this.clean(p.c_adr_voie) + ' ' + this.clean(p.c_com_cp) + ' ' + this.clean(p.c_com_nom)).trim()
            }
        );
    }
    myMap.addLayer(clusterGroup);
    alert('cc');
    console.log(this.files);
    console.log('e');
  }

  clean(s: string): string {
      return s ? s : '';
  }


}
