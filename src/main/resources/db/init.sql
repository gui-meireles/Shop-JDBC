create database demo_db;

use demo_db;

create table category
(
    cat_id int auto_increment
        primary key,
    name   varchar(50) not null
);

create table product
(
    prd_id      int auto_increment
        primary key,
    name        varchar(50)  not null,
    description varchar(255) null,
    cat_id      int          not null,
    constraint product_category_cat_id_fk
        foreign key (cat_id) references category (cat_id)
);

create table shop
(
    shp_id  int auto_increment
        primary key,
    name    varchar(50)  not null,
    address varchar(255) null
);

create table product_shop
(
    prd_id int not null,
    shp_id int not null,
    constraint product_shop_product_prd_id_fk
        foreign key (prd_id) references product (prd_id),
    constraint product_shop_shop_shp_id_fk
        foreign key (shp_id) references shop (shp_id)
);
