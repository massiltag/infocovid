package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * https://www.data.gouv.fr/fr/datasets/synthese-des-indicateurs-de-suivi-de-lepidemie-covid-19/
 */
@Data
@Builder
public class StatsRecap {

    /**
     * Date de la synthèse TODO utiliser {@link java.time.LocalDateTime}
     */
    private LocalDate date;

    /**
     * Décès à l'hôpital
     */
    private int dchosp;

    /**
     * Décès en EHPAD
     */
    private int esms_dc;

    /**
     * Cumul des cas confirmés
     */
    private int conf;

    /**
     * Nouveaux cas confirmés
     */
    private int conf_j1;

    /**
     * Taux d'occupation (Tension Réa.)
     */
    private double to;

    /**
     * Taux de positivité (positif/total)
     */
    private double tx_pos;

    /**
     * Taux d'incidence
     */
    private double tx_incid;

    /**
     * Facteur de reproduction
     */
    private double r;

    /**
     * Nombre de patients actuellement hospitalisés
     */
    private int hosp;

    /**
     * Nombre de patients actuellement en réanimation
     */
    private int rea;

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StatsRecap that = (StatsRecap) o;
            return getDchosp() == that.getDchosp() && getEsms_dc() == that.getEsms_dc() && getConf() == that.getConf() && getConf_j1() == that.getConf_j1() && Double.compare(that.getTo(), getTo()) == 0 && Double.compare(that.getTx_pos(), getTx_pos()) == 0 && Objects.equals(getDate(), that.getDate());
        } catch (NullPointerException e) {
            return false;
        }
    }

}
