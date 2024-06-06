alter table if exists scores 
  add constraint FKnbh6g2yu7yj3qst6w8s0ycxmg 
  foreign key (product_id) 
  references product;

alter table if exists scores 
  add constraint FKtkgoiahryd4yntgywbqyyw8o8 
  foreign key (user_id) 
  references users;