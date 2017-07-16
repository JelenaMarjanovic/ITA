CREATE DATABASE  IF NOT EXISTS `drustvenamreza` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `drustvenamreza`;

-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: drustvenamreza
-- ------------------------------------------------------
-- Server version	5.7.13-log

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(25) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `datum_rodj` date DEFAULT NULL,
  `drzava_rodj` varchar(60) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `username` varchar(45) NOT NULL,
  `biografija` text(1000),
  `fotografija` blob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
)
ENGINE = InnoDB;

--
-- Table structure for table `prijateljstva`
--

DROP TABLE IF EXISTS `prijateljstva`;
CREATE TABLE `prijateljstva` (
  `id_korisnika` int(11) NOT NULL,
  `id_prijatelja` int(11) NOT NULL,
  PRIMARY KEY (`id_korisnika`,`id_prijatelja`),
  KEY `fk_prijateljstva_prijatelj_idx` (`id_prijatelja`),
  CONSTRAINT `fk_prijateljstva_korisnik` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_prijateljstva_prijatelj` FOREIGN KEY (`id_prijatelja`) REFERENCES `korisnici` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)
ENGINE = InnoDB;

--
-- Table structure for table `statusi`
--

DROP TABLE IF EXISTS `statusi`;
CREATE TABLE `statusi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(50) NOT NULL,
  `tekst` text(250),
  `slika` blob,
  `datum_vreme` datetime NOT NULL,
  `korisnik` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_statusi_korisnici_id_idx` (`korisnik`),
  CONSTRAINT `fk_statusi_korisnici` FOREIGN KEY (`korisnik`) REFERENCES `korisnici` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- Dump completed on 2016-08-02  3:29:21
