package com.pantheonsorbonne.infocovid.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
public class Prevision {

    /**
     *
     */
    @JsonProperty("confinement")
    private boolean confinement;

    /**
     *
     */
    @JsonProperty("nombre_cas")
    private int nombreCas;
    
    /**
    *
    */
   @JsonProperty("type_cas")
   private String typeCas;

}
