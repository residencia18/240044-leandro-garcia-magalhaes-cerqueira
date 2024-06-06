alter table if exists cart_item 
  add constraint FK1uobyhgl1wvgt1jpccia8xxs3 
  foreign key (cart_id) 
  references cart;

alter table if exists cart_item 
  add constraint FKjcyd5wv4igqnw413rgxbfu4nv 
  foreign key (product_id) 
  references product;