create table customer
(
    id          serial constraint customer_pk primary key,
    "firstname" varchar,
    "lastname"  varchar,
    birthdate   varchar
);

create table customer2
(
    id          serial constraint customer2_pk primary key,
    "firstname" varchar,
    "lastname"  varchar,
    birthdate   varchar
);