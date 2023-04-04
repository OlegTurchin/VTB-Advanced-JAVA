CREATE TABLE IF NOT EXISTS products
(
    id    SERIAL PRIMARY KEY NOT NULL UNIQUE,
    name  VARCHAR(50)        NOT NULL UNIQUE,
    price DOUBLE PRECISION   NOT NULL
);
insert into products (name, price) values ('Seeds', 6.6);
insert into products (name, price) values ('Toothpick', 9.85);
insert into products (name, price) values ('Bread', 4.02);
insert into products (name, price) values ('Wine', 8.04);
insert into products (name, price) values ('Sandwich', 8.72);