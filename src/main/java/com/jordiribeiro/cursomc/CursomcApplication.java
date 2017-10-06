package com.jordiribeiro.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.domain.Cidade;
import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.Endereco;
import com.jordiribeiro.cursomc.domain.Estado;
import com.jordiribeiro.cursomc.domain.ItemPedido;
import com.jordiribeiro.cursomc.domain.Pagamento;
import com.jordiribeiro.cursomc.domain.PagamentoComBoleto;
import com.jordiribeiro.cursomc.domain.PagamentoComCartao;
import com.jordiribeiro.cursomc.domain.Pedido;
import com.jordiribeiro.cursomc.domain.Produto;
import com.jordiribeiro.cursomc.domain.enums.EstadoPagamento;
import com.jordiribeiro.cursomc.domain.enums.TipoCliente;
import com.jordiribeiro.cursomc.repositories.CategoriaRepository;
import com.jordiribeiro.cursomc.repositories.CidadeRepository;
import com.jordiribeiro.cursomc.repositories.ClienteRepository;
import com.jordiribeiro.cursomc.repositories.EnderecoRepository;
import com.jordiribeiro.cursomc.repositories.EstadoRepository;
import com.jordiribeiro.cursomc.repositories.ItemPedidoRepository;
import com.jordiribeiro.cursomc.repositories.PagamentoRepository;
import com.jordiribeiro.cursomc.repositories.PedidoRepository;
import com.jordiribeiro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private ProdutoRepository produtorepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	@Autowired
	private CidadeRepository cidaderepository;
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private EnderecoRepository enderecorepository;
	
	@Autowired
	private PedidoRepository pedidorepository;
	@Autowired
	private PagamentoRepository pagamentorepository;
	@Autowired
 	private ItemPedidoRepository itempedidorepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1=new Categoria(null,"Informatica");
		Categoria cat2=new Categoria(null,"Escritorio");
		Categoria cat3=new Categoria(null,"Mesa");
		Categoria cat4=new Categoria(null,"Jogos");
		Categoria cat5=new Categoria(null,"Cozinha");
		Categoria cat6=new Categoria(null,"Pereciveis");

		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		
		
		cat1.getProduto().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProduto().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		

			
		categoriarepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6));
		produtorepository.save(Arrays.asList(p1,p2,p3));
		
		
		
		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");
		
		Cidade c1=new Cidade(null,"Uberlandia",est1);
		Cidade c2=new Cidade(null,"São Paulo",est2);
		Cidade c3=new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadorepository.save(Arrays.asList(est1,est2));
		cidaderepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1=new Cliente(null,"Maria Silva","maria@gmail.com","3333",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("3222-1900","3232-4040"));
		
		Endereco e1=new Endereco(null,"rua flores","300","Aptpo 303","Jardim","38220834",cli1,c1);
		
		Endereco e2=new Endereco(null,"Avenida matos","105","Sala 800","centro","38777012",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienterepository.save(Arrays.asList(cli1));
		enderecorepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyy hh:m");
		
		Pedido ped1=new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2=new Pedido(null,sdf.parse("10/10/2017 19:32"),cli1,e2);
		
		
		
		Pagamento pagto1=new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		

		Pagamento pagto2=new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/20/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		
		pedidorepository.save(Arrays.asList(ped1,ped2));
		pagamentorepository.save(Arrays.asList(pagto1,pagto2));
		
		
		ItemPedido ip1=new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2=new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3=new ItemPedido(ped2,p2,100.00,1,800.00);
		
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidorepository.save(Arrays.asList(ip1,ip2,ip3));
		
		
	}
	
}
