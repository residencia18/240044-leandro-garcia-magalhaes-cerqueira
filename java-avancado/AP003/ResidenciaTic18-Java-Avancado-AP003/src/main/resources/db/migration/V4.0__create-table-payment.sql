create table payment (
  amount numeric(38,2),
  payment_type smallint check (payment_type between 0 and 3),
  id bigserial not null,
  payday timestamp(6),
  primary key (id)
);

-- Primeiro Insert
INSERT INTO payment (amount, payment_type, payday) VALUES (150.75, 1, '2023-05-20 15:30:00.000000');

-- Segundo Insert
INSERT INTO payment (amount, payment_type, payday) VALUES (299.99, 2, '2023-05-21 10:15:00.000000');