package com.pantheonsorbonne.infocovid.controllers;

import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;
import com.pantheonsorbonne.infocovid.services.PrevisionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.pantheonsorbonne.infocovid.controllers.BaseController.BASE_URL;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PrevisionController {

    private final PrevisionService previsionService;

    @GetMapping(BASE_URL + "/previsions/confinement")
    public ResponseEntity<Prevision> getPrevision() {
        return ResponseEntity.ok(previsionService.getPrevision());
    }

    @GetMapping(BASE_URL + "/previsions/immunite")
    public ResponseEntity<PrevisionImmunite> getMMC() {
        return ResponseEntity.ok(previsionService.getRegressionLineaireImmunite());
    }

}
