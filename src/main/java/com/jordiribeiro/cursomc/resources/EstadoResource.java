package com.jordiribeiro.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jordiribeiro.cursomc.domain.Estado;
import com.jordiribeiro.cursomc.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> find(@PathVariable Integer id) {
			
			Estado obj=service.buscar(id);
			return ResponseEntity.ok().body(obj);
		}
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> inserir(@RequestBody Estado obj){
			service.inserir(obj);
			URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		
}
