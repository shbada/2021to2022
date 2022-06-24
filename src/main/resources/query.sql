-- 회원
create table account (
                         id int8 not null,
                         age int4,
                         email varchar(255),
                         password varchar(255),
                         username varchar(255),
                         primary key (id)
)

-- 회원권한 (회원 : 회원권한 = 1: N)
create table account_roles (
                               account_id int8 not null,
                               role_id int8 not null,
                               primary key (account_id, role_id)
)

-- 자원
create table resources (
                           resource_id int8 not null,
                           http_method varchar(255),
                           order_num int4,
                           resource_name varchar(255),
                           resource_type varchar(255),
                           primary key (resource_id)
)

-- 권한
create table role (
                      role_id int8 not null,
                      role_desc varchar(255),
                      role_name varchar(255),
                      primary key (role_id)
)

-- 권한자원 (권한 : 자원권한 = 1 : N), (자원 : 자원권한 = 1 : N)
create table role_resources (
                                resource_id int8 not null,
                                role_id int8 not null,
                                primary key (resource_id, role_id)
)