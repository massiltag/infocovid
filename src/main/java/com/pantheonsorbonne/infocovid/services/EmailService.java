package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;

public interface EmailService {

	/**
	 * Sauvegarde l'adresse mail donnée dans la base de données
	 * @param emailAddressDTO adresse mail
	 * @throws EmailException message d'erreur
	 */
    void save(EmailAddressDTO emailAddressDTO) throws EmailException;


    /**
     * S'exécute tous les jours à 21h, envoie un rapport journalier par mail aux inscrits.
     */
    void SendDailyReport();

}
