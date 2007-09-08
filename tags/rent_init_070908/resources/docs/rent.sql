-- MySQL dump 10.10
--
-- Host: localhost    Database: rent
-- ------------------------------------------------------
-- Server version	5.0.20-community-log

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
-- Table structure for table `house_info`
--

DROP TABLE IF EXISTS `house_info`;
CREATE TABLE `house_info` (
  `id` char(32) NOT NULL,
  `username` varchar(64) NOT NULL,
  `version` int(10) unsigned zerofill NOT NULL,
  `publish_time` datetime NOT NULL,
  `longitude` float default NULL,
  `latitude` float default NULL,
  `province` int(10) unsigned default NULL,
  `city` int(10) unsigned default NULL,
  `area` int(10) unsigned default NULL,
  `rooms` int(10) unsigned default NULL,
  `price` int(10) unsigned default NULL,
  `end_time` datetime default NULL,
  `rent_type` int(10) unsigned NOT NULL,
  `provider` varchar(64) NOT NULL,
  `phone` varchar(64) default NULL,
  `email` varchar(64) default NULL,
  `address_detail` varchar(80) default NULL,
  `description` varchar(255) default NULL,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `view_count` int(10) unsigned zerofill default NULL,
  `source_url` varchar(255) default NULL,
  `source_provider` varchar(60) default NULL,
  `provider_type` int(10) unsigned zerofill default NULL,
  `deleted` tinyint(1) unsigned zerofill NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `FK_house_info_1` (`username`),
  CONSTRAINT `FK_house_info_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `house_info`
--


/*!40000 ALTER TABLE `house_info` DISABLE KEYS */;
LOCK TABLES `house_info` WRITE;
INSERT INTO `house_info` VALUES ('402881001472f912011472fe2e960001','kevin',0000000000,'2007-08-17 16:43:54',116.375,39.8997,1,1,1,1,2000,NULL,1,'陈先生','12343444','ddd','地铁宣武门站',NULL,'2007-08-17 08:43:54',NULL,NULL,NULL,NULL,0),('8085808414447abb011445c2c1270001','kevin',0000000000,'2007-08-08 21:55:59',116.357,39.9376,1,1,1,1,1800,NULL,1,'陈先生','13810399051','austinjust@gmail.com','西直门南大街八号楼',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('8085808414447abb011445c5f5480002','kevin',0000000008,'2007-08-08 21:59:34',116.358,39.9417,1,1,1,1,2000,NULL,1,'陈飞飞','13810399051','austinjust@gmail.com','西直门南大街八号楼',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841445e562011446164e8e0001','kevin',0000000001,'2007-08-08 23:27:20',116.366,39.9383,1,1,1,3,5000,NULL,1,'陈先生','1111111','fdsafds','西直门冠英商务楼',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841445e562011446537edd0002','kevin',0000000000,'2007-08-09 00:34:10',116.346,39.9536,1,1,1,2,3000,NULL,1,'胡女士','213213','afdfdsd','交大东路',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841445e56201144688cbe20003','kevin',0000000000,'2007-08-09 01:32:23',116.385,39.9234,1,1,1,0,20000,NULL,1,'陈先生','12121212','afdsfs@fdsf.com','后海花园四合院',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841445e5620114468fcdf30004','kevin',0000000000,'2007-08-09 01:40:03',116.347,39.9412,1,1,1,2,22000,NULL,1,'吴小姐','32323232','fds@fds.com','西直门外大街18号',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841449e56a011449e861460001','kevin',0000000000,'2007-08-09 17:15:39',116.409,39.9199,1,1,1,2,3000,NULL,1,'a','1231231','fdsafd@fdsf.com','富强胡同',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841449e56a011449f320600002','kevin',0000000000,'2007-08-09 17:27:24',116.359,39.9356,1,1,1,2,2500,NULL,1,'ssss','12132132','dfef2@ddd.cn','青少年宫东侧',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841449e56a01144a07f2250003','kevin',0000000003,'2007-08-09 17:50:08',116.353,39.94,1,1,1,1,44,NULL,1,'jhj','jj666','jkjk','sfdsff',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841449e56a01144a0ffd0c0004','kevin',0000000001,'2007-08-09 17:58:55',116.347,39.9829,1,1,1,1,6667,NULL,1,'lkkjlk','jgkjh','hkjk','jkjkk',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('80858084144e57ac01144f126fbf0001','kevin',0000000000,'2007-08-10 17:19:41',116.389,39.9122,1,1,1,1,2222,NULL,1,'dsfds','fdsfdsa','fdsafdsds','dfdsfds',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841450d6700114512631630001','kevin',0000000000,'2007-08-11 03:00:31',116.322,39.9678,1,1,1,3,3000,NULL,1,'张女士','123453213','dafds@fddd.com','北三环四通桥',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841450d6700114512967870002','kevin',0000000000,'2007-08-11 03:04:01',116.362,39.9582,1,1,1,1,1400,NULL,1,'哈哈哈','32324324','email','学院南路',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841450d6700114514886250003','kevin',0000000001,'2007-08-11 03:38:00',118.086,24.4618,1,1,1,2,2500,NULL,1,'陈先生','13912345678','aaa@some.com','斗西路光明大厦',NULL,'2007-08-11 12:37:52',NULL,NULL,NULL,NULL,0),('808580841454f875011454fa53050001','kevin',0000000000,'2007-08-11 20:51:05',118.084,24.4666,1,1,1,3,2100,NULL,1,'陈先生','13810399051','austinjust@gmail.com','湖滨南路38号',NULL,'2007-08-11 12:51:05',NULL,NULL,NULL,NULL,0),('808580841454f875011455039d320002','kevin',0000000000,'2007-08-11 21:01:13',118.091,24.4581,1,1,1,1,1200,NULL,1,'邓女士','13910221234','auss@msn.com','中山公园',NULL,'2007-08-11 13:01:13',NULL,NULL,NULL,NULL,0),('808580841455700a011457a4ad360001','kevin',0000000002,'2007-08-12 09:16:23',118.075,24.4602,1,1,1,1,800,NULL,1,'张女士','123456','fang@sss.com','夏和路3号',NULL,'2007-08-12 01:16:23',NULL,NULL,NULL,NULL,0),('808580841455700a011457a7729f0002','kevin',0000000000,'2007-08-12 09:19:25',118.064,24.439,1,1,1,3,3000,NULL,1,'曾先生','3232323','','鼓浪屿观海别墅豪华套件',NULL,'2007-08-12 01:19:25',NULL,NULL,NULL,NULL,0),('808580841455700a011457d2e53e0003','kevin',0000000000,'2007-08-12 10:06:52',118.079,24.4502,1,1,1,2,2000,NULL,1,'陈先生','13810399051','austinjust@gmail.com','轮渡观海两居',NULL,'2007-08-12 02:06:52',NULL,NULL,NULL,NULL,0),('80858084145802d3011458048ad60001','kevin',0000000000,'2007-08-12 11:01:06',118.085,24.4468,1,1,1,1,800,NULL,1,'陈先生','1112232132','3432@ss.com','思明南路3号',NULL,'2007-08-12 03:01:06',NULL,NULL,NULL,NULL,0),('80858084145899af011458a9a2730001','kevin',0000000000,'2007-08-12 14:01:25',118.055,24.4937,1,1,1,2,1200,NULL,1,'陈先生','9876234','','海沧大桥附近',NULL,'2007-08-12 06:01:26',NULL,NULL,NULL,NULL,0),('808580841458e83f011458f3fd8b0001','kevin',0000000001,'2007-08-12 15:22:38',118.09,24.4703,1,1,1,1,900,NULL,1,'陈先生','13810992134','austinjust@gmail.com','白鹭洲公园南侧',NULL,'2007-08-12 07:22:38',NULL,NULL,NULL,NULL,0),('80858084145de4d401145e59fd2b0001','kevin',0000000000,'2007-08-13 16:32:09',116.397,39.917,1,1,1,1,2000,NULL,1,'aa','aa','aa','test',NULL,'2007-08-17 08:45:41',NULL,NULL,NULL,NULL,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `house_info` ENABLE KEYS */;

--
-- Table structure for table `house_media`
--

DROP TABLE IF EXISTS `house_media`;
CREATE TABLE `house_media` (
  `house_id` char(32) NOT NULL,
  `media_id` char(32) NOT NULL,
  PRIMARY KEY  (`house_id`,`media_id`),
  KEY `FK_house_media_media` (`media_id`),
  CONSTRAINT `FK_house_media_house` FOREIGN KEY (`house_id`) REFERENCES `house_info` (`id`),
  CONSTRAINT `FK_house_media_media` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `house_media`
--


/*!40000 ALTER TABLE `house_media` DISABLE KEYS */;
LOCK TABLES `house_media` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `house_media` ENABLE KEYS */;

--
-- Table structure for table `location_cache`
--

DROP TABLE IF EXISTS `location_cache`;
CREATE TABLE `location_cache` (
  `address` varchar(80) NOT NULL,
  `format_address` varchar(80) default NULL,
  `longitude` float default NULL,
  `latitude` float default NULL,
  `zoom` int(10) unsigned default NULL,
  `update_time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  USING BTREE (`address`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location_cache`
--


/*!40000 ALTER TABLE `location_cache` DISABLE KEYS */;
LOCK TABLES `location_cache` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `location_cache` ENABLE KEYS */;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` char(32) NOT NULL,
  `local_path` varchar(45) default NULL,
  `url` varchar(45) default NULL,
  `type` varchar(45) NOT NULL,
  `comment` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `media`
--


/*!40000 ALTER TABLE `media` DISABLE KEYS */;
LOCK TABLES `media` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `media` ENABLE KEYS */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT 'role name',
  `description` varchar(255) default NULL COMMENT 'description',
  PRIMARY KEY  (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--


/*!40000 ALTER TABLE `role` DISABLE KEYS */;
LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES ('ADMIN','系统管理员','系统管理员角色'),('ANONYMOUS','匿名用户','匿名用户角色'),('NORMALADMIN','普通管理员','普通管理员角色'),('NORMALUSER','普通用户','普通用户角色');
UNLOCK TABLES;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(64) NOT NULL,
  `password` char(32) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `version` int(10) unsigned zerofill NOT NULL,
  `display_name` varchar(64) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('audin@live.com','96e79218965eb72c92a549dd5a330112',1,0000000000,'Audin Chan'),('kevin','96e79218965eb72c92a549dd5a330112',1,0000000000,'Kevin Chen'),('test','098f6bcd4621d373cade4e832627b4f6',1,0000000000,'Tester');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `id` char(32) NOT NULL,
  `username` varchar(64) NOT NULL,
  `full_name` varchar(32) default NULL,
  `phone_no` varchar(45) default NULL,
  `cell_phone` varchar(45) default NULL,
  `email` varchar(45) default NULL,
  `address` varchar(45) default NULL,
  `longitude` double default NULL,
  `latitude` double default NULL,
  `actived` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_user_profile_user` USING BTREE (`username`),
  CONSTRAINT `FK_user_profile_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `user_profile`
--


/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
LOCK TABLES `user_profile` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `username` varchar(64) NOT NULL,
  `role` varchar(64) NOT NULL,
  PRIMARY KEY  USING BTREE (`username`,`role`),
  KEY `FK_user_role_2` (`role`),
  CONSTRAINT `FK_user_role_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FK_user_role_2` FOREIGN KEY (`role`) REFERENCES `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='user role mapping';

--
-- Dumping data for table `user_role`
--


/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` VALUES ('audin@live.com','NORMALUSER'),('kevin','NORMALUSER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

