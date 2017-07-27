SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `resturantDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `resturantDB` ;

-- -----------------------------------------------------
-- Table `resturantDB`.`sales`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`sales` (
  `stockID` INT NOT NULL ,
  `salesDate` VARCHAR(45) NULL ,
  `salesTotalCost` VARCHAR(45) NULL ,
  `salesTotalVAT` VARCHAR(45) NULL ,
  `` VARCHAR(45) NULL ,
  PRIMARY KEY (`stockID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`inventory`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`inventory` (
  `inventoryID` INT NOT NULL ,
  `stock_stockID` INT NOT NULL ,
  `item` VARCHAR(45) NULL ,
  `qty` VARCHAR(45) NULL ,
  PRIMARY KEY (`inventoryID`, `stock_stockID`) ,
  INDEX `fk_inventory_stock1_idx` (`stock_stockID` ASC) ,
  CONSTRAINT `fk_inventory_stock1`
    FOREIGN KEY (`stock_stockID` )
    REFERENCES `resturantDB`.`sales` (`stockID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`supplier`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`supplier` (
  `supplierID` INT NOT NULL ,
  `supplierName` VARCHAR(45) NULL ,
  `supplierEmail` VARCHAR(45) NULL ,
  `supplierContactNumber` VARCHAR(45) NULL ,
  `supplierAddress` VARCHAR(45) NULL ,
  `` VARCHAR(45) NULL ,
  PRIMARY KEY (`supplierID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`employee` (
  `employeeID` INT NOT NULL ,
  `employeeFName` VARCHAR(45) NULL ,
  `employeeLName` VARCHAR(45) NULL ,
  `employeePassword` VARCHAR(45) NULL ,
  `employeeContactNumber` VARCHAR(45) NULL ,
  PRIMARY KEY (`employeeID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`reservation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`reservation` (
  `reservationID` INT NOT NULL ,
  `employee_employeeID` INT NOT NULL ,
  `reservationDate` VARCHAR(45) NULL ,
  `reservationTableNumber` VARCHAR(45) NULL ,
  `reservationNumberPeople` VARCHAR(45) NULL ,
  PRIMARY KEY (`reservationID`, `employee_employeeID`) ,
  INDEX `fk_reservation_employee1_idx` (`employee_employeeID` ASC) ,
  CONSTRAINT `fk_reservation_employee1`
    FOREIGN KEY (`employee_employeeID` )
    REFERENCES `resturantDB`.`employee` (`employeeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`recipe`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`recipe` (
  `recipeID` INT NOT NULL ,
  `recipeName` VARCHAR(45) NULL ,
  `recipePrice` VARCHAR(45) NULL ,
  `recipeVAT` VARCHAR(45) NULL ,
  PRIMARY KEY (`recipeID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`inventory_recipe`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`inventory_recipe` (
  `inventory_recipeID` INT NOT NULL ,
  `inventoryID` INT NOT NULL ,
  `recipeID` INT NOT NULL ,
  PRIMARY KEY (`inventory_recipeID`, `inventoryID`, `recipeID`) ,
  INDEX `fk_inventory_recipt_inventory_idx` (`inventoryID` ASC) ,
  INDEX `fk_inventory_recipt_reciept1_idx` (`recipeID` ASC) ,
  CONSTRAINT `fk_inventory_recipt_inventory`
    FOREIGN KEY (`inventoryID` )
    REFERENCES `resturantDB`.`inventory` (`inventoryID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_recipt_reciept1`
    FOREIGN KEY (`recipeID` )
    REFERENCES `resturantDB`.`recipe` (`recipeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`stockOrder`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`stockOrder` (
  `stockOrderID` INT NOT NULL ,
  `inventory_inventoryID` INT NOT NULL ,
  `supplier_supplierID` INT NOT NULL ,
  `dateOrdered` VARCHAR(45) NULL ,
  `quantity` VARCHAR(45) NULL ,
  `status` VARCHAR(45) NULL ,
  PRIMARY KEY (`stockOrderID`, `inventory_inventoryID`, `supplier_supplierID`) ,
  INDEX `fk_stockOrder_inventory1_idx` (`inventory_inventoryID` ASC) ,
  INDEX `fk_stockOrder_supplier1_idx` (`supplier_supplierID` ASC) ,
  CONSTRAINT `fk_stockOrder_inventory1`
    FOREIGN KEY (`inventory_inventoryID` )
    REFERENCES `resturantDB`.`inventory` (`inventoryID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stockOrder_supplier1`
    FOREIGN KEY (`supplier_supplierID` )
    REFERENCES `resturantDB`.`supplier` (`supplierID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`sales_recipe`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`sales_recipe` (
  `sales_recipeID` INT NOT NULL ,
  `recipeID` INT NOT NULL ,
  `stockID` INT NOT NULL ,
  PRIMARY KEY (`sales_recipeID`, `recipeID`, `stockID`) ,
  INDEX `fk_sales_recipt_reciept1_idx` (`recipeID` ASC) ,
  INDEX `fk_sales_recipt_stock1_idx` (`stockID` ASC) ,
  CONSTRAINT `fk_sales_recipt_reciept1`
    FOREIGN KEY (`recipeID` )
    REFERENCES `resturantDB`.`recipe` (`recipeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_recipt_stock1`
    FOREIGN KEY (`stockID` )
    REFERENCES `resturantDB`.`sales` (`stockID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`sales_employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resturantDB`.`sales_employee` (
  `sales_employeeID` INT NOT NULL ,
  `stockID` INT NOT NULL ,
  `employeeID` INT NOT NULL ,
  PRIMARY KEY (`sales_employeeID`, `stockID`, `employeeID`) ,
  INDEX `fk_sales_employee_stock1_idx` (`stockID` ASC) ,
  INDEX `fk_sales_employee_employee1_idx` (`employeeID` ASC) ,
  CONSTRAINT `fk_sales_employee_stock1`
    FOREIGN KEY (`stockID` )
    REFERENCES `resturantDB`.`sales` (`stockID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_employee_employee1`
    FOREIGN KEY (`employeeID` )
    REFERENCES `resturantDB`.`employee` (`employeeID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `resturantDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
