DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
	`category_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	PRIMARY KEY (`category_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `category`
  (`name`)
VALUES
  ('Action'),
  ('Comedy'),
  ('Kids'),
  ('Horror');

SELECT * FROM `category`;