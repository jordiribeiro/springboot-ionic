package com.jordiribeiro.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailservice;
	
	private Random rand=new Random();
	
	 public void sendNewPassword(String email){
		 Cliente cli = clienterepository.findByEmail(email);
		 
		 if(cli==null){
			 throw new ObjectNotFoundException("Email não encontrava");
		 }
		 String newPass=newPassword();
		  cli.setSenha(pe.encode(newPass));
		  clienterepository.save(cli);
		  emailservice.sendNewPasswordEmail(cli,newPass);
	 }

	private String newPassword() {
		
		char[] vet=new char[10];
		for (int i=0;i<10;i++) {
			vet[i]=randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt=rand.nextInt(3);
		
		if(opt==0) {//gera digito
			return (char) (rand.nextInt(10)+48);
		}else if (opt == 1) {//gera letra maiuscula
			return (char) (rand.nextInt(26)+65);
		}else {//gera letra minuscula
			return (char) (rand.nextInt(26)+97);
		}
		
		
	}
}
