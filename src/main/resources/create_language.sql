DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
	`language_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	PRIMARY KEY (`language_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `language`
  (`name`)
VALUES
  ('English'),
  ('Dutch'),
  ('French'),
  ('German');

SELECT * FROM `language`;