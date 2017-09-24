package com.jordiribeiro.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jordiribeiro.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends  Pagamento {
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date datavencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date datapagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,Date datavencimento,Date datapagamento) {
		super(id, estado, pedido);
		this.datapagamento=datapagamento;
		this.datavencimento=datavencimento;
		// TODO Auto-generated constructor stub
	}

	public Date getDatavencimento() {
		return datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}

	public Date getDatapagamento() {
		return datapagamento;
	}

	public void setDatapagamento(Date datapagamento) {
		this.datapagamento = datapagamento;
	}
	
	
}
