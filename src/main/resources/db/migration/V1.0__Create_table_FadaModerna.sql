create table if not exists FairyModern(
    id serial,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    fairyAge int not null,
    fairyNationality varchar(50) not null,
    constraint pk_customers primary key(id)
);

