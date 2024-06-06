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

create table cart_item (
  price numeric(38,2),
  quantity integer not null,
  cart_id bigint,
  id bigserial not null,
  product_id bigint,
  primary key (id)
);

create table category (
  id bigserial not null,
  name varchar(255),
  primary key (id)
);

create table payment (
  amount numeric(38,2),
  payment_type smallint check (payment_type between 0 and 3),
  id bigserial not null,
  payday timestamp(6),
  primary key (id)
);

create table product (
  price numeric(38,2),
  stock integer not null,
  category_id bigint,
  id bigserial not null,
  chassis varchar(255),
  chipset varchar(255),
  cpu varchar(255),
  description varchar(255),
  memory varchar(255),
  name varchar(255),
  network varchar(255),
  operational_system varchar(255),
  slots varchar(255),
  storage varchar(255),
  url_image varchar(255),
  primary key (id)
);

create table scores (
  score integer,
  id bigserial not null,
  product_id bigint not null,
  user_id bigint,
  user_comment varchar(255),
  primary key (id)
);

create table token (
  date_creation timestamp(6),
  id bigserial not null,
  user_id bigint unique,
  token varchar(255),
  primary key (id)
);

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

alter table if exists cart 
  add constraint FKgnt7redt801ttf7qhg2tn62pg 
  foreign key (payment_id) 
  references payment;

alter table if exists cart 
  add constraint FKg5uhi8vpsuy0lgloxk2h4w5o6 
  foreign key (user_id) 
  references users;

alter table if exists cart_item 
  add constraint FK1uobyhgl1wvgt1jpccia8xxs3 
  foreign key (cart_id) 
  references cart;

alter table if exists cart_item 
  add constraint FKjcyd5wv4igqnw413rgxbfu4nv 
  foreign key (product_id) 
  references product;

alter table if exists product 
  add constraint FK1mtsbur82frn64de7balymq9s 
  foreign key (category_id) 
  references category;

alter table if exists scores 
  add constraint FKnbh6g2yu7yj3qst6w8s0ycxmg 
  foreign key (product_id) 
  references product;

alter table if exists scores 
  add constraint FKtkgoiahryd4yntgywbqyyw8o8 
  foreign key (user_id) 
  references users;

alter table if exists token 
  add constraint FKj8rfw4x0wjjyibfqq566j4qng 
  foreign key (user_id) 
  references users;