package com.jordiribeiro.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.ItemPedido;
import com.jordiribeiro.cursomc.domain.PagamentoComBoleto;
import com.jordiribeiro.cursomc.domain.Pedido;
import com.jordiribeiro.cursomc.domain.enums.EstadoPagamento;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.repositories.ItemPedidoRepository;
import com.jordiribeiro.cursomc.repositories.PagamentoRepository;
import com.jordiribeiro.cursomc.repositories.PedidoRepository;
import com.jordiribeiro.cursomc.repositories.ProdutoRepository;
import com.jordiribeiro.cursomc.security.UserSS;
import com.jordiribeiro.cursomc.services.exceptions.AuthorizationException;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoservice;
	
	@Autowired
	private PagamentoRepository pagamentorepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidorepository;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private EmailService emailservice;
	
	
	public Pedido find(Integer id) {
		Pedido obj=repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeti não encontrado"+ id + "\n, Tipo"+ Pedido.class.getName());
		}
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
 		obj.setId(null);
        	obj.setInstante(new Date());
        	obj.setCliente(clienterepository.findOne(obj.getCliente().getId()));
 		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
 		obj.getPagamento().setPedido(obj);
 		if (obj.getPagamento() instanceof PagamentoComBoleto) {
 			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
 			boletoservice.preencherPagamentoComBoleto(pagto, obj.getInstante());
 		}
 		obj = repo.save(obj);
 		pagamentorepository.save(obj.getPagamento());
 		for (ItemPedido ip : obj.getItens()) {
 			ip.setDesconto(0.0);
 			ip.setProduto(produtorepository.findOne(ip.getProduto().getId()));
 			ip.setPreco(ip.getProduto().getPreco());
 			ip.setPedido(obj);
 		}
 		itemPedidorepository.save(obj.getItens());
 		
 		emailservice.sendOrderConfirmationEmail(obj);;
 		
 		return obj;
 	}
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy ,String direction) {
		UserSS user=UserService.authenticated();
		
		if(user==null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Cliente cliente= clienterepository.findOne(user.getID());
		
		return repo.findByCliente(cliente, pageRequest);
	}
}
