-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: kindergarden
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `last_payment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (10001,0,'/','2017-12-01 00:00:00'),(10002,0,'/','2017-12-01 00:00:00'),(10003,0,'/','2017-12-01 00:00:00');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `classroom_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_class_1_idx` (`classroom_id`),
  CONSTRAINT `fk_class_1` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (101,'Justice League',1001,150,''),(102,'Avengers',1002,100,'Fun'),(103,'Bat Family',1003,100,'Dark and gloomy');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `floor` int(6) NOT NULL,
  `room_number` int(11) NOT NULL,
  PRIMARY KEY (`id`,`name`,`floor`,`room_number`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1001,'Watch Tower',2,209),(1002,'Avengers Mansion',1,102),(1003,'Bat Cave',3,303);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT '/',
  `date_of_employment` date DEFAULT NULL,
  `pay` double NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `password` varchar(43) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (11,'Alexander','Luthor','Bald dude!','2004-07-12',6000000,'Teacher','voqxgmGfp4+hAonOGu0KYeabf/TDHbM2Zo+8YEFT0mk'),(12,'John','Jones','Green dude','1991-12-01',5000,'Teacher','Pd5rTTXJlm5koFosuxA0/QywUctCRMio8o3F+caokV0'),(13,'Martin','Stein','Old dude.','2001-09-08',5000,'Teacher','uA8x/FD71crYbXJdAaJxOZW0SW3xAewnVQJ2vBkfrew'),(14,'Reed','Richards','Smart dude.','2014-05-14',4500,'Accountaint','5tmHt7PeOCmbmHjPFeXL82o3cGN/C0KKW6iCjbABVgk');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardian`
--

DROP TABLE IF EXISTS `guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guardian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `UMCN` varchar(12) DEFAULT NULL,
  `comment` varchar(200) DEFAULT '/',
  `account_id` int(11) DEFAULT NULL,
  `password` varchar(43) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_guardian_1_idx` (`account_id`),
  CONSTRAINT `fk_guardian_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardian`
--

