DROP DATABASE IF EXISTS BD_Tindertec; 
CREATE DATABASE BD_Tindertec;
USE BD_Tindertec;

drop table if exists tb_carreras;
create table tb_carreras
(
cod_carrera int auto_increment,
des_carrera varchar(60),
primary key (cod_carrera)
);

drop table if exists tb_sedes_usu;
create table tb_sedes_usu
(
cod_sede int auto_increment,
des_sede varchar(60),
primary key (cod_sede)
);

drop table if exists tb_interes_genero;
create table tb_interes_genero
(
cod_interes int auto_increment,
des_interes varchar(60),
primary key (cod_interes)
);

drop table if exists tb_genero_usu;
create table tb_genero_usu
(
cod_genero int auto_increment,
des_genero varchar(60),
primary key (cod_genero)
);


/**/drop table if exists tb_rol;
create table tb_rol(
id int auto_increment primary key,
rol_nombre varchar(20)
);




drop table if exists tb_usuario;
create table tb_usuario
(
cod_usu int auto_increment,
nombres varchar(60),
email varchar(50),
clave varchar(200),
foto1 varchar(800),
foto2 varchar(800) default '',
foto3 varchar(800) default '',
foto4 varchar(800) default '',
foto5 varchar(800) default '',
fecha_naci date,
descripcion varchar(350),
intentos int default 0,
fecha_bloqueo datetime,
cod_carrera int,
cod_sede int,
cod_interes int,
cod_genero int,
primary key (cod_usu),
UNIQUE (email),
constraint fk_carrera foreign key (cod_carrera) references tb_carreras(cod_carrera),
constraint fk_sede foreign key (cod_sede) references tb_sedes_usu(cod_sede),
constraint fk_interes foreign key (cod_interes) references tb_interes_genero(cod_interes),
constraint fk_genero_usu foreign key (cod_genero) references tb_genero_usu(cod_genero)
);

drop table if exists usuario_rol;
create table usuario_rol(
usuario_id int auto_increment primary key,
rol_id int,
constraint fk_rol_id foreign key (rol_id) references tb_rol(id),
constraint fk_usuario_id foreign key (usuario_id) references tb_usuario(cod_usu)
);

drop table if exists tb_match;
create table tb_match
(
id_match int auto_increment,
det_match varchar(800) ,
cod_usu1 int,
cod_usu2 int,
primary key (id_match),
constraint fk_usu1 foreign key (cod_usu1) references tb_usuario(cod_usu),
constraint fk_usu2 foreign key (cod_usu2) references tb_usuario(cod_usu)
);

drop table if exists tb_likes;
create table tb_likes
(
cod_like int primary key auto_increment,
cod_usu int,
cod_usu_like int,
det_match varchar(800),
constraint fk_usu_l foreign key (cod_usu) references tb_usuario(cod_usu),
constraint fk_usu_like foreign key (cod_usu_like) references tb_usuario(cod_usu)
);

drop table if exists tb_dislike;
create table tb_dislike
(
cod_dislike int primary key auto_increment,
cod_usu int,
cod_usu_dislike int,
constraint fk_usu_dis foreign key (cod_usu) references tb_usuario(cod_usu),
constraint fk_usu_dislike foreign key (cod_usu_dislike) references tb_usuario(cod_usu)
);

drop table if exists tb_chat;
create table tb_chat(
cod_chat int primary key auto_increment,
cod_usu1 int,
cod_usu2 int,
mensaje varchar(80),
fecha datetime,
constraint fk_usu__1 foreign key (cod_usu1) references tb_usuario(cod_usu),
constraint fk_usu__2 foreign key (cod_usu2) references tb_usuario(cod_usu)
);