package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import com.pantheonsorbonne.infocovid.repositories.CentreVacRepository;
import com.pantheonsorbonne.infocovid.services.CentreVacService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CentreVacServiceImpl implements CentreVacService  {

    private final CentreVacRepository centreVacRepository;

    @Override
    public List<CentreVaccinationDTO> getAllCentres() {
        return centreVacRepository.findAll();
    }

    @Override
    public List<CentreVaccinationDTO> getAllByAddressLike(String addr) {
        return centreVacRepository.findAllByAddrContainsIgnoreCase(addr);
    }

}
