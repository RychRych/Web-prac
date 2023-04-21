INSERT INTO client (id, full_name, address, phone_number) VALUES
	(134566, 'Мельникова Эмилия Михайловна', 'въезд Бухарестская, 06', '83586074997'),
	(740297, 'Демьянов Степан Степанович', 'бульвар Балканская, 65', '80561319691'),
	(295738, 'Журавлева Анастасия Николаевна', 'шоссе Гоголя, 16', '81818907012')
;	

INSERT INTO data_carrier (id, film_id, film_title, type, rent_price, is_available) VALUES
	(37502, 459, 'Зелёная миля', 'cd', 230, 'false'),
	(29571, 125, 'Список Шиндлера', 'cassette', 210, 'false'),
	(29547, 298, 'Побег из Шоушенка', 'cd', 199, 'true')
;	

INSERT INTO film (id, title, production_company, director, year_of_release) VALUES
	(459, 'Зелёная миля', 'Castle Rock Entertainment Darkwoods Productions', 
	 'Фрэнк Дарабонт', 1999),
	(125, 'Список Шиндлера', 'Amblin Entertainment Universal Pictures', 
	 'Стивен Спилберг', 1993),
	(298, 'Побег из Шоушенка', 'Castle Rock Entertainment', 'Фрэнк Дарабонт', 1994)
;	

INSERT INTO transaction (id, client_id, carrier_id, film_title, rent_price, 
						 date_of_lease, date_of_return) VALUES
	(675921, 295738, 29547, 'Побег из Шоушенка', 199, TO_DATE('17/12/2015', 'DD/MM/YYYY'),
	 TO_DATE('27/12/2015', 'DD/MM/YYYY')),
	(736592, 725821, 37502, 'Зелёная миля', 230, TO_DATE('13/08/2019', 'DD/MM/YYYY'),
	 TO_DATE('23/08/2019', 'DD/MM/YYYY')),
	(668204, 917340, 29547, 'Побег из Шоушенка', 199, TO_DATE('03/04/2023', 'DD/MM/YYYY'),
	 TO_DATE('30/04/2023', 'DD/MM/YYYY'))	 
;	