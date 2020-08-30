--1.1 select part 1
--select employeeId, last name, first name and email for records in the Employee table where the last name is King
select "EmployeeId" , "LastName" , "FirstName" , "Email" from "Employee" where "LastName" = 'King';

--1.1 select part 2
--select city and state from employees where first name is andrew and reports to is null
select "City", "State" from "Employee" where "FirstName" = 'Andrew' and "ReportsTo" is null;

--1.2 sub queries
--select all records from the album where composer is ac/dc
select * from "Album" where "ArtistId" in (
select "ArtistId" from "Artist" where "Name" = 'AC/DC'
);

--1.3 ORDER BY
--task - select all albums and sort set in descending order by title
select * from "Album" order by "Title" desc;

--task - select first name from customer and sort result set in ascending order by city
select "FirstName" from "Customer" order by "City";

--1.6 LIKE
--task - select all invoices with a billing address like T%
select * from "Invoice" where "BillingAddress" like 'T%';

--1.7 BETWEEN
--task - select all invoices that have a total between 15 and 50
select * from "Invoice" where "Total" between 15 and 50;

--task - select all employees hired between 1st of june 2003 and 1st of march 2004
select * from "Employee" where "HireDate" between '2003-06-01' and '2004-05-1';



--Section 2 DML statements
--2.1 INSERT INTO
--Task - Insert two complete new records into Genre table
insert into "Genre" values (26, 'Death Metal');
insert into "Genre" values (27, 'Chiptune');
--Task - Insert two complete new records into Employee table
insert into "Employee" values (9, 'Baggins', 'Billy', 'Janitor', 3, '1962-05-10', '2019-08-09', '123 Rocky Road', 'Mizpah', 'MN', 'USA', '56601', '1 (218) 000-1234', 'N/A', 'N/A' );
insert into "Employee" values (10, 'Smough', 'Executioner', 'Secretary', 1, '2011-09-22', '2018-05-23', '1 Chamber Way', 'Anor Londo', 'Lothric', 'Lordran', '00000', '1 (800) 762-7433', '1 (762) 743-7462', 'smough@chinookcorp.com' );

--task insert two complete records into customer table
insert into "Customer" values (60, 'Father', 'Gascoigne', null, 'Church Abbey', 'Yharnam', null, 'Yharnam', null, null, null, 'fatherguacamole@yharnamail.yha', 3);
insert into "Customer" values (61, 'Dragonslayer', 'Ornstein', null, '3 Chamber Way', 'Anor Londo', 'Lothric', 'Lordran', 00000, null, null, 'peekachew@londobros.lor',10);

--2.2 UPDATE
--Task - Update Aaron Mitchel in Customer table to Robert Waller
update "Customer" set "FirstName" = 'Robert', "LastName"  = 'Walter'
where "FirstName" = 'Aaron' and "LastName" = 'Mitchell';

--update name of artist in artist table changing 'creedence clearwater revival' to 'ccr'
update "Artist" set "Name" = 'CCR' where "Name" = 'Creedence Clearwater Revival';

--2.3 DELETE
--Task Delete a record in customer table where the name is Robert Walter

--step one - delete all records involving Robert Walter from invoiceline 
delete from "InvoiceLine" where "InvoiceId" in 
(select "InvoiceId" from "Invoice"  where "CustomerId" in 
(select "CustomerId" from "Customer" where "FirstName" = 'Robert' and "LastName" = 'Walter'));

--step two - delete all references of Robert Walter from invoice
delete from "Invoice" where "CustomerId" in 
(select "CustomerId" from "Customer" where "FirstName" = 'Robert' and "LastName" = 'Walter');

--step three - send Robert Walter to the abyss
delete from "Customer" where "FirstName" = 'Robert' and "LastName" = 'Walter';



--3 SQL functions
--3.1 system defined functions, create a query that returns the current time
select localtime;

--3.1 part two create a query the uses length to return the length of name in MediaType
select length("Name") from "MediaType";

--3.2 system defined aggregate functions
--task - create a function that returns the average total of all invoices
select avg("Total" ) from "Invoice"
--task - create a function that returns the most expensive track
select max("UnitPrice" ) from "Track" 

--3.3 User Defined Functions
--Task - create a function that returns the average price of invoiceline items in the invoiceline table

--select avg("UnitPrice") from "InvoiceLine";
--drop function average_invoiceline(); -- i am here because i didn't get it right the first time and didn't know about the or replace clause in function declaration.
create or replace function average_invoiceline()
	returns numeric as $$
	declare 
		totalRecords integer;
	declare
		aggregatetotal numeric;
	begin 
		select count("UnitPrice") into totalRecords from "InvoiceLine";
		select sum("UnitPrice") into aggregatetotal from "InvoiceLine";
		return aggregatetotal / totalRecords;
	end;
