# Application users
create user example with encrypted password 'example';
create user example_ddl with encrypted password 'example_ddl';
create user example_ro with encrypted password 'example_ro';

create database example;

\connect example;

alter default privileges in schema public grant all privileges on tables TO example_ddl;
alter default privileges for role example_ddl in schema public grant select, insert, update, delete on tables TO example;
alter default privileges for role example_ddl in schema public grant select on tables TO example_ro;
