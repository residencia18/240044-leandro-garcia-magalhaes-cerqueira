
CREATE TABLE IF NOT EXISTS public.APP_USER
(
    id SERIAL PRIMARY KEY,
    username character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default" 
)