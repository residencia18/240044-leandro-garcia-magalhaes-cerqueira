alter table if exists cart 
  add constraint FKgnt7redt801ttf7qhg2tn62pg 
  foreign key (payment_id) 
  references payment;

alter table if exists cart 
  add constraint FKg5uhi8vpsuy0lgloxk2h4w5o6 
  foreign key (user_id) 
  references users;