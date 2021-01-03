USE `edem_db`;

CREATE TABLE `account` (
  `id` int NOT NULL,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
  CONSTRAINT `fkk_user` FOREIGN KEY (`id`) REFERENCES `edem_db`.`user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `desc` varchar(255) NOT NULL COMMENT 'short description',
  `contenet` text NOT NULL,
  `logo` varchar(255) NOT NULL COMMENT 'link to picture for action',
  `comment` int NOT NULL DEFAULT '0' COMMENT 'count of comments in general page',
  `co2` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `animal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `desc` text NOT NULL,
  `content` text NOT NULL,
  `logo` varchar(255) NOT NULL,
  `co2` int NOT NULL COMMENT 'necessary count co2 for saving one animal',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` int NOT NULL,
  `content` text NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_account` int NOT NULL,
  `id_action` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_account_idx` (`id_account`),
  KEY `id_action_idx` (`id_action`),
  CONSTRAINT `fk_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_action` FOREIGN KEY (`id_action`) REFERENCES `action` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_account` int NOT NULL,
  `id_action` int NOT NULL,
  `reducedCO2` int NOT NULL DEFAULT '0',
  `id_animal` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_action_idx` (`id_action`),
  KEY `fk_account_idx` (`id_account`),
  KEY `fk_animal_idx` (`id_animal`),
  CONSTRAINT `fk_usage_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_usage_action` FOREIGN KEY (`id_action`) REFERENCES `action` (`id`),
  CONSTRAINT `fk_usage_animal` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('USER','ADMINISTRATOR') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;