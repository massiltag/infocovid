package com.pantheonsorbonne.infocovid.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Décrit un centre de vaccination, est utilisé pour l'affichage de la carte des centres.
 * @see <a href="https://www.data.gouv.fr/fr/datasets/lieux-de-vaccination-contre-la-covid-19/">Voir source</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
@Document(collection = "centres")
public class CentreVaccinationDTO {

    @Id
    @JsonIgnore
    private String _id;

    /**
     * Nom
     */
    private String nom;

    /**
     * Longitude
     */
    private String lon;

    /**
     * Latitude
     */
    private String lat;

    /**
     * Adresse
     */
    private String addr;

}
