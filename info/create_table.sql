DROP TABLE transaction;
DROP TABLE client;
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
	year_of_release 		integer,
	available_cd			integer,
	price_of_cd				real,
	available_cassette		integer,
	price_of_cassette		integer,
	total_of_cd				integer,
	total_of_cassette		integer
);


CREATE TABLE transaction (
	id				serial		PRIMARY KEY,
	client_id			serial		REFERENCES client (id),
	film_id			serial		REFERENCES film (id),
	film_title			varchar(100),
	rent_price			real,
	date_of_lease			date,
	date_of_return  		date
);
