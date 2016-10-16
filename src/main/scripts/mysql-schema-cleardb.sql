-- script om schema te maken in de cleardb cloud omgeving
-- Merk op dat je in cleardb niet zelf een database kunt maken, dus we beperken ons in
-- dit script tot het maken van tabellen, gegeven dat er al een database is.

-- NB:  ook de mysql-testdata.sql  werkt niet zomaar in cleardb, want je kunt dus niet een database naam kiezen (use `cerioscoop_db` werkt niet)


-- TODO: er zit natuurlijk overlap in deze file en de mysql-schema.sql
--  Wie doet een poging om dit te verbeteren?


-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.chair wordt geschreven
DROP TABLE IF EXISTS `chair`;
CREATE TABLE IF NOT EXISTS `chair` (
  `chair_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `nummer` int(100) unsigned DEFAULT NULL,
  PRIMARY KEY (`chair_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.customer wordt geschreven
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `customer_create_date` date NOT NULL,
  `customer_create_time` time NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.employee wordt geschreven
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `employee_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `employee_create_date` date NOT NULL,
  `employee_create_time` time NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.language wordt geschreven
DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `language_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `language_name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.movie wordt geschreven
DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `movie_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(50) COLLATE utf8_bin NOT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `minutes` int(3) unsigned DEFAULT NULL,
  `movie_type` int(1) unsigned DEFAULT NULL,
  `language` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  `trailer` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.payment wordt geschreven
DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(20) unsigned NOT NULL,
  `show_id` int(20) unsigned NOT NULL,
  `room_id` int(20) unsigned NOT NULL,
  `chair_id` int(20) unsigned NOT NULL,
  `amount` double unsigned DEFAULT NULL,
  `payment_date` date NOT NULL,
  `payment_time` time NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.room wordt geschreven
DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `chair_amount` int(50) unsigned DEFAULT NULL,
  `room_type` int(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.show wordt geschreven
DROP TABLE IF EXISTS `show_table`;
CREATE TABLE IF NOT EXISTS `show_table` (
  `show_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `movie_id` int(20) unsigned NOT NULL,
  `room_id` int(20) unsigned NOT NULL,
  `show_date` date NOT NULL,
  `show_time` time NOT NULL,
  `chairs_sold` int(20) unsigned NOT NULL,
  PRIMARY KEY (`show_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

