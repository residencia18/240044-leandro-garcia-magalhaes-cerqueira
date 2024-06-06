create table scores (
  score integer,
  id bigserial not null,
  product_id bigint not null,
  user_id bigint,
  user_comment varchar(255),
  primary key (id)
);

-- Primeiro Insert
INSERT INTO scores (score, product_id, user_id, user_comment) VALUES (5, 1, 1, 'Excellent product, highly recommend!');

-- Segundo Insert
INSERT INTO scores (score, product_id, user_id, user_comment) VALUES (3, 2, 2, 'Decent quality, but could be improved.');