package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.dto.StatsDepartementDTO;
import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.services.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pantheonsorbonne.infocovid.controllers.BaseController.BASE_URL;
import static com.pantheonsorbonne.infocovid.util.StringUtil.stringToLocalDate;

/**
 * Contrôleur qui expose les données relatives à la COVID-19 (BDD/APIs publiques)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class StatsController {

    private final MetricsService metricsService;

    @GetMapping(BASE_URL + "/metrics")  // Date au format yyyy-MM-dd
    public ResponseEntity<Metrics> getMetricsForDay(@RequestParam String date) {
        return ResponseEntity.ok(metricsService.getForDay(stringToLocalDate(date)));
    }

    @GetMapping(BASE_URL + "/metrics/range")  // Date au format yyyy-MM-dd
    public ResponseEntity<List<Metrics>> getMetricsForRange(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(metricsService.getForRange(stringToLocalDate(from), stringToLocalDate(to)));
    }

    @GetMapping(BASE_URL + "/metrics/departments") // Positivité par département
    public ResponseEntity<List<StatsDepartementDTO>> getByDepartments() {
        return ResponseEntity.ok(metricsService.getStatsByDep());
    }

}
