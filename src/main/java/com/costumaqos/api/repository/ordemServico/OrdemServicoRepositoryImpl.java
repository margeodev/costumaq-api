package com.costumaqos.api.repository.ordemServico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.costumaqos.api.model.OrdemServico;
import com.costumaqos.api.repository.projection.ResumoOrdemServico;

public class OrdemServicoRepositoryImpl implements OrdemServicoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<ResumoOrdemServico> resumir() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoOrdemServico> criteria = builder.createQuery(ResumoOrdemServico.class);
		Root<OrdemServico> root = criteria.from(OrdemServico.class);
		
		criteria.select(builder.construct(ResumoOrdemServico.class, 
				root.get("codigo"),
				root.get("descricao"),
				root.get("dataEntrada"),
				root.get("equipamento").get("numeroSerie"),
				root.get("cliente").get("nome")));		
				
		TypedQuery<ResumoOrdemServico> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

}
