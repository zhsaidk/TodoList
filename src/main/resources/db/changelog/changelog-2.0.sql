--liquibase formatted sql

--changeset zhavokhir:2.1
INSERT INTO users(first_name, last_name, username, birth_date, password, role)
VALUES ('Anna', 'Morozova', 'anna@gmail.com', '2005-02-02', '{noop}123', 'USER'),
       ('Ivan', 'Ivanov', 'ivan@gmail.com', '2005-02-02', '{noop}123', 'USER'),
       ('Sveta', 'Svetnikova', 'sveta@gmail.com', '2003-02-02', '{noop}123', 'ADMIN'),
       ('Boris', 'Borisov', 'boris@gmail.com', '2001-02-02', '{noop}123', 'USER'),
       ('Nikolay', 'Morozov', 'nikolay@gmail.com', '2003-02-02', '{noop}123', 'USER');
--rollback delete from users where first_name in ('Anna', 'Ivan', 'Sveta', 'Boris', 'Nikolay')

--changeset zhavokhir:2.2
INSERT INTO tasks(message, user_id)
VALUES ('task1', 1),
       ('task2', 1),
       ('task3', 1);