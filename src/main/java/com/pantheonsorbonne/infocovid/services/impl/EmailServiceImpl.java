package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;
import com.pantheonsorbonne.infocovid.repositories.EmailAddressRepository;
import com.pantheonsorbonne.infocovid.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final EmailAddressRepository emailAddressRepository;

    private static final String SUBJECT = "[INFCOVID] Daily Report";

    @Override
    public void save(EmailAddressDTO emailAddressDTO) throws EmailException {
        if (emailAddressRepository.findByAddress(emailAddressDTO.getAddress()) != null) {
            throw new EmailException("Vous êtes déjà abonné aux diffusions.");
        }
        emailAddressRepository.save(emailAddressDTO);
    }

    /**
     * <p>
     *     S'exécute tous les jours à 21h, envoie un rapport journalier par mail aux inscrits.
     *     TODO remplacer le "content"
     * </p>
     */
    @Override
    @Scheduled(cron = "0 0 21 * * *")
    public void SendDailyReport() {
        List<EmailAddressDTO> recipients = emailAddressRepository.findAll();
        recipients.forEach(m -> sendSimpleMessage(m.getAddress(), SUBJECT, "content"));
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("INFCOVID");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


}