LOCK TABLES `guardian` WRITE;
/*!40000 ALTER TABLE `guardian` DISABLE KEYS */;
INSERT INTO `guardian` VALUES (1,'Clark','Kent','125496378946','Dude looks like Superman?',10001,'cv66Cd1IrS10NlZwOVGUfc8wSSG1cFurAZZ0Y3ku9S8'),(2,'Louis','Lane','689534679125','Reporter',10001,'q8+1Ox/wLYyIB0/tbYfLsCAWYagBXawIcLiizwkcqG8'),(3,'Bruce','Wayne','986574326498','Rich guy',10002,'EcNq9+bqLS9pMOMS93ll+Zr9gtXHPOnLjLsUG0mFZ/Y'),(4,'Selina','Kayle','789456359715','Loves cats, stuff goes missing around her!',10002,'NRaL6w5H9zTU0yY7M+fer5Tf0TTSeXzvUOhTp+q71os'),(5,'Barry','Allen','964587321648','Dude is always late.',10003,'89MbzqVBsJd33L7Lr1PuRgViwzU18LXw+Ynr1HexmeQ'),(6,'Diana','Prince','986573154695','Damn!',10003,'5LtMat0tofiaNN+Jmo+2JgGfQ7SUTDQKwY4VrzDHwlE');
/*!40000 ALTER TABLE `guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid`
--

DROP TABLE IF EXISTS `kid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID_UNIQUE` (`id`),
  KEY `fk_kid_1_idx` (`group_id`),
  CONSTRAINT `fk_kid_1` FOREIGN KEY (`group_id`) REFERENCES `class` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid`
--

LOCK TABLES `kid` WRITE;
/*!40000 ALTER TABLE `kid` DISABLE KEYS */;
INSERT INTO `kid` VALUES (1,'Richard','Grayson','2012-11-11',101,'Likes Acrobatics'),(2,'Damian','Wayne','2013-02-27',103,'Hir to the mantle of the bat'),(3,'Jason','Todd','2012-08-16',103,'Aggressive'),(4,'Timothy','Drake','2012-06-23',103,'Smart'),(5,'Hal','Jordan','2012-02-20',101,'Likes green and to fly.'),(6,'Victor','Stone','2013-12-03',101,'Tech freak.'),(7,'Oliver','Queen','2013-05-08',101,'Very precise.'),(8,'Arthur','Qurry','2012-01-29',101,'Likes swimming.'),(9,'Lucifer','Morningstar','2011-12-31',102,'Kid\'s the devil.'),(10,'Tony','Stark','2012-05-29',102,'Rich and arogant.'),(11,'Peter','Parker','2013-06-27',102,'Nice kid. Likes spiders.'),(12,'Stiven','Strange','2012-07-19',102,'Want\'s to be a magician'),(13,'Thor','Odinson','2012-01-01',102,'Goldilocks'),(14,'Bruce','Banner','2012-12-18',102,'You wouldn\'t like him when he\'s angry.'),(15,'Steve','Rogers','2012-07-04',102,'Natural leader.'),(16,'Wade','Wilson','2012-11-22',102,'Wild'),(17,'James','Howlett','2012-09-27',102,'Wolverine'),(18,'Jon','Snow','2017-12-11',101,'King in the north');
/*!40000 ALTER TABLE `kid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kid_guardian`
--

DROP TABLE IF EXISTS `kid_guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kid_guardian` (
  `guardian_id` int(11) NOT NULL,
  `kid_id` int(11) NOT NULL,
  `realtion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`guardian_id`,`kid_id`),
  KEY `fk_kid_guardian_2_idx` (`kid_id`),
  CONSTRAINT `fk_kid_guardian_1` FOREIGN KEY (`guardian_id`) REFERENCES `guardian` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_kid_guardian_2` FOREIGN KEY (`kid_id`) REFERENCES `kid` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kid_guardian`
--

LOCK TABLES `kid_guardian` WRITE;
/*!40000 ALTER TABLE `kid_guardian` DISABLE KEYS */;
INSERT INTO `kid_guardian` VALUES (1,5,'Father'),(1,6,'Father'),(1,7,'Father'),(1,8,'Father'),(1,9,'Father'),(2,5,'Mother'),(2,6,'Mother'),(2,7,'Mother'),(2,8,'Mother'),(2,9,'Mother'),(3,1,'Step-father'),(3,2,'Father'),(3,3,'Step-father'),(3,4,'Step-father'),(4,1,'Step-mother'),(4,2,'Step-mother'),(4,3,'Step-mother'),(4,4,'Step-mother'),(5,10,'Father'),(5,11,'Father'),(5,12,'Father'),(5,13,'Father'),(5,14,'Father'),(5,15,'Father'),(5,16,'Father'),(5,17,'Guardian'),(6,10,'Mother'),(6,11,'Mother'),(6,12,'Mother'),(6,13,'Mother'),(6,14,'Mother'),(6,15,'Mother'),(6,16,'Mother'),(6,17,'Guardian');
/*!40000 ALTER TABLE `kid_guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_payment_1_idx` (`account_id`),
  CONSTRAINT `fk_payment_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,10001,600,'2017-11-30 23:00:00',NULL),(4,10002,500,'2017-11-30 23:00:00',NULL),(5,10003,800,'2017-11-30 23:00:00',NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_group`
--

DROP TABLE IF EXISTS `teacher_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_group` (
  `teacher_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`,`group_id`),
  KEY `fk_teacher_group_2_idx` (`group_id`),
  CONSTRAINT `fk_teacher_group_1` FOREIGN KEY (`teacher_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_group_2` FOREIGN KEY (`group_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_group`
--

LOCK TABLES `teacher_group` WRITE;
/*!40000 ALTER TABLE `teacher_group` DISABLE KEYS */;
INSERT INTO `teacher_group` VALUES (11,101,'Shady business'),(11,102,'What? Wrong universe?'),(12,101,''),(12,102,NULL),(13,103,NULL);
/*!40000 ALTER TABLE `teacher_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-29 11:29:14
