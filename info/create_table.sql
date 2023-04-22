DROP TYPE carrier_type CASCADE;
CREATE TYPE carrier_type AS ENUM ('cassette', 'cd');
DROP TABLE transaction;
DROP TABLE client;
DROP TABLE data_carrier;
DROP TABLE film;


CREATE TABLE client (
	id 				serial		PRIMARY KEY,
	full_name 			varchar(200),
	address 			varchar(600),
	phone_number 			varchar(11)
);

CREATE TABLE film (
	id				serial		PRIMARY KEY,
	title 				varchar(200),
	production_company 		varchar(200),
	director 			varchar(200),
	year_of_release 		integer
);

CREATE TABLE data_carrier (
	id				serial		PRIMARY KEY,
	film_id				serial		REFERENCES film (id),
	film_title			varchar(200),
	type				carrier_type,
	rent_price			real,
	is_available			boolean
);

CREATE TABLE transaction (
	id				serial		PRIMARY KEY,
	client_id			serial		REFERENCES client (id),
	carrier_id			serial		REFERENCES data_carrier (id),
	film_title			varchar(100),
	rent_price			real,
	date_of_lease			date,
	date_of_return  		date
);
