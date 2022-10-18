insert into roles(name)
values ('ADMIN'),
       ('USER');

insert into users(email, first_name, last_name, password)
values ('admin@gmail.com', 'ADMIN', 'ADMIN', '$2a$10$4H4osfef1.womBmFCsz7ROHLbDaCfPSBZjv8.glrZCSJjNKhRzRR2');

insert into user_roles(user_id, role_id)
values (1, 1);