CREATE TABLE IF NOT EXISTS equipamento(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	numero_serie VARCHAR(200) NOT NULL,
	modelo VARCHAR(200),
	fabricante VARCHAR(200),
    UNIQUE (numero_serie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

