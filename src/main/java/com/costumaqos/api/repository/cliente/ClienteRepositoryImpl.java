package com.costumaqos.api.repository.cliente;

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

import com.costumaqos.api.model.Cliente;
import com.costumaqos.api.repository.filter.ClienteFilter;
import com.costumaqos.api.repository.projection.ResumoCliente;
import com.costumaqos.api.repository.projection.ResumoClienteMax;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = builder.createQuery(Cliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cliente> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
	}
	
	@Override
	public Page<ResumoCliente> resumir(ClienteFilter clienteFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoCliente> criteria = builder.createQuery(ResumoCliente.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		criteria.select(builder.construct(ResumoCliente.class, 
				root.get("id"), root.get("nome"), 
				root.get("cpf"), root.get("telefone"),
				root.get("whatsapp")));
		
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoCliente> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(clienteFilter));
	}
	

	private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(clienteFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + clienteFilter.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(clienteFilter.getCpf())) {
			predicates.add(builder.like(root.get("cpf"), "%" + clienteFilter.getCpf() + "%"));
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
	
	private Long total(ClienteFilter clienteFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
		Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<ResumoClienteMax> resumir() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoClienteMax> criteria = builder.createQuery(ResumoClienteMax.class);
		Root<Cliente> root = criteria.from(Cliente.class);
		
				
		criteria.select(builder.construct(ResumoClienteMax.class, 
				root.get("id"), root.get("nome"))).orderBy(builder.desc(root.get("id")));		
		
		TypedQuery<ResumoClienteMax> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}	

}
