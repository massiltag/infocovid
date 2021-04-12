import {Component, OnInit} from '@angular/core';

//tslint:disable
import * as L from 'leaflet';
import {StatsDepartement} from '../../../../models/stats-departement.model';
import {MetricsService} from '../../../../services/metrics.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})

export class MapComponent implements OnInit {
  markerClusterGroup: L.MarkerClusterGroup;

  searching: boolean;

  constructor(private metricsService: MetricsService) { }

  ngOnInit(): void {

    // Déclaration de la carte avec les coordonnées du centre et le niveau de zoom.
    const myMap = L.map('map').setView([46.8, 3], 5);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: 'myMap'
    }).addTo(myMap);

    const myIcon = L.icon({
      iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png'
    });

    this.searching = true;
    this.metricsService.getLastCasByDep().subscribe(data => {
      this.searching = false;
      this.initGeoJson(myMap, data);
    })

  }

  initGeoJson(map: any, data: StatsDepartement[]): void {
    const style = {
      color: '#8341b5',
      weight: 1.7,
      opacity: 0.65
    };
    $.getJSON('assets/departments.geojson', function(geo){
      L.geoJSON(geo, {
        onEachFeature: function (feature, layer) {
          const filtered = data.filter(d => d.dep === feature.properties.code);
          const thisdepInfo = filtered.length ? filtered[0] : undefined;
          let content = `<h2>${feature.properties.nom} (${feature.properties.code})</h2>`;
          content += thisdepInfo ? `<h4>Nombre de cas positifs : <b>${thisdepInfo.pos}</b></h4>` : `<p>Nombre de cas positifs indisponible</p>`;
          const date = thisdepInfo.date.split('-')[2] + '/' + thisdepInfo.date.split('-')[1] + '/' + thisdepInfo.date.split('-')[0];
          content += `<p>Informations en date du ${date}</p>`;
          layer.bindPopup(content)
        },
        style: style
      }).addTo(map);
    });
  }

}



