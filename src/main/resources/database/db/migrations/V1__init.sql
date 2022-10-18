create table flyway_schema_history
(
    installed_rank integer                 not null,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null,
    constraint flyway_schema_history_pk
        primary key ()
);

alter table flyway_schema_history
    owner to postgres;

create unique index flyway_schema_history_pk
    on flyway_schema_history (installed_rank);

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table categories
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table categories
    owner to postgres;

create table product
(
    product_id  bigint not null
        primary key,
    count       bigint,
    name        varchar(255),
    price       bigint,
    category_id bigint
        constraint fkowomku74u72o6h8q0khj7id8q
            references categories
);

alter table product
    owner to postgres;

create table roles
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table roles
    owner to postgres;

create table users
(
    id         integer      not null
        primary key,
    email      varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255) not null
);

alter table users
    owner to postgres;

create table user_roles
(
    user_id integer not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id bigint  not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles
);

alter table user_roles
    owner to postgres;

