package com.pantheonsorbonne.infocovid.remote;

import com.pantheonsorbonne.infocovid.domain.mappers.StatsRecapMapper;
import com.pantheonsorbonne.infocovid.domain.mappers.VaccineStatsMapper;
import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Client qui sert à récupérer des informations depuis les APIs du gouvernement
 * <ul>
 *     <li>@see <a href="https://www.data.gouv.fr/fr/datasets/synthese-des-indicateurs-de-suivi-de-lepidemie-covid-19/">Synthèse des incidateurs</a></li>
 *     <li>@see <a href="https://www.data.gouv.fr/fr/datasets/donnees-relatives-aux-personnes-vaccinees-contre-la-covid-19-1/">Personnes vaccinées</a></li>
 * </ul>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GouvClient {

    private final RestTemplate restTemplate;

    private static String STATS_URL = "https://www.data.gouv.fr/fr/datasets/r/f335f9ea-86e3-4ffa-9684-93c009d5e617";

    private static String VACC_URL = "https://www.data.gouv.fr/fr/datasets/r/efe23314-67c4-45d3-89a2-3faef82fae90";

    public StatsRecap getStatsRecap(LocalDate date) {
        try {
            return getStatsRecap().stream().filter(o -> o.getDate().equals(date)).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return StatsRecap.builder().build();
        }
    }

    public List<StatsRecap> getStatsRecap() {
        // Get URL
        String httpUrl = STATS_URL;

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        String response = Optional.ofNullable(responseEntity.getBody()).orElse("");

        return StatsRecapMapper.csvToList(response);
    }

    public VaccineStats getVaccStats(LocalDate date) {
        try {
            return getVaccStats().stream().filter(o -> o.getDate().equals(date)).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return VaccineStats.builder().build();
        }
    }

    public List<VaccineStats> getVaccStats() {
        // Get URL
        String httpUrl = VACC_URL;

        // Build URI
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, new HttpHeaders());

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.build(false).toUriString(),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        String response = Optional.ofNullable(responseEntity.getBody()).orElse("");

        return VaccineStatsMapper.csvToList(response);
    }


}
