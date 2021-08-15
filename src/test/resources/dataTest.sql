
-------------------------INSERT USER TABLE-------------------------
insert into user (id, name, email, dob, password) values (1, 'esmaeeil', 'esmaeeil@gmail.com', '2020-2-5', 'pass');

insert into user (id, name, email, dob, password) values (2, 'ali', 'ali@gmail.com', '2020-2-5', 'pass');

insert into user (id, name, email, dob, password) values (3, 'ans', 'ans@gmail.com', '2020-2-5', 'pass');
--------------------------------------------------------------------

-------------------------INSERT BUYER TABLE-------------------------
insert into buyer (id) values (1);

insert into buyer (id) values (2);

insert into buyer (id) values (3);
--------------------------------------------------------------------


-------------------------INSERT PRODUCT TABLE-------------------------
insert into product (id, name,  description, category , price ,  in_stock ) 
values (nextval('PRODUCT_SEQ'), 'Iphone 12',  'new Iphone 12', 'Mobile', 3000, 5);

insert into product (id, name,  description, category , price ,  in_stock ) 
values (nextval('PRODUCT_SEQ'), 'Iphone 13',  'new Iphone 13', 'Mobile', 3500, 10);

insert into product (id, name,  description, category , price ,  in_stock ) 
values (nextval('PRODUCT_SEQ'), 'Lenovo legion 514',  'new Lenovo labtop', 'Labtop', 3500, 10);
--------------------------------------------------------------------


-------------------------INSERT PURCHASE TABLE-------------------------
insert into purchase (id, purchase_date,total, buyer_id) 
values (nextval('purchase_seq'), '2021-5-5', 0.0, 1);

insert into purchase (id, purchase_date,total, buyer_id) 
values (nextval('purchase_seq'), '2021-10-10', 0.0, 1);

insert into purchase (id, purchase_date,total, buyer_id) 
values (nextval('purchase_seq'), '2021-10-25', 0.0, 2);

insert into purchase (id, purchase_date,total, buyer_id) 
values (nextval('purchase_seq'), '2010-1-1', 0.0, 3);
--------------------------------------------------------------------


-------------------------INSERT PURCHASE_PRODUCT TABLE-------------------------
insert into purchase_product (purchase_id, product_id, quantity, sub_total)
values (1, 1000, 5, 15000);

insert into purchase_product (purchase_id, product_id, quantity, sub_total)
values (1, 1001, 10, 35000);

insert into purchase_product (purchase_id, product_id, quantity, sub_total)
values (1, 1002, 10, 35000);

insert into purchase_product (purchase_id, product_id, quantity, sub_total)
values (2, 1002, 3, 10500);

insert into purchase_product (purchase_id, product_id, quantity, sub_total)
values (2, 1001, 3, 10500);

--------------------------------------------------------------------

