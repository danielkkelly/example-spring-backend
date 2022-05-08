# Application users
create user 'example'@'%' identified by 'example';
create user 'example-ddl'@'%' identified by 'example-ddl';
create user 'example-ro'@'%' identified by 'example-ro';

create database example;
grant all privileges on example.* to 'example-ddl'@'%';
grant delete, insert, select, update, create temporary tables on example.* to 'example'@'%';
grant select on example.* TO 'example-ro'@'%';
