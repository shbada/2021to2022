-- users table create
create table users (
                       id int auto_increment primary key,
                       user_id varchar(20),
                       pwd varchar(20),
                       name varchar(20),
                       create_at datetime default NOW()

);


create table orders (
    id int auto_increment primary key,
    user_id varchar(50) not null,
    product_id varchar(20) not null,
    order_id varchar(50) not null,
    qty int default 0,
    unit_price int default 0,
    total_price int default 0,
    created_at datetime default now()
)