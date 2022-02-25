INSERT INTO customers (id, first_name, last_name, latitude, longitude)
VALUES (1, 'Patrick', 'Washington', 54.32, 34.56),
       (2, 'Tom', 'Howard', 43.32, 26.56);


INSERT INTO warehouse (id, name, latitude, longitude)
VALUES (1, 'Amazon1', 54.54, 34.54),
       (2, 'Amazon2', 43.44, 38.52),
       (3, 'Amazon3', 13.64, 65.62),
       (4, 'Amazon4', 23.94, 45.72),
       (5, 'Amazon5', 43.42, 33.82),
       (6, 'Amazon6', 54.64, 56.23);

INSERT INTO product (id, name)
VALUES (1, 'Apple'),
       (2, 'Orange'),
       (3, 'Pear');

INSERT INTO warehouse_product (id, warehouse_id, product_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 2, 1),
       (5, 2, 2),
       (6, 2, 3),
       (7, 1, 2),
       (8, 3, 3),
       (9, 3, 1);