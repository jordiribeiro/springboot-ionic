package com.jordiribeiro.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jordiribeiro.cursomc.domain.Categoria;
import com.jordiribeiro.cursomc.domain.Cliente;
import com.jordiribeiro.cursomc.domain.Pagamento;
import com.jordiribeiro.cursomc.domain.PagamentoComBoleto;
import com.jordiribeiro.cursomc.dto.CategoriaDTO;
import com.jordiribeiro.cursomc.repositories.CategoriaRepository;
import com.jordiribeiro.cursomc.services.exceptions.DataIntegrityException;
import com.jordiribeiro.cursomc.services.exceptions.ObjectNotFoundException;

import java.util.Calendar;
import java.util.Date;



@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDatavencimento(cal.getTime());
	}
}