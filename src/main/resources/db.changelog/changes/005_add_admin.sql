--liquibase formatted sql
--changeset 4abrec:005_add_admin

INSERT INTO users VALUES ('5ebecd88-01a2-4d81-ae8f-63187d4b33d9', 'admin', '$2a$12$Ac2BSo6avBVC1iS2qr.40OMyAK5ENZD7T9we9tE05CfksBiJP9vxq');