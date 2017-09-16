drop table if exists turma cascade;
drop table if exists professor cascade;
drop table if exists disciplina cascade;
drop table if exists sala cascade;
drop table if exists periodo cascade;
drop table if exists indisponibilidade cascade;
drop table if exists aula cascade;
drop table if exists preferencia cascade;

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

create table preferencia (
  cd serial not null,
  professor integer not null,
  dia integer not null,
  horario integer not null,
  tipo integer not null,
  constraint pk_preferencia primary key (cd),
  constraint fk_preferencia_professor foreign key (professor)
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

insert into periodo (nome,preferencia) values ('SEGUNDO PERÍODO', 1);
insert into periodo (nome,preferencia) values ('QUARTO PERÍODO', 1);
insert into periodo (nome,preferencia) values ('SEXTO PERÍODO', 2);
insert into periodo (nome,preferencia) values ('OITAVO PERÍODO', 2);

insert into sala (nome) values ('LAB DESENVOLVIMENTO 1');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 2');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 3');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 4');
insert into sala (nome) values ('LAB DESENVOLVIMENTO 5');
insert into sala (nome) values ('LAB REDES');
insert into sala (nome) values ('LAB HARDWARE');
insert into sala (nome) values ('B2 SALA 2 S');
insert into sala (nome) values ('B5 SALA 2 S');
insert into sala (nome) values ('B8 SALA 2 S');

insert into professor (nome) values ('ELLEN');
insert into professor (nome) values ('LUCAS');
insert into professor (nome) values ('WANCHARLES');
insert into professor (nome) values ('FLAVIO');
insert into professor (nome) values ('BRUNO');
insert into professor (nome) values ('DIEGO');
insert into professor (nome) values ('MARCELA');
insert into professor (nome) values ('EROS');
insert into professor (nome) values ('RAFAEL');
insert into professor (nome) values ('EVERSON');
insert into professor (nome) values ('ISRAEL');
insert into professor (nome) values ('JOÃO PAULO');
insert into professor (nome) values ('CRISTIANO');
insert into professor (nome) values ('CRISTIANO H');
insert into professor (nome) values ('ALEXANDRE');
insert into professor (nome) values ('RAUL');

