package com.pantheonsorbonne.infocovid.exceptions;

/**
 * Exception lancée quand l'envoi de mail est en erreur
 */
public class EmailException extends Exception {

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

}
