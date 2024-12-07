create table permissoes (
    id        bigint auto_increment primary key,
    descricao varchar(100) not null,
    role      varchar(50)  not null,
    constraint UK38hnxjpwmg8h2hmm9ppwcgu3f unique (role),
    constraint UKkp0r3oerafjatth8tegfvedge unique (descricao)
);