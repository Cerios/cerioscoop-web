DROP TABLE IF EXISTS `show`;

CREATE TABLE `show` (
	`show_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`movie_id` INT(20) UNSIGNED NOT NULL,
	`room_id` INT(20) UNSIGNED NOT NULL,
	`show_date` DATE NOT NULL,
	`show_time` TIME NOT NULL,
	PRIMARY KEY (`show_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `show`
  (`movie_id`, `room_id`, `show_date`, `show_time`)
VALUES
  ('1', '1', '2016-07-01', '21:00:00'),
  ('2', '1', '2016-07-01', '21:00:00'),
  ('2', '2', '2016-07-01', '21:00:00'),
  ('3', '1', '2016-07-01', '21:00:00');
  
SELECT * FROM `show`;