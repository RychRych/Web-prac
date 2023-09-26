INSERT INTO client (id, full_name, address, phone_number) VALUES
															  (134566, 'Мельникова Эмилия Михайловна', 'въезд Бухарестская, 06', '83586074997'),
															  (740297, 'Демьянов Степан Степанович', 'бульвар Балканская, 65', '80561319691'),
															  (295738, 'Журавлева Анастасия Николаевна', 'шоссе Гоголя, 16', '81818907012')
;

INSERT INTO film (id, title, production_company, director, year_of_release,
				  available_cd, price_of_cd, available_cassette, price_of_cassette,
				  total_of_cd, total_of_cassette) VALUES
													  (459, 'Зелёная миля', 'Castle Rock Entertainment Darkwoods Productions',
													   'Фрэнк Дарабонт', 1999, 15, 230, 10, 170, 800, 315),
													  (125, 'Список Шиндлера', 'Amblin Entertainment Universal Pictures',
													   'Стивен Спилберг', 1993, 20, 140, 15, 135, 500, 250),
													  (298, 'Побег из Шоушенка', 'Castle Rock Entertainment', 'Фрэнк Дарабонт',
													   1994, 40, 350, 24, 230, 1000, 700)
;

INSERT INTO transaction (id, client_id, film_id, film_title, type_of_carrier,
						 rent_price, date_of_lease, date_of_return) VALUES
																		(675921, 295738, 298, 'Побег из Шоушенка', 'cd', 350, TO_DATE('17/12/2015', 'DD/MM/YYYY'),
																		 TO_DATE('27/12/2015', 'DD/MM/YYYY')),
																		(736592, 740297, 459, 'Зелёная миля', 'cassette', 170, TO_DATE('13/08/2019', 'DD/MM/YYYY'),
																		 TO_DATE('23/08/2019', 'DD/MM/YYYY')),
																		(668204, 134566, 298, 'Побег из Шоушенка', 'cassette', 230, TO_DATE('03/04/2023', 'DD/MM/YYYY'),
																		 TO_DATE('30/04/2023', 'DD/MM/YYYY'))
;