create table if not exists conta_luz (
	id int not null auto_increment,
	bandeira varchar(20),
	referencia date,
	vencimento date,
	consumo decimal,
	valor decimal,

	primary key (id)
);