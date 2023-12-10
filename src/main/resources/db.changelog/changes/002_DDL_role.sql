--liquibase formatted sql
--changeset 4abrec:002_DDL_role

CREATE TABLE role
(
    id   uuid PRIMARY KEY,
    name varchar(25) NOT NULL
);