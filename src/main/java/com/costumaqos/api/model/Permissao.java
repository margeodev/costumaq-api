package com.costumaqos.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Permissao {

	@Id
	private Long id;
	private String descricao;
	
}
