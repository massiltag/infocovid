package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Sert à stocker les statistiques
 */
public interface MetricsRepository extends MongoRepository<Metrics, String> {

    List<Metrics> findAll();

    Metrics findFirstByDate(LocalDate date);

}
