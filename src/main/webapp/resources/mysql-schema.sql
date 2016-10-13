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


-- Databasestructuur van cerioscoop wordt geschreven
DROP DATABASE IF EXISTS `cerioscoop_db`;
CREATE DATABASE IF NOT EXISTS `cerioscoop_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `cerioscoop_db`;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.customer wordt geschreven
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `last_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  UNIQUE (`username`),
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.movie wordt geschreven
DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `movie_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) COLLATE utf8_bin NOT NULL,
  `movie_description` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.show_transaction wordt geschreven
DROP TABLE IF EXISTS `show_transaction`;
CREATE TABLE IF NOT EXISTS `show_transaction` (
  `transaction_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(20) unsigned NOT NULL,
  `show_id` int(20) unsigned NOT NULL,
  `bankaccount` varchar(34) COLLATE utf8_bin NOT NULL,
  `reserved_places` int(50) unsigned NOT NULL,
  `total_price` float NOT NULL,
  PRIMARY KEY (`transaction_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  FOREIGN KEY (`show_id`) REFERENCES `show_table` (`show_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.room wordt geschreven
DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `room_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `capacity` int(50) unsigned DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
-- Structuur van  tabel cerioscoop.show_table wordt geschreven
DROP TABLE IF EXISTS `show_table`;
CREATE TABLE IF NOT EXISTS `show_table` (
  `show_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `movie_id` int(20) unsigned NOT NULL,
  `room_id` int(20) unsigned NOT NULL,
  `show_date` date NOT NULL,
  `show_time` time NOT NULL,
  `available_places` int(50) NOT NULL,
  `show_price` float NOT NULL,
  PRIMARY KEY (`show_id`),
  FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Data exporteren was gedeselecteerd
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
