create table if not exists aparelho (
	id int not null auto_increment,
	nome varchar(20),
	modelo varchar(20),
	marca varchar(20),
	potencia varchar(20),

	primary key (id)
);