package com.pantheonsorbonne.infocovid.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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

}
