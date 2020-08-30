

create role fighter login password 'fightclub' noinherit createdb;

create database fightclub;

grant connect on database fightclub to fighter;