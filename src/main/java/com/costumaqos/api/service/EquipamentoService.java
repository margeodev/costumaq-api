package com.costumaqos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.costumaqos.api.model.Equipamento;
import com.costumaqos.api.repository.equipamento.EquipamentoRepository;



@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepository equipamentos;
	
	public Equipamento atualizar(Long id, Equipamento equipamento) {
		Equipamento equipamentoSalvo = buscarEquipamentoPeloId(id);
		
		BeanUtils.copyProperties(equipamento, equipamentoSalvo, "id");
		return equipamentos.save(equipamentoSalvo);
	}

	public Equipamento buscarEquipamentoPeloId(Long id) {
		Equipamento equipamentoSalvo = equipamentos.findOne(id);
		if (equipamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return equipamentoSalvo;
	}

}
