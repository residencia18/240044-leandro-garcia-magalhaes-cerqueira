CREATE TABLE IF NOT EXISTS public.BOOK
(
	id SERIAL,
	title VARCHAR (255),
	genre VARCHAR (255),
	subgenre VARCHAR (255),
	publication_date DATE,
	page_count INTEGER,
	stars INTEGER,
	review VARCHAR (1000),
	cover VARCHAR (500),
	is_physical BOOLEAN,
	publisher_id INTEGER REFERENCES publisher(id),
	primary key (id));