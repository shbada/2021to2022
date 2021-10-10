create table person (
    id bigint primary key auto_increment,
    name varchar(255),
    age varchar(255),
    address varchar(255)
);

insert into person(name, age, address)
values ('테스트', '10', '인천');

insert into person(name, age, address)
values ('홍길동', '20', '서울');

insert into person(name, age, address)
values ('아무개', '25', '강원');