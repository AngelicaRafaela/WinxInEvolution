create table enchantix(
	id serial,
	requisitosEnchantix varchar(250) not null,
	aparenciaEnchantix varchar(250) not null,
	poderesEnchantix varchar(250) notnull,
	fada_id int not null,
	constraint pk_enchantix primary key(id),
	constraint fada_enchantix_fk foreign key (fada_id) references fada(id)	
);

