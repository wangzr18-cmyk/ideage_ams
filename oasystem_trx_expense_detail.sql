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
-- Table structure for table `trx_expense_detail`
--

DROP TABLE IF EXISTS `trx_expense_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trx_expense_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '経費明細ID',
  `request_id` int NOT NULL COMMENT '経費申請ID',
  `request_date` date NOT NULL COMMENT '経費申請年月',
  `site_name` varchar(20) NOT NULL COMMENT '現場名',
  `expense_date` date NOT NULL COMMENT '日付',
  `transport_destination` varchar(20) DEFAULT NULL COMMENT '交通費行先',
  `transport_section` varchar(20) DEFAULT NULL COMMENT '交通費区間',
  `transport_method` varchar(20) DEFAULT NULL COMMENT '交通手段',
  `transport_expense_type` char(1) DEFAULT NULL COMMENT '経費種類',
  `transport_item` varchar(20) DEFAULT NULL COMMENT '交通費事項',
  `transport_amount` int DEFAULT '0' COMMENT '交通費金額',
  `other_payee` varchar(20) DEFAULT NULL COMMENT '立替経費支払い先',
  `other_account_title` char(1) DEFAULT NULL COMMENT '立替経費勘定科目',
  `other_summary` varchar(20) DEFAULT NULL COMMENT '立替経費摘要',
  `other_amount` int DEFAULT '0' COMMENT '立替経費金額',
  `remarks` varchar(255) DEFAULT NULL COMMENT '備考',
  `create_user_id` char(3) DEFAULT NULL COMMENT '登録者ID',
  `update_user_id` char(3) DEFAULT NULL COMMENT '更新者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登録時間',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
  `del_flg` char(1) DEFAULT '0' COMMENT '削除フラグ',
  PRIMARY KEY (`id`),
  KEY `request_id` (`request_id`),
  CONSTRAINT `trx_expense_detail_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `trx_expense_request` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='経費明細表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trx_expense_detail`
--

LOCK TABLES `trx_expense_detail` WRITE;
/*!40000 ALTER TABLE `trx_expense_detail` DISABLE KEYS */;
INSERT INTO `trx_expense_detail` VALUES (1,1,'2026-03-01','東京本社','2026-03-02','品川','新宿-品川','電車','1','通勤',500,'佐藤商店','A','昼食代',1000,'昼食代込み','U01','U01','2026-03-17 13:01:40','2026-03-30 17:01:54','0'),(2,1,'2026-03-01','東京本社','2026-03-03','渋谷','新宿-渋谷','電車','2','通勤',600,'高橋商店','B','飲み物代',500,'飲み物代込み','U01','U01','2026-03-17 13:01:40','2026-03-30 17:01:54','0'),(3,2,'2026-03-05','大阪支社','2026-03-06','梅田','本社-梅田','電車','2','通勤',400,'中村商店','A','昼食代',800,'昼食代込み','U02','U02','2026-03-17 13:01:40','2026-03-27 10:48:15','0'),(4,2,'2026-03-05','大阪支社','2026-03-07','難波','本社-難波','電車','2','通勤',350,'山本商店','B','飲み物代',400,'飲み物代込み','U02','U02','2026-03-17 13:01:40','2026-03-27 10:48:15','0');
/*!40000 ALTER TABLE `trx_expense_detail` ENABLE KEYS */;
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
