-- 1) cria a coluna ativo (ainda aceitando nulo, pois já podem existir linhas)
alter table consertos add ativo boolean;

-- 2) marca todos os consertos já cadastrados como ativos
update consertos set ativo = true;

-- 3) agora que nenhuma linha está nula, a coluna passa a ser obrigatória
alter table consertos alter column ativo set not null;
