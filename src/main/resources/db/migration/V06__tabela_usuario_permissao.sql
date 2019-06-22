CREATE TABLE IF NOT EXISTS usuario (
	id BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS permissao (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO usuario (id, nome, email, senha) values (1, 'Administrador', 'admin@costumaq.com', '$2a$10$qPihO93we3Ko3eZnlNIAEOY2Wjms92r3sYKztYQwpGPHV5jAj4eWG');

INSERT INTO usuario (id, nome, email, senha) values (2, 'Eridan', 'eridan@costumaq.com', '$2a$10$JOac.m6xlROe/ogvMdR2OeF897r91NmKUyzKsXxywsmSZrufAFBCO');

INSERT INTO permissao (id, descricao) values (1, 'ROLE_LISTAR_TUDO');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_EDITAR_TUDO');

-- ADMIN
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);

-- ERIDAN
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);