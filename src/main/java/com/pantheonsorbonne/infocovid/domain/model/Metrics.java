package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "metrics")
public class Metrics {

    LocalDate date;

    StatsRecap recap;

    VaccineStats vaccineStats;

}
