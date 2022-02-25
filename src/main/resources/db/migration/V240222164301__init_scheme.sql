CREATE TABLE customers
(
    id         BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    latitude   DOUBLE      NOT NULL,
    longitude  DOUBLE      NOT NULL
);

CREATE TABLE warehouse
(
    id        BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(20) NOT NULL,
    latitude  DOUBLE      NOT NULL,
    longitude DOUBLE      NOT NULL
);

CREATE TABLE product
(
    id   BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE warehouse_product
(
    id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    warehouse_id BIGINT NOT NULL,
    product_id   BIGINT NOT NULL
);

CREATE TABLE customer_order
(
    id                             BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer_id                    BIGINT      NOT NULL,
    product_id                     BIGINT      NOT NULL,
    warehouse_id                   BIGINT      NOT NULL,
    customer_warehouse_distance_id BIGINT      NOT NULL,
    status                         VARCHAR(15) NOT NULL
);

CREATE TABLE customer_warehouse_distance
(
    id           BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer_id  BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL,
    distance     DOUBLE NOT NULL
);


