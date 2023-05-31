DROP TABLE IF EXISTS "book_purchase";

CREATE TABLE "book_purchase" (
  id BIGSERIAL PRIMARY KEY,
  book_id INTEGER NULL REFERENCES books,
  purchase_id INTEGER NULL REFERENCES purchases,
  UNIQUE (book_id, purchase_id)
);