CREATE TABLE file_link
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	file_link_type VARCHAR(10),
	url CHAR(65) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE file_link
    ADD CONSTRAINT UNIQUE INDEX uq_file_link_url(url);

CREATE TABLE customer
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(60),
	phone VARCHAR(60),
	email VARCHAR(60) NOT NULL,
	file_link_id BIGINT,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE customer
    ADD CONSTRAINT fk_customer__file_link_file_link_id
    FOREIGN KEY (file_link_id) REFERENCES file_link (id);

ALTER TABLE customer
    ADD CONSTRAINT UNIQUE INDEX uq_customer_email (email);

CREATE TABLE product
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    pre_tax DECIMAL(15, 2) NOT NULL,
    after_tax DECIMAL(15, 2) NOT NULL,
    code VARCHAR(50) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE product
    ADD CONSTRAINT UNIQUE INDEX uq_product_name (name);

ALTER TABLE product
    ADD CONSTRAINT UNIQUE INDEX uq_product_code (code);

CREATE TABLE customer_order
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    order_status VARCHAR(20) NOT NULL,
    pre_tax DECIMAL(15, 2) NOT NULL,
    after_tax DECIMAL(15, 2) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE customer_order
    ADD CONSTRAINT fk_customer_order__customer_customer_id
    FOREIGN KEY (customer_id) REFERENCES customer (id);

CREATE TABLE customer_order_product
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    pre_tax DECIMAL(15, 2) NOT NULL,
    after_tax DECIMAL(15, 2) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE customer_order_product
    ADD CONSTRAINT fk_customer_order_product__customer_order_customer_order_id
    FOREIGN KEY (customer_order_id) REFERENCES customer_order (id);

ALTER TABLE customer_order_product
    ADD CONSTRAINT fk_customer_order_product__product_product_id
    FOREIGN KEY (product_id) REFERENCES product (id);

CREATE TABLE product_file_link
(
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     product_id BIGINT NOT NULL,
     file_link_id BIGINT NOT NULL
);

ALTER TABLE product_file_link
    ADD CONSTRAINT fk_product_file_link__product_product_id
    FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product_file_link
    ADD CONSTRAINT fk_product_file_link__file_link_file_link_id
    FOREIGN KEY (file_link_id) REFERENCES file_link (id);