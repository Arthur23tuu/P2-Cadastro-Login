create database bd__login;
go
use bd__login;
go
create table usuario(
    usuario varchar(10) primary key,
    nome varchar(30) default '',
    senha char(32) default ''
);