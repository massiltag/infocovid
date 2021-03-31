package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@Document(collection = "metrics")
public class Metrics {

    @Id
    int id;

    LocalDate date;

    StatsRecap recap;

    VaccineStats vaccineStats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics = (Metrics) o;
        return getDate().isEqual(metrics.getDate()) && Objects.equals(getRecap(), metrics.getRecap()) && Objects.equals(getVaccineStats(), metrics.getVaccineStats());
    }

}
