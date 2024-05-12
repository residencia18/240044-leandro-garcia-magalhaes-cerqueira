
CREATE TABLE IF NOT EXISTS password_reset_token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    user_id SERIAL NOT NULL,
    expiry_date TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

