/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zanesmith
 */
public class DatabaseEdits {

    static class refresh {

        public refresh() throws SQLException {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useSSL=false", "root", "");
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = null;
            -- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `resturantDB`.`inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`inventory` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`inventory` (
  `inventoryID` INT NOT NULL AUTO_INCREMENT,
  `item` VARCHAR(45) NULL,
  `qty` INT NULL,
  PRIMARY KEY (`inventoryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`supplier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`supplier` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`supplier` (
  `supplierID` INT NOT NULL AUTO_INCREMENT,
  `supplierName` VARCHAR(45) NULL,
  `supplierEmail` VARCHAR(45) NULL,
  `supplierNumber` VARCHAR(45) NULL,
  `supplierAddress` VARCHAR(45) NULL,
  PRIMARY KEY (`supplierID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`employee` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`employee` (
  `employeeID` INT NOT NULL AUTO_INCREMENT,
  `employeeFName` VARCHAR(45) NULL,
  `employeeLName` VARCHAR(45) NULL,
  `employeePassword` VARCHAR(45) NULL,
  `employeeContactNumber` VARCHAR(45) NULL,
  PRIMARY KEY (`employeeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`reservation` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`reservation` (
  `reservationID` INT NOT NULL AUTO_INCREMENT,
  `employeeID` INT NOT NULL,
  `reservationDate` VARCHAR(45) NULL,
  `reservationTime` VARCHAR(45) NULL,
  `reservationCustomer` VARCHAR(45) NULL,
  `reservationTableNumber` VARCHAR(45) NULL,
  `reservationNumberPeople` VARCHAR(45) NULL,
  PRIMARY KEY (`reservationID`, `employeeID`),
  CONSTRAINT `fk_reservation_employee1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `resturantDB`.`employee` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`recipe` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`recipe` (
  `recipeID` INT NOT NULL AUTO_INCREMENT,
  `recipeName` VARCHAR(45) NULL,
  `recipePrice` VARCHAR(45) NULL,
  `recipeVAT` VARCHAR(45) NULL,
  PRIMARY KEY (`recipeID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`inventory_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`inventory_recipe` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`inventory_recipe` (
  `inventory_recipeID` INT NOT NULL,
  `inventoryID` INT NOT NULL,
  `recipeID` INT NOT NULL,
  PRIMARY KEY (`inventory_recipeID`, `inventoryID`, `recipeID`),
  CONSTRAINT `fk_inventory_recipt_inventory`
    FOREIGN KEY (`inventoryID`)
    REFERENCES `resturantDB`.`inventory` (`inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_recipt_reciept1`
    FOREIGN KEY (`recipeID`)
    REFERENCES `resturantDB`.`recipe` (`recipeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`stockOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`stockOrder` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`stockOrder` (
  `stockOrderID` INT NOT NULL AUTO_INCREMENT,
  `inventoryID` INT NOT NULL,
  `supplierID` INT NOT NULL,
  `dateOrdered` VARCHAR(45) NULL,
  `quantity` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`stockOrderID`, `inventoryID`, `supplierID`),
  CONSTRAINT `fk_stockOrder_inventory1`
    FOREIGN KEY (`inventoryID`)
    REFERENCES `resturantDB`.`inventory` (`inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stockOrder_supplier1`
    FOREIGN KEY (`supplierID`)
    REFERENCES `resturantDB`.`supplier` (`supplierID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`Sale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`Sale` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`Sale` (
  `saleID` INT NOT NULL,
  `inventory_inventoryID` INT NOT NULL,
  `saleDate` VARCHAR(45) NULL,
  `SaleTotalCost` INT NULL,
  `SaleTotalVAT` INT NULL,
  PRIMARY KEY (`saleID`, `inventory_inventoryID`),
  CONSTRAINT `fk_Sale_inventory1`
    FOREIGN KEY (`inventory_inventoryID`)
    REFERENCES `resturantDB`.`inventory` (`inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`sales_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`sales_recipe` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`sales_recipe` (
  `sales_recipeID` INT NOT NULL,
  `recipeID` INT NOT NULL,
  `saleID` INT NOT NULL,
  `inventoryID` INT NOT NULL,
  PRIMARY KEY (`sales_recipeID`, `recipeID`, `saleID`, `inventoryID`),
  CONSTRAINT `fk_sales_recipt_reciept1`
    FOREIGN KEY (`recipeID`)
    REFERENCES `resturantDB`.`recipe` (`recipeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_recipe_Sale1`
    FOREIGN KEY (`saleID` , `inventoryID`)
    REFERENCES `resturantDB`.`Sale` (`saleID` , `inventory_inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resturantDB`.`sales_employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resturantDB`.`sales_employee` ;

CREATE TABLE IF NOT EXISTS `resturantDB`.`sales_employee` (
  `sales_employeeID` INT NOT NULL,
  `employeeID` INT NOT NULL,
  `saleID` INT NOT NULL,
  `inventoryID` INT NOT NULL,
  PRIMARY KEY (`sales_employeeID`, `employeeID`, `saleID`, `inventoryID`),
  CONSTRAINT `fk_sales_employee_employee1`
    FOREIGN KEY (`employeeID`)
    REFERENCES `resturantDB`.`employee` (`employeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_employee_Sale1`
    FOREIGN KEY (`saleID` , `inventoryID`)
    REFERENCES `resturantDB`.`Sale` (`saleID` , `inventory_inventoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

            try {

                // 3. Execute SQL query
                myStmt.executeUpdate("CREATE DATABASE if not exists resturantdb");
                myStmt.executeUpdate("use restaurant");
                 myStmt.executeUpdate("drop table if exists inventory;");
                myStmt.executeUpdate("drop table if exists supplier;");
                myStmt.executeUpdate("drop table if exists employee;");
                myStmt.executeUpdate("drop table if exists reservation;");
                myStmt.executeUpdate("drop table if exists recipe;");
                myStmt.executeUpdate("drop table if exists inventory_recipe;");
                myStmt.executeUpdate("drop table if exists stockOrder;");
                myStmt.executeUpdate("drop table if exists sale;");
                myStmt.executeUpdate("drop table if exists sales_recipe;");
                myStmt.executeUpdate("drop table if exists sales_employee;");
                
                
                String myTableName = "CREATE TABLE inventory ("
                        + "inventoryID INT(64) NOT NULL AUTO_INCREMENT,"
                        + "item VARCHAR(75),"
                        + "qty VARCHAR(64),"
                        + "stock INT(64),PRIMARY KEY (inventoryID))";
                
                
                myStmt.executeUpdate(myTableName);
                myTableName = "CREATE TABLE supplier ("
                        + "supplierID INT(64) NOT NULL AUTO_INCREMENT,"
                        + "supplierName VARCHAR(45),"
                        + "supplierEmail VARCHAR(45),"
                        + "supplierNumber VARCHAR(45),"
                        + "supplierAddress VARCHAR(45),"
                        + "PRIMARY KEY (supplierID))";
                
                
                myStmt.executeUpdate(myTableName);
                myTableName = "CREATE TABLE employee ("
                        + "employeeID INT(64) NOT NULL AUTO_INCREMENT,"
                        + "employeeFName VARCHAR(64),"
                        + "employeeLName VARCHAR(64),"
                        + "employeePassword VARCHAR(75),"
                        + "employeeContactNumber VARCHAR(75),"
                        + "PRIMARY KEY (employeeID))";
                
                myStmt.executeUpdate(myTableName);
                myTableName = "CREATE TABLE employee ("
                        + "employeeID INT(64) NOT NULL AUTO_INCREMENT,"
                        + "employeeFName VARCHAR(64),"
                        + "employeeLName VARCHAR(64),"
                        + "employeePassword VARCHAR(75),"
                        + "employeeContactNumber VARCHAR(75),"
                        + "PRIMARY KEY (employeeID))";
                
                
                
                
                myStmt.executeUpdate(myTableName);
            } catch (Exception exc) {
                exc.printStackTrace();
            } finally {

                if (myStmt != null) {
                    myStmt.close();
                }

                if (myConn != null) {
                    myConn.close();
                }
            }
        }
    }
    
}
