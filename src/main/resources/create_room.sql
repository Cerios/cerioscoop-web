DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
	`room_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`chair_amount` INT(50) UNSIGNED NULL,
	`type` INT(1) UNSIGNED NULL,
	PRIMARY KEY (`room_id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `room`
  (`name`, `chair_amount`, `type`)
VALUES
  ('red room', '50', '2'),
  ('blue room', '20','3');

SELECT * FROM `room`;