INSERT INTO customer (first_name, last_name, username, password, email) VALUES
	('Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl'),
	('Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl'),
	('Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl');

INSERT INTO movie (title, movie_description) VALUES
	('The Legend of Tarzan (2016)', 'A story about a boy that survived in the jungle.'),
	('Tarzan the Ape Man (1932)', 'A story about a boy that survived in the jungle.'),
	('Tarzan (1999)', 'A story about a boy that survived in the jungle.'),
	('Wedding Crashers', 'A story about two guys that crach weddings.'),
	('Blood Diamond', 'A story about illegal trading of diamonds in Sierra Leone.'),
	('The Lion King', 'A story about a lion called Simba.'),
	('Snatch', 'A story about a diamond that all the pikeys want to have.');

INSERT INTO show_transaction (customer_id, show_id, bankaccount, reserved_places, total_price) VALUES
	(2, 1, 'NL32INGB1234567890', 5, 50),
	(1, 7, 'NL32INGB2234567890', 2, 20),
	(3, 6, 'NL32INGB3234567890', 3, 30),
	(2, 5, 'NL32INGB5234567890', 5, 50);

INSERT INTO room (room_name, capacity) VALUES
	('red room', 50),
	('blue room', 20);

INSERT INTO show_table (movie_id, room_id, show_date, show_time, available_places, show_price) VALUES
	(1, 1, '2016-12-01', '12:00:00', 50, 10),
	(2, 1, '2016-12-01', '21:00:00', 50, 20),
	(2, 2, '2016-12-01', '16:00:00', 20, 10),
	(4, 1, '2016-12-01', '20:00:00', 50, 20),
	(5, 1, '2016-12-01', '21:00:00', 50, 20),
	(2, 2, '2016-12-01', '10:00:00', 20, 10),
	(5, 1, '2017-12-01', '15:00:00', 50, 10);