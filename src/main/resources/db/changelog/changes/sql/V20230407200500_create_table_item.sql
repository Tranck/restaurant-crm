CREATE TABLE IF NOT EXISTS public.item (
    id              BIGSERIAL       PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    description     TEXT,
    category        BIGINT,
    weight          INT,
    picture         TEXT,
    price           DECIMAL,
    CONSTRAINT fk_category FOREIGN KEY (category) REFERENCES public.item_category (id)
);