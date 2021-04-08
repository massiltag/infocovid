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
	     *
	     */
	    @JsonProperty("immunite")
	    private boolean immunite;

	    /**
	     *
	     */
	    @JsonProperty("nbVaccinAtteint")
	    private int nbVaccinAtteint;
	    
	    /**
	     *
	     */
	    @JsonProperty("nbVaccinQuotidien")
	    private ArrayList<Double> nbVaccinQuotidien;
	

}
