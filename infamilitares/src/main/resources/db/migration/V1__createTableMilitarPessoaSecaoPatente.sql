create table secao(
	id bigint auto_increment primary key,
    nome varchar(255) not null
);

create table patente(
	id bigint auto_increment primary key,
    nome varchar(255) not null
);

create table militar (
	id bigint auto_increment primary key,
	nome varchar(100) not null,
	email varchar(100) not null,
    telefone varchar(20) not null,
    nome_guerra varchar(50) not null,
    secao_id bigint,
    patente_id bigint,
    senha varchar(100) not null,
    is_ativo boolean default false,
	foreign key (patente_id) references patente(id),
	foreign key (secao_id) references secao(id)
);

insert into secao(nome) values
("SARGENTEACAO"), ("ARMARIA"), ("ALMOXARIFADO"), ("SAUDE");

insert into patente(nome) values
("SOLDADO EV"), ("SOLDADO EP"), ("CABO"), ("III SARGENTO");

insert into militar(nome, email, telefone, nome_guerra, secao_id, patente_id, senha) values
("Augusto Neves Lima", "augusto.nevesti@gmail.com.br", "71981160642", "ev00", 1, 1, "infasenhabase"),
("Soldado EV 02", "emailbase@infa.com.br", "71999999999", "ev02", 1, 1, "infasenhabase"),
("Soldado EV 03", "emailbase@infa.com.br", "71999999999", "ev03", 1, 1, "infasenhabase"),
("Soldado EV 04", "emailbase@infa.com.br", "71999999999", "ev04", 1, 1, "infasenhabase"),
("Soldado EV 05", "emailbase@infa.com.br", "71999999999", "ev05", 1, 1, "infasenhabase"),
("Soldado EV 06", "emailbase@infa.com.br", "71999999999", "ev06", 1, 1, "infasenhabase"),
("Soldado EV 07", "emailbase@infa.com.br", "71999999999", "ev07", 1, 1, "infasenhabase"),
("Soldado EV 08", "emailbase@infa.com.br", "71999999999", "ev08", 1, 1, "infasenhabase"),
("Soldado EV 09", "emailbase@infa.com.br", "71999999999", "ev09", 1, 1, "infasenhabase"),
("Soldado EV 10", "emailbase@infa.com.br", "71999999999", "ev10", 1, 1, "infasenhabase"),
("Soldado EV 11", "emailbase@infa.com.br", "71999999999", "ev11", 1, 1, "infasenhabase"),
("Soldado EV 12", "emailbase@infa.com.br", "71999999999", "ev12", 1, 1, "infasenhabase"),
("Soldado EV 13", "emailbase@infa.com.br", "71999999999", "ev13", 1, 1, "infasenhabase"),
("Soldado EV 14", "emailbase@infa.com.br", "71999999999", "ev14", 1, 1, "infasenhabase"),
("Soldado EV 15", "emailbase@infa.com.br", "71999999999", "ev15", 1, 1, "infasenhabase"),
("Soldado EV 16", "emailbase@infa.com.br", "71999999999", "ev16", 1, 1, "infasenhabase"),
("Soldado EP 01", "emailbase@infa.com.br", "71999999999", "ep01", 1, 2, "infasenhabase"),
("Soldado EP 02", "emailbase@infa.com.br", "71999999999", "ep01", 1, 2, "infasenhabase"),
("Soldado EP 03", "emailbase@infa.com.br", "71999999999", "ep01", 1, 2, "infasenhabase"),
("Cabo 01", "emailbase@infa.com.br", "71999999999", "cb01", 1, 3, "infasenhabase"),
("Cabo 02", "emailbase@infa.com.br", "71999999999", "cb02", 1, 3, "infasenhabase"),
("Cabo 03", "emailbase@infa.com.br", "71999999999", "cb03", 1, 3, "infasenhabase"),
("Sargento 01", "emailbase@infa.com.br", "71999999999", "sgt01", 1, 4, "infasenhabase"),
("Sargento 02", "emailbase@infa.com.br", "71999999999", "sgt02", 1, 4, "infasenhabase"),
("Sargento 03", "emailbase@infa.com.br", "71999999999", "sgt03", 1, 4, "infasenhabase");
