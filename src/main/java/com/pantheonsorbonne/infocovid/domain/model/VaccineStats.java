package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "indicateursVaccins")
public class VaccineStats {

    /**
     * Date de l'info vaccin TODO utiliser {@link java.time.LocalDateTime}
     */
    private LocalDate date;

    /**
     * Nombre de nouvelles vaccinations (dose 1)
     */
    private int n_dose1;

    /**
     * Nombre de nouvelles vaccinations (dose 2)
     */
    private int n_dose2;

    /**
     * Cumul des premières vaccinations
     */
    private int n_cum_dose1;

}
