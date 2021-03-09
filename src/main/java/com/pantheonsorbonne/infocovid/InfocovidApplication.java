package com.pantheonsorbonne.infocovid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InfocovidApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfocovidApplication.class, args);
	}

	// Bean for performing REST queries to remote APIs
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
