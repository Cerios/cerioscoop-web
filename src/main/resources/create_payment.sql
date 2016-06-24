DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
	`payment_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`customer_id` INT(20) UNSIGNED NOT NULL,
	`show_id` INT(20) UNSIGNED NOT NULL,
	`room_id` INT(20) UNSIGNED NOT NULL,
	`chair_id` INT(20) UNSIGNED NOT NULL,
	`amount` DOUBLE UNSIGNED NULL,
	`payment_date` DATE NOT NULL,
	`payment_time` TIME NOT NULL,
	PRIMARY KEY (`payment_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `payment`
  (`customer_id`, `show_id`, `room_id`, `chair_id`, `amount`, `payment_date`, `payment_time`)
VALUES
  ('1', '2', '1', '1', '10', '2016-07-01', '21:00:00'),
  ('2', '1', '2', '2', '10', '2016-07-01', '21:00:00'),
  ('3', '1', '1', '1', '10', '2016-07-01', '21:00:00');
  
SELECT * FROM `payment`;