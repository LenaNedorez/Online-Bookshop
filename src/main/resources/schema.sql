DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
                         id INT,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50)
);

DROP TABLE IF EXISTS book_browsings;

CREATE TABLE book_browsings (
                                id SERIAL PRIMARY KEY,
                                id integer NULL,
                                books_id integer NULL,
                                users_id integer NULL,
                                date_time varchar(255)
);

DROP TABLE IF EXISTS book_file;

CREATE TABLE book_file (
                           id SERIAL PRIMARY KEY,
                           id integer NULL,
                           hash varchar(36) NOT NULL,
                           type_id TEXT NULL,
                           path varchar(255),
                           book_id integer NULL
);

DROP TABLE IF EXISTS book_purchase;

CREATE TABLE book_purchase (
                               id BIGSERIAL PRIMARY KEY,
                               book_id INTEGER NULL REFERENCES books,
                               purchase_id INTEGER NULL REFERENCES purchases,
                               UNIQUE (book_id, purchase_id)
);

DROP TABLE IF EXISTS book_rate;

CREATE TABLE book_rate (
                           id SERIAL PRIMARY KEY,
                           id integer NULL,
                           book_id integer NULL,
                           user_id integer NULL,
                           rate TEXT NULL
);

DROP TABLE IF EXISTS book_review;

CREATE TABLE book_review (
                             id SERIAL PRIMARY KEY,
                             id integer NULL,
                             date_time varchar(255),
                             book_id integer NULL,
                             user_id integer NULL,
                             text TEXT default NULL
);

DROP TABLE IF EXISTS book_review_like;

CREATE TABLE book_review_like (
                                  id SERIAL PRIMARY KEY,
                                  id integer NULL,
                                  book_review_id integer NULL,
                                  value TEXT NULL
);

DROP TABLE IF EXISTS books;

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       id integer NULL,
                       pub_date varchar(255),
                       author_id integer NULL,
                       is_bestseller integer NULL,
                       slug varchar(255),
                       title TEXT default NULL,
                       image varchar(255),
                       description TEXT default NULL,
                       price integer NULL,
                       discount integer NULL,
                       purchase_id integer NULL
);

DROP TABLE IF EXISTS instore_accounts;

CREATE TABLE instore_accounts (
                                  id SERIAL PRIMARY KEY,
                                  id integer NULL,
                                  current_amount integer NULL
);

DROP TABLE IF EXISTS password_reset_tokens;

CREATE TABLE password_reset_tokens (
                                       id SERIAL PRIMARY KEY,
                                       bookstore_user_id integer NULL,
                                       expire_time varchar(255),
);

DROP TABLE IF EXISTS payment_transactions;

CREATE TABLE payment_transactions (
                                      id SERIAL PRIMARY KEY,
                                      id integer NULL,
                                      users_id integer NULL,
                                      timestamp varchar(255),
                                      amount integer NULL,
                                      status varchar(255) default NULL
);

DROP TABLE IF EXISTS purchases;

CREATE TABLE purchases (
                           id SERIAL PRIMARY KEY,
                           id integer NULL,
                           users_id integer NULL,
                           purchase_date varchar(255),
                           amount integer NULL,
                           status varchar(255) default NULL
);

DROP TABLE IF EXISTS sms_keys;

CREATE TABLE sms_keys (
                          id SERIAL PRIMARY KEY,
                          code varchar(255),
                          expire_time varchar(255),
);
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id INT,
                       name VARCHAR(50),
                       email VARCHAR(50),
                       phone VARCHAR(50),
                       password VARCHAR(50)
);