drop table Word if exists
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1
create table Word (id bigint not null, description varchar(255), name varchar(255), primary key (id))
