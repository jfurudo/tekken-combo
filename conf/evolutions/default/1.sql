
# moves schema

# --- !Ups
create table moves (
moveId int GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
name varchar(255),
command varchar(255),
detection varchar(255),
damgage varchar(255),
startup varchar(255),
hit varchar(255),
guard varchar(255),
counter varchar(255),
note varchar(255));

# --- !Downs
drop table moves;

