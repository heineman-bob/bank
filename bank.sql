#Flush the database if it exists
DROP DATABASE IF EXISTS `bank`;


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
    PRIMARY KEY (id)
 );



#Create the accounts table
CREATE TABLE `bank`.`account`(
   `id` BIGINT NOT NULL AUTO_INCREMENT,
   `userId` BIGINT NOT NULL,
   `balance` DECIMAL(12,2) NOT NULL,
   `accountNumber` BIGINT NOT NULL,
   `nickname` VARCHAR(100) NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (userId)
       REFERENCES user(id)
       ON UPDATE CASCADE ON DELETE CASCADE
 );
 
 
-- #Create the legder table
-- CREATE TABLE `bank`.`ledger`(
--    `id` BIGINT NOT NULL AUTO_INCREMENT,
--    `accountId` BIGINT NOT NULL,
--    `amount` DECIMAL(12,2),
--    `date` DATETIME,
--    PRIMARY KEY (id),
--    FOREIGN KEY (accountId)
--        REFERENCES account(id)
--        ON UPDATE CASCADE ON DELETE CASCADE
--    
-- );
 
 
 #user data
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('1', 'Bob', 'Heineman', 'slmd2k3');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('2', 'Jen', 'McCord', 'hotcodegirl');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('3', 'Veronica', 'Heineman', 'vheineman');
INSERT INTO `bank`.`user` (`id`, `firstName`, `lastName`, `username`) VALUES ('4', 'Mark', 'Heineman', 'mheineman3');

 #account data
 INSERT INTO `bank`.`account` (`id`, `userId`, `balance`, `accountNumber`, `nickname`) VALUES ('1', '1', 100.00, 1122334455, 'primary checking');
 
 #ledger data
 --INSERT INTO `bank`.`ledger` (`id`, `accountId`, `amount`, `date`) VALUES ('1', '1', 100.00, NOW());
 