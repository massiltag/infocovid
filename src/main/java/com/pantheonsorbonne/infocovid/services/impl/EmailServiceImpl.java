package com.pantheonsorbonne.infocovid.services.impl;

import com.pantheonsorbonne.infocovid.domain.dto.EmailAddressDTO;
import com.pantheonsorbonne.infocovid.domain.model.Metrics;
import com.pantheonsorbonne.infocovid.exceptions.EmailException;
import com.pantheonsorbonne.infocovid.repositories.EmailAddressRepository;
import com.pantheonsorbonne.infocovid.services.EmailService;
import com.pantheonsorbonne.infocovid.services.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service d'envoi de mail
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MetricsService metricsService;

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
     * </p>
     */
    @Override
    @Scheduled(cron = "0 0 21 * * *")
    public void SendDailyReport() {
        List<EmailAddressDTO> recipients = emailAddressRepository.findAll();
        Metrics m = metricsService.getForDay(LocalDate.now().minusDays(1));
        Metrics m2 = metricsService.getForDay(LocalDate.now().minusDays(5));

        StringBuilder sb = new StringBuilder();
        sb.append("Bonjour,\n\n");
        sb.append("Voici votre compte rendu journalier du " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".\n\n");

        int dc = m.getRecap().getDchosp() + m.getRecap().getEsms_dc();
        sb.append("Récapitulatif :\n");
        sb.append("Nouveaux cas" + " : " + m.getRecap().getConf_j1() + "\n");
        sb.append("Nouveaux décès" + " : " + dc + "\n");
        sb.append("Cumul des cas confirmés" + " : " + m.getRecap().getConf() + "\n");
        sb.append("Tension réa." + " : " + String.format("%.2f", m.getRecap().getTo()) + "\n");
        sb.append("Taux de positivité" + " : " + m2.getRecap().getTx_pos() + "\n");
        sb.append("Taux d'incidence" + " : " + m2.getRecap().getTx_incid() + "\n");
        sb.append("Nombre de personnes hospitalisées" + " : " + m.getRecap().getHosp() + "\n");
        sb.append("Nombre de personnes en réa." + " : " + m.getRecap().getRea() + "\n\n");

        sb.append("Vaccination :\n");
        sb.append("Nombre de personnes vaccinées (dose 1)" + " : " + m2.getVaccineStats().getN_dose1() + "\n");
        sb.append("Nombre de personnes vaccinées (dose 2)" + " : " + m2.getVaccineStats().getN_dose2() + "\n");
        sb.append("Cumul des personnes vaccinées" + " : " + m2.getVaccineStats().getN_cum_dose1() + "\n\n");

        sb.append("Bien cordialement,\n");
        sb.append("L'équipe Scoovidoo");

        recipients.forEach(mail -> sendSimpleMessage(mail.getAddress(), SUBJECT, sb.toString()));
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
