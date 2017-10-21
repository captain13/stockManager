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
  `employeeHoursWorked` varchar(45) DEFAULT '0hrs00',
  `employeeStatus` varchar(45) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`employeeID`),
  UNIQUE KEY `employeeFName_UNIQUE` (`employeeFName`,`employeeLName`,`employeeContactNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Admin','Admin','AdPassword#$','0','0',NULL,1),(2,'Andrew','Schwabe','Password','076 273 1721','0hrs00','Active',1),(3,'Zane','Smith','Password','034 233 8321','0hrs00','Active',1),(4,'Chad','Phillips','Password','087 237 1277','0hrs00','Active',0),(5,'Sean','Thomson','Password','072 377 1122','0hrs00',NULL,0),(6,'Itumeleng','Madisha','Password','072 377 1122','0hrs00',NULL,0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenses` (
  `expensesID` int(11) NOT NULL AUTO_INCREMENT,
  `expensesType` varchar(45) DEFAULT NULL,
  `expensesAmount` decimal(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `stockOrderID` int(11) DEFAULT NULL,
  `wageID` int(11) DEFAULT NULL,
  PRIMARY KEY (`expensesID`),
  KEY `fk_expenses_wage1_idx` (`wageID`),
  KEY `fk_expenses_stockorder1_idx` (`stockOrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
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
  `qty` int(20) DEFAULT NULL,
  `itemThreshold` double DEFAULT NULL,
  `itemLimit` int(11) DEFAULT NULL,
  `itemCost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`inventoryID`),
  UNIQUE KEY `item_UNIQUE` (`item`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,'Chicken Fillet','Meat',400,0.25,1000,15.00),(2,'Beef Burger Patty','Meat',1000,0.25,1000,15.00),(3,'Burger rolls','Grain',700,0.25,1000,15.00),(4,'Lettuce','Vegetable',0,0.5,1000,15.00),(5,'Tomatoe','Vegetable',0,0.5,100,15.00),(6,'Chicken Wings','Meat',1000,0.2,1000,15.00),(7,'Pork Ribs','Meat',1000,0.2,1000,15.00),(8,'Chicken Breast','Meat',1000,0.2,1000,15.00),(9,'Avocardo','Vegetable',1000,0.25,1000,15.00),(10,'Steak','Meat',1000,0.25,1000,15.00),(11,'Cheese','Dairy',1000,0.25,1000,15.00),(12,'Feta','Dairy',100,0.5,1000,15.00),(13,'Pizza Base','Grain',100,0.5,100,15.00),(14,'Napoletan Sauce','Other',1000,0.2,1000,15.00),(15,'Bacon','Meat',1000,0.2,1000,15.00),(16,'Castle Light','Alcohol',1000,0.2,1000,15.00),(17,'Black Label Beer','Alcohol',1000,0.25,1000,15.00),(18,'Coke','Soft Drinks',100,0.5,1000,15.00),(19,'Coke Light','Soft Drinks',100,0.5,100,15.00),(20,'Sprite','Soft Drinks',1000,0.2,1000,15.00),(21,'Fanta','Soft Drinks',1000,0.2,1000,15.00),(22,'Ice Tea','Soft Drinks',6600,0.2,1000,15.00),(23,'Chips','Other',0,0.5,100,15.00);
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
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`inventory_recipeID`,`inventoryID`,`recipeID`),
  KEY `fk_inventory_recipt_inventory` (`inventoryID`),
  KEY `fk_inventory_recipt_reciept1` (`recipeID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_recipe`
--

LOCK TABLES `inventory_recipe` WRITE;
/*!40000 ALTER TABLE `inventory_recipe` DISABLE KEYS */;
INSERT INTO `inventory_recipe` VALUES (1,1,1,200),(2,3,1,100),(3,4,1,50),(4,5,1,50),(5,23,1,100),(6,2,2,200),(7,3,2,100),(8,4,2,50),(9,5,2,50),(10,11,2,160),(11,23,2,160),(12,7,3,300),(13,23,3,100);
/*!40000 ALTER TABLE `inventory_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `recipeID` int(11) NOT NULL,
  `orderQuantity` int(11) DEFAULT NULL,
  `salesID` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID`,`recipeID`,`salesID`),
  KEY `fk_receipt_recipe1` (`recipeID`),
  KEY `fk_receipt_sales1` (`salesID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (1,1,1,1,'2017-10-21','13:58:54',75.00),(2,1,1,2,'2017-10-21','14:03:20',75.00),(3,1,1,3,'2017-10-21','14:06:44',75.00);
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
  `recipePrice` decimal(10,2) DEFAULT NULL,
  `recipeVAT` decimal(10,2) DEFAULT NULL,
  `recipeType` varchar(45) DEFAULT NULL,
  `recipeImageDirectory` varchar(150) DEFAULT NULL,
  `recipeCount` int(11) DEFAULT '0',
  `specialsID` int(11) DEFAULT NULL,
  PRIMARY KEY (`recipeID`),
  KEY `fk_recipe_specials1_idx` (`specialsID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Chicken Burger & Chips',75.00,10.50,'Main Meal','./src/images/_f_chickenBurger.jpg',11,0),(2,'Beef Burger & Chips',75.00,10.50,'Main Meal','./src/images/_f_beefBurger.jpg',8,0),(3,'Pork Ribs & Chips',75.00,10.50,'Main Meal','./src/images/_f_porkRibs.jpg',8,0),(4,'Chicken Wings & Chips',75.00,10.50,'Light Meal','./src/images/_f_chickenWings.jpg',8,0),(5,'Green Salad',75.00,10.50,'Light Meal','./src/images/_f_salad.jpg',8,0),(6,'Ceasar Salad',75.00,10.50,'Light Meal','./src/images/_f_caesarSalad.jpg',8,0),(7,'Coke',75.00,10.50,'Drinks','./src/images/_d_cokeLogo.jpg',8,0),(8,'Coke Light',75.00,10.50,'Drinks','./src/images/_d_cokeLightLogo.jpg',8,0),(9,'Fanta',75.00,10.50,'Drinks','./src/images/_d_fantaLogo.jpg',8,0),(10,'Sprite',75.00,10.50,'Drinks','./src/images/_d_spriteLogo.jpg',8,0),(11,'Ice Tea Peach',75.00,10.50,'Drinks','./src/images/_d_liptonLogo.jpg',8,0),(12,'Ice Tea Lemon',75.00,10.50,'Drinks','./src/images/_d_liptonLogo.jpg',8,0),(13,'Ice Cream & Chocolate Sauce',75.00,10.50,'Dessert','./src/images/_d_Choc_Icecream.jpg',8,0),(14,'Chocolate Mousse',75.00,10.50,'Dessert','./src/images/_d_Choc_Mousse.jpg',8,0),(15,'Large Chips',75.00,10.50,'Extra','./src/images/_e_chips.jpg',8,0),(16,'Small Chips',75.00,10.50,'Extra','./src/images/_e_chips.jpg',8,0),(17,'Avocardo',75.00,10.50,'Extra','./src/images/_e_avocardo.jpg',8,0),(18,'Feta',75.00,10.50,'Extra','./src/images/_e_feta.jpg',8,0),(19,'Tomato',75.00,10.50,'Extra','./src/images/_e_tomato.jpg',8,0);
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
  `reservationTime` time DEFAULT NULL,
  `reservationCustomer` varchar(45) DEFAULT NULL,
  `reservationTableNumber` int(11) DEFAULT NULL,
  `reservationNumberPeople` int(11) DEFAULT NULL,
  `contactNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reservationID`,`employeeID`),
  KEY `fk_reservation_employee1` (`employeeID`)
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
  `totalCost` decimal(10,2) DEFAULT NULL,
  `saleType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`salesID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,'2017-10-21',75.00,'Cash'),(2,'2017-10-21',75.00,'Cash'),(3,'2017-10-21',75.00,'Cash');
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
  KEY `fk_sales_employee_sales1` (`salesID`)
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
  `specialsPrice` decimal(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`specialsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specials`
--

LOCK TABLES `specials` WRITE;
/*!40000 ALTER TABLE `specials` DISABLE KEYS */;
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
  `quantity` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `totalCost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`stockOrderID`,`inventoryID`,`supplierID`),
  KEY `fk_stockOrder_inventory1` (`inventoryID`),
  KEY `fk_stockOrder_supplier1` (`supplierID`)
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
  PRIMARY KEY (`supplierID`),
  UNIQUE KEY `supplierName_UNIQUE` (`supplierName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Meat Co.','swabe@live.co.za','084 772 2883','Rondebosch'),(2,'VegCity','swabe@live.co.za','078 232 1221','Claremont');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wage`
--

DROP TABLE IF EXISTS `wage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wage` (
  `wageID` int(11) NOT NULL AUTO_INCREMENT,
  `employeeID` int(11) NOT NULL,
  `wageAmount` decimal(10,2) DEFAULT NULL,
  `postion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wageID`,`employeeID`),
  KEY `fk_wage_employee1_idx` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wage`
--

LOCK TABLES `wage` WRITE;
/*!40000 ALTER TABLE `wage` DISABLE KEYS */;
/*!40000 ALTER TABLE `wage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-21 14:21:15
