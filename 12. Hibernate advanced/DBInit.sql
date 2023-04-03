BEGIN;

DROP TABLE IF EXISTS items CASCADE;

CREATE TABLE IF NOT EXISTS items (
id SMALLSERIAL NOT NULL UNIQUE PRIMARY KEY ,
value SMALLINT NOT NULL,
version SMALLINT NOT NULL DEFAULT 0);

INSERT INTO items (value) VALUES
(0), (0), (0), (0), (0), (0), (0), (0), (0), (0),
(0), (0), (0), (0), (0), (0), (0), (0), (0), (0),
(0), (0), (0), (0), (0), (0), (0), (0), (0), (0),
(0), (0), (0), (0), (0), (0), (0), (0), (0), (0);

CREATE INDEX ind ON items(id);

COMMIT;