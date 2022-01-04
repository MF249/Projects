drop database if exists bookdb;

create database bookdb;

use bookdb;

create table login (
	title varchar(45) not null,
	author varchar(45) not null,
    edition varchar(45) not null,
    publisher varchar(45) not null,
    isbn varchar(45) not null,
    username varchar(45) not null,
	primary key (isbn)
);