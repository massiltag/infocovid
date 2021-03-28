package com.pantheonsorbonne.infocovid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class InfocovidApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfocovidApplication.class, args);
	}

	// Bean for performing REST queries to remote APIs
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Bean for sending e-mails with GMail
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("infcovidoo@gmail.com");
		mailSender.setPassword("1234ABCD");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		return mailSender;
	}

}
