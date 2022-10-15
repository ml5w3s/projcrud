CREATE DATABASE db;
USE db;

CREATE TABLE pessoa(
id INT(5) AUTO_INCREMENT,
nome VARCHAR(35),
PRIMARY KEY(id)
);

INSERT INTO pessoa(nome) VALUES ('Bruce'),('Gorety'),('Odetty'),('Claudetty');