package com.costumaqos.api.repository.ordemServico;

import org.springframework.data.jpa.repository.JpaRepository;

import com.costumaqos.api.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, OrdemServicoRepositoryQuery {

}
