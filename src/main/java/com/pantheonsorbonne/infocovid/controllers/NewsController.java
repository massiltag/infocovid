package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pantheonsorbonne.infocovid.controllers.BaseController.BASE_URL;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class NewsController {

    private final NewsService newsService;

    @GetMapping(BASE_URL + "/news/general")
    public ResponseEntity<List<NewsDTO>> getNews() {
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping(BASE_URL + "/news/vaccins")
    public ResponseEntity<List<NewsDTO>> getVaccineNews() {
        return ResponseEntity.ok(newsService.getVaccineNews());
    }

}
