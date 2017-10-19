package com.jordiribeiro.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.domain.Cidade;
import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.Endereco;
import com.jordiribeiro.cursomc.domain.enums.TipoCliente;
import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.dto.ClienteDTO;
import com.jordiribeiro.cursomc.dto.ClienteNewDTO;
import com.jordiribeiro.cursomc.repositories.CidadeRepository;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.repositories.EnderecoRepository;
import com.jordiribeiro.cursomc.services.exceptions.DataIntegrityException;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidaderepository;
	
	@Autowired
	private EnderecoRepository enderecoeepository;
	
	public Cliente find(Integer id) {
		Cliente obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Cliente.class.getName());
		}
		return obj;
	}
	public void inserir(Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoeepository.save(obj.getEnderecos());
		
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel exlcuir um cliente que possua pedidos");
		}
		
	}
	public Cliente update(Cliente obj) {
		Cliente newobj=find(obj.getId());
		updateData(newobj,obj);
		return repo.save(newobj);
	}
	public List<Cliente> findAll() {
				return repo.findAll();
	}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy ,String direction) {
			PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objdto) {
		return new Cliente(objdto.getId(),objdto.getNome(),objdto.getEmail(),null,null);
	}
	public Cliente fromDTO(ClienteNewDTO objdto) {
		Cliente cli=new Cliente(null, objdto.getNome(), objdto.getEmail(), objdto.getCpfoucnpj(), TipoCliente.toEnum(objdto.getTipo()));
		Cidade cid=cidaderepository.findOne(objdto.getCidadeid());
		Endereco end=new Endereco(null, objdto.getLogradouro(), objdto.getNumero(), objdto.getComplemento(), objdto.getBairro(), objdto.getCep(),cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objdto.getTelefone1());
		if(objdto.getTelefone2() != null) {
			cli.getTelefones().add(objdto.getTelefone2());
		}
		if(objdto.getTelefone3() != null) {
			cli.getTelefones().add(objdto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newobj,Cliente obj) {
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}
	
	
}
