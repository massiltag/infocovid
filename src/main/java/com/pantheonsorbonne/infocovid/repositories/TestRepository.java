package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.dto.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends MongoRepository<Test, Integer> {

    List<Test> findAllById(int id);

    List<Test> findAllByAttrCustom(String attrCustom);

}
