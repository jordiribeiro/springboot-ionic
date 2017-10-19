package com.jordiribeiro.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.enums.TipoCliente;
import com.jordiribeiro.cursomc.dto.*;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.resources.exceptions.FieldMessage;
import com.jordiribeiro.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objdto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		

		// inclua os testes aqui, inserindo erros na lista
		if (objdto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objdto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj","CPF Inválido!"));
		}
		if (objdto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objdto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj","CNPJ Inválido!"));
		}

		Cliente aux=repo.findByEmail(objdto.getEmail());
		if(aux!=null) {
			list.add(new FieldMessage("email","Email já em uso!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
