-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: oasystem
-- ------------------------------------------------------
-- Server version	9.6.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '8588053b-20d7-11f1-8950-60e9aa84e312:1-141';

--
-- Table structure for table `tb_admin_user`
--

DROP TABLE IF EXISTS `tb_admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_admin_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'シーケンスid',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT 'ユーザー名',
  `password_md5` varchar(50) NOT NULL DEFAULT '' COMMENT 'パスワード',
  `user_token` varchar(50) NOT NULL DEFAULT '' COMMENT 'トークン',
  `is_skj` tinyint NOT NULL DEFAULT '0' COMMENT '削除フラグ　0:未削除　1:削除済',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録時間',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin_user`
--

LOCK TABLES `tb_admin_user` WRITE;
/*!40000 ALTER TABLE `tb_admin_user` DISABLE KEYS */;
INSERT INTO `tb_admin_user` VALUES (1,'admin','{MD5}e10adc3949ba59abbe56e057f20f883e','9a7b027587d5794c23bcb22b27acb759',0,'2023-01-01 11:50:13'),(2,'test','{MD5}e10adc3949ba59abbe56e057f20f883e','',0,'2024-05-15 21:49:54');
/*!40000 ALTER TABLE `tb_admin_user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-06 11:02:08
