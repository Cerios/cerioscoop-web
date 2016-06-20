DROP TABLE IF EXISTS `showing`;

CREATE TABLE `showing` (
	`showing_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`film_id` INT(20) UNSIGNED NOT NULL,
	`room_id` INT(20) UNSIGNED NOT NULL,
	`premiere_date` DATE NOT NULL,
	`premiere_time` TIME NOT NULL,
	`last_showing_date` DATE NOT NULL,
	`last_showing_time` TIME NOT NULL,
	PRIMARY KEY (`showing_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `showing`
  (`film_id`, `room_id`, `premiere_date`, `premiere_time`, `last_showing_date`, `last_showing_time`)
VALUES
  ('1', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('2', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('2', '2', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00'),
  ('3', '1', '2016-07-01', '21:00:00', '2016-07-09', '20:00:00');
  
SELECT * FROM `showing`;