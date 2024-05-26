create table if not exists consumo (
	id int not null auto_increment,
	id_aparelho int,
	data date,
	gasto_hora int,

	primary key (id)
);