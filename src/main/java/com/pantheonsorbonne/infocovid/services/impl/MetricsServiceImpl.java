package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import com.pantheonsorbonne.infocovid.remote.GouvClient;
import com.pantheonsorbonne.infocovid.repositories.MetricsRepository;
import com.pantheonsorbonne.infocovid.services.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.pantheonsorbonne.infocovid.util.StringUtil.getDateRange;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository metricsRepository;

    private final GouvClient gouvClient;

    @Override
    public Metrics getForDay(LocalDate day) {
        Metrics result = metricsRepository.findFirstByDate(day);
        if (result == null) {
            log.info("Fetching metrics for " + day);
            result = Metrics.builder()
                    .date(day)
                    .recap(gouvClient.getStatsRecap(day))
                    .vaccineStats(gouvClient.getVaccStats(day))
                    .build();
            metricsRepository.save(result);
        }
        return result;
    }

    @Override
    public List<Metrics> getForRange(LocalDate from, LocalDate to) {
        List<Metrics> result = new ArrayList<>();
        getDateRange(from, to).forEach(d -> result.add(getForDay(d)));
        return result;
    }

    @Override
    public StatsRecap getStatsForDay(LocalDate day) {
        return getForDay(day).getRecap();
    }

    @Override
    public VaccineStats getVaccineStatsForDay(LocalDate day) {
        return getForDay(day).getVaccineStats();
    }
}
