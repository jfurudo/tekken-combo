# moves schema

# --- !Ups
CREATE TABLE moves (
id INT NOT NULL PRIMARY KEY,
name VARCHAR(255),
command VARCHAR(255),
detection VARCHAR(255),
damgage VARCHAR(255),
startup VARCHAR(255),
hit VARCHAR(255),
guard VARCHAR(255),
counter VARCHAR(255),
note VARCHAR(255));

# --- !Downs
DROP TABLE moves;

