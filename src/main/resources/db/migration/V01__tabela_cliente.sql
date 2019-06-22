CREATE TABLE IF NOT EXISTS cliente(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	cpf VARCHAR(20),
	cnpj VARCHAR(30),
	telefone VARCHAR(20) NOT NULL,
	whatsapp VARCHAR(20),
	logradouro VARCHAR(200),
	numero VARCHAR(20),
	complemento VARCHAR(200),
	bairro VARCHAR(30),
	cep VARCHAR(20),
	localidade VARCHAR(100),
	uf VARCHAR(20),
	tipo_cliente VARCHAR(30),
	email VARCHAR(100),
    UNIQUE (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

