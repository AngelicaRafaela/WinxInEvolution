create table if not exists transf(
    id serial,
    name varchar(30) not null,
    winx_id int not null,
    constraint pk_transf primary key(id),
    constraint winx_transf_fk foreign key (winx_id) references winx(id)
);
