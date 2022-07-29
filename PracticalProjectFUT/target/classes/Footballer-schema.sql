DROP TABLE IF EXISTS `Footballer` CASCADE;
create table `Footballer` (
	`id` INTEGER UNIQUE PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL, 
	`position` VARCHAR(255) NOT NULL,
    `card_type` VARCHAR(255) NOT NULL,
    `rating` INTEGER NOT NULL
);