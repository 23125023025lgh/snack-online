
USE db_store;
ALTER TABLE product CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
UPDATE product SET name = '香脆薯片' WHERE id = 2;
SELECT * FROM product;
