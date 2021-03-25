package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailAddressRepository extends MongoRepository<EmailAddressDTO, String> {

    EmailAddressDTO findByAddress(String address);

    List<EmailAddressDTO> findAll();

}
