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

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '8588053b-20d7-11f1-8950-60e9aa84e312:1-63';

--
-- Table structure for table `admin_login_user`
--

DROP TABLE IF EXISTS `admin_login_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_login_user` (
  `emp_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員ID',
  `emp_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'パスワードMD5',
  `user_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `series_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `issued_time` datetime DEFAULT NULL,
  `expired_time` datetime DEFAULT NULL,
  `create_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='ログインユーザー';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_login_user`
--

LOCK TABLES `admin_login_user` WRITE;
/*!40000 ALTER TABLE `admin_login_user` DISABLE KEYS */;
INSERT INTO `admin_login_user` VALUES ('001','小野グーリン','123456','OS6Sll+vzjRIIFeEVOP6Rw==','gNP4yMr1hZkfg+Hy8JiKQg==','2025-10-31 17:33:45','2025-11-30 17:33:45','001','001','2025-10-20 16:11:45','2025-10-31 17:33:45','0'),('002','中野ブルー','123456',NULL,NULL,'2025-10-30 13:39:57','2025-11-29 13:39:57','001','002','2025-10-20 16:11:45','2025-10-30 13:39:57','0'),('013','大野レッド','123456',NULL,NULL,'2025-10-31 09:51:36','2025-11-30 09:51:36','001','013','2025-10-20 16:11:45','2025-10-31 10:20:29','0');
/*!40000 ALTER TABLE `admin_login_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role_users`
--

DROP TABLE IF EXISTS `admin_role_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_role_users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '関連ID',
  `emp_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_code` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department_code` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `emp_id` (`emp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='ユーザー役職関連';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role_users`
--

LOCK TABLES `admin_role_users` WRITE;
/*!40000 ALTER TABLE `admin_role_users` DISABLE KEYS */;
INSERT INTO `admin_role_users` VALUES (1,'001','001','001','001','001','2026-03-16 14:35:28','2026-03-16 14:35:28','0'),(2,'002','002','002','001','001','2026-03-16 14:35:28','2026-03-16 14:35:28','0'),(3,'013','003','002','001','001','2026-03-16 14:35:28','2026-03-16 14:35:28','0');
/*!40000 ALTER TABLE `admin_role_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_roles`
--

DROP TABLE IF EXISTS `admin_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_roles` (
  `role_code` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '役職コード',
  `role_level` int NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '役職名',
  `department_code` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部門コード',
  `create_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`role_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='役職マスタ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_roles`
--

LOCK TABLES `admin_roles` WRITE;
/*!40000 ALTER TABLE `admin_roles` DISABLE KEYS */;
INSERT INTO `admin_roles` VALUES ('001',0,'管理員','001','001','001','2025-10-20 16:11:44','2025-10-29 11:49:36','0'),('002',1,'部長','002','001','001','2025-10-20 16:11:44','2025-10-29 09:41:52','0'),('003',2,'社員','002','001','001','2025-10-20 16:11:44','2025-10-29 09:41:54','0');
/*!40000 ALTER TABLE `admin_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_users`
--

DROP TABLE IF EXISTS `admin_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '社員内部ID',
  `emp_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員番号（001形式）',
  `emp_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社員名',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `post_code` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `join_date` date NOT NULL,
  `emp_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status_date` date DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `update_user_id` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `emp_id` (`emp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='社員マスタ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_users`
--

LOCK TABLES `admin_users` WRITE;
/*!40000 ALTER TABLE `admin_users` DISABLE KEYS */;
INSERT INTO `admin_users` VALUES (1,'001','小野グーリン','02001111110','Green.Ono@xxx.com','0011111','2020-04-01','1','2025-10-29','東京都中央区','dist/img/rabbit.jpg','001','001','2025-10-20 16:11:45','2025-10-29 13:20:38','0'),(2,'002','中野ブルー','01006666660','Blue.Nakano@xxx.com','002222','2022-06-02','1','2023-12-21','東京都港区','dist/img/images.jpg','001','001','2025-10-20 16:11:45','2025-10-29 13:20:43','0'),(3,'013','大野レッド','03001111110','Red.Ohno@xxx.com','003333','2023-07-03','1','2024-11-12','東京都江戸川区','dist/img/user.jpg','001','001','2025-10-20 16:11:45','2025-10-30 13:26:26','0');
/*!40000 ALTER TABLE `admin_users` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `tb_admin_user` VALUES (1,'admin','{MD5}e10adc3949ba59abbe56e057f20f883e','9a7b027587d5794c23bcb22b27acb759',0,'2023-01-01 11:50:13'),(2,'test','e10adc3949ba59abbe56e057f20f883e','',0,'2024-05-15 21:49:54');
/*!40000 ALTER TABLE `tb_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ssm_article`
--

DROP TABLE IF EXISTS `tb_ssm_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ssm_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'シーケンスid',
  `article_title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'タイトル',
  `article_content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'コンテンツ',
  `add_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT '作成者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成時間',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_skj` tinyint NOT NULL DEFAULT '0' COMMENT '削除フラグ　0:未削除　1:削除済',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ssm_article`
--

LOCK TABLES `tb_ssm_article` WRITE;
/*!40000 ALTER TABLE `tb_ssm_article` DISABLE KEYS */;
INSERT INTO `tb_ssm_article` VALUES (1,'321','<p>123</p>','321','2024-05-22 21:28:56','2024-05-22 21:28:56',1);
/*!40000 ALTER TABLE `tb_ssm_article` ENABLE KEYS */;
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

-- Dump completed on 2026-03-16 17:43:03
