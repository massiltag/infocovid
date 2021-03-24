package com.pantheonsorbonne.infocovid.remote;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import com.pantheonsorbonne.infocovid.domain.dto.news.SmartableNewsDTO;
import com.pantheonsorbonne.infocovid.domain.mappers.NewsMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *     Client qui permet de récupérer les actualités depuis les sources ;
 * </p>
 */
@Slf4j
@Component
public class NewsClient {

    private final RestTemplate restTemplate;

    @Autowired
    public NewsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NewsDTO> getGeneralNews() {
        List<NewsDTO> result = getMediastackNews("covid");
        result.addAll(getSmartableNews());
        return result.stream()
                .filter(o -> o.getImage() != null)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<NewsDTO> getVaccineNews() {
        return getMediastackNews("vaccin")
                .stream()
                .filter(o -> o.getImage() != null)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<NewsDTO> getMediastackNews(String keyword) {
        // Build URL
        String mediastackUrl = "http://api.mediastack.com/v1/news";

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(mediastackUrl)
                .queryParam("access_key", "4b2ad922cce30ad1c91a2a5309894029")
                .queryParam("keywords", keyword)
                .queryParam("countries", "fr")
                .queryParam("limit", "100");
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MediastackNewsWrapper> httpEntity = new HttpEntity<>(new MediastackNewsWrapper(), headers);
        ResponseEntity<MediastackNewsWrapper> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        return Optional.ofNullable(responseEntity.getBody().getData())
                .orElse(new ArrayList<>());
    }

    private List<NewsDTO> getSmartableNews() {
        // Build URL
        String smartableUrl = "https://coronavirus-smartable.p.rapidapi.com/news/v1/FR/";

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(smartableUrl);

        // Add headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "coronavirus-smartable.p.rapidapi.com");
        headers.set("x-rapidapi-key", "c904f954c0msh94562e99092c0d0p186eeajsnec8afa8843ea");
        headers.set("useQueryString", "true");

        HttpEntity<SmartableNewsWrapper> httpEntity = new HttpEntity<>(new SmartableNewsWrapper(), headers);
        ResponseEntity<SmartableNewsWrapper> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        List<SmartableNewsDTO> result = Optional.ofNullable(responseEntity.getBody().getData())
                .orElse(new ArrayList<>());

        return result.stream()
                .map(NewsMapper::smartableNewsToNewsDTO)
                .collect(Collectors.toList());
    }


    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    private static class MediastackNewsWrapper {

        @JsonProperty("data")
        private List<NewsDTO> data;

    }

    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    private static class SmartableNewsWrapper {

        @JsonProperty("news")
        private List<SmartableNewsDTO> data;

    }
}
