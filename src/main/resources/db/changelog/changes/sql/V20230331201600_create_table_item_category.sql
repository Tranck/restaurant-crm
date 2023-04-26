CREATE TABLE IF NOT EXISTS item_category (
    id      BIGSERIAL       PRIMARY KEY,
    name    VARCHAR(255)    UNIQUE NOT NULL
);