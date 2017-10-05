package com.jordiribeiro.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Pedido;
import com.jordiribeiro.cursomc.repositories.PedidoRepository;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Pedido obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Pedido.class.getName());
		}
		return obj;
	}
}
