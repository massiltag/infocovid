package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import com.pantheonsorbonne.infocovid.repositories.CentreVacRepository;
import com.pantheonsorbonne.infocovid.services.CentreVacService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
