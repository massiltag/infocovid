package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.dto.ApiResponseDTO;
import com.pantheonsorbonne.infocovid.remote.APIClient;
import com.pantheonsorbonne.infocovid.services.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BaseController {

    protected static final String BASE_URL = "/api/";

    private final APIClient apiClient;

    private final TestService testService;

    @GetMapping(BASE_URL + "/getLiveData")
    public ResponseEntity<ApiResponseDTO> getDataTest() {
        return ResponseEntity.ok(this.apiClient.getLiveData());
    }

    @GetMapping(BASE_URL + "/test")
    public ResponseEntity<String> test() {
        testService.save();
        return ResponseEntity.ok("yes");
    }

}
