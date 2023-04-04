CREATE TABLE IF NOT EXISTS persons
(
    id   SERIAL PRIMARY KEY NOT NULL UNIQUE,
    NAME VARCHAR(50)        NOT NULL
);
insert into persons (name)
values ('Ronnie');
insert into persons (name)
values ('Albertine');
insert into persons (name)
values ('Carlee');
insert into persons (name)
values ('Nancee');
insert into persons (name)
values ('Bondy');
