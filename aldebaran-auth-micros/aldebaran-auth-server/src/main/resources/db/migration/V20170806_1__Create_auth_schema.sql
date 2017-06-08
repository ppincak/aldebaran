CREATE TABLE auth_user
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	credentials_expired BOOLEAN NOT NULL,
	email VARCHAR(200) NOT NULL,
	enabled BOOLEAN NOT NULL,
	expired BOOLEAN NOT NULL,
	locked BOOLEAN NOT NULL,
	`password` CHAR(60) NOT NULL,
	username VARCHAR(40) NOT NULL,
	first_name VARCHAR(50),
	last_name VARCHAR(60),
	gender VARCHAR(10),
	birth_date TIMESTAMP,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE auth_user
    ADD CONSTRAINT UNIQUE INDEX uq_auth_user_username (username);