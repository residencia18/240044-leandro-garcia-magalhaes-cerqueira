create table users (
  active BOOLEAN not null,
  date_creation timestamp(6),
  date_modification timestamp(6),
  id bigserial not null,
  role varchar(25) not null check (role in ('ROLE_ADMIN','ROLE_CLIENT')),
  code_verifier varchar(200),
  creator_by varchar(255),
  email varchar(255) not null unique,
  modified_by varchar(255),
  password varchar(255),
  password_confirm varchar(255),
  username varchar(255) not null unique,
  primary key (id)
);

-- Primeiro Insert
INSERT INTO users (active, role, email, password, password_confirm, username
) VALUES (TRUE, 'ROLE_ADMIN', 'admin@example.com', '$2a$10$ubiDvkO1jHZOJsCBFLVeLuXvAPAyFtbKlLHxoSZg0f6wLUJU3xBvi', '$2a$10$ubiDvkO1jHZOJsCBFLVeLuXvAPAyFtbKlLHxoSZg0f6wLUJU3xBvi', 'adminuser');

-- Segundo Insert
INSERT INTO users (active, role, email, password, password_confirm, username
) VALUES (TRUE, 'ROLE_CLIENT', 'client@example.com', '$2a$10$ubiDvkO1jHZOJsCBFLVeLuXvAPAyFtbKlLHxoSZg0f6wLUJU3xBvi', '$2a$10$ubiDvkO1jHZOJsCBFLVeLuXvAPAyFtbKlLHxoSZg0f6wLUJU3xBvi', 'clientuser');