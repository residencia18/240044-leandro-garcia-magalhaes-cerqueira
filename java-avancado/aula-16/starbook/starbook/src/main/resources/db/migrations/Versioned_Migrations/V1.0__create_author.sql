-- Table: public.author

-- DROP TABLE IF EXISTS public.author;

CREATE TABLE IF NOT EXISTS public.author
(
    id SERIAL,
    name character varying(200),
    CONSTRAINT author_pkey PRIMARY KEY (id)
)