insert into disciplina (nome, cargaHoraria, periodo) values ('Mat.D', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('Prog II', 6, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('OAC', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('TGA', 4, 1);
insert into disciplina (nome, cargaHoraria, periodo) values ('Calc II', 6, 1);

insert into disciplina (nome, cargaHoraria, periodo) values ('POO I', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('SAD', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('Redes', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('AS', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('Sociologia', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('BD I', 4, 2);
insert into disciplina (nome, cargaHoraria, periodo) values ('Adm F.', 4, 2);

insert into disciplina (nome, cargaHoraria, periodo) values ('TPA', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('APL', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('Sist D.', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('Anteprojeto', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('GPS', 4, 3);
insert into disciplina (nome, cargaHoraria, periodo) values ('Empredendorismo', 4, 3);

insert into disciplina (nome, cargaHoraria, periodo) values ('PD II', 6, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Lab Redes', 2, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Android', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Meta Heur', 4, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Info e Soc', 2, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Ética de Leg', 2, 4);
insert into disciplina (nome, cargaHoraria, periodo) values ('Lab Hardware', 4, 4);

------------------------------------ Turmas -------------------------------------------
-- Turma AUX
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (1, 1, 1,0,0);

-- 2º Período
-- MD CALC TGA
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (1, 1, 9, 2, 0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (5, 1, 9, 3, 0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (4, 2, 9, 2, 0);
-- Prog
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (2, 13, 5, 2, 0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (2, 13, 5, 2, 1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (2, 13, 5, 2, 2);
-- OAC
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (3, 3, 1, 2, 1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (3, 3, 1, 2, 2);

-- 4º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (6, 12, 7,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (9, 9, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (11, 8, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (10, 7, 8,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (12, 2, 8,2,0);
-- Redes A e B
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (8, 10, 6,2,1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (8, 10, 6,2,2);
-- SAD A e B
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (7, 4, 3,2,1);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (7, 4, 3,2,2);

-- 6º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (13, 9, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (14, 2, 10,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (15, 12, 10,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (16, 8, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (17, 4, 4,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (18, 2, 10,2,0);

-- 7º Período
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (19, 8, 3,6,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (20, 10, 6,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (21, 3, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (22, 15, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (23, 13, 3,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (24, 14, 9,2,0);
insert into turma(disciplina, professor, sala, numeroAulas, tipo) values (25, 16, 7,2,0);

---------------------------------------------------------------------------------------

------------------------------------ Aulas --------------------------------------------

-- MD CALC TGA
insert into aula(turmaA, turmaB) values (2,1);
insert into aula(turmaA, turmaB) values (2,1);

insert into aula(turmaA, turmaB) values (3,1);
insert into aula(turmaA, turmaB) values (3,1);

insert into aula(turmaA, turmaB) values (4,1);
insert into aula(turmaA, turmaB) values (4,1);

--Prog
insert into aula(turmaA, turmaB) values (5,1);

--Prog + OAC

insert into aula(turmaA, turmaB) values (8,7);
insert into aula(turmaA, turmaB) values (8,7);
insert into aula(turmaA, turmaB) values (9,6);
insert into aula(turmaA, turmaB) values (9,6);


-- 3º Período

insert into aula(turmaA, turmaB) values (10,1);
insert into aula(turmaA, turmaB) values (10,1);
insert into aula(turmaA, turmaB) values (11,1);
insert into aula(turmaA, turmaB) values (11,1);
insert into aula(turmaA, turmaB) values (12,1);
insert into aula(turmaA, turmaB) values (12,1);
insert into aula(turmaA, turmaB) values (13,1);
insert into aula(turmaA, turmaB) values (13,1);
insert into aula(turmaA, turmaB) values (14,1);

insert into aula(turmaA, turmaB) values (15,18);
insert into aula(turmaA, turmaB) values (15,18);

insert into aula(turmaA, turmaB) values (16,17);
insert into aula(turmaA, turmaB) values (16,17);



-- 5º Período
insert into aula(turmaA, turmaB) values (19,1);
insert into aula(turmaA, turmaB) values (19,1);
insert into aula(turmaA, turmaB) values (20,1);
insert into aula(turmaA, turmaB) values (20,1);
insert into aula(turmaA, turmaB) values (21,1);
insert into aula(turmaA, turmaB) values (21,1);
insert into aula(turmaA, turmaB) values (22,1);
insert into aula(turmaA, turmaB) values (22,1);
insert into aula(turmaA, turmaB) values (23,1);
insert into aula(turmaA, turmaB) values (23,1);
insert into aula(turmaA, turmaB) values (24,1);
insert into aula(turmaA, turmaB) values (24,1);


-- 7º Período
insert into aula(turmaA, turmaB) values (25,1);
insert into aula(turmaA, turmaB) values (26,1);
insert into aula(turmaA, turmaB) values (27,1);
insert into aula(turmaA, turmaB) values (27,1);
insert into aula(turmaA, turmaB) values (28,1);
insert into aula(turmaA, turmaB) values (28,1);
insert into aula(turmaA, turmaB) values (29,1);
insert into aula(turmaA, turmaB) values (30,1);
insert into aula(turmaA, turmaB) values (31,1);
insert into aula(turmaA, turmaB) values (31,1);

-- insert indisponibilidade

insert into indisponibilidade(professor, dia, horario) values (14, 0, 2);
insert into indisponibilidade(professor, dia, horario) values (1, 0, 3);
insert into indisponibilidade(professor, dia, horario) values (1, 0, 5);
insert into indisponibilidade(professor, dia, horario) values (11, 1, 0);
insert into indisponibilidade(professor, dia, horario) values (11, 1, 1);
insert into indisponibilidade(professor, dia, horario) values (15, 2, 4);
insert into indisponibilidade(professor, dia, horario) values (15, 2, 5);
insert into indisponibilidade(professor, dia, horario) values (1, 3, 1);
insert into indisponibilidade(professor, dia, horario) values (1, 3, 2);
insert into indisponibilidade(professor, dia, horario) values (16, 3, 3);
insert into indisponibilidade(professor, dia, horario) values (16, 3, 4);
insert into indisponibilidade(professor, dia, horario) values (16, 3, 5);
insert into indisponibilidade(professor, dia, horario) values (16, 4, 0);
insert into indisponibilidade(professor, dia, horario) values (16, 4, 1);

insert into indisponibilidade(professor, dia, horario) values (11, 0, 8);
insert into indisponibilidade(professor, dia, horario) values (11, 0, 9);
insert into indisponibilidade(professor, dia, horario) values (9, 1, 6);
insert into indisponibilidade(professor, dia, horario) values (9, 1, 7);
insert into indisponibilidade(professor, dia, horario) values (9, 1, 8);
insert into indisponibilidade(professor, dia, horario) values (9, 1, 9);
insert into indisponibilidade(professor, dia, horario) values (15, 1, 6);
insert into indisponibilidade(professor, dia, horario) values (15, 1, 7);
insert into indisponibilidade(professor, dia, horario) values (15, 1, 8);
insert into indisponibilidade(professor, dia, horario) values (15, 1, 9);
insert into indisponibilidade(professor, dia, horario) values (6, 2, 10);
insert into indisponibilidade(professor, dia, horario) values (6, 2, 11);
insert into indisponibilidade(professor, dia, horario) values (9, 3, 6);
insert into indisponibilidade(professor, dia, horario) values (9, 3, 7);
insert into indisponibilidade(professor, dia, horario) values (6, 3, 8);
insert into indisponibilidade(professor, dia, horario) values (6, 3, 9);
insert into indisponibilidade(professor, dia, horario) values (7, 4, 0);

insert into indisponibilidade(professor, dia, horario) values (14, 1, 2);
insert into indisponibilidade(professor, dia, horario) values (3, 1, 3);
insert into indisponibilidade(professor, dia, horario) values (3, 1, 4);
insert into indisponibilidade(professor, dia, horario) values (3, 1, 5);
insert into indisponibilidade(professor, dia, horario) values (15, 3, 3);
insert into indisponibilidade(professor, dia, horario) values (15, 3, 4);
insert into indisponibilidade(professor, dia, horario) values (15, 3, 5);
insert into indisponibilidade(professor, dia, horario) values (6, 4, 0);
insert into indisponibilidade(professor, dia, horario) values (6, 4, 1);
insert into indisponibilidade(professor, dia, horario) values (6, 4, 5);

insert into indisponibilidade(professor, dia, horario) values (5, 0, 8);
insert into indisponibilidade(professor, dia, horario) values (5, 0, 9);
insert into indisponibilidade(professor, dia, horario) values (5, 0, 10);
insert into indisponibilidade(professor, dia, horario) values (7, 1, 11);
insert into indisponibilidade(professor, dia, horario) values (3, 2, 6);
insert into indisponibilidade(professor, dia, horario) values (3, 2, 7);
insert into indisponibilidade(professor, dia, horario) values (4, 3, 6);
insert into indisponibilidade(professor, dia, horario) values (4, 3, 7);
insert into indisponibilidade(professor, dia, horario) values (4, 3, 8);

insert into preferencia(professor, dia, horario, tipo) values (15, 2, 0, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 1, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 2, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 3, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 4, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 5, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 6, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 7, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 8, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 9, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 10, 0);
insert into preferencia(professor, dia, horario, tipo) values (15, 2, 11, 0);

insert into preferencia(professor, dia, horario, tipo) values (3, 1, 0, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 1, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 2, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 3, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 4, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 5, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 6, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 7, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 8, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 9, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 10, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 1, 11, 0);

insert into preferencia(professor, dia, horario, tipo) values (3, 3, 0, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 1, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 2, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 3, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 4, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 5, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 6, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 7, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 8, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 9, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 10, 0);
insert into preferencia(professor, dia, horario, tipo) values (3, 3, 11, 0);

insert into preferencia(professor, dia, horario, tipo) values (8, 0, 0, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 1, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 2, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 3, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 4, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 5, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 6, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 7, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 8, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 9, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 10, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 0, 11, 0);

insert into preferencia(professor, dia, horario, tipo) values (8, 1, 0, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 1, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 2, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 3, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 4, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 5, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 6, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 7, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 8, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 9, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 10, 0);
insert into preferencia(professor, dia, horario, tipo) values (8, 1, 11, 0);

insert into preferencia(professor, dia, horario, tipo) values (2, 2, 0, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 1, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 2, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 3, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 4, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 5, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 6, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 7, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 8, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 9, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 10, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 2, 11, 1);

insert into preferencia(professor, dia, horario, tipo) values (2, 4, 0, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 1, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 2, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 3, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 4, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 5, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 6, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 7, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 8, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 9, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 10, 1);
insert into preferencia(professor, dia, horario, tipo) values (2, 4, 11, 1);



















