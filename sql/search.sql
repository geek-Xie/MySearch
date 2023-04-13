-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: search
-- ------------------------------------------------------
-- Server version	8.0.27

--
-- Table structure for table `search`
--

DROP TABLE IF EXISTS `search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `search` (
  `search_id` int NOT NULL AUTO_INCREMENT,
  `search_engine` char(24) NOT NULL,
  `user_email` char(100) NOT NULL,
  `search_content` char(244) NOT NULL,
  `search_date` datetime NOT NULL,
  `return_url` char(244) NOT NULL,
  PRIMARY KEY (`search_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search`
--

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` char(24) NOT NULL,
  `user_email` char(244) NOT NULL,
  `user_password` char(25) NOT NULL,
  `image_url` char(244) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_user_email_uindex` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--


-- Dump completed on 2023-04-13 12:45:22
