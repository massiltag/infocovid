import {Component, OnInit} from '@angular/core';
import * as leaflet from 'leaflet';
import {DataService} from '../../../../services/data.service';

declare var require: any;

@Component({
    selector: 'app-map-vaccin',
    templateUrl: './vaccin-map.component.html',
    styleUrls: ['./vaccin-map.component.scss']
})
export class VaccinMapComponent implements OnInit {

    markerClusterGroup: leaflet.MarkerClusterGroup;

    constructor(private dataService: DataService) { }

    searching: boolean;
    loading: boolean;

    ngOnInit(): void {
        // Déclaration de la carte avec les coordonnées du centre et le niveau de zoom.

        this.loading = true;
        setTimeout(() => {
            this.loading = false;
            this.initMap();
        }, 1000);


    }

    initMap(): void {
        const myMap = leaflet.map('map').setView([46.8, 3], 6);

        leaflet.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: 'myMap'
        }).addTo(myMap);

        const myIcon = leaflet.icon({
            iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png'
        });

        this.searching = true;
        const clusterGroup = new leaflet.MarkerClusterGroup();
        this.dataService.getCentresVaccination().subscribe(data => {
            this.searching = false;
            for (const centre of data) {
                const content =
                    '<div>' +
                    '   <h2>' + centre.nom + '</h2>' +
                    '   <h3>' + centre.addr + '</h3>' +
                    '</div>';
                const marker = leaflet.marker([parseFloat(centre.lat), parseFloat(centre.lon)], { icon: myIcon });

                // marker.bindPopup(content).addTo(myMap);
                marker.bindPopup(content);
                clusterGroup.addLayer(marker);
                myMap.addLayer(clusterGroup);
            }
        });
    }


}
