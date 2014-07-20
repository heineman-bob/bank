#Flush the database if it exists
DROP DATABASE `bank`;


#Create User for banking app
#A Note on Security here.  This authentication and password are just here for ease of development
#  in an enterprise setting something more robust would be used to secure the database and all privileges would 
#  most likely not be granted.
CREATE USER 'bankingApp'@'localhost' IDENTIFIED BY 'bankingApp';
#Grant access
GRANT ALL PRIVILEGES ON bank.* TO 'bankingApp'@'localhost' WITH GRANT OPTION;

#Create Bank Schema
CREATE SCHEMA `bank`;

#Create the User table
CREATE TABLE `bank`.`user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(45) NULL,
    `lastName` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

#Insert sandbox data
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`) VALUES ('1', 'Bob', 'Heineman');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`) VALUES ('2', 'Jen', 'McCord');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`) VALUES ('3', 'Veronica', 'Heineman');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`) VALUES ('4', 'Mark', 'Heineman');
