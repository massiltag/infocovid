package com.pantheonsorbonne.infocovid.controllers;

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

//    @GetMapping(BASE_URL + "/stats/recap")  // Date au format yyyy-MM-dd
//    public ResponseEntity<List<StatsRecap>> getStatsRecap(@RequestParam(required = false) String date) {
//        if (date != null)
//            return ResponseEntity.ok(List.of(gouvClient.getStatsRecap(date)));
//        else
//            return ResponseEntity.ok(gouvClient.getStatsRecap());
//    }
//
//    @GetMapping(BASE_URL + "/stats/vaccins") // Date au format yyyy-MM-dd
//    public ResponseEntity<List<VaccineStats>> getVaccineStats(@RequestParam(required = false) String date) {
//        if (date != null)
//            return ResponseEntity.ok(List.of(gouvClient.getVaccStats(date)));
//        else
//            return ResponseEntity.ok(gouvClient.getVaccStats());
//    }

}
