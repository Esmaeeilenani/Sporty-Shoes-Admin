--------------------Table with relation should drop first----------------------
DROP TABLE purchase_product IF EXISTS;--should be droped before (purchase and product)

DROP TABLE purchase IF EXISTS;--should be droped before (Buyer)

DROP TABLE admin IF EXISTS;-- should be droped before (user)

DROP TABLE buyer IF EXISTS;--should be droped after (purchase) and before (user)
-------------------------------------------------------------------------------


DROP TABLE user IF EXISTS;

DROP TABLE product IF EXISTS;

DROP SEQUENCE IF EXISTS product_seq;

DROP SEQUENCE IF EXISTS purchase_seq;