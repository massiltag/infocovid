package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sert à stocker les centres de vaccination (importer le JSON en base Mongo en amont)
 */
@Repository
public interface CentreVacRepository extends MongoRepository<CentreVaccinationDTO, String> {

    List<CentreVaccinationDTO> findAllByAddrContainsIgnoreCase(String addr);

}
