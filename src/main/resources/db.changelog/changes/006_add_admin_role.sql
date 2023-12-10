--liquibase formatted sql
--changeset 4abrec:006_add_admin_role

INSERT INTO user_role
VALUES ('5ebecd88-01a2-4d81-ae8f-63187d4b33d9', '30f55f30-ee75-44f4-bbe0-4763ba12f87a');