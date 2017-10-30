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

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}
	
}
