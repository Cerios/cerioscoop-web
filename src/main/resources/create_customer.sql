DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
	`customer_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`last_name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`username` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`password` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`e-mail` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`customer_create_date` DATE NOT NULL,
	`customer_create_time` TIME NOT NULL,
	PRIMARY KEY (`customer_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `customer`
  (`first_name`, `last_name`, `username`, `password`, `e-mail`, `customer_create_date`, `customer_create_time`)
VALUES
  ('Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl', '2016-07-01', '21:00:00'),
  ('Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl', '2016-07-01', '21:00:00'),
  ('Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl', '2016-07-01', '21:00:00');
  
SELECT * FROM `customer`;