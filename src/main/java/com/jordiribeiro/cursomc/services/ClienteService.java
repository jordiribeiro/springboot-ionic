package com.jordiribeiro.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Cliente.class.getName());
		}
		return obj;
	}
}
