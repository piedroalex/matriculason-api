create table alunos (
    id        bigint       not null	primary key,
    cpf       varchar(100) not null,
    email     varchar(100) not null,
    nome      varchar(100) not null,
    matricula varchar(100) not null,
    curso_id  bigint       not null,
    constraint UKmc87q8fpvldpdyfo9o5633o5l unique (email),
    constraint UKnlwiu48rutiltbnjle59krljo unique (cpf),
    constraint UKprosr3jo55p8vlcu7e0g7s2ov unique (matricula),
    constraint FKdtfmtlv30938vwotyw8qo5d8t foreign key (curso_id) references cursos (id)
);

create table administradores (
    id           bigint       not null	primary key,
    cpf          varchar(100) not null,
    email        varchar(100) not null,
    nome         varchar(100) not null,
    cargo        varchar(100) not null,
    departamento varchar(100) not null,
    constraint UK16vb32dwfq32vxgxd6k6awqrq unique (cargo),
    constraint UKmc87q8fpvldpdyfo9o5633o5l unique (email),
    constraint UKmims61qt1j972b3ggvn2dgl5c unique (departamento),
    constraint UKnlwiu48rutiltbnjle59krljo unique (cpf)
);