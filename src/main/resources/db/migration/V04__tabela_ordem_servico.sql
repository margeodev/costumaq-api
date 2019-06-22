CREATE TABLE IF NOT EXISTS ordem_servico(
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
	descricao_problema VARCHAR(1000),
	observacao_equipamento VARCHAR(500),
	data_recebimento DATE NOT NULL,		
	id_equipamento BIGINT(20) NOT NULL,
	id_cliente BIGINT(20) NOT NULL,	
	FOREIGN KEY (id_cliente) REFERENCES cliente(id),
	FOREIGN KEY (id_equipamento) REFERENCES equipamento(id),
	UNIQUE (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=10000;