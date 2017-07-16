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
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `IDX_prezimeime` (`prezime`,`ime`),
  KEY `IDX_drzavarodj` (`drzava_rodj`)
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
  KEY `IDX_naslov` (`naslov`),
  KEY `IDX_datumvreme` (`datum_vreme`),
  FULLTEXT KEY `IDX_tekst` (`tekst`),
  CONSTRAINT `fk_statusi_korisnici` FOREIGN KEY (`korisnik`) REFERENCES `korisnici` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
)
ENGINE = InnoDB;

--
-- Dumping routines for database 'drustvenamreza'
--

--
-- UDF - broj prijatelja za unetog korisnika
-- Podrazumeva se da se u tabeli `prijateljstva` ne nalaze duplirani unosi relacija izmedju dvoje korisnika
--

DROP FUNCTION IF EXISTS `broj_prijatelja`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `broj_prijatelja`(korisnickiID int) RETURNS int(11)
BEGIN
	DECLARE brojac int;
	SELECT count(*) INTO brojac FROM prijateljstva WHERE id_korisnika = korisnickiID OR id_prijatelja = korisnickiID;
RETURN brojac;
END ;;
DELIMITER ;

--
-- Stored procedures
--

DROP PROCEDURE IF EXISTS `brisanje_korisnika`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `brisanje_korisnika`(korisnickiID int)
BEGIN
	DELETE FROM korisnici WHERE id = korisnickiID;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `izmena_korisnika`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `izmena_korisnika`(korisnickiID int, nime varchar(25), nprezime varchar(50), ndatum date, ndrzava varchar(60), nemail varchar(80), nusername varchar(45), nbio text, nfoto blob)
BEGIN
	UPDATE korisnici SET ime = nime, prezime = nprezime, datum_rodj = ndatum, drzava_rodj = ndrzava, email = nemail, username = nusername, biografija = nbio, fotografija = nfoto WHERE id = korisnickiID;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS `unos_korisnika`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `unos_korisnika`(nime varchar(25), nprezime varchar(50), ndatum date, ndrzava varchar(60), nemail varchar(80), nusername varchar(45), nbio text, nfoto blob)
BEGIN
	DECLARE brojacKorisnika int;
	SELECT count(id) FROM korisnici WHERE username = nusername INTO brojacKorisnika;
	IF brojacKorisnika < 1 THEN
    INSERT INTO korisnici (ime, prezime, datum_rodj, drzava_rodj, email, username, biografija, fotografija)
    VALUES (nime, nprezime, ndatum, ndrzava, nemail, nusername, nbio, nfoto);
	END IF;
END ;;
DELIMITER ;

--
-- Final view structure for view `prikaz_korisnika`
--

DROP VIEW IF EXISTS `prikaz_korisnika`;
CREATE
	ALGORITHM = UNDEFINED
	DEFINER = `root`@`localhost`
	SQL SECURITY DEFINER
VIEW `prikaz_korisnika` AS
SELECT
	`korisnici`.`ime` AS `ime`,
	`korisnici`.`prezime` AS `prezime`,
	`korisnici`.`datum_rodj` AS `datum_rodj`,
	`korisnici`.`drzava_rodj` AS `drzava_rodj`
FROM `korisnici`;

-- Dump completed on 2016-08-24  4:27:41
