CREATE DATABASE IF NOT EXISTS `digital_bank_customer_db`;
USE `digital_bank_customer_db`;

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` 	INT NOT 		NULL 		AUTO_INCREMENT,
  `name` 			VARCHAR(100) 	NOT NULL,
  `email` 			VARCHAR(100) 	NOT NULL,
  `mobile_number` 	VARCHAR(20) 	NOT NULL,
  `pwd` 			VARCHAR(500) 	NOT NULL,
  `role` 			VARCHAR(100) 	NOT NULL,
  `create_dt` 		DATE 			DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
);


CREATE TABLE IF NOT EXISTS `authorities` (
  `authority_id` 				INT 			NOT NULL AUTO_INCREMENT,
  `customer_id` 	INT 			NOT NULL,
  `authority` 		VARCHAR(50) 	NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);