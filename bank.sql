CREATE SCHEMA `bank`;

CREATE TABLE `bank`.`user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(45) NULL,
    `lastName` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`) VALUES ('1', 'Bob', 'Heineman');
