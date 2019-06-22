package com.costumaqos.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Size(max=1000)
	@Column(name = "descricao_problema")
	private String descricaoProblema;
	
	@Size(max=500)
	@Column(name = "observacao_equipamento")
	private String observacaoEquipamento;
	
	@NotNull
	@Column(name = "data_recebimento")
	private LocalDate dataEntrada;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_equipamento")
	private Equipamento equipamento;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
}
