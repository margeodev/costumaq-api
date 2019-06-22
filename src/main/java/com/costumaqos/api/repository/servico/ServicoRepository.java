package com.costumaqos.api.repository.servico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.costumaqos.api.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
	List<Servico> findByConcluido(boolean concluido);
	Page<Servico> findByConcluidoOrderByIdDesc(boolean concluido, Pageable pageable);
	List<Servico> findByOrdemServicoEquipamentoNumeroSerie(String numeroSerie);
}
