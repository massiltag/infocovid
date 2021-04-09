package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.domain.model.StatsRecap;
import com.pantheonsorbonne.infocovid.domain.model.VaccineStats;

import java.time.LocalDate;
import java.util.List;

public interface MetricsService {


    /**
     * Récupération des statistiques pour le jour 'day'
     * @param day   Jour concerné
     * @return      {@link Metrics} du jour
     */
    Metrics getForDay(LocalDate day);

    /**
     * Récupération des statistiques pour la période entre 'from' et 'to'
     * @param from  date début
     * @param to    date fin
     * @return      {@link Metrics} du jour
     */
    List<Metrics> getForRange(LocalDate from, LocalDate to);

   /**
    * Récupère les statistiques lié au covid pour un jour donné
    * @param day Date du jour 
    * @return Indicateurs contenant une synthèse des informations connues à la date indiquée
	*
    */
    StatsRecap getStatsForDay(LocalDate day);

    /**
     * Récupère les statistiques lié à la vaccination pour un jour donné
     * @param day Date du jour 
     * @return Indicateurs contenant une synthèse des informations connues à la date indiquée
     */
    VaccineStats getVaccineStatsForDay(LocalDate day);

}
