--liquibase formatted sql
--changeset 4abrec:001_DDL_users

CREATE TABLE users
(
    id       uuid PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE,
    password varchar     NOT NULL
);