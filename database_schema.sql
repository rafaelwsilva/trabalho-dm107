CREATE DATABASE IF NOT EXISTS trabalhoDM107;
USE trabalhoDM107;

CREATE TABLE IF NOT EXISTS usuario (
  id int NOT NULL auto_increment,
  username varchar(50),
  password varchar(50),
  PRIMARY KEY (id)
);

INSERT INTO usuario (id, username, password) VALUES
(1, 'admin', 'admin');

CREATE TABLE IF NOT EXISTS entrega (
  id int NOT NULL auto_increment,
  numero_pedido int NOT NULL,
  id_cliente int NOT NULL,
  nome_recebedor varchar(250),
  cpf_recebedor varchar(14),
  data_hora_entrega timestamp,
  PRIMARY KEY (id)
);

CREATE USER IF NOT EXISTS 'dm107'@'localhost' IDENTIFIED BY 'dm107';
GRANT ALL PRIVILEGES ON trabalhoDM107.* TO 'dm107'@'localhost';

