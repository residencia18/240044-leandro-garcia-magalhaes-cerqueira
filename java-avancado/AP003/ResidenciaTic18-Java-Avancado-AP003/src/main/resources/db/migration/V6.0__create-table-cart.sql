create table cart (
  status smallint check (status between 0 and 2),
  total_items integer not null,
  total_price numeric(38,2),
  id bigserial not null,
  payment_id bigint unique,
  purchase_date timestamp(6),
  user_id bigint not null,
  primary key (id)
);

-- Primeiro Insert
INSERT INTO cart (
  status, total_items, total_price, payment_id, purchase_date, user_id) VALUES (1, 3, 299.99, 1, '2023-05-22 12:00:00.000000', 1);

-- Segundo Insert
INSERT INTO cart (status, total_items, total_price, payment_id, purchase_date, user_id) VALUES (2, 5, 499.99, 2, '2023-05-22 14:30:00.000000', 2);