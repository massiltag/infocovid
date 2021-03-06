package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Regroupement d'indicateurs à une date concernée (synthèse du jour + stats de vaccination)
 */
@Data
@Builder
@Document(collection = "metrics")
public class Metrics {

    @Id
    int id;

    /**
     * Date du jour
     */
    LocalDate date;

    /**
     * Synthèse du jour avec les données principales
     */
    StatsRecap recap;

    /**
     * Statistiques des vaccinnations (doses et cumul)
     */
    VaccineStats vaccineStats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics = (Metrics) o;
        return getDate().isEqual(metrics.getDate()) && Objects.equals(getRecap(), metrics.getRecap()) && Objects.equals(getVaccineStats(), metrics.getVaccineStats());
    }

    public boolean hasNulls() {
        return recap.hasNulls() || vaccineStats.hasNulls();
    }

}
