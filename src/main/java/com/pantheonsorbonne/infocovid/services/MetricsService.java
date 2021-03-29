package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;

import java.time.LocalDate;
import java.util.List;

public interface MetricsService {

    Metrics getForDay(LocalDate day);

    List<Metrics> getForRange(LocalDate from, LocalDate to);

    StatsRecap getStatsForDay(LocalDate day);

    VaccineStats getVaccineStatsForDay(LocalDate day);

}
