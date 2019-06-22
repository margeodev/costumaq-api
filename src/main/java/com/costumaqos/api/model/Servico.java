package com.costumaqos.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.costumaqos.api.enums.StatusServico;
import com.costumaqos.api.enums.TipoServico;

import lombok.Data;


@Data
@Entity
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 1000)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_mecanico")
	private Mecanico mecanico;
	
	@OneToOne
	@JoinColumn(name = "codigo_ordem_servico")
	private OrdemServico ordemServico;
	
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	@Column(name = "data_garantia")
	private LocalDate dataGarantia;
	private BigDecimal valor;
	
	@Column(name = "tipo_servico")
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	
	@Column(name = "status_servico")
	@Enumerated(EnumType.STRING)
	private StatusServico statusServico;
	
	@Column(name = "concluido")
	private boolean concluido;
	
}
