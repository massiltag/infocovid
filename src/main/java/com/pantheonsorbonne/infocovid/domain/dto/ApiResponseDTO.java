package com.pantheonsorbonne.infocovid.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class ApiResponseDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("date")
    private String date;

    @JsonProperty("hospitalises")
    private int hospitalises;

    @JsonProperty("reanimation")
    private int reanimation;

    @JsonProperty("nouvellesHospitalisations")
    private int nouvellesHospitalisations;

    @JsonProperty("nouvellesReanimations")
    private int nouvellesReanimations;

    @JsonProperty("deces")
    private int deces;

    @JsonProperty("gueris")
    private int gueris;

}
