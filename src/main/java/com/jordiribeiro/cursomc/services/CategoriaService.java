package com.jordiribeiro.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.repositories.CategoriaRepository;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Categoria.class.getName());
		}
		return obj;
	}
}
