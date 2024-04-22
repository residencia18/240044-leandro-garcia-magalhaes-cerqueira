-- Table: public.author

-- DROP TABLE IF EXISTS public.author;

CREATE TABLE IF NOT EXISTS public.author
(
    id bigint NOT NULL DEFAULT nextval('author_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT author_pkey PRIMARY KEY (id)
)