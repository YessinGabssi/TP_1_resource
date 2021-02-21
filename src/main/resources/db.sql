CREATE DATABASE javafx_tp1;


USE javafx_tp1;

drop table IF EXISTS produit;


CREATE TABLE produit
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom  varchar(250) NOT NULL,
    prix double default 0.0
);
