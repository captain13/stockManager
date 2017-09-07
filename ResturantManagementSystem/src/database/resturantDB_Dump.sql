-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema resturantdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema resturantdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resturantdb` DEFAULT CHARACTER SET utf8 ;
USE `resturantdb` ;

-- -----------------------------------------------------
-- Table `resturantdb`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`employee` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`employee` (
  `employeeID` INT(11) NOT NULL AUTO_INCREMENT,
  `employeeFName` VARCHAR(45) NULL DEFAULT NULL,
  `employeeLName` VARCHAR(45) NULL DEFAULT NULL,
  `employeePassword` VARCHAR(45) NOT NULL,
  `employeeContactNumber` VARCHAR(45) NULL DEFAULT NULL,
  `employeeHoursWorked` VARCHAR(45) NULL DEFAULT '00:00',
  `employeeStatus` VARCHAR(45) NULL DEFAULT NULL,
  `employeePosition` VARCHAR(45) NULL DEFAULT NULL,
  `admin` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`employeeID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`inventory` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`inventory` (
  `inventoryID` INT(11) NOT NULL AUTO_INCREMENT,
  `item` VARCHAR(45) NULL DEFAULT NULL,
  `category` VARCHAR(45) NULL DEFAULT NULL,
  `qty` INT(11) NULL DEFAULT NULL,
  `itemThreshold` DOUBLE NULL DEFAULT NULL,
  `itemLimit` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`inventoryID`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`specials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`specials` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`specials` (
  `specialsID` INT NOT NULL AUTO_INCREMENT,
  `specialsPrice` DOUBLE NULL,
  `status` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`specialsID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantdb`.`recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`recipe` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`recipe` (
  `recipeID` INT(11) NOT NULL AUTO_INCREMENT,
  `recipeName` VARCHAR(45) NULL DEFAULT NULL,
  `recipePrice` DOUBLE NULL DEFAULT NULL,
  `recipeVAT` DOUBLE NULL DEFAULT NULL,
  `recipeType` VARCHAR(45) NULL DEFAULT NULL,
  `recipeImageDirectory` VARCHAR(150) NULL DEFAULT NULL,
  `recipeCount` INT(11) NULL DEFAULT NULL,
  `specialsID` INT NULL,
  PRIMARY KEY (`recipeID`),
  INDEX `fk_recipe_specials1_idx` (`specialsID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`inventory_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`inventory_recipe` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`inventory_recipe` (
  `inventory_recipeID` INT(11) NOT NULL AUTO_INCREMENT,
  `inventoryID` INT(11) NOT NULL,
  `recipeID` INT(11) NOT NULL,
  `qty` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`inventory_recipeID`, `inventoryID`, `recipeID`),
  INDEX `fk_inventory_recipt_inventory` (`inventoryID` ASC),
  INDEX `fk_inventory_recipt_reciept1` (`recipeID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`sales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`sales` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`sales` (
  `salesID` INT(11) NOT NULL AUTO_INCREMENT,
  `salesDate` DATE NULL DEFAULT NULL,
  `totalCost` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`salesID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`receipt` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`receipt` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `recipeID` INT(11) NOT NULL,
  `orderQuantity` INT(11) NULL DEFAULT NULL,
  `salesID` INT(11) NOT NULL,
  `date` DATE NULL,
  `time` TIME NULL,
  `cost` DOUBLE NULL,
  PRIMARY KEY (`ID`, `recipeID`, `salesID`),
  INDEX `fk_receipt_recipe1` (`recipeID` ASC),
  INDEX `fk_receipt_sales1` (`salesID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`reservation` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`reservation` (
  `reservationID` INT(11) NOT NULL AUTO_INCREMENT,
  `employeeID` INT(11) NOT NULL,
  `reservationDate` DATE NULL DEFAULT NULL,
  `reservationTime` VARCHAR(45) NULL DEFAULT NULL,
  `reservationCustomer` VARCHAR(45) NULL DEFAULT NULL,
  `reservationTableNumber` INT(11) NULL DEFAULT NULL,
  `reservationNumberPeople` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`reservationID`, `employeeID`),
  INDEX `fk_reservation_employee1` (`employeeID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`sales_employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`sales_employee` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`sales_employee` (
  `sales_employeeID` INT(11) NOT NULL AUTO_INCREMENT,
  `employeeID` INT(11) NOT NULL,
  `salesID` INT(11) NOT NULL,
  PRIMARY KEY (`sales_employeeID`, `employeeID`, `salesID`),
  INDEX `fk_sales_employee_employee1` (`employeeID` ASC),
  INDEX `fk_sales_employee_sales1` (`salesID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`supplier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`supplier` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`supplier` (
  `supplierID` INT(11) NOT NULL AUTO_INCREMENT,
  `supplierName` VARCHAR(45) NULL DEFAULT NULL,
  `supplierEmail` VARCHAR(45) NULL DEFAULT NULL,
  `supplierNumber` VARCHAR(45) NULL DEFAULT NULL,
  `supplierAddress` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`supplierID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resturantdb`.`stockorder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantdb`.`stockorder` ;

CREATE TABLE IF NOT EXISTS `resturantdb`.`stockorder` (
  `stockOrderID` INT(11) NOT NULL AUTO_INCREMENT,
  `inventoryID` INT(11) NOT NULL,
  `supplierID` INT(11) NOT NULL,
  `dateOrdered` DATE NULL DEFAULT NULL,
  `dateETA` DATE NULL DEFAULT NULL,
  `quantity` DOUBLE NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`stockOrderID`, `inventoryID`, `supplierID`),
  INDEX `fk_stockOrder_inventory1` (`inventoryID` ASC),
  INDEX `fk_stockOrder_supplier1` (`supplierID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `resturantdb`.`employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantdb`;
INSERT INTO `resturantdb`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (1,'Andrew','Schwabe','Password','076 273 1721','00:00',NULL,NULL,1);
INSERT INTO `resturantdb`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES(2,'Zane','Smith','Password','034 233 8321','00:00',NULL,NULL,1);
INSERT INTO `resturantdb`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES(3,'Chad','Phillips','Password','087 237 1277','00:00',NULL,NULL,0);
INSERT INTO `resturantdb`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES(4,'Sean','Thomson','Password','072 377 1122','00:00',NULL,NULL,0);
INSERT INTO `resturantdb`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES(5,'Itumeleng','Madisha','Password','072 377 1122','00:00',NULL,NULL,0);
COMMIT;


-- -----------------------------------------------------
-- Data for table `resturantdb`.`inventory`
-- -----------------------------------------------------
START TRANSACTION;

USE `resturantDB`;
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (1, 'Chicken Fillet', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (2, 'Beef Burger Patty', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (3, 'Burger rolls','Grain', 1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (4, 'Lettuce','Vegetable', 100, 0.50, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (5, 'Tomatoe','Vegetable', 100, 0.50, 100);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (6, 'Chicken Wings','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (7, 'Pork Ribs','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (8, 'Chicken Breast','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (9, 'Avocardo', 'Vegetable',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (10, 'Steak', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (11, 'Cheese','Dairy', 1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (12, 'Feta','Dairy', 100, 0.50, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (13, 'Pizza Base','Grain', 100, 0.50, 100);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (14, 'Napoletan Sauce','Other', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (15, 'Bacon','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (16, 'Castle Light','Alcohol', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (17, 'Black Label Beer', 'Alcohol',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (18, 'Coke','Soft Drinks', 100, 0.50, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (19, 'Coke Light','Soft Drinks', 100, 0.50, 100);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (20, 'Sprite','Soft Drinks', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (21, 'Fanta','Soft Drinks', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (22, 'Ice Tea','Soft Drinks', 6600, 0.2, 1000);
COMMIT;


-- -----------------------------------------------------
-- Data for table `resturantdb`.`recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantdb`;
INSERT INTO `resturantdb`.`recipe` (`recipeID`, `recipeName`, `recipePrice`, `recipeVAT`,`recipeType`,`recipeImageDirectory`,`recipeCount`) VALUES (3,'Chicken Burger & Chips',75,10.5,'Main Meal','./src/images/_f_chickenBurger.jpg',8);
INSERT INTO `resturantdb`.`recipe` (`recipeID`, `recipeName`, `recipePrice`, `recipeVAT`,`recipeType`,`recipeImageDirectory`,`recipeCount`) VALUES (4,'Beef Burger & Chips',75,10.5,'Main Meal','./src/images/_f_beefBurger.jpg',8);

COMMIT;

-- -----------------------------------------------------
-- Data for table `resturantdb`.`inventory_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantdb`;
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (1,1,3,200);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (2,3,3,100);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (3,4,3,50);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (4,5,3,50);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (5,8,3,100);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (6,2,4,200);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (7,3,4,100);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (8,4,4,50);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (9,5,4,50);
INSERT INTO `resturantdb`.`inventory_recipe` (`inventory_recipeID`, `inventoryID`, `recipeID`, `qty`) VALUES (10,8,4,160);
COMMIT;



-- -----------------------------------------------------
-- Data for table `resturantdb`.`supplier`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantdb`;
INSERT INTO `resturantdb`.`supplier` (`supplierID`, `supplierName`, `supplierEmail`, `supplierNumber`, `supplierAddress`) VALUES(1,'Meat Co.','meatco@gmail.com','084 772 2883','Rondebosch');
INSERT INTO `resturantdb`.`supplier` (`supplierID`, `supplierName`, `supplierEmail`, `supplierNumber`, `supplierAddress`) VALUES(2,'VegCity','vegcity@hotmail.co.za','078 232 1221','Claremont');
COMMIT;

