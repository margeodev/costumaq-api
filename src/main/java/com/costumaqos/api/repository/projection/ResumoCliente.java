package com.costumaqos.api.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResumoCliente {
	
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String whatsapp;
	
}
