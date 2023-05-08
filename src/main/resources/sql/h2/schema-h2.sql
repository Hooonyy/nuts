DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    users_seq  SERIAL       NOT NULL,
    users_id   VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    users_name VARCHAR(100),
    address    VARCHAR(100),
    age        VARCHAR(255),
    file_path  varchar(255),
    primary key (users_seq)
);
truncate table users restart identity;
ALTER TABLE users
    ALTER COLUMN users_seq RESTART WITH 17;


DROP TABLE IF EXISTS users_address;
CREATE TABLE users_address
(
    users_address_seq SERIAL NOT NULL,
    users_seq         bigint NOT NULL,
    address1          VARCHAR(100),
    address2          VARCHAR(100),
    nickname          VARCHAR(100),
    is_main_address   VARCHAR(1),
    primary key (users_address_seq)
);
truncate table users_address restart identity;
ALTER TABLE users_address
    ALTER COLUMN users_address_seq RESTART WITH 10;

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    product_seq           bigint       NOT NULL,
    product_name          VARCHAR(100) not null,
    product_imgpath       VARCHAR(100),
    product_price         bigint       NOT NULL,
    product_contents      VARCHAR(100),
    product_etc           VARCHAR(100),
    product_discount_code VARCHAR(100),
    product_discount      bigint       NOT NULL,
    product_isdel         VARCHAR(100),
    primary key (product_seq)
);
truncate table product restart identity;
ALTER TABLE product
    ALTER COLUMN product_seq RESTART WITH 11;