package com.costumaqos.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Equipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "numero_serie")
	private String numeroSerie;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String modelo;
	
	@Size(min = 1, max = 200)
	private String fabricante;
}
