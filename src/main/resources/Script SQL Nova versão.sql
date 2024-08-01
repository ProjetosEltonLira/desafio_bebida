CREATE TABLE tipobebida (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(60) NOT NULL
);

INSERT INTO tipobebida (descricao) VALUES ('SEM ALCOOL');
INSERT INTO tipobebida (descricao) VALUES ('ALCOOLICA');
INSERT INTO tipobebida (descricao) VALUES ('REFRIGERANTE');
INSERT INTO tipobebida (descricao) VALUES ('SUCO');
INSERT INTO tipobebida (descricao) VALUES ('CERVEJA');
INSERT INTO tipobebida (descricao) VALUES ('VINHO');
INSERT INTO tipobebida (descricao) VALUES ('AGUA');

CREATE TABLE tipobebida (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(60) NOT NULL
);

CREATE TABLE secao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    capacidade DOUBLE,
    volumeAtual DOUBLE
);

CREATE TABLE bebida (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60),
    tipobebida_id INT,
    FOREIGN KEY (tipobebida_id) REFERENCES tipobebida(id)
);

-- Tabela intermediária para a relação muitos-para-muitos entre bebida e sessao, com a quantidade
CREATE TABLE bebida_secao (
    bebida_id INT,
    secao_id INT,
    quantidade DOUBLE,
    PRIMARY KEY (bebida_id, secao_id),
    FOREIGN KEY (bebida_id) REFERENCES bebida(id),
    FOREIGN KEY (secao_id) REFERENCES secao(id)
);