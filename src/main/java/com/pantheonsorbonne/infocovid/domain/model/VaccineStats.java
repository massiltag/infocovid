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
     * Date de l'info vaccin
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

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VaccineStats that = (VaccineStats) o;
            return getN_dose1() == that.getN_dose1() && getN_dose2() == that.getN_dose2() && getN_cum_dose1() == that.getN_cum_dose1() && getDate().isEqual(that.getDate());
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean hasNulls() {
        return n_dose1 == 0
                || n_dose2 == 0
                || n_cum_dose1 == 0;
    }

}
