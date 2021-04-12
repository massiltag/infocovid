package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;

import java.util.List;

public interface CentreVacService {

	/**
	 * Récupère tous les centres de vaccination en France
	 * @return Liste de centres de vaccination
	 */
    List<CentreVaccinationDTO> getAllCentres();

    /**
     * Récupère les centres de vaccination selon l'adresse donnée
     * @param addr adresse d'un centre de vaccination
     * @return Liste de centres de vaccination
     */
    List<CentreVaccinationDTO> getAllByAddressLike(String addr);

}
