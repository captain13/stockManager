-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: resturantdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeFName` varchar(45) DEFAULT NULL,
  `employeeLName` varchar(45) DEFAULT NULL,
  `employeePassword` varchar(45) NOT NULL,
  `employeeContactNumber` varchar(45) DEFAULT NULL,
  `employeeHoursWorked` varchar(45) DEFAULT '00:00',
  `employeeStatus` varchar(45) DEFAULT NULL,
  `employeePosition` varchar(45) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Andrew','Schwabe','Password','076 273 1721','00:00',NULL,NULL,1),(2,'Zane','Smith','Password','034 233 8321','00:00',NULL,NULL,1),(3,'Chad','Phillips','Password','087 237 1277','00:00',NULL,NULL,0),(4,'Sean','Thompson','Password','072 377 1122','00:00',NULL,NULL,0),(5,'Itumeleng','Madisha','Password','072 377 1122','00:00',NULL,NULL,0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `inventoryID` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `itemThreshold` double DEFAULT NULL,
  `itemLimit` int(11) DEFAULT NULL,
  PRIMARY KEY (`inventoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'Chicken Fillet ',NULL,1000,0.25,1000),(2,'Beef Burger Patty',NULL,1000,0.25,1000),(3,'Burger rolls',NULL,1000,0.25,1000),(4,'Lettuce',NULL,100,0.5,1000),(5,'Tomatoe',NULL,100,0.5,100),(6,'Chicken Wings',NULL,1000,0.2,1000),(7,'Pork Ribs',NULL,1000,0.2,1000),(8,'Chips',NULL,100,0.2,1000);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_recipe`
--

DROP TABLE IF EXISTS `inventory_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_recipe` (
  `inventory_recipeID` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryID` int(11) NOT NULL,
  `recipeID` int(11) NOT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`inventory_recipeID`,`inventoryID`,`recipeID`),
  KEY `fk_inventory_recipt_inventory` (`inventoryID`),
  KEY `fk_inventory_recipt_reciept1` (`recipeID`),
  CONSTRAINT `fk_inventory_recipt_inventory` FOREIGN KEY (`inventoryID`) REFERENCES `inventory` (`inventoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_recipt_reciept1` FOREIGN KEY (`recipeID`) REFERENCES `recipe` (`recipeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_recipe`
--

LOCK TABLES `inventory_recipe` WRITE;
/*!40000 ALTER TABLE `inventory_recipe` DISABLE KEYS */;
INSERT INTO `inventory_recipe` VALUES (11,1,3,200),(12,3,3,100),(13,4,3,50),(14,5,3,50),(15,8,3,100),(16,2,4,200),(17,3,4,100),(18,4,4,50),(19,5,4,50),(20,8,4,160);
/*!40000 ALTER TABLE `inventory_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `receiptID` int(11) NOT NULL AUTO_INCREMENT,
  `recipeID` int(11) NOT NULL,
  `salesID` int(11) NOT NULL,
  `orderQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`receiptID`,`recipeID`,`salesID`),
  KEY `fk_receipt_recipe1` (`recipeID`),
  KEY `fk_receipt_sales1` (`salesID`),
  CONSTRAINT `fk_receipt_recipe1` FOREIGN KEY (`recipeID`) REFERENCES `recipe` (`recipeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_sales1` FOREIGN KEY (`salesID`) REFERENCES `sales` (`salesID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe` (
  `recipeID` int(11) NOT NULL AUTO_INCREMENT,
  `recipeName` varchar(45) DEFAULT NULL,
  `recipePrice` double DEFAULT NULL,
  `recipeVAT` double DEFAULT NULL,
  `recipeType` varchar(45) DEFAULT NULL,
  `recipeImageDirectory` varchar(150) DEFAULT NULL,
  `recipeCount` int(11) DEFAULT NULL,
  `specialsID` int(11) DEFAULT NULL,
  PRIMARY KEY (`recipeID`),
  KEY `fk_recipe_specials1_idx` (`specialsID`),
  CONSTRAINT `fk_recipe_specials1` FOREIGN KEY (`specialsID`) REFERENCES `specials` (`specialsID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (3,'Chicken Burger & Chips',75,10.5,'Main Meal','./src/images/_f_chickenBurger.jpg',8,1),(4,'Beef Burger & Chips',75,10.5,'Main Meal','./src/images/_f_beefBurger.jpg',8,2);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservationID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `reservationDate` date DEFAULT NULL,
  `reservationTime` varchar(45) DEFAULT NULL,
  `reservationCustomer` varchar(45) DEFAULT NULL,
  `reservationTableNumber` int(11) DEFAULT NULL,
  `reservationNumberPeople` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservationID`,`employeeID`),
  KEY `fk_reservation_employee1` (`employeeID`),
  CONSTRAINT `fk_reservation_employee1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `salesID` int(11) NOT NULL AUTO_INCREMENT,
  `salesDate` date DEFAULT NULL,
  `totalCost` double DEFAULT NULL,
  PRIMARY KEY (`salesID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_employee`
--

DROP TABLE IF EXISTS `sales_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_employee` (
  `sales_employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `salesID` int(11) NOT NULL,
  PRIMARY KEY (`sales_employeeID`,`employeeID`,`salesID`),
  KEY `fk_sales_employee_employee1` (`employeeID`),
  KEY `fk_sales_employee_sales1` (`salesID`),
  CONSTRAINT `fk_sales_employee_employee1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_employee_sales1` FOREIGN KEY (`salesID`) REFERENCES `sales` (`salesID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_employee`
--

LOCK TABLES `sales_employee` WRITE;
/*!40000 ALTER TABLE `sales_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specials`
--

DROP TABLE IF EXISTS `specials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specials` (
  `specialsID` int(11) NOT NULL AUTO_INCREMENT,
  `specialsPrice` double DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`specialsID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specials`
--

LOCK TABLES `specials` WRITE;
/*!40000 ALTER TABLE `specials` DISABLE KEYS */;
INSERT INTO `specials` VALUES (1,60,0),(2,80,0);
/*!40000 ALTER TABLE `specials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockorder`
--

DROP TABLE IF EXISTS `stockorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockorder` (
  `stockOrderID` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryID` int(11) NOT NULL,
  `supplierID` int(11) NOT NULL,
  `dateOrdered` date DEFAULT NULL,
  `dateETA` date DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stockOrderID`,`inventoryID`,`supplierID`),
  KEY `fk_stockOrder_inventory1` (`inventoryID`),
  KEY `fk_stockOrder_supplier1` (`supplierID`),
  CONSTRAINT `fk_stockOrder_inventory1` FOREIGN KEY (`inventoryID`) REFERENCES `inventory` (`inventoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_stockOrder_supplier1` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockorder`
--

LOCK TABLES `stockorder` WRITE;
/*!40000 ALTER TABLE `stockorder` DISABLE KEYS */;
INSERT INTO `stockorder` VALUES (1,1,1,'2017-08-31','2017-08-31',2000,'Delievered'),(2,4,2,'2017-08-31','2017-10-10',2000,'Not Delievered');
/*!40000 ALTER TABLE `stockorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierID` int(11) NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(45) DEFAULT NULL,
  `supplierEmail` varchar(45) DEFAULT NULL,
  `supplierNumber` varchar(45) DEFAULT NULL,
  `supplierAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Meat Co.','meatco@gmail.com','084 772 2883','Rondebosch'),(2,'VegCity','vegcity@hotmail.co.za','078 232 1221','Claremont');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-31  0:32:04
