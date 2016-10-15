-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server versie:                10.1.14-MariaDB - Source distribution
-- Server OS:                    Win32
-- HeidiSQL Versie:              9.3.0.5104
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

USE `cerioscoop_db`;

-- Dumpen data van tabel cerioscoop.customer: ~3 rows (ongeveer)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `username`, `password`, `email`) VALUES
	(1, 'Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl'),
	(2, 'Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl'),
	(3, 'Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl'),
	(4, 'TestCustomer', 'C', 'C', 'C', 'C@cerios.nl');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.movie: ~7 rows (ongeveer)
DELETE FROM `movie`;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` (`movie_id`, `title`, `movie_description`) VALUES
	(1, 'The Legend of Tarzan (2016)', 'A story about a boy that survived in the jungle.'),
	(2, 'Tarzan the Ape Man (1932)', 'A story about a boy that survived in the jungle.'),
	(3, 'Tarzan (1999)', 'A story about a boy that survived in the jungle.'),
	(4, 'Wedding Crashers', 'A story about two guys that crach weddings.'),
	(5, 'Blood Diamond','A story about illegal trading of diamonds in Sierra Leone.'),
	(6, 'The Lion King', 'A story about a lion called Simba.'),
	(7, 'Snatch', 'A story about a diamond that all the pikeys want to have.');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.show_transaction: ~3 rows (ongeveer)
DELETE FROM `show_transaction`;
/*!40000 ALTER TABLE `show_transaction` DISABLE KEYS */;
INSERT INTO `show_transaction` (`transaction_id`, `customer_id`, `show_id`, `bankaccount`, `reserved_places`, `total_price`) VALUES
	(1, 2, 1, 'NL32INGB1234567890', 5, 50),
	(2, 1, 7, 'NL32INGB2234567890', 2, 20),
	(3, 3, 6, 'NL32INGB3234567890', 3, 30),
	(4, 2, 5, 'NL32INGB5234567890', 5, 50),
	(5, 1, 4, 'NL32INGB6234567890', 2, 20),
	(6, 4, 3, 'NL32INGB7234567890', 3, 30),
	(7, 3, 2, 'NL32INGB4234567890', 1, 10);
/*!40000 ALTER TABLE `show_transaction` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.room: ~2 rows (ongeveer)
DELETE FROM `room`;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` (`room_id`, `room_name`, `capacity`) VALUES
	(1, 'red room', 50),
	(2, 'blue room', 20);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;

-- Dumpen data van tabel cerioscoop.show: ~10 rows (ongeveer)
DELETE FROM `show_table`;
/*!40000 ALTER TABLE `show_table` DISABLE KEYS */;
INSERT INTO `show_table` (`show_id`,`movie_id`, `room_id`, `show_date`, `show_time`, `available_places`, `show_price`) VALUES
	(1, 1, 1, CURDATE(), '12:00:00', 50, 10),
	(2, 2, 1, CURDATE(), '21:00:00', 50, 20),
	(3, 4, 2, CURDATE(), '16:00:00', 20, 10),
	(4, 4, 1, '2016-12-01', '20:00:00', 50, 20),
	(5, 5, 1, '2016-12-01', '21:00:00', 50, 20),
	(6, 6, 2, '2016-12-01', '10:00:00', 20, 10),
	(7, 7, 1, '2017-12-01', '15:00:00', 50, 10);
/*!40000 ALTER TABLE `show_table` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
