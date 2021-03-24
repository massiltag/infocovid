package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.dto.ApiResponseDTO;
import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.remote.APIClient;
import com.pantheonsorbonne.infocovid.services.CentreVacService;
import com.pantheonsorbonne.infocovid.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BaseController {

    protected static final String BASE_URL = "/api/";

    private final APIClient apiClient;

    private final NewsService newsService;

    private final CentreVacService centreVacService;

    @GetMapping(BASE_URL + "/getLiveData")
    public ResponseEntity<ApiResponseDTO> getData() {
        return ResponseEntity.ok(this.apiClient.getLiveData());
    }

    @GetMapping(BASE_URL + "/news/general")
    public ResponseEntity<List<NewsDTO>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping(BASE_URL + "/news/vaccins")
    public ResponseEntity<List<NewsDTO>> getVaccineNews() {
        return ResponseEntity.ok(newsService.getVaccineNews());
    }

    @GetMapping(BASE_URL + "/vaccins/centres")
    public ResponseEntity<List<CentreVaccinationDTO>> getCentres() {
        return ResponseEntity.ok(centreVacService.getAllCentres());
    }



}
