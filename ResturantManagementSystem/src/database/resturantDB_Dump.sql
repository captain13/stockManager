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
  INDEX `fk_recipe_specials1_idx` (`specialsID` ASC),
  CONSTRAINT `fk_recipe_specials1`
    FOREIGN KEY (`specialsID`)
    REFERENCES `resturantdb`.`specials` (`specialsID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  INDEX `fk_inventory_recipt_reciept1` (`recipeID` ASC),
  CONSTRAINT `fk_inventory_recipt_inventory`
    FOREIGN KEY (`inventoryID`)
    REFERENCES `resturantdb`.`inventory` (`inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_recipt_reciept1`
    FOREIGN KEY (`recipeID`)
    REFERENCES `resturantdb`.`recipe` (`recipeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `receiptID` INT(11) NOT NULL AUTO_INCREMENT,
  `recipeID` INT(11) NOT NULL,
  `salesID` INT(11) NOT NULL,
  `orderQuantity` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`receiptID`, `recipeID`, `salesID`),
  INDEX `fk_receipt_recipe1` (`recipeID` ASC),
  INDEX `fk_receipt_sales1` (`salesID` ASC),
  CONSTRAINT `fk_receipt_recipe1`
    FOREIGN KEY (`recipeID`)
    REFERENCES `resturantdb`.`recipe` (`recipeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_sales1`
    FOREIGN KEY (`salesID`)
    REFERENCES `resturantdb`.`sales` (`salesID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  INDEX `fk_reservation_employee1` (`employeeID` ASC),
  CONSTRAINT `fk_reservation_employee1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `resturantdb`.`employee` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  INDEX `fk_sales_employee_sales1` (`salesID` ASC),
  CONSTRAINT `fk_sales_employee_employee1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `resturantdb`.`employee` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_employee_sales1`
    FOREIGN KEY (`salesID`)
    REFERENCES `resturantdb`.`sales` (`salesID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  INDEX `fk_stockOrder_supplier1` (`supplierID` ASC),
  CONSTRAINT `fk_stockOrder_inventory1`
    FOREIGN KEY (`inventoryID`)
    REFERENCES `resturantdb`.`inventory` (`inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stockOrder_supplier1`
    FOREIGN KEY (`supplierID`)
    REFERENCES `resturantdb`.`supplier` (`supplierID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `resturantDB`.`inventory`
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
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (8, 'Chips','Other', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (9, 'Chicken Fillet', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (10, 'Beef Burger Patty', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (11, 'Burger rolls','Grain', 1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (12, 'Lettuce','Vegetable', 100, 0.50, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (13, 'Tomatoe','Vegetable', 100, 0.50, 100);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (14, 'Chicken Wings','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (15, 'Pork Ribs','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (16, 'Chips','Other', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (17, 'Chicken Fillet', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (18, 'Beef Burger Patty', 'Meat',1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (19, 'Burger rolls','Grain', 1000, 0.25, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (20, 'Lettuce','Vegetable', 100, 0.50, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (21, 'Tomatoe','Vegetable', 100, 0.50, 100);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (22, 'Chicken Wings','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (23, 'Pork Ribs','Meat', 1000, 0.2, 1000);
INSERT INTO `resturantDB`.`inventory` (`inventoryID`, `item`,`category`, `qty`, `itemThreshold`, `itemLimit`) VALUES (24, 'Chips','Other', 1000, 0.2, 1000);
COMMIT;


-- -----------------------------------------------------
-- Data for table `resturantDB`.`supplier`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantDB`;
INSERT INTO `resturantDB`.`supplier` (`supplierID`, `supplierName`, `supplierEmail`, `supplierNumber`, `supplierAddress`) VALUES (1, 'Meat Co.', 'meatco@gmail.com', '084 772 2883', 'Rondebosch');
INSERT INTO `resturantDB`.`supplier` (`supplierID`, `supplierName`, `supplierEmail`, `supplierNumber`, `supplierAddress`) VALUES (2, 'VegCity', 'vegcity@hotmail.co.za', '078 232 1221', 'Claremont');

COMMIT;


-- -----------------------------------------------------
-- Data for table `resturantDB`.`employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `resturantDB`;
INSERT INTO `resturantDB`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (1, 'Andrew', 'Schwabe', 'Password', '076 273 1721', '00:00', NULL, NULL, 1);
INSERT INTO `resturantDB`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (2, 'Zane', 'Smith', 'Password', '034 233 8321', '00:00', NULL, NULL, 1);
INSERT INTO `resturantDB`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (3, 'Chad', 'Phillips', 'Password', '087 237 1277', '00:00', NULL, NULL, 0);
INSERT INTO `resturantDB`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (4, 'Sean', 'Thompson', 'Password', '072 377 1122', '00:00', NULL, NULL, 0);
INSERT INTO `resturantDB`.`employee` (`employeeID`, `employeeFName`, `employeeLName`, `employeePassword`, `employeeContactNumber`, `employeeHoursWorked`, `employeeStatus`, `employeePosition`, `admin`) VALUES (5, 'Itumeleng', 'Madisha', 'Password', '072 377 1122', '00:00', NULL, NULL, 0);

COMMIT;
