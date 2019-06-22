CREATE TABLE IF NOT EXISTS servico(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(1000),
	id_mecanico BIGINT(20),
	codigo_ordem_servico  BIGINT(20) NOT NULL,
	data_entrega DATE,	
	data_garantia DATE,	
	valor DECIMAL(10,2),
	tipo_servico VARCHAR(50),
	status_servico VARCHAR(20) DEFAULT 'NAO_DEFINIDO',
	concluido TINYINT (1) DEFAULT 0,
	
	FOREIGN KEY (id_mecanico) REFERENCES mecanico(id),
	FOREIGN KEY (codigo_ordem_servico) REFERENCES ordem_servico(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;