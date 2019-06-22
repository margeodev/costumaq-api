package com.costumaqos.api.repository.equipamento;

import org.springframework.data.jpa.repository.JpaRepository;

import com.costumaqos.api.model.Equipamento;



public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>, EquipamentoRepositoryQuery {

}
