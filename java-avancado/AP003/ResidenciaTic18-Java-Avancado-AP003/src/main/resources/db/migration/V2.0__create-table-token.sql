create table token (
  date_creation timestamp(6),
  id bigserial not null,
  user_id bigint unique,
  token varchar(255),
  primary key (id)
);