-- Consertos de exemplo para testar a API
insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('02/09/2026', '05/09/2026', 'Carlos Souza', 12, 'Volkswagen', 'Gol', '2015', 'Prata', true);

insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('08/09/2026', null, 'Ana Ribeiro', 7, 'Fiat', 'Uno', '2012', null, true);

insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('15/09/2026', '20/09/2026', 'Roberto Lima', 20, 'Chevrolet', 'Onix', '2019', 'Branco', true);

insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('22/09/2026', null, 'Fernanda Alves', 4, 'Hyundai', 'HB20', '2021', 'Vermelho', true);

insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('25/09/2026', '26/09/2026', 'Carlos Souza', 12, 'Toyota', 'Corolla', '2018', null, true);

-- conserto excluído logicamente: não aparece em /consertos/algunsdados
insert into consertos (data_entrada, data_saida, nome, anos_experiencia, marca, modelo, ano, cor, ativo)
values ('01/08/2026', '03/08/2026', 'Ana Ribeiro', 7, 'Renault', 'Sandero', '2016', 'Preto', false);
