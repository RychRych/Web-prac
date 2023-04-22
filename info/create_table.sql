DROP TYPE carrier_type CASCADE;
CREATE TYPE carrier_type AS ENUM ('cassette', 'cd');
DROP TABLE transaction;
DROP TABLE client;
DROP TABLE data_carrier;
DROP TABLE film;

CREATE TABLE transaction (
	id				serial,
	client_id			serial,
	carrier_id			serial,
	film_title			varchar(100),
	rent_price			real,
	date_of_lease			date,
	date_of_return  		date
);


CREATE TABLE client (
	id 				serial,
	full_name 			varchar(200),
	address 			varchar(600),
	phone_number 			varchar(11)
);


CREATE TABLE data_carrier (
	id				serial,
	film_id				serial,
	film_title			varchar(200),
	type				carrier_type,
	rent_price			real,
	is_available			boolean

);


CREATE TABLE film (
	id				serial,
	title 				varchar(200),
	production_company 		varchar(200),
	director 			varchar(200),
	year_of_release 		integer
);
