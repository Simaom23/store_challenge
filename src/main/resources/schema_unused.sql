CREATE TABLE IF NOT EXISTS 'prices' (
  'price_id' int AUTO_INCREMENT  PRIMARY KEY,
  'brand_id' int NOT NULL,
  'start_date' timestamp NOT NULL,
  'end_date' timestamp NOT NULL,
  'price_list' int NOT NULL,
  'product_id' int NOT NULL,
  'priority' int NOT NULL,
  'price' numeric NOT NULL,
  'curr' varchar(3) NOT NULL
);
