DROP TABLE IF EXISTS "sms_keys";

CREATE TABLE "sms_keys" (
  id SERIAL PRIMARY KEY,
  code varchar(255),
  expire_time varchar(255),
);