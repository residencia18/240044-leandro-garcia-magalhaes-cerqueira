CREATE TABLE book_author_mapping (
		book_id INTEGER REFERENCES book(id),
		author_id INTEGER REFERENCES author(id),
		primary key (book_id, author_id));
		