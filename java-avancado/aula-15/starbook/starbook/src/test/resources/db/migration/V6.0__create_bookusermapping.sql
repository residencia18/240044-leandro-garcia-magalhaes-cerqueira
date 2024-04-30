CREATE TABLE book_user_mapping (
		book_id INTEGER REFERENCES book(id),
		user_id INTEGER REFERENCES app_user(id),
		primary key (book_id, user_id));
	