package com.pantheonsorbonne.infocovid.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class PrevisionImmunite {
		/**
	     *Booléen en cas d'immunité collective ou pas
	     */
	    @JsonProperty("immunite")
	    private boolean immunite;

	    /**
	     * Le nombre de vaccination total le jour où l'immunité collective est atteinte
	     */
	    @JsonProperty("nbVaccinAtteint")
	    private int nbVaccinAtteint;
	    
	    /**
	     *Liste des prévisions du nombre de vaccinations sur les prochains jours
	     */
	    @JsonProperty("nbVaccinQuotidien")
	    private ArrayList<Double> nbVaccinQuotidien;
	

}
