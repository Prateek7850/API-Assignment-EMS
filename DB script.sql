create database EMS
create table Employee (
id BIGINT primary key,
name varchar(30) not null,
designation enum ('JUNIOR_DEVELOPER', 'SENIOR_DEVELOPER', 'MANAGER') not null,
salary double not null,
department_id BIGINT ,
constraint department_id foreign key(department_id) REFERENCES department(id) ON DELETE SET NULL
)
create table department(
  id BIGINT primary key,
  name varchar (25) unique
) 
select * from Employee
select * from department
Create table User (
id int primary key,
user_name varchar (30),
password varchar(100),
)
INSERT INTO User (id,username, password) VALUES (1,'user', '1234');
Create table role(
id int primary key,
role_name varchar(50)
)
Insert into role values(1,"ADMIN") 
Insert into role values(2,"USER") 

create table user_role(
user_id int,
role_id int,
constraint user_id foreign key (user_id) 
references user(id) on delete set CASCADE,
constraint role_id foreign key (user_id) 
references role(id) on delete set CASCADE
)
insert into user_role values(1,1)