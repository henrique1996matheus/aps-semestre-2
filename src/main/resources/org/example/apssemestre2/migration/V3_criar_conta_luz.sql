create table if not exists conta_luz (
	id int not null auto_increment,
	bandeira varchar(20),
	referencia varchar(20),
	vencimento varchar(20),
	consumo varchar(20),
	valor varchar(20),

	primary key (id)
);