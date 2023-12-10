--liquibase formatted sql
--changeset 4abrec:004_DML_role

INSERT INTO role VALUES ('30f55f30-ee75-44f4-bbe0-4763ba12f87a', 'ADMIN'),
                        ('22a359b6-ef4c-4c77-818b-930f3d6652ce', 'USER')