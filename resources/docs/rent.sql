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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` varchar(32) NOT NULL,
  `city_id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `hit_count` int(10) unsigned default NULL,
  `tag` varchar(45) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_area_1` (`city_id`),
  CONSTRAINT `FK_area_1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `hit_count` int(10) unsigned zerofill default NULL,
  `tag` varchar(45) default NULL,
  `province_id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_city_1` (`province_id`),
  CONSTRAINT `FK_city_1` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
-- Table structure for table `iptable`
--

DROP TABLE IF EXISTS `iptable`;
CREATE TABLE `iptable` (
  `start_ip` int(10) unsigned NOT NULL,
  `end_ip` int(10) unsigned NOT NULL,
  `country` varchar(60) NOT NULL,
  `local` varchar(60) default NULL,
  PRIMARY KEY  (`start_ip`,`end_ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lat` float default NULL,
  `lng` float default NULL,
  `hit_count` int(10) unsigned zerofill default NULL,
  `tag` varchar(60) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

