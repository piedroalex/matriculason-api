create table cursos (
    id   bigint auto_increment primary key,
    nome varchar(100) not null,
    constraint UK6mhpxd1gh9j6mgr8kp7idoy6e unique (nome)
);