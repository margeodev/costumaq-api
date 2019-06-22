package com.costumaqos.api.repository.equipamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.costumaqos.api.model.Equipamento;
import com.costumaqos.api.repository.filter.EquipamentoFilter;
import com.costumaqos.api.repository.projection.ResumoClienteMax;
import com.costumaqos.api.repository.projection.ResumoEquipamento;



public class EquipamentoRepositoryImpl implements EquipamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Equipamento> filtrar(EquipamentoFilter equipamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Equipamento> criteria = builder.createQuery(Equipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		
		Predicate[] predicates = criarRestricoes(equipamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Equipamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(equipamentoFilter));
	}
	
	private Predicate[] criarRestricoes(EquipamentoFilter equipamentoFilter, CriteriaBuilder builder, Root<Equipamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(equipamentoFilter.getNumeroSerie())) {
			predicates.add(builder.like(
					builder.lower(root.get("numeroSerie")), "%" + equipamentoFilter.getNumeroSerie().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(equipamentoFilter.getModelo())) {
			predicates.add(builder.like(root.get("modelo"), "%" + equipamentoFilter.getModelo() + "%"));
		}
		
		if(!StringUtils.isEmpty(equipamentoFilter.getFabricante())) {
			predicates.add(builder.like(root.get("fabricante"), "%" + equipamentoFilter.getFabricante() + "%"));
		}
				
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(EquipamentoFilter equipamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		
		Predicate[] predicates = criarRestricoes(equipamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	
	@Override
	public List<ResumoEquipamento> resumir() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoEquipamento> criteria = builder.createQuery(ResumoEquipamento.class);
		Root<Equipamento> root = criteria.from(Equipamento.class);
		
		criteria.select(builder.construct(ResumoEquipamento.class, 
				root.get("id"), root.get("numeroSerie"))).orderBy(builder.desc(root.get("id")));
		
		TypedQuery<ResumoEquipamento> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
}
