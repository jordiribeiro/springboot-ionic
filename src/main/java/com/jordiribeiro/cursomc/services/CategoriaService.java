package com.jordiribeiro.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.repositories.CategoriaRepository;
import com.jordiribeiro.cursomc.services.exceptions.DataIntegrityException;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
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
		find(id);
		try {
			repo.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possua produtos");
		}
		
	}
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public List<Categoria> findAll() {
				return repo.findAll();
	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String sortBy) {
			PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), sortBy);
			return repo.findAll(pageRequest);
	}
}
