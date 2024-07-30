Create database sistemaestoquebebida;

drop table estoque;
drop table sessao;
drop table bebida;
drop table tipobebida;

CREATE TABLE tipobebida (
    id int PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(60) NOT NULL
);

INSERT INTO tipobebida (descricao) VALUES ('SEM ALCOOL');
INSERT INTO tipobebida (descricao) VALUES ('ALCOOLICA');

CREATE TABLE bebida (
    id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60),
    quantidade DOUBLE,
    idtipobebida int
);

CREATE TABLE sessao (
    id int PRIMARY KEY AUTO_INCREMENT,
    capacidade DOUBLE,
    volumeAtual DOUBLE,
    idbebida int,
    idtipobebida int,
	FOREIGN KEY (idbebida) REFERENCES bebida(id),
    FOREIGN KEY (idtipobebida) REFERENCES tipobebida(id)
);

CREATE TABLE estoque (
    id int PRIMARY KEY AUTO_INCREMENT,
    idsessao int,
    FOREIGN KEY (idsessao) REFERENCES sessao(id)
);

"CRIAR A TABELA DE BEBIDA SEM RELACIONAMENTO"

CREATE TABLE tipobebida (
    id int PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(60) NOT NULL
);

INSERT INTO tipobebida (descricao) VALUES ('SEM ALCOOL');
INSERT INTO tipobebida (descricao) VALUES ('ALCOOLICA');

CREATE TABLE bebida (
    id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60),
    quantidade DOUBLE,
    idTipoBebida int,
    FOREIGN KEY (idTipoBebida) REFERENCES tipobebida(id)
);

CREATE TABLE sessao (
    id int PRIMARY KEY AUTO_INCREMENT,
    capacidade DOUBLE,
    volumeAtual DOUBLE,
    quantidade DOUBLE,
    idBebida int,
    idTipoBebida int,
    FOREIGN KEY (idBebida) REFERENCES bebida(id),
    FOREIGN KEY (idTipoBebida) REFERENCES tipobebida(id)
);

CREATE TABLE estoque (
    id int PRIMARY KEY AUTO_INCREMENT,
    idsessao int,
    FOREIGN KEY (idsessao) REFERENCES sessao(id)
);




