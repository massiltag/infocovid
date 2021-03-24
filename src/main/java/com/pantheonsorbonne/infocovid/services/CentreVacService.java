package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;

import java.util.List;

public interface CentreVacService {

    List<CentreVaccinationDTO> getAllCentres();

    List<CentreVaccinationDTO> getAllByAddressLike(String addr);

}
