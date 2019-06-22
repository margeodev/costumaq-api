package com.costumaqos.api.repository.projection;

import java.time.LocalDate;

import com.costumaqos.api.enums.TipoServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResumoOrdemServico {

	private Long codigo;
	private String descricao;
	private LocalDate dataEntrada;
	private String equipamento;
	private String cliente;
	private TipoServico tipoServico;
	
}
