package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.dto.ApiResponseDTO;
import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.remote.APIClient;
import com.pantheonsorbonne.infocovid.remote.NewsClient;
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

    private final NewsClient newsClient;

    @GetMapping(BASE_URL + "/getLiveData")
    public ResponseEntity<ApiResponseDTO> getDataTest() {
        return ResponseEntity.ok(this.apiClient.getLiveData());
    }

    @GetMapping(BASE_URL + "/getNews")
    public ResponseEntity<List<NewsDTO>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping(BASE_URL + "/test")
    public ResponseEntity<List<NewsDTO>> test() {
        return ResponseEntity.ok(newsClient.getNews());
    }

}
