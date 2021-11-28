-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: car_depot
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car_models`
--

DROP TABLE IF EXISTS `car_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_models` (
  `model_id` int NOT NULL AUTO_INCREMENT,
  `car_model` varchar(200) NOT NULL,
  `capacity` int NOT NULL,
  `load_type` varchar(100) NOT NULL,
  `fuel_tank` int NOT NULL,
  `car_type` int NOT NULL,
  PRIMARY KEY (`model_id`),
  UNIQUE KEY `model_UNIQUE` (`car_model`),
  KEY `model_type_idx` (`car_type`),
  CONSTRAINT `model_type_car_type` FOREIGN KEY (`car_type`) REFERENCES `car_types` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_models`
--

LOCK TABLES `car_models` WRITE;
/*!40000 ALTER TABLE `car_models` DISABLE KEYS */;
INSERT INTO `car_models` VALUES (1,'Volkswagen Transporter T4',8,'people',70,1),(2,'MAZ 232',35,'people',300,2),(3,'Scania P',1000,'goods',150,3),(4,'Scania S',5000,'goods',600,4);
/*!40000 ALTER TABLE `car_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_types`
--

DROP TABLE IF EXISTS `car_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_types` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `car_type` varchar(100) NOT NULL,
  `required_license_type` int NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_UNIQUE` (`car_type`),
  KEY `license_type_idx` (`required_license_type`),
  CONSTRAINT `license_type_car_type` FOREIGN KEY (`required_license_type`) REFERENCES `license_types` (`license_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_types`
--

LOCK TABLES `car_types` WRITE;
/*!40000 ALTER TABLE `car_types` DISABLE KEYS */;
INSERT INTO `car_types` VALUES (1,'small bus',4),(2,'bus',6),(3,'small truck',5),(4,'big truck with trailer',8);
/*!40000 ALTER TABLE `car_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(45) NOT NULL,
  `fuel_level` int NOT NULL,
  `mileage` int NOT NULL DEFAULT '10000',
  `broken` bit(1) NOT NULL DEFAULT b'0',
  `car_model` int NOT NULL,
  `driver_id` int DEFAULT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `plate_number_UNIQUE` (`plate_number`),
  KEY `car_model_idx` (`car_model`),
  KEY `drivers_id_idx` (`driver_id`),
  CONSTRAINT `car_model_cars` FOREIGN KEY (`car_model`) REFERENCES `car_models` (`model_id`),
  CONSTRAINT `drivers_id_cars` FOREIGN KEY (`driver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'6795MM-5',50,12000,_binary '',1,NULL),(2,'6949BM-5',200,25000,_binary '',2,NULL),(3,'1231BC-5',121,20000,_binary '\0',3,2),(4,'8301KO-5',400,30000,_binary '\0',4,3);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_info`
--

DROP TABLE IF EXISTS `client_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_info` (
  `user_id` int NOT NULL,
  `company` varchar(100) NOT NULL,
  `note` text,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `users_id_client_info` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_info`
--

LOCK TABLES `client_info` WRITE;
/*!40000 ALTER TABLE `client_info` DISABLE KEYS */;
INSERT INTO `client_info` VALUES (4,'LTD Co Inc','Nice guy');
/*!40000 ALTER TABLE `client_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_licenses`
--

DROP TABLE IF EXISTS `driver_licenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `driver_licenses` (
  `user_id` int NOT NULL,
  `license_type` int NOT NULL,
  `obtaining_date` date NOT NULL,
  `license_number` varchar(45) NOT NULL,
  KEY `license_type_idx` (`license_type`),
  KEY `users_id_driver_licenses` (`user_id`),
  CONSTRAINT `license_type_for_driver` FOREIGN KEY (`license_type`) REFERENCES `license_types` (`license_id`) ON DELETE CASCADE,
  CONSTRAINT `users_id_driver_licenses` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_licenses`
--

LOCK TABLES `driver_licenses` WRITE;
/*!40000 ALTER TABLE `driver_licenses` DISABLE KEYS */;
INSERT INTO `driver_licenses` VALUES (2,5,'2011-11-11','7AB250666'),(3,5,'2012-12-12','6BC654899'),(3,8,'2017-12-17','6MM645211');
/*!40000 ALTER TABLE `driver_licenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'00001','initial create tables','SQL','V00001__initial_create_tables.sql',394442553,'root','2021-11-28 12:21:39',558,1),(2,'00002','insert initial data','SQL','V00002__insert_initial_data.sql',-1169713765,'root','2021-11-28 12:21:40',78,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gas_station`
--

DROP TABLE IF EXISTS `gas_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gas_station` (
  `refuel_record_id` int NOT NULL AUTO_INCREMENT,
  `refuel_date` date NOT NULL,
  `fuel_price` int NOT NULL,
  `refuel_amount` int NOT NULL,
  `car_id` int NOT NULL,
  PRIMARY KEY (`refuel_record_id`),
  KEY `car_id_refuel` (`car_id`),
  CONSTRAINT `car_id_refuel` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gas_station`
--

LOCK TABLES `gas_station` WRITE;
/*!40000 ALTER TABLE `gas_station` DISABLE KEYS */;
INSERT INTO `gas_station` VALUES (1,'2021-10-02',2,30,1),(2,'2021-10-01',2,50,2),(3,'2021-10-10',2,40,3),(4,'2021-09-23',2,200,4);
/*!40000 ALTER TABLE `gas_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license_types`
--

DROP TABLE IF EXISTS `license_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `license_types` (
  `license_id` int NOT NULL,
  `license_type` varchar(10) NOT NULL,
  PRIMARY KEY (`license_id`),
  UNIQUE KEY `license_id_UNIQUE` (`license_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license_types`
--

LOCK TABLES `license_types` WRITE;
/*!40000 ALTER TABLE `license_types` DISABLE KEYS */;
INSERT INTO `license_types` VALUES (1,'AM'),(2,'A'),(3,'A1'),(4,'B'),(5,'C'),(6,'D'),(7,'BE'),(8,'CE'),(9,'DE'),(10,'F'),(11,'I');
/*!40000 ALTER TABLE `license_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `dest_from` varchar(200) NOT NULL,
  `dest_to` varchar(200) NOT NULL,
  `distance` int NOT NULL,
  `date_start` date NOT NULL,
  `date_finish` date NOT NULL,
  `load` int NOT NULL,
  `load_note` varchar(200) NOT NULL,
  `completed` bit(1) NOT NULL DEFAULT b'0',
  `payment` int NOT NULL,
  `client_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `cars_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `clients_id_idx` (`client_id`),
  KEY `admins_id_idx` (`admin_id`),
  KEY `cars_id_idx` (`cars_id`),
  CONSTRAINT `admins_id_orders` FOREIGN KEY (`admin_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `cars_id_orders` FOREIGN KEY (`cars_id`) REFERENCES `cars` (`car_id`),
  CONSTRAINT `clients_id_orders` FOREIGN KEY (`client_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'Uzda','Minsk',70,'2021-12-20','2021-12-20',400,'Packaged milk',_binary '\0',1000,4,1,NULL),(2,'Farm','Uzda',10,'2021-10-18','2021-12-18',3000,'Milk in tank',_binary '\0',1500,4,1,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_station`
--

DROP TABLE IF EXISTS `repair_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_station` (
  `repair_record_id` int NOT NULL AUTO_INCREMENT,
  `repair_start` date NOT NULL,
  `repair_end` date NOT NULL,
  `expenses` int NOT NULL,
  `car_id` int NOT NULL,
  PRIMARY KEY (`repair_record_id`),
  KEY `car_id_repair` (`car_id`),
  CONSTRAINT `car_id_repair` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_station`
--

LOCK TABLES `repair_station` WRITE;
/*!40000 ALTER TABLE `repair_station` DISABLE KEYS */;
INSERT INTO `repair_station` VALUES (1,'2021-10-10','2022-02-20',10000,1),(2,'2021-11-11','2022-02-20',30000,2);
/*!40000 ALTER TABLE `repair_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrator'),(3,'client'),(2,'driver');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id_for_user` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','Strelkov Viktor','+375297766858',1),(2,'driver1','driver1','Gruzdev Anatoly','+375336462214',2),(3,'driver2','driver2','Samsonov Semen','+375354789476',2),(4,'client','client','Zuzkin Gennadiy','+375356794315',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-28 20:07:33
