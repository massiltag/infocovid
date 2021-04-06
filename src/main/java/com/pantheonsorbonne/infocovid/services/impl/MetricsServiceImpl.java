package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import com.pantheonsorbonne.infocovid.remote.GouvClient;
import com.pantheonsorbonne.infocovid.repositories.MetricsRepository;
import com.pantheonsorbonne.infocovid.services.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        if (result == null || result.hasNulls()) {
            result = Metrics.builder()
                    .id(Integer.parseInt(String.valueOf(day.getYear())
                            .concat(day.getMonthValue() < 10 ? "0" + day.getMonthValue() : String.valueOf(day.getMonthValue()))
                            .concat(day.getDayOfMonth() < 10 ? "0" + day.getDayOfMonth() : String.valueOf(day.getDayOfMonth()))))
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

    /**
     * <p>
     *     Met à jour les informations concernant les dix derniers jours en BDD
     *     en effectuant des appels aux APIs du gouvernement (pour d'éventuels changements)
     *     Car certaines informations ne sont pas disponibles de suite.
     *     ex : taux de positivité disponible 3 jours après.
     * </p>
     */
    @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(cron = "0 0 2 * * *")
    public void updateLast10days() {
        getDateRange(LocalDate.now(), LocalDate.now().minus(10, ChronoUnit.DAYS)).forEach(this::updateDay);
    }

    private void updateDay(LocalDate day) {
        Metrics result = metricsRepository.findFirstByDate(day);
        Metrics apiResult = Metrics.builder()
                .id(Integer.parseInt(String.valueOf(day.getYear())
                        .concat(String.valueOf(day.getMonthValue()))
                        .concat(String.valueOf(day.getDayOfMonth()))))
                .date(day)
                .recap(gouvClient.getStatsRecap(day))
                .vaccineStats(gouvClient.getVaccStats(day))
                .build();

        if (result == null || !result.equals(apiResult)) {
            result = apiResult;
            metricsRepository.save(result);
        }
    }
}