$$ language plpgsql;

--now test it
select average_invoiceline() as "Average of Invoice Lines";



--Task - Create a function that returns all employees who are born after 1968

create or replace function get_all_born_after_1968(out "Born After 1968" timestamp)
	returns setof timestamp as $time$ 
	begin 
		return query select "BirthDate" from "Employee"
		where "BirthDate" >= '1968-01-01';
	end;
$time$ language plpgsql;

--test function return
select get_all_born_after_1968();

--section 4 Triggers
--4.1 after insert trigger
--Task - create an after insert trigger on the employee table fired after a new record is
--inserted into the table to set the phone number to 867-5309
--part one: define the function that sets the value after insertion
--just updates the value of "phone" on the newest record's employee id.
create or replace function reference_eighties_song()
	returns trigger as $emp_phone$
	begin 
		update "Employee" set "Phone" = '867-5309'
		where "EmployeeId" = new."EmployeeId";
	return new;
	end;
$emp_phone$ language plpgsql;

--actually creates the trigger for each time something is inserted.
create trigger reference_eighties_song
after insert on "Employee"
for each row
execute procedure reference_eighties_song();
--delete from "Employee" where "EmployeeId" = 11; this and the next line are here only for testing.
--insert into "Employee" values (11, 'Smough', 'Executioner', 'Secretary', 1, '2011-09-22', '2018-05-23', '1 Chamber Way', 'Anor Londo', 'Lothric', 'Lordran', '00000', '1 (800) 762-7433', '1 (762) 743-7462', 'smough@chinookcorp.com' );

--4.2 Before Insert Trigger
--Task - create a before trigger on the customer table that fires before a row is inserted from the table to set the company to revature
--step 1: create the function to set the new insert's value to 'Revature'
create or replace function set_company_to_revature()
	returns trigger as $emp_company$
	begin 
		new."Company" = 'Revature';
		return new;
	end;
$emp_company$ language plpgsql;

--step 2: create the trigger and make it work for the "Customer" table
create trigger set_company_to_revature
before insert on "Customer"
for each row 
execute procedure set_company_to_revature(); --line below is just to show it works.
--insert into "Customer" values (62, 'Dragonslayer', 'Ornstein', null, '3 Chamber Way', 'Anor Londo', 'Lothric', 'Lordran', 00000, null, null, 'peekachew@londobros.lor',10);

--section 5
--5.1 inner Joines
--Task - create an inner join that joins customers and orders and specifies the name of the customer and the invoiceid
--Should be read as 'select and concatenate the name of the customer and set it to the "name" column and select invoiceid from invoice
--and join the results that match customer's customerid field matches invoice's customerid
select concat("FirstName", ' ', "LastName") as "Name","Invoice"."InvoiceId" from "Customer" inner join "Invoice" 
on "Customer"."CustomerId" = "Invoice"."CustomerId" ; 

--5.2 full outer
--Task - create an outer join (FULL OUTER JOIN/just use full join) that  joines customer and invoice tables specifying firstname, lastname, invoiceid, and total
select "Customer"."CustomerId","FirstName", "LastName","Invoice"."InvoiceId", "Invoice"."Total" from "Customer" full join "Invoice"
on "Customer"."CustomerId" = "Invoice"."CustomerId"; 

--5.3 right join - create a right join that joins album and artist specifying artist name and title
select "Name","Album"."Title" from "Album" right join "Artist"
on "Album"."ArtistId" = "Artist"."ArtistId" ;

--5.4 cross join - create a cartesian product between two tables
--put another way, produce a table of all combinations of each value in both tables
--join album and artist and sort by artist name in ascending order
select * from "Album" cross join "Artist" order by "Artist"."Name" asc;

--5.5 self join - perform a self-join on employee, joining the reportsto column
select concat(a."FirstName",' ' , a."LastName") as "Name" , concat(b."FirstName", ' ', b."LastName") as "Reports To"
from "Employee" a join "Employee" b
on a."ReportsTo" = b."EmployeeId" ;

--6 Set operations
--6.1 create a union query for finding the unique records of last name, first name, and phone number for all customers and employees
select "LastName", "FirstName", "Phone" from "Customer"
union all
select "LastName", "FirstName", "Phone" from "Employee";

--6.2 create an except all query for finding all the records of city, state and 
--postal codes for all customers and all records of employees that have a 
--different city, state, and postal codes of any customer.
select "City", "State", "PostalCode"  from "Customer" 
except all
select "City", "State", "PostalCode"  from "Employee";
