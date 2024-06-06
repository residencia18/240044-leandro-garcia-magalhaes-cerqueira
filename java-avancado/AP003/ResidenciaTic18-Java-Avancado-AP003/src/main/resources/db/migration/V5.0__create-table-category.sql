create table category (
  id bigserial not null,
  name varchar(255),
  primary key (id)
);

-- Primeiro Insert
INSERT INTO category (name) VALUES ('Dell');

-- Segundo Insert
INSERT INTO category (name) VALUES ('Positivo');