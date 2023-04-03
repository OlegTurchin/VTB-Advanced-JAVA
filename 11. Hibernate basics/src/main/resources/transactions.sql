CREATE TABLE IF NOT EXISTS transactions
(
    id      SERIAL                       NOT NULL,
    person  INT REFERENCES persons (ID)  NOT NULL,
    product INT REFERENCES products (ID) NOT NULL,
    price   DOUBLE PRECISION             NOT NULL
)