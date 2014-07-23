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
    `username` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

#Insert sandbox data
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('1', 'Bob', 'Heineman', 'slmd2k3');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('2', 'Jen', 'McCord', 'hotcodegirl');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('3', 'Veronica', 'Heineman', 'vheineman');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('4', 'Mark', 'Heineman', 'mheineman3');
