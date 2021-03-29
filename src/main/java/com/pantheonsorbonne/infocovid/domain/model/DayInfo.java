package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "indicateurs")
public class DayInfo {

    @Id
    String jour;

    VaccineStats vaccineStats;

    StatsRecap statsRecap;

}
