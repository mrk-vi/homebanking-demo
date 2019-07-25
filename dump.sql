-- MySQL dump 10.13  Distrib 8.0.16, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: pps-home-banking
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jf8hwde5kvshk44s9pllcqid9` (`user_id`),
  CONSTRAINT `FKqrcysxoyqjtyq2obdovndf3dq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,2);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank` (
  `id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qwdav9q6oh8gxw7h9lr5tynkq` (`address`),
  UNIQUE KEY `UK_ld5hsh9a9d6a849esqbf7d9qm` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (5,'Piazza San Carlo 156, 10121 Torino','Intesa Sanpaolo si colloca tra i primissimi gruppi bancari dell\'eurozona con una capitalizzazione di mercato di 33,0 miliardi di euro.','Intesa San Paolo'),(41,'Piazza Salimbeni, 3 53100 Siena','Nata nel 1472, Monte dei Paschi di Siena è la più antica banca del mondo ancora in attività. È oggi a capo di uno dei principali gruppi bancari italiani, con quote di mercato di rilievo in tutte le aree di business in cui opera.','Monte dei Paschi di Siena'),(77,'Via Luzzatti, 8 73046 Matino','Punto di riferimento per tutti gli operatori che hanno esigenze  sempre più complesse e diversificate, mira al soddisfacimento economico-finanziario di famiglie ed imprese attraverso una vasta gamma di prodotti e servizi.','Banca Popolare Pugliese'),(127,'Piazza Gae Aulenti, 3 20124 Milano MI','UniCredit risponde ai bisogni di clienti sempre più esigenti  grazie a un\'offerta commerciale completa.','UniCredit');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_account` (
  `id` int(11) NOT NULL,
  `balance` float DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `bank_product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_78n4uode7k9ihvebfbnqeu117` (`iban`),
  KEY `FKm3n83lpktuyinucke33ohlv1p` (`bank_product_id`),
  KEY `FKj818ht4ban0c4uw4bmsbf3jme` (`customer_id`),
  CONSTRAINT `FKj818ht4ban0c4uw4bmsbf3jme` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKm3n83lpktuyinucke33ohlv1p` FOREIGN KEY (`bank_product_id`) REFERENCES `bank_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (14,150,'IT000000713',7,13),(15,150,'IT000000813',8,13),(29,55,'IT000000728',7,28),(30,30,'IT000000828',8,28),(50,150,'IT0000004349',43,49),(51,150,'IT0000004449',44,49),(65,150,'IT0000004364',43,64),(66,150,'IT0000004464',44,64),(86,250,'IT0000007985',79,85),(87,150,'IT0000008085',80,85),(101,305,'IT00000079100',79,100),(102,150,'IT00000080100',80,100);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_branch`
--

DROP TABLE IF EXISTS `bank_branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_branch` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `closing` time DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `opening` time DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hvao9rivxlt8jay1cacvdpjxf` (`name`),
  KEY `FKrks5h7px153bdiil3d5axu5ir` (`bank_id`),
  CONSTRAINT `FKrks5h7px153bdiil3d5axu5ir` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_branch`
--

LOCK TABLES `bank_branch` WRITE;
/*!40000 ALTER TABLE `bank_branch` DISABLE KEYS */;
INSERT INTO `bank_branch` VALUES (9,'Corso Italia, 2, 56125 Pisa PI','16:15:00','Intesa Sanpaolo S.p.A. - Filiale Imprese','08:30:00',5),(24,'Corso Giuseppe Galliano, 2B, 73048 Nardò LE','17:00:00','Intesa Sanpaolo Spa - Filiale HUB','09:00:00',5),(45,'Via Matteotti, 24, 48032 Casola Valsenio RA','18:00:00','Banca Monte Dei Paschi Di Siena Filiale Di Casola Valsenio','09:00:00',41),(60,'Viale Aldo Moro, 70019 Triggiano BA','15:30:00','Banca Monte dei Paschi di Siena Filiale di Triggiano','08:20:00',41),(81,'Via Giuseppe Verdi, 26, 70032 Bitonto BA','15:45:00','Banca Popolare Pugliese - Agenzia di Bitonto','08:30:00',77),(96,'Piazza di S. Bernardo, 111, 00185 Roma RM','18:00:00','Banca Popolare Pugliese - Agenzia di Consulenza','09:00:00',77),(118,'Piazza Sant\'Oronzo, 43, 73100 Lecce LE','15:45:00','Intesa Sanpaolo Spa - Filiale HUB Lecce	','08:05:00',5);
/*!40000 ALTER TABLE `bank_branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_manager`
--

DROP TABLE IF EXISTS `bank_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_manager` (
  `id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8mnhfl0hdg5ve5e45s0c0t7km` (`user_id`),
  KEY `FK269695t2p973jmcu3u6mru1r2` (`bank_id`),
  CONSTRAINT `FK269695t2p973jmcu3u6mru1r2` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`),
  CONSTRAINT `FK5rg07o1gf8y26ccqrdwe0ik3x` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_manager`
--

LOCK TABLES `bank_manager` WRITE;
/*!40000 ALTER TABLE `bank_manager` DISABLE KEYS */;
INSERT INTO `bank_manager` VALUES (3,0,5,4),(39,0,41,40),(75,0,77,76),(125,0,127,126);
/*!40000 ALTER TABLE `bank_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_product`
--

DROP TABLE IF EXISTS `bank_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_product` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `interest_rate` float NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `bank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkm3mk9l5k3xey5wcwvo1sbgrh` (`bank_id`),
  CONSTRAINT `FKkm3mk9l5k3xey5wcwvo1sbgrh` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_product`
