package com.jordiribeiro.cursomc.domain;

import javax.persistence.Entity;

import com.jordiribeiro.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	private Integer numeroparcelas;
	
	public PagamentoComCartao() {
	}



	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,Integer numeroparcelas) {
		super(id, estado, pedido);
		this.numeroparcelas = numeroparcelas;
		// TODO Auto-generated constructor stub
	}



	public Integer getNumeroparcelas() {
		return numeroparcelas;
	}



	public void setNumeroparcelas(Integer numeroparcelas) {
		this.numeroparcelas = numeroparcelas;
	}
	
}
