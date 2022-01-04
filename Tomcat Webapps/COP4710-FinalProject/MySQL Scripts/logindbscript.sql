drop database if exists logindb;
create database logindb;
use logindb;

drop table if exists login;
create table login (
	username varchar(45) not null,
	password varchar(45) not null,
    name varchar(45) not null,
    email varchar(45) not null,
    accountType varchar(45) not null,
	primary key (username)
);
insert into login values ('superadmin', 'password', 'Super Admin', 'superadmin@cop4710.com', 'admin');

drop table if exists book;
create table book (
	title varchar(45) not null,
	author varchar(45) not null,
    edition varchar(45) not null,
    publisher varchar(45) not null,
    isbn varchar(45) not null,
    username varchar(45) not null,
    semester varchar(45) not null,
	primary key (username,semester,isbn)
);

drop table if exists faculty;
create table faculty (
	name varchar(45) not null,
	email varchar(45) not null,
	primary key (email)
);
insert into faculty values ('John Smith', 'facultyone@cop4710.com');	