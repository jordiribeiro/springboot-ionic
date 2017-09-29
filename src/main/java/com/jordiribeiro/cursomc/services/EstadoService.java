package com.jordiribeiro.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Estado;
import com.jordiribeiro.cursomc.repositories.EstadoRepository;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public Estado buscar(Integer id) {
		Estado obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Estado.class.getName());
		}
		return obj;
	}
	public void inserir(Estado obj) {
		obj.setId(null);
		repo.save(obj);
	}
}
