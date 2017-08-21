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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Andrew','Schwabe','Password','076 273 1721','00:00','Active',NULL,1),(2,'Zane','Smith','Password','034 233 8321','00:00',NULL,NULL,1),(3,'Chad','Phillips','Password','087 237 1277','00:00',NULL,NULL,0),(4,'Sean','Thompson','Password','072 377 1122','00:00',NULL,NULL,0);
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
  `qty` double DEFAULT NULL,
  `itemThreshold` double DEFAULT '0.25',
  `itemLimit` double DEFAULT NULL,
  PRIMARY KEY (`inventoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'Chicken Fillet ',1000,0.25,1000),(2,'Beef Burger Patty',1000,0.25,1000),(3,'Burger rolls',1000,0.25,1000),(4,'Lettuce',100,0.5,1000),(5,'Tomatoe',100,0.5,100),(6,'Chicken Wings',1000,0.2,1000),(7,'Pork Ribs',1000,0.2,1000),(8,'Chips',1000,0.2,1000),(9,'Pizza Base ',3000,0.2,6000),(10,'Cheese',6000,0.2,7000),(11,'Neapolitan sauce',5000,0.2,5000),(12,'Salami',2000,0.2,2000),(13,'Feta',2000,0.2,2000),(14,'Avocado',2000,0.2,2000),(15,'Bacon Pieces',2000,0.2,2000),(16,'Coke',6600,0.2,13200),(17,'Fanta',6600,0.2,6600),(18,'Diet Coke',6600,0.2,6600),(19,'Lipton Peach Ice Tea',6600,0.2,6600),(20,'Chips',6600,0.5,6600);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_recipe`
--

LOCK TABLES `inventory_recipe` WRITE;
/*!40000 ALTER TABLE `inventory_recipe` DISABLE KEYS */;
INSERT INTO `inventory_recipe` VALUES (1,1,1,200),(2,3,1,100),(3,4,1,50),(4,5,1,50),(5,8,1,100),(6,2,2,200),(7,3,2,100),(8,4,2,50),(9,5,2,50),(10,8,2,100),(11,6,3,300),(12,8,3,100),(13,7,4,300),(14,8,4,100),(15,9,5,400),(16,10,5,300),(17,11,5,100),(18,13,5,50),(19,14,5,25),(20,15,5,25),(21,9,6,400),(22,10,6,300),(23,11,6,100),(24,12,6,50),(25,20,7,200),(26,16,8,330),(27,18,9,330),(28,17,10,330),(29,19,11,330);
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
  `recipePrice` varchar(45) DEFAULT NULL,
  `recipeVAT` varchar(45) DEFAULT NULL,
  `recipeType` varchar(45) DEFAULT NULL,
  `recipeImageDirectory` varchar(150) DEFAULT NULL,
  `recipeCount` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`recipeID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Chicken Burger & Chips','65.00','9.10','Main Meal','./src/images/_f_chickenBurger.jpg',NULL),(2,'Beef Burger & Chips','65.00','9.10','Main Meal','./src/images/_f_beefBurger.jpg',NULL),(3,'Chicken Wings & Chips','55.00','7.70','Light Meal','./src/images/_f_chickenWings.jpg',NULL),(4,'Pork Ribs & Chips','90.00','12.60','Main Meal','./src/images/_f_porkRibs.jpg',NULL),(5,'California Pizza','90.00','12.60','Main Meal','./src/images/_f_pizza1.jpg',NULL),(6,'Salami Pizza','85.00','11.90','Main Meal','./src/images/_f_pizza.jpg',NULL),(7,'Chips','20.00','2.80','Extra','./src/images/_e_chips.jpg',''),(8,'Coke','15.00','2.10','Drinks','./src/images/_d_cokeLogo.jpg',NULL),(9,'Coke Zero','15.00','2.10','Drinks','./src/images/_d_cokeLightLogo.jpg',NULL),(10,'Fanta','15.00','2.10','Drinks','./src/images/_d_fantaLogo.jpg',NULL),(11,'Lipton Peach Ice-Tea','20.00','2.80','Drinks','./src/images/_d_liptonLogo.jpg',NULL);
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
  `reservationDate` varchar(45) DEFAULT NULL,
  `reservationTime` varchar(45) DEFAULT NULL,
  `reservationCustomer` varchar(45) DEFAULT NULL,
  `reservationTableNumber` varchar(45) DEFAULT NULL,
  `reservationNumberPeople` varchar(45) DEFAULT NULL,
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
  `salesDate` varchar(45) DEFAULT NULL,
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
-- Table structure for table `sales_recipe`
--

DROP TABLE IF EXISTS `sales_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_recipe` (
  `sales_recipeID` int(11) NOT NULL,
  `recipeID` int(11) NOT NULL,
  `saleID` int(11) NOT NULL,
  `inventoryID` int(11) NOT NULL,
  PRIMARY KEY (`sales_recipeID`,`recipeID`,`saleID`,`inventoryID`),
  KEY `fk_sales_recipt_reciept1` (`recipeID`),
  KEY `fk_sales_recipe_Sale1` (`saleID`,`inventoryID`),
  CONSTRAINT `fk_sales_recipe_Sale1` FOREIGN KEY (`saleID`, `inventoryID`) REFERENCES `sale` (`saleID`, `inventory_inventoryID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_recipt_reciept1` FOREIGN KEY (`recipeID`) REFERENCES `recipe` (`recipeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_recipe`
--

LOCK TABLES `sales_recipe` WRITE;
/*!40000 ALTER TABLE `sales_recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_recipe` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockorder`
--

LOCK TABLES `stockorder` WRITE;
/*!40000 ALTER TABLE `stockorder` DISABLE KEYS */;
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

-- Dump completed on 2017-08-21 10:48:50
