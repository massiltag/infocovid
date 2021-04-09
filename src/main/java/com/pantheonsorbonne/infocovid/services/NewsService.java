package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.news.NewsDTO;

import java.util.List;

public interface NewsService {

	 /**
     * <p>
     *     S'exécute tous les jours à 20h, permet de récupérer les nouveaux articles parus
     *     et les enregistrer en base.
     * </p>
     */
    void getAndSaveArticles();

    /**
     * <p>
     *     Permet de récupérer les articles en base, fait une requête API si base vide.
     * </p>
     * @return liste des articles
     */
    List<NewsDTO> getNews();

    /**
     * <p>
     *     Permet de récupérer les articles en base concernant les vaccins, fait une requête API si base vide.
     * </p>
     * @return liste des articles
     */
    List<NewsDTO> getVaccineNews();

    /**
     * Permet de vider la base de données, s'exécute le 1er de chaque mois à minuit
     */
    void clear();

}
