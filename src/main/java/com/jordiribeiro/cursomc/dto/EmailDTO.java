package com.jordiribeiro.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message="Preencimento obrigatorio")
	@Email(message="Email invalido")
	private String email;
	
	public EmailDTO() {

	}
	
	
	public EmailDTO(String email) {
		super();
		this.email = email;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
