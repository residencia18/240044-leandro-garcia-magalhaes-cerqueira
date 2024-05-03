CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(255),
    password varchar(255),
    email varchar(255),
    role varchar(255)
);
