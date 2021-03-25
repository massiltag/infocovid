package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;

public interface MailService {

    void save(EmailAddressDTO emailAddressDTO) throws EmailException;

}
