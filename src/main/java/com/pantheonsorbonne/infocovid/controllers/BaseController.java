package com.pantheonsorbonne.infocovid.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantheonsorbonne.infocovid.domain.dto.ApiResponseDTO;
import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;
import com.pantheonsorbonne.infocovid.remote.APIClient;
import com.pantheonsorbonne.infocovid.remote.GouvClient;
import com.pantheonsorbonne.infocovid.services.CentreVacService;
import com.pantheonsorbonne.infocovid.services.EmailService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BaseController {

    protected static final String BASE_URL = "/api/";

    private final APIClient apiClient;

    private final GouvClient gouvClient;

    private final CentreVacService centreVacService;

    private final EmailService emailService;

    @GetMapping(BASE_URL + "/getLiveData")
    public ResponseEntity<ApiResponseDTO> getData() {
        return ResponseEntity.ok(this.apiClient.getLiveData());
    }

    @GetMapping(BASE_URL + "/vaccins/centres")
    public ResponseEntity<List<CentreVaccinationDTO>> getCentres() {
        return ResponseEntity.ok(centreVacService.getAllCentres());
    }

    @PostMapping(BASE_URL + "/subscribe")
    public ResponseEntity<ResponseMessage> subscribe(@RequestParam String email) {
        log.info("api called with " + email);
        try {
            emailService.save(EmailAddressDTO.builder().address(email).build());
            return ResponseEntity.ok(
                    ResponseMessage.builder().message("Vous êtes maintenant abonné.").build()
            );
        } catch (EmailException e) {
            return ResponseEntity
                    .status(200)
                    .body(ResponseMessage.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(200)
                    .body(ResponseMessage.builder().message("Une erreur s'est produite.").build());
        }
    }

    @GetMapping(BASE_URL + "/test")
    public ResponseEntity<String> test() {
        emailService.SendDailyReport();
        return ResponseEntity.ok("yih");
    }


    @Data
    @Builder
    @JsonAutoDetect(
            fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            setterVisibility = JsonAutoDetect.Visibility.NONE
    )
    private static class ResponseMessage {

        @JsonProperty("message")
        String message;

    }

}
