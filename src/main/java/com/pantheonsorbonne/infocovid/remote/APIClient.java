package com.pantheonsorbonne.infocovid.remote;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantheonsorbonne.infocovid.domain.dto.ApiResponseDTO;
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

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class APIClient {

    private final RestTemplate restTemplate;

    private static String LIVE_DATA = "https://coronavirusapi-france.now.sh/FranceLiveGlobalData";

    @Autowired
    public APIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponseDTO getLiveData() {
        // Build URL
        String httpUrl = LIVE_DATA;

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<LiveWrapper> httpEntity = new HttpEntity<>(new LiveWrapper(), headers);
        ResponseEntity<LiveWrapper> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );
        return Optional.ofNullable(responseEntity.getBody().getLiveData().get(0))
                .orElse(ApiResponseDTO.builder().build());
    }


    @Data
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    private static class LiveWrapper {

        @JsonProperty("FranceGlobalLiveData")
        private List<ApiResponseDTO> liveData;

    }

}
