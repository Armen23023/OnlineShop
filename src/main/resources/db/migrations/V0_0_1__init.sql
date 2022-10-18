create table categories
(
    id   SERIAL,
    name varchar(255),
    primary key (id)
);

create table product
(
    id          SERIAL,
    count       bigint,
    name        varchar(255),
    price       bigint,
    category_id serial,
    primary key (id),
    CONSTRAINT fk_product_1 FOREIGN KEY (category_id) REFERENCES categories (id)
);

create table roles
(
    id   SERIAL,
    name varchar(255) unique,
    primary key (id)
);

create table users
(
    id         SERIAL,
    email      varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    created timestamp not null default CURRENT_TIMESTAMP,
    updated timestamp not null default CURRENT_TIMESTAMP,
    password   varchar(255) not null,
    primary key (id)
);

create table user_roles
(
    user_id serial,
    role_id serial,
    CONSTRAINT fk_user_roles_1 FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_roles_2 FOREIGN KEY (role_id) REFERENCES roles (id)
);


