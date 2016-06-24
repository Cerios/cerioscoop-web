DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
	`employee_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`last_name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`username` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`password` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`e-mail` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`employee_create_date` DATE NOT NULL,
	`employee_create_time` TIME NOT NULL,
	PRIMARY KEY (`employee_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `employee`
  (`first_name`, `last_name`, `username`, `password`, `e-mail`, `employee_create_date`, `employee_create_time`)
VALUES
  ('Rutger', 'van Velzen', 'faust', 'Fausttothemax', 'rutger.van.velzen@cerios.nl', '2016-07-01', '21:00:00'),
  ('Marcel', 'Groothuis', 'marcel', 'HeerserMarcel', 'marcel.groothuis@cerios.nl', '2016-07-01', '21:00:00'),
  ('Ron', 'Sanders', 'ron', 'SuperRon', 'ron.sanders@cerios.nl', '2016-07-01', '21:00:00');
  
SELECT * FROM `employee`;