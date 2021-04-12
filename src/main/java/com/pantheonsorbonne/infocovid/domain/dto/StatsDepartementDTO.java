package com.pantheonsorbonne.infocovid.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO pour l'affichage des nombres de cas positifs sur la carte, n'est pas enregistré en BDD
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
public class StatsDepartementDTO {

    /**
     * Département
     */
    private String dep;

    /**
     * Date de la synthèse
     */
    private LocalDate date;

    /**
     * Nombre de patients declarés positifs à la COVID-19
     */
    private int pos;

}
