create table if not exists aparelho (
	id int not null auto_increment,
	id_categoria int null,
	nome varchar(20),
	modelo varchar(20),
	marca varchar(20),
	potencia varchar(20),
	uso_medio int,

	primary key (id)
);