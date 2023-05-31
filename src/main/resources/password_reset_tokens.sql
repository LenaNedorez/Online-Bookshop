DROP TABLE IF EXISTS "password_reset_tokens";

CREATE TABLE "password_reset_tokens" (
  id SERIAL PRIMARY KEY,
  bookstore_user_id integer NULL,
  expire_time varchar(255),
);