# moves schema

# --- !Ups
CREATE TABLE moves (
id INT NOT NULL PRIMARY KEY,
name VARCHAR(255),
command VARCHAR(255),
detection VARCHAR(255),
damage VARCHAR(255),
startup VARCHAR(255),
hit VARCHAR(255),
guard VARCHAR(255),
counter VARCHAR(255),
note VARCHAR(255));

# --- !Downs
DROP TABLE moves;


# --- !Ups
create table recipes (
id bigint auto_increment not null primary key,
author bigint not null
);

# --- !Downs
drop table recipes;

# --- !Ups
create table recipes_moves (
move_id int not null,
recipe_id bigint not null
);

# --- !Downs
drop table recipe_move;
