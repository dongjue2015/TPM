CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shop`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.7.3-m13

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
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `resourceID` int(10) NOT NULL,
  `resourceGrade` int(1) NOT NULL,
  `accessPath` varchar(20) DEFAULT '',
  `checked` varchar(5) DEFAULT '',
  `delFlag` int(1) DEFAULT '0',
  `parentID` int(10) DEFAULT NULL,
  `resourceCode` varchar(20) DEFAULT '',
  `resourceDesc` varchar(20) DEFAULT '',
  `resourceName` varchar(20) DEFAULT '',
  `resourceOrder` varchar(20) DEFAULT '',
  `resourceType` varchar(20) DEFAULT '',
  PRIMARY KEY (`resourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (2,2,'','false',0,1,'','','空压站设备','0','ServiceNode'),(3,2,'','false',0,1,'','','制冷制设备(卷烟区)','0','ServiceNode'),(4,2,'','false',0,1,'','','制冷制设备(办公区)','0','ServiceNode'),(5,2,'','false',0,1,'','','中水站设备','0','ServiceNode'),(6,2,'','false',0,1,'','','抽水组设备','0','ServiceNode'),(7,3,'airComSta.do','false',0,2,'','','电器设备','0','ServiceNode'),(8,3,'','false',0,2,'','','控制系统','0','ServiceNode'),(9,3,'','false',0,2,'','','机器设备','0','ServiceNode'),(10,2,'','false',0,1,'','','用户管理','0','SystemNode'),(11,2,'','false',0,1,'','','角色管理','0','SystemNode'),(12,2,'','false',0,1,'','','权限管理','0','SystemNode'),(13,2,'','false',0,1,'','','组织管理','0','SystemNode'),(14,2,'','false',0,1,'','','邮件服务','0','OtherNode'),(15,2,'','false',0,1,'','','其他服务','0','OtherNode'),(16,3,'','false',0,14,'','','发送邮件','0','OtherNode'),(17,3,'','false',0,14,'','','自动发送','0','OtherNode'),(18,3,'','false',0,14,'','','邮件管理','0','OtherNode');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `user_age` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','admin','20');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'shop'
--

--
-- Dumping routines for database 'shop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-30 21:38:40
