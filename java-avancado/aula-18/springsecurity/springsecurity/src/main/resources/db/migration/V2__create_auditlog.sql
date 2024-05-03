CREATE TABLE audit_log(
	id SERIAL PRIMARY KEY,
	event_name VARCHAR(50) NOT NULL,
	event_description TEXT,
	timestamp TIMESTAMP,
	user_id VARCHAR(50),
	affected_resource VARCHAR(50),
	origin VARCHAR (50)
	);