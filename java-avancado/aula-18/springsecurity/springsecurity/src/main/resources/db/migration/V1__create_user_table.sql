CREATE TABLE IF NOT EXISTS public.users
(
    id SERIAL PRIMARY KEY,
    username varchar(255),
    password varchar(255),
    email varchar(255),
    role varchar(255)
);