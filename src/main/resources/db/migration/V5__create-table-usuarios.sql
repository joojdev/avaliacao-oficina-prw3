create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)

);

-- usuário padrão para testar a API (senha: 123456, guardada com hash BCrypt)
insert into usuarios (login, senha)
values ('admin@oficina.com', '$2a$10$g1GibGdS4Yd9OGnKAqvM9u/jCV58fkVU2bGRP0VWJuriPfVqbnV4C');
