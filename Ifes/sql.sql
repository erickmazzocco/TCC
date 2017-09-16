drop table if exists turma cascade;
drop table if exists professor cascade;
drop table if exists disciplina cascade;
drop table if exists sala cascade;
drop table if exists periodo cascade;
drop table if exists indisponibilidade cascade;
drop table if exists aula cascade;

create table periodo(
  cd serial not null,
  nome varchar(50)  not null,
  preferencia integer not null,
  constraint pk_periodo primary key (cd)
);

create table disciplina(
  cd serial not null,
  nome varchar(50)  not null,
  cargaHoraria integer not null,
  periodo integer not null,
  constraint pk_disciplina primary key (cd),
  constraint fk_disciplina_periodo foreign key (periodo)
  references periodo (cd)
);

create table sala(
  cd serial not null,
  nome varchar(50)  not null,  
  constraint pk_sala primary key (cd)
);

create table professor(
  cd serial not null,
  nome varchar(50) not null,
  constraint pk_professor primary key (cd)
);

create table turma(
  cd serial not null,  
  disciplina integer not null,
  professor integer not null,
  sala integer not null,  
  numeroAulas integer not null,
  tipo integer not null,
  
  constraint pk_turma primary key (cd),
  constraint fk_turma_disciplina foreign key (disciplina)
  references disciplina (cd),
  constraint fk_turma_professor foreign key (professor)
  references professor (cd),
  constraint fk_turma_sala foreign key (sala)
  references sala (cd)    
);

create table indisponibilidade (
  cd serial not null,
  professor integer not null,
  dia integer not null,
  horario integer not null,
  constraint pk_indisponibilidade primary key (cd),
  constraint fk_indisponibilidade_professor foreign key (professor)
  REFERENCES professor (cd)
);

create table aula (
  cd serial not null,
  turmaA integer not null,
  turmaB integer null,
  constraint pk_aula primary key (cd),
  constraint pk_aula_turmaA foreign key (turmaA)
  references turma (cd),
  constraint pk_aula_turmaB foreign key (turmaB)
  references turma (cd)
);

insert into periodo (nome,preferencia) values ('PRIMEIRO PERÍODO', 1);
insert into periodo (nome,preferencia) values ('TERCEIRO PERÍODO', 1);
insert into periodo (nome,preferencia) values ('QUINTO PERÍODO', 2);
insert into periodo (nome,preferencia) values ('SÉTIMO PERÍODO', 2);

insert into sala (nome) values ('LAB DESENVOLVIMENTO 1');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 2');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 3');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 4');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 5');
insert into sala (nome) values ('LAB REDES');
insert into sala (nome) values ('B2 SALA 2 S');
insert into sala (nome) values ('B5 SALA 2 S');

insert into professor (nome) values ('RAFAEL');
insert into professor (nome) values ('EROS');
insert into professor (nome) values ('WANCHARLES');
insert into professor (nome) values ('FLAVIO');
insert into professor (nome) values ('BRUNO');
insert into professor (nome) values ('DIEGO');
insert into professor (nome) values ('GUILHERME');
insert into professor (nome) values ('ELIZÂNGELA');
insert into professor (nome) values ('ÂNGELO');
insert into professor (nome) values ('EVERSON');
insert into professor (nome) values ('CRISTIANO');
insert into professor (nome) values ('ISRAEL');
insert into professor (nome) values ('INDISPONÍVEL');
insert into professor (nome) values ('JOÃO PAULO');
insert into professor (nome) values ('GLEICE');
insert into professor (nome) values ('ALEXANDRE');

