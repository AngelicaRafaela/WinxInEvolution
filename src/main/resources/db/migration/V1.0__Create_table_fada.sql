create table if not exists fada(
    id serial,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    fairyAge int not null,
    fairyNationality varchar(50) not null,
    constraint pk_fada primary key(id)
);

