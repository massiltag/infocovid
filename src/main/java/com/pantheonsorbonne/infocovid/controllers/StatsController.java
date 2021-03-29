package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import com.pantheonsorbonne.infocovid.remote.GouvClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pantheonsorbonne.infocovid.controllers.BaseController.BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class StatsController {

    private final GouvClient gouvClient;

    @GetMapping(BASE_URL + "/stats/recap")  // Date au format yyyy-MM-dd
    public ResponseEntity<List<StatsRecap>> getStatsRecap(@RequestParam(required = false) String date) {
        if (date != null)
            return ResponseEntity.ok(gouvClient.getStatsRecap(date));
        else
            return ResponseEntity.ok(gouvClient.getStatsRecap());
    }

    @GetMapping(BASE_URL + "/stats/vaccins") // Date au format yyyy-MM-dd
    public ResponseEntity<List<VaccineStats>> getVaccineStats(@RequestParam(required = false) String date) {
        if (date != null)
            return ResponseEntity.ok(gouvClient.getVaccStats(date));
        else
            return ResponseEntity.ok(gouvClient.getVaccStats());
    }

}
