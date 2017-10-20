package com.jordiribeiro.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.enums.TipoCliente;
import com.jordiribeiro.cursomc.dto.*;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.resources.exceptions.FieldMessage;
import com.jordiribeiro.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objdto, ConstraintValidatorContext context) {
		
		Map<String,String> map = (Map<String,String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id=Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		

		

		Cliente aux=repo.findByEmail(objdto.getEmail());
		if(aux!=null && !aux.getId().equals(id)) {
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
