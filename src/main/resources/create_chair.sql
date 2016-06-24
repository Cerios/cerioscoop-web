DROP TABLE IF EXISTS `chair`;
CREATE TABLE `chair` (
	`chair_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`nummer` INT(100) UNSIGNED NULL,
	PRIMARY KEY (`chair_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `chair`
  (`nummer`)
VALUES
  ('1'),
  ('2'),
  ('3'),
  ('4');

SELECT * FROM `chair`;