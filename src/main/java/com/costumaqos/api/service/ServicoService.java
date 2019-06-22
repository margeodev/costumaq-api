package com.costumaqos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.costumaqos.api.model.Servico;
import com.costumaqos.api.repository.servico.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository orcamentos;
	
	public Servico atualizar(Long id, Servico orcamento) {
		Servico orcamentoSalvo = buscarOrcamentoPorId(id);
		
		BeanUtils.copyProperties(orcamento, orcamentoSalvo, "id");
		return orcamentos.save(orcamentoSalvo);
	}
	
	public Servico buscarOrcamentoPorId(Long id) {
		Servico orcamentoSalvo = orcamentos.findOne(id);
		if (orcamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return orcamentoSalvo;
	}
}
