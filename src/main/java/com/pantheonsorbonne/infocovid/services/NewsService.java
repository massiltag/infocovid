package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;

import java.util.List;

public interface NewsService {

    void getAndSaveArticles();

    List<NewsDTO> getNews();

    List<NewsDTO> getVaccineNews();

    void clear();

}
