# moves schema

# --- !Ups
CREATE TABLE moves (
id bigint NOT NULL PRIMARY KEY,
name varchar(255),
command varchar(255),
detection varchar(255),
damage varchar(255),
startup varchar(255),
hit varchar(255),
guard varchar(255),
counter varchar(255),
note varchar(255));

# --- !Downs
DROP TABLE moves;


# recipes schema

# --- !Ups
create table recipes (
id bigint auto_increment not null primary key,
author bigint not null
);

# --- !Downs
drop table recipes;

# recipes_moves join table schema

# --- !Ups
create table recipes_moves (
recipe_id bigint not null,
move_id int not null
);

# --- !Downs
drop table recipes_moves;

# user schema

# --- !Ups
create table user (
id bigint auto_increment not null primary key,
twitter_id bigint not null,
name varchar(255)
);

# --- !Downs
drop table user;
