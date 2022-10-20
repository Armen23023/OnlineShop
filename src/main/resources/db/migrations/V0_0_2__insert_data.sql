insert into roles(name)
values ('ADMIN'),
       ('USER');

insert into users(email, first_name, last_name, password)
values ('admin@gmail.com', 'ADMIN', 'ADMIN', '$2a$04$6xGi8kYgkBFRm.M1uhVGe.C4Qp2ZjnjqJO0dcqjFbI36m8UC8Z58C');

insert into user_roles(user_id, role_id)
values (1, 1);