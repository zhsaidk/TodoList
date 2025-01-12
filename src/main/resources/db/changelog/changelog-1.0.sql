--liquibase formatted sql

--changeset zhavokhir:1.0
create table if not exists "users"
(
    id         bigserial primary key,
    first_name VARCHAR(64)  NOT NULL,
    last_name  VARCHAR(64)  NOT NULL,
    username      VARCHAR(128) NOT NULL,
    birth_date DATE,
    password   VARCHAR(128) NOT NULL,
    role       VARCHAR(32),
    constraint uq_full_name unique (first_name, last_name)
);
--rollback drop table if exists user

--changeset zhavokhir:1.1
create table if not exists "tasks"
(
    id      BIGSERIAL PRIMARY KEY,
    message VARCHAR(64),
    user_id BIGINT REFERENCES "users" (id) on delete set null
);
--rollback drop table if exists task