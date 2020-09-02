--create the user that will interact with the bank database
/*create role bankuser login password 'b@nkp4$$' noinherit createdb;

--create the bank database
create database bankdb;

--allow the bankuser to connect to the bank database
grant connect on database bankdb to bankuser;*/

--create our Users table
CREATE TABLE Users(
UserId serial,
Username varchar(16) UNIQUE NOT NULL,
Password varchar(30) NOT null,
primary key(UserId)
);

--CREATE our Customers TABLE
CREATE TABLE Customers(
CustomerId serial,
FirstName varchar(30) NOT NULL,
LastName varchar (30) NOT null,
UserId integer NOT null,
primary key(CustomerId),
constraint fk_userid
	foreign key(UserId)
		references Users(UserId)
);


--create Account table
create table Accounts(
AccountId serial,
CustomerId integer not null,
Balance double precision not null,
primary key (AccountId),
constraint fk_customerid
	foreign key(CustomerId)
		references Customers(CustomerId)
);

--drop table TransactionHistory;
--create the TransactionHistory table
create table TransactionHistory(
entry_date timestamp,
MESSAGE varchar(500),
customerid varchar(10)
-- no foreign key constraint here as the account may disappear and shouldn't affect logging
);

--used for the trigger new_account_trigger, just sets the balance to 0.0
--we do this since each new account will start with a balance of 0
create or replace function set_account_balance()
returns trigger
as $new_account$
begin 
	if(TG_OP = 'INSERT') then 
	new.balance = 0.0;
	end if;
return new;
end;
$new_account$ language plpgsql;

--trigger that occurs when a new account is inserted in accounts
create trigger set_account_balance
before insert on Accounts
for each row 
execute function set_account_balance();



--test our constraints like foreign keys and trigger : for design verification only
/*insert into users (username, password) values ('testuser','testpass');
insert into customers (firstname,lastname, userid) values ('testuser','testa',1);
insert into Accounts(customerid ) values (1);*/

--this view is used for administrator to get all user info excluding accounts
create view userInformation as 
select customers.customerid, customers.firstname, customers.lastname, customers.userid , users.username , users."password" from customers, users 
where customers.userid  = users.userid;

--this is just here to test the view
select * from userInformation;



--following is a mirror for unit tests : uncomment block comment to proceed
--create the user that will interact with the bank database
create role banktest login password 'testpass' noinherit createdb;

--create the bank database
create database banktestdb;

--allow the bankuser to connect to the bank database
grant connect on database banktestdb to banktest;
drop table accounts;
drop table customers;
drop table users;

--create our Users table
CREATE TABLE Users(
UserId serial,
Username varchar(16) UNIQUE NOT NULL,
Password varchar(30) NOT null,
primary key(UserId)
);

--CREATE our Customers TABLE
CREATE TABLE Customers(
CustomerId serial,
FirstName varchar(30) NOT NULL,
LastName varchar (30) NOT null,
UserId integer NOT null,
primary key(CustomerId),
constraint fk_userid
	foreign key(UserId)
		references Users(UserId)
);

--create Account table
create table Accounts(
AccountId serial,
CustomerId integer not null,
Balance double precision not null,
primary key (AccountId),
constraint fk_customerid
	foreign key(CustomerId)
		references Customers(CustomerId)
);

create or replace function set_account_balance()
returns trigger
as $new_account$
begin 
	if(TG_OP = 'INSERT') then 
	new.balance = 0.0;
	end if;
return new;
end;
$new_account$ language plpgsql;

--trigger that occurs when a new account is inserted in accounts
create trigger set_account_balance
before insert on Accounts
for each row 
execute function set_account_balance();
insert into users (username, password) values ('testuser', 'testpass');
insert into customers (firstname, lastname, userid) values ('test', 'test', 1);
insert into accounts (customerid) values (1);
