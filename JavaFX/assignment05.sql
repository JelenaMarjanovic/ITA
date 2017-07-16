CREATE DATABASE  IF NOT EXISTS `assignment05` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `assignment05`;

-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: assignment05
-- ------------------------------------------------------
-- Server version	5.7.13-log

--
-- Table structure for table `contract`
--
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `speed` varchar(3) NOT NULL,
  `bandwidth` varchar(6) NOT NULL,
  `duration` varchar(1) NOT NULL,
  PRIMARY KEY (`contract_id`)
)
ENGINE = InnoDB;

-- Dump completed on 2016-12-18  10:03:13