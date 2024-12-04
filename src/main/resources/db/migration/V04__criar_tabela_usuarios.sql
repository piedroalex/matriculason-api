create table usuarios (
    id        bigint auto_increment primary key,
    senha     varchar(100) not null,
    status    bit          not null,
    username  varchar(50)  not null,
    pessoa_id bigint       null,
    constraint UKm2dvbwfge291euvmk6vkkocao unique (username),
    constraint UKn0su9mto386dwac9we1dplsuv unique (pessoa_id)
);