--liquibase formatted sql

--changeset zhavokhir:2.1
INSERT INTO users(first_name, last_name, birth_date, role)
VALUES ('Anna', 'Morozova', '2005-02-02', 'USER'),
       ('Ivan', 'Ivanov', '2005-02-02', 'USER'),
       ('Sveta', 'Svetnikova', '2003-02-02', 'ADMIN'),
       ('Boris', 'Borisov', '2001-02-02', 'USER'),
       ('Nikolay', 'Morozov', '2003-02-02', 'USER');
--rollback delete from users where first_name in ('Anna', 'Ivan', 'Sveta', 'Boris', 'Nikolay')