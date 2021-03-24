package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.dto.CentreVaccinationDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreVacRepository extends MongoRepository<CentreVaccinationDTO, String> {

    List<CentreVaccinationDTO> findAllByAddrContainsIgnoreCase(String addr);

}
