package com.costumaqos.api.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.costumaqos.api.enums.TipoCliente;

import lombok.Data;

@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotNull
	@Size(min=5, max=50)
	private String nome;
	
	@Size(min=10, max=30)
	private String cnpj;
	
	@Size(min=10, max=30)
	private String cpf;
	
	@NotNull
	@Size(min = 5, max = 20)
	private String telefone;
	private String whatsapp;
	private String email;
	
	@Embedded
	private Endereco endereco;	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cliente")
	private TipoCliente tipoCliente;
	
}


