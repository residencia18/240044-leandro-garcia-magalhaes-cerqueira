create table cart_item (
  price numeric(38,2),
  quantity integer not null,
  cart_id bigint,
  id bigserial not null,
  product_id bigint,
  primary key (id)
);

-- Primeiro Insert
INSERT INTO cart_item (price, quantity, cart_id, product_id) VALUES (99.99, 2, 1, 1);

-- Segundo Insert
INSERT INTO cart_item (price, quantity, cart_id, product_id) VALUES (199.99, 1, 2, 2);