package com.pantheonsorbonne.infocovid.repositories;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sert à stocker les actualités
 */
@Repository
public interface NewsRepository extends MongoRepository<NewsDTO, String> {

    List<NewsDTO> findAll();

}