DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
	`movie_id` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL COLLATE 'utf8_bin',
	`minutes` INT(3) UNSIGNED NULL,
	`movie_type` INT(1) UNSIGNED NULL,
	`language` VARCHAR(50) NOT NULL COLLATE 'utf8_bin',
	PRIMARY KEY (`movie_id`)
)

COLLATE='utf8_bin'
ENGINE=InnoDB;

INSERT INTO `movie`
  (`name`, `minutes`, `movie_type`, `language`)
VALUES
  ('The Legend of Tarzan (2016)', '90', '3', 'Nederlands'),
  ('Tarzan the Ape Man (1932)', '90', '2', 'Engels'),
  ('Tarzan (1999)', '120', '2', 'Engels');

SELECT * FROM `movie`;