create database cadproduto;
use cadproduto;

create table produto(
id_produto int primary key auto_increment,
nome varchar(80) not null,
categoria int,
descricao varchar(300),
codBarras char(12) not null unique,
marca int,
peso double,
preco double,
datacad date);

create table categoria(
id_categoria int primary key auto_increment,
categoria varchar(50) not null);

create table marca(
id_marca int primary key auto_increment,
marca varchar(50)not null);

alter table produto add constraint fk_produto_categoria foreign key (categoria) references categoria(id_categoria);

alter table produto add constraint fk_produto_marca foreign key (marca) references marca (id_marca);

INSERT INTO marca (id_marca, marca) VALUES
(null, "Kodilar"),
(null, "Sadia"),
(null, "Nestlé"),
(null, "Danone"),
(null, "Pepsi"),
(null, "Coca-Cola"),
(null, "Unilever"),
(null, "Procter & Gamble"),
(null, "Kimberly-Clark"),
(null, "Johnson & Johnson"),
(null, "Colgate-Palmolive"),
(null, "3M"),
(null, "Nivea"),
(null, "Pampers"),
(null, "Heineken"),
(null, "Ambev"),
(null, "Perdigão"),
(null, "Bauducco"),
(null, "Itambé"),
(null, "Pif Paf"),
(null, "Aurora");

INSERT INTO categoria (id_categoria, categoria) VALUES
(1, 'Bebidas'),
(2, 'Frios e Laticínios'),
(3, 'Carnes e Peixes'),
(4, 'Padaria e Confeitaria'),
(5, 'Hortifruti'),
(6, 'Mercearia'),
(7, 'Limpeza'),
(8, 'Higiene e Beleza'),
(9, 'Congelados'),
(10, 'Pet Shop'),
(11, 'Utilidades Domésticas');

DELIMITER //
create procedure selecionar_produtos()
begin
select id_produto,nome,categoria.categoria,descricao,codBarras,marca.marca,peso,preco,datacad from produto inner join categoria on categoria.id_categoria = produto.categoria inner join marca on marca.id_marca = produto.marca;
end //
DELIMITER ;
call selecionar_produtos();

DELIMITER //
create procedure inserir_produto(in nome varchar(80),in categoria int, in descricao varchar(300),in codbarras char(12),in marca int,in peso double,in preco double,in datacad date)
begin
insert into produto values(null,nome,categoria,descricao,codbarras,marca,peso, preco,datacad);
end //
DELIMITER ;


DELIMITER //
create procedure alterar_produto(in id_produto_dig int,in nome_dig varchar(80),in categoria_dig int, in descricao_dig varchar(300),in codbarras_dig char(12),in marca_dig int,in peso_dig double,in preco_dig double, in data_dig date)
begin
update produto set nome = nome_dig, categoria = categoria_dig, descricao = descricao_dig, codbarras = codbarras_dig, marca = marca_dig, peso = peso_dig, preco = preco_dig, datacad = data_dig where id_produto = id_produto_dig;
end //
DELIMITER ;

DELIMITER //
create procedure excluir_produto(in id_produto_dig int)
begin
	delete from produto where id_produto = id_produto_dig;
end //
DELIMITER ;

DELIMITER //
create procedure selecionar_categoria()
begin
	select * from categoria;
end //
DELIMITER ;

DELIMITER //
create procedure selecionar_marca()
begin
	select * from marca;
end //
DELIMITER ;