--

LOCK TABLES `bank_product` WRITE;
/*!40000 ALTER TABLE `bank_product` DISABLE KEYS */;
INSERT INTO `bank_product` VALUES (7,'Sconto 7,50 euro sul canone mensile totale se lo apri entro il 31 dicembre 2019 ed è intestato solo a te.',0.25,'Conto XME',0,5),(8,'Fino a 10.000 euro per i tuoi acquisti nei negozi e online',1.2,'Carta Superflash',1,5),(43,'Il conto corrente che disegni come vuoi tu: semplice, flessibile e conveniente',0.5,'MPS Mio',0,41),(44,'Acquisti online? Rafforza la sicurezza delle tue carte con il servizio 3D Secure (Mastercard Identity Check o Verified by VISA)',2,'MPS Spider',1,41),(79,'Dedicato a coloro che preferiscono gestire il conto on line e prevede la possibilità di eseguire un numero illimitato di operazioni.',0.3,'Energy Web',0,77),(80,'Nexi Prepaid è la carta prepagata ricaricabile contactless che potrai utilizzare per i tuoi acquisti in tutto il mondo, anche su internet, in totale sicurezza.',1,'Nexi',1,77),(117,'asdasda',0.3,'nuovo conto',0,5);
/*!40000 ALTER TABLE `bank_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_service`
--

DROP TABLE IF EXISTS `bank_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_service` (
  `id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `bank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6y4trgrt8frg1yg03sntlnfkx` (`type`,`bank_id`),
  KEY `FK22klim182oxdxjsaih61bib4a` (`bank_id`),
  CONSTRAINT `FK22klim182oxdxjsaih61bib4a` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_service`
--

LOCK TABLES `bank_service` WRITE;
/*!40000 ALTER TABLE `bank_service` DISABLE KEYS */;
INSERT INTO `bank_service` VALUES (78,0,77),(6,1,5),(42,1,41);
/*!40000 ALTER TABLE `bank_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_service_operation`
--

DROP TABLE IF EXISTS `bank_service_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank_service_operation` (
  `id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `operation_state` int(11) DEFAULT NULL,
  `operation_type` int(11) DEFAULT NULL,
  `bank_account_id` int(11) DEFAULT NULL,
  `bank_service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65vlstqujwoumja48b9tqpxqw` (`bank_account_id`),
  KEY `FKqv3pakdr0mnkmjld6caww2h6d` (`bank_service_id`),
  CONSTRAINT `FK65vlstqujwoumja48b9tqpxqw` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`id`),
  CONSTRAINT `FKqv3pakdr0mnkmjld6caww2h6d` FOREIGN KEY (`bank_service_id`) REFERENCES `bank_service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_service_operation`
--

LOCK TABLES `bank_service_operation` WRITE;
/*!40000 ALTER TABLE `bank_service_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_service_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banking_operation`
--

DROP TABLE IF EXISTS `banking_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `banking_operation` (
  `id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `operation_state` int(11) DEFAULT NULL,
  `operation_type` int(11) DEFAULT NULL,
  `recipient_iban` varchar(255) DEFAULT NULL,
  `bank_account_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKny0g1smfs4utbntmdgl0vftmo` (`bank_account_id`),
  KEY `FK6aho632pu22ufxosyt3i7xjs5` (`employee_id`),
  CONSTRAINT `FK6aho632pu22ufxosyt3i7xjs5` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKny0g1smfs4utbntmdgl0vftmo` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banking_operation`
--

LOCK TABLES `banking_operation` WRITE;
/*!40000 ALTER TABLE `banking_operation` DISABLE KEYS */;
INSERT INTO `banking_operation` VALUES (16,100,'2019-07-24 10:52:06',2,0,NULL,14,10),(17,50,'2019-07-24 10:52:06',2,1,NULL,14,10),(18,150,'2019-07-24 10:52:06',1,0,NULL,14,10),(19,300,'2019-07-24 10:52:06',0,2,'IT000000728',14,NULL),(20,100,'2019-07-24 10:52:06',2,0,NULL,15,10),(21,50,'2019-07-24 10:52:06',2,1,NULL,15,10),(22,150,'2019-07-24 10:52:06',1,0,NULL,15,10),(23,30,'2019-07-24 10:52:06',0,2,'IT0000004464',15,NULL),(31,100,'2019-07-24 10:52:07',2,0,NULL,29,25),(32,50,'2019-07-24 10:52:07',2,1,NULL,29,25),(33,150,'2019-07-24 10:52:07',1,0,NULL,29,25),(34,70,'2019-07-24 10:52:07',1,2,'IT0000007985',29,25),(35,100,'2019-07-24 10:52:07',2,0,NULL,30,25),(36,50,'2019-07-24 10:52:07',2,1,NULL,30,25),(37,150,'2019-07-24 10:52:07',1,0,NULL,30,25),(38,155,'2019-07-24 10:52:07',0,2,'IT00000079100',30,25),(52,100,'2019-07-24 10:52:08',2,0,NULL,50,46),(53,50,'2019-07-24 10:52:08',2,1,NULL,50,46),(54,150,'2019-07-24 10:52:08',1,0,NULL,50,46),(55,213,'2019-07-24 10:52:08',0,2,'IT000000813',50,NULL),(56,100,'2019-07-24 10:52:08',2,0,NULL,51,46),(57,50,'2019-07-24 10:52:08',2,1,NULL,51,46),(58,150,'2019-07-24 10:52:08',1,0,NULL,51,46),(59,65,'2019-07-24 10:52:08',0,2,'IT000000828',51,NULL),(67,100,'2019-07-24 10:52:08',2,0,NULL,65,61),(68,50,'2019-07-24 10:52:08',2,1,NULL,65,61),(69,150,'2019-07-24 10:52:08',1,0,NULL,65,61),(70,85,'2019-07-24 10:52:08',0,2,'IT00000079100',65,NULL),(71,100,'2019-07-24 10:52:08',2,0,NULL,66,61),(72,50,'2019-07-24 10:52:09',2,1,NULL,66,61),(73,150,'2019-07-24 10:52:09',1,0,NULL,66,61),(74,960,'2019-07-24 10:52:09',0,2,'IT000000728',66,NULL),(88,100,'2019-07-24 10:52:09',2,0,NULL,86,82),(89,50,'2019-07-24 10:52:09',2,1,NULL,86,82),(90,150,'2019-07-24 10:52:09',1,0,NULL,86,82),(91,560,'2019-07-24 10:52:09',0,2,'IT0000004449',86,NULL),(92,100,'2019-07-24 10:52:09',2,0,NULL,87,82),(93,50,'2019-07-24 10:52:09',2,1,NULL,87,82),(94,150,'2019-07-24 10:52:09',1,0,NULL,87,82),(95,100,'2019-07-24 10:52:09',0,2,'IT0000004464',87,NULL),(103,100,'2019-07-24 10:52:10',2,0,NULL,101,97),(104,50,'2019-07-24 10:52:10',2,1,NULL,101,97),(105,150,'2019-07-24 10:52:10',1,0,NULL,101,97),(106,50,'2019-07-24 10:52:10',0,2,'IT000000813',101,NULL),(107,100,'2019-07-24 10:52:10',2,0,NULL,102,97),(108,50,'2019-07-24 10:52:10',2,1,NULL,102,97),(109,150,'2019-07-24 10:52:10',1,0,NULL,102,97),(110,76,'2019-07-24 10:52:10',0,2,'IT0000004349',102,NULL),(111,20,'2019-07-25 15:03:46',2,0,NULL,29,25),(112,10,'2019-07-25 15:03:57',1,1,NULL,30,25),(113,15,'2019-07-25 15:04:19',2,2,'IT000000828',29,25),(114,20,'2019-07-25 15:06:39',2,0,NULL,30,25);
/*!40000 ALTER TABLE `banking_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `bank_branch_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKnsctqrvxrymnoj5b4tnys6i8f` (`user_id`,`bank_branch_id`),
  KEY `FKfun9qdge0x1m23fdtgfxe1wpm` (`bank_branch_id`),
  CONSTRAINT `FKfun9qdge0x1m23fdtgfxe1wpm` FOREIGN KEY (`bank_branch_id`) REFERENCES `bank_branch` (`id`),
  CONSTRAINT `FKj8dlm21j202cadsbfkoem0s58` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (13,0,9,12),(28,0,24,27),(49,0,45,48),(64,0,60,63),(85,0,81,84),(100,0,96,99);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `bank_branch_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mpps3d3r9pdvyjx3iqixi96fi` (`user_id`),
  KEY `FKi6yfl9n6a3ttit12dk7hkv5f5` (`bank_branch_id`),
  CONSTRAINT `FK6lk0xml9r7okjdq0onka4ytju` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKi6yfl9n6a3ttit12dk7hkv5f5` FOREIGN KEY (`bank_branch_id`) REFERENCES `bank_branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (10,9,11),(25,24,26),(46,45,47),(61,60,62),(82,81,83),(97,96,98),(119,118,120);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (129),(129),(129),(129),(129),(129),(129),(129),(129),(129),(129),(129),(129);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `bank_id` int(11) DEFAULT NULL,
  `bank_branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKimgje631o9hmkkpc7fkl79y2v` (`bank_id`),
  KEY `FKotkvc7moeuxiqbtl4v9r09fp7` (`bank_branch_id`),
  CONSTRAINT `FKimgje631o9hmkkpc7fkl79y2v` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`),
  CONSTRAINT `FKotkvc7moeuxiqbtl4v9r09fp7` FOREIGN KEY (`bank_branch_id`) REFERENCES `bank_branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'intesa.jpg',5,NULL),(2,'mps.jpg',41,NULL),(3,'bpp.jpg',77,NULL),(128,'unicredit.png',127,NULL);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6sou31qus5dnws6dwfu61e71v` (`mail`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'2019-07-24 10:52:05','admin@localhost.com','$2a$11$GpjMpplbAFf3vW.8XeiAAuZ0a4JPVBjbFvhNsdj5zsf487rLUeziu',3,'admin'),(4,'2019-07-24 10:52:05','manager.intesa@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'intesa'),(11,'2019-07-24 10:52:06','intesa.pisa@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'intesa_pisa'),(12,'2019-07-24 10:52:06','customer00@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer00'),(26,'2019-07-24 10:52:07','intesa.nardo@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'intesa_nardo'),(27,'2019-07-24 10:52:07','customer01@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer01'),(40,'2019-07-24 10:52:07','manager.mps@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'mps'),(47,'2019-07-24 10:52:08','mps.valsenio@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'mps_valsenio'),(48,'2019-07-24 10:52:08','customer10@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer10'),(62,'2019-07-24 10:52:08','mps.triggiano@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'mps_triggiano'),(63,'2019-07-24 10:52:08','customer11@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer11'),(76,'2019-07-24 10:52:09','manager.bpp@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'bpp'),(83,'2019-07-24 10:52:09','bpp.bitonto@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'bpp_bitonto'),(84,'2019-07-24 10:52:09','customer20@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer20'),(98,'2019-07-24 10:52:10','bpp.roma@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'bpp_roma'),(99,'2019-07-24 10:52:10','customer21@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'customer21'),(120,'2019-07-25 16:55:18','intesa_lecce@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'intesa_lecce'),(126,'2019-07-25 17:34:10','manager.unicredit@localhost.com','$2a$11$NnV3gW2hElJJ54UFysCci.RwHdVKcvhuJVNFo/j1gJNmaiP2kZgTK',3,'unicredit');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-25 20:15:40
