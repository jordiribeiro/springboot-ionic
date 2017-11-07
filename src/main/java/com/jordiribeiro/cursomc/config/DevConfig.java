package com.jordiribeiro.cursomc.config;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jordiribeiro.cursomc.services.DbService;
import com.jordiribeiro.cursomc.services.EmailService;
import com.jordiribeiro.cursomc.services.SmtpEmailService;


@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DbService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	
	@Bean
	public  EmailService emailservice() {
		return new SmtpEmailService();
		
	}
}