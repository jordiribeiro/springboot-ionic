package com.jordiribeiro.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jordiribeiro.cursomc.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService dbservice;
	
	
	@Bean
	public boolean intantiateDatabase() throws ParseException {
		dbservice.instantiateTestDatabase();
		return true;
		
	}
}
