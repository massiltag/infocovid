package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;
import com.pantheonsorbonne.infocovid.repositories.EmailAddressRepository;
import com.pantheonsorbonne.infocovid.services.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final EmailAddressRepository emailAddressRepository;

    @Override
    public void save(EmailAddressDTO emailAddressDTO) throws EmailException {
        if (emailAddressRepository.findByAddress(emailAddressDTO.getAddress()) != null) {
            throw new EmailException("Vous êtes déjà abonné aux diffusions.");
        }
        emailAddressRepository.save(emailAddressDTO);
    }

}
