package com.pantheonsorbonne.infocovid.remote;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantheonsorbonne.infocovid.domain.dto.NewsDTO;
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

@Slf4j
@Component
public class NewsClient {

    private final RestTemplate restTemplate;

    private static String NEWS_URL = "http://api.mediastack.com/v1/news";

    @Autowired
    public NewsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NewsDTO> getNews() {
        // Build URL
        String httpUrl = NEWS_URL;

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                .queryParam("access_key", "4b2ad922cce30ad1c91a2a5309894029")
                .queryParam("keywords", "covid")
                .queryParam("countries", "fr")
                .queryParam("limit", "100");
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<NewsWrapper> httpEntity = new HttpEntity<>(new NewsWrapper(), headers);
        ResponseEntity<NewsWrapper> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );
        List<NewsDTO> result = Optional.ofNullable(responseEntity.getBody().getData())
                .orElse(new ArrayList<>());

        return result.stream()
                .filter(o -> o.getImage() != null)
                .distinct()
                .collect(Collectors.toList());
    }


    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    private static class NewsWrapper {

        @JsonProperty("data")
        private List<NewsDTO> data;

    }
}
