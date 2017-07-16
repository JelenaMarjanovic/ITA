CREATE DATABASE  IF NOT EXISTS `assignment01` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `assignment01`;

-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: assignment01
-- ------------------------------------------------------
-- Server version	5.7.13-log

--
-- Table structure for table `employee`
--
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int NOT NULL,
  `address` varchar(100) NOT NULL,
  `sallary` double NOT NULL,
  PRIMARY KEY (`employee_id`)
)
ENGINE = InnoDB;

-- Dump completed on 2016-10-24  9:15:21