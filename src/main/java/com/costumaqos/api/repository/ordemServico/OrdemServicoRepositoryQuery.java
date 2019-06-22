package com.costumaqos.api.repository.ordemServico;

import java.util.List;

import com.costumaqos.api.repository.projection.ResumoOrdemServico;

public interface OrdemServicoRepositoryQuery {
	
	public List<ResumoOrdemServico> resumir();
	
}