insert into disciplina (nome, cargaHoraria, periodo) values ('PROG 1', 6, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('CÁLC 1', 6, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('FSI 1', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('LÓGICA', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('COMUN EMP', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('METOD PESQ', 4, 1);

insert into disciplina (nome, cargaHoraria, periodo) values ('PROB ESTAT', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('SISTEMAS OP', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('ESTRUTURA DE DADOS', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('ADM FIN', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('TGS', 4, 2);

insert into disciplina (nome, cargaHoraria, periodo) values ('POO', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('BD2', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('SERV REDES', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('ENG SOFT', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('PROJETO SIS', 4, 3);

insert into disciplina (nome, cargaHoraria, periodo) values ('LAB ENG SOFT', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('PROJ DP 1', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('COMÉRCIO', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('DESENV WEB', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('GESTÃO DE SI', 4, 4);

------------------------------------ Turmas -------------------------------------------
-- Turma AUX
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (1, 1, 1,0,0);

-- Programação A, B
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (1, 4, 1,2,1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (1, 4, 1,2,2);

-- Cálculo A e B
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (2, 9, 8,3,1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (2, 9, 8,3,2);

-- Lógica A e B
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (4, 6, 8,2,1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (4, 6, 8,2,2);

-- FSI, Metodologia e Comunicação

insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (3, 5, 8,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (5, 7, 8,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (6, 8, 8,2,0);

-- 3º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (7, 12, 7,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (8, 10, 6,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (9, 11, 2,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (10, 13, 7,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (11, 3, 7,2,0);

-- 5º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (12, 1, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (13, 2, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (14, 14, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (15, 16, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (16, 15, 4,2,0);

-- 7º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (17, 1, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (18, 2, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (19, 3, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (20, 4, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (21, 11, 3,2,0);

---------------------------------------------------------------------------------------

------------------------------------ Aulas --------------------------------------------

--Cálculo A e Programação B
insert into aula(turmaA, turmaB) values (4,3);
insert into aula(turmaA, turmaB) values (4,3);

--Cálculo B e Lógica A
insert into aula(turmaA, turmaB) values (5,6);
insert into aula(turmaA, turmaB) values (5,6);

--Programação A e Lógica B
insert into aula(turmaA, turmaB) values (2,7);
insert into aula(turmaA, turmaB) values (2,7);

--FSI, Metodologia e Comunicação
insert into aula(turmaA, turmaB) values (8,1);
insert into aula(turmaA, turmaB) values (8,1);

insert into aula(turmaA, turmaB) values (9,1);
insert into aula(turmaA, turmaB) values (9,1);

insert into aula(turmaA, turmaB) values (10,1);
insert into aula(turmaA, turmaB) values (10,1);

-- 3º Período
insert into aula(turmaA, turmaB) values (11,1);
insert into aula(turmaA, turmaB) values (11,1);
insert into aula(turmaA, turmaB) values (12,1);
insert into aula(turmaA, turmaB) values (12,1);
insert into aula(turmaA, turmaB) values (13,1);
insert into aula(turmaA, turmaB) values (13,1);
insert into aula(turmaA, turmaB) values (14,1);
insert into aula(turmaA, turmaB) values (14,1);
insert into aula(turmaA, turmaB) values (15,1);
insert into aula(turmaA, turmaB) values (15,1);


-- 5º Período
insert into aula(turmaA, turmaB) values (16,1);
insert into aula(turmaA, turmaB) values (16,1);
insert into aula(turmaA, turmaB) values (17,1);
insert into aula(turmaA, turmaB) values (17,1);
insert into aula(turmaA, turmaB) values (18,1);
insert into aula(turmaA, turmaB) values (18,1);
insert into aula(turmaA, turmaB) values (19,1);
insert into aula(turmaA, turmaB) values (19,1);
insert into aula(turmaA, turmaB) values (20,1);
insert into aula(turmaA, turmaB) values (20,1);


-- 7º Período
insert into aula(turmaA, turmaB) values (21,1);
insert into aula(turmaA, turmaB) values (21,1);
insert into aula(turmaA, turmaB) values (22,1);
insert into aula(turmaA, turmaB) values (22,1);
insert into aula(turmaA, turmaB) values (23,1);
insert into aula(turmaA, turmaB) values (23,1);
insert into aula(turmaA, turmaB) values (24,1);
insert into aula(turmaA, turmaB) values (24,1);
insert into aula(turmaA, turmaB) values (25,1);
insert into aula(turmaA, turmaB) values (25,1);







