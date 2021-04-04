
-- src/main/resources/db/migration/V1__Initial.sql

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age INT NOT NULL
);