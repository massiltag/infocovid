package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;
import com.pantheonsorbonne.infocovid.services.MetricsService;
import com.pantheonsorbonne.infocovid.services.PrevisionService;
import com.pantheonsorbonne.infocovid.util.PrevisionConfinement;
import com.pantheonsorbonne.infocovid.util.RegressionLineaire;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrevisionServiceImpl implements PrevisionService {

    private final MetricsService metricsService;

    PrevisionConfinement p;

    RegressionLineaire mmc;

    @Override
    public Prevision getPrevision() {
        List<Metrics> metrics = metricsService.getForRange(LocalDate.now().minusDays(30), LocalDate.now().minusDays(2));

        List<Integer> nbCas = metrics
                .stream()
                .map(m -> m.getRecap().getConf())
                .collect(Collectors.toList());

        List<Integer> nbHosp = metrics
                .stream()
                .map(m -> m.getRecap().getHosp())
                .collect(Collectors.toList());

        List<Integer> nbDc = metrics
                .stream()
                .map(m -> m.getRecap().getEsms_dc() + m.getRecap().getDchosp())
                .collect(Collectors.toList());

        p = PrevisionConfinement
                .builder()
                .nbCas(nbCas)
                .nbHospitalisations(nbHosp)
                .nbDeces(nbDc)
                .build();

        return p.previsionConfinement();
    }


    @Override  
    public PrevisionImmunite getRegressionLineaireImmunite() {
        List<Metrics> metrics = metricsService.getForRange(LocalDate.now().minusDays(40), LocalDate.now().minusDays(1));
 //       List<Metrics> metrics = metricsService.getForRange(LocalDate.now().minusDays(30), LocalDate.now().minusDays(2));
  

        List<Integer> nbVaccins = metrics
                .stream()
                .map(m -> m.getVaccineStats().getN_cum_dose1())
                .collect(Collectors.toList());

        mmc = RegressionLineaire
                .builder()
                .nbVaccinationsList(nbVaccins)
                .jourList(new ArrayList<Integer>())
                .build();
        System.out.println(nbVaccins);
       
        return mmc.previsionImmunite();
    }

}
