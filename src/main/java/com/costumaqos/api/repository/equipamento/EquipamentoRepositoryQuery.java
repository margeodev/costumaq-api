package com.costumaqos.api.repository.equipamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.costumaqos.api.model.Equipamento;
import com.costumaqos.api.repository.filter.EquipamentoFilter;
import com.costumaqos.api.repository.projection.ResumoEquipamento;



public interface EquipamentoRepositoryQuery {

	public Page<Equipamento> filtrar(EquipamentoFilter equipamentoFilter, Pageable pageable);
	public List<ResumoEquipamento> resumir();
	
}
