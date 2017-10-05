package com.jordiribeiro.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.repositories.CategoriaRepository;
import com.jordiribeiro.cursomc.services.exceptions.DataIntegrityException;
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
	public void inserir(Categoria obj) {
		obj.setId(null);
		repo.save(obj);
	}
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possua produtos");
		}
		
	}
}
