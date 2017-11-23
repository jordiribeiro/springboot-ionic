package com.jordiribeiro.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
