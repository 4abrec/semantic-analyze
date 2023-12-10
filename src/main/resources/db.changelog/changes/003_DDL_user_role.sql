--liquibase formatted sql
--changeset 4abrec:003_DDL_user_role

CREATE TABLE user_role
(
    user_id uuid  REFERENCES users (id),
    role_id uuid REFERENCES role (id),
    PRIMARY KEY (user_id, role_id)
);