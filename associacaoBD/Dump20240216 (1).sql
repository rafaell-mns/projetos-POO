-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: associacao_bd
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `tb_associacoes`
--

DROP TABLE IF EXISTS `tb_associacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_associacoes` (
  `num` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`num`,`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_associacoes`
--

LOCK TABLES `tb_associacoes` WRITE;
/*!40000 ALTER TABLE `tb_associacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_associacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_associados`
--

DROP TABLE IF EXISTS `tb_associados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_associados` (
  `num` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `data_associacao` bigint NOT NULL,
  `data_nascimento` bigint NOT NULL,
  `data_remissao` bigint NOT NULL,
  `id_associacao` int NOT NULL,
  PRIMARY KEY (`num`,`id_associacao`),
  KEY `fk_associacoes_associado` (`id_associacao`),
  CONSTRAINT `fk_associacoes_associado` FOREIGN KEY (`id_associacao`) REFERENCES `tb_associacoes` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_associados`
--

LOCK TABLES `tb_associados` WRITE;
/*!40000 ALTER TABLE `tb_associados` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_associados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamentos`
--

DROP TABLE IF EXISTS `tb_pagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pagamentos` (
  `id_associado` int NOT NULL,
  `valor_pago` double NOT NULL,
  `data_pagamento` bigint NOT NULL,
  `id_taxa` int NOT NULL,
  KEY `fk_associado_pagamento_idx` (`id_associado`),
  KEY `fk_taxa_idx` (`id_taxa`),
  CONSTRAINT `fk_associado_pagamento` FOREIGN KEY (`id_associado`) REFERENCES `tb_associados` (`num`),
  CONSTRAINT `fk_taxa` FOREIGN KEY (`id_taxa`) REFERENCES `tb_taxas` (`id_taxa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamentos`
--

LOCK TABLES `tb_pagamentos` WRITE;
/*!40000 ALTER TABLE `tb_pagamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pagamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_taxas`
--

DROP TABLE IF EXISTS `tb_taxas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_taxas` (
  `id_taxa` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `vigencia` int NOT NULL,
  `valor_ano` double NOT NULL,
  `parcelas` int NOT NULL,
  `administrativa` tinyint DEFAULT NULL,
  `id_associacao` int NOT NULL,
  PRIMARY KEY (`id_taxa`),
  KEY `fk_associacao_taxa_idx` (`id_associacao`),
  CONSTRAINT `fk_associacao_taxa` FOREIGN KEY (`id_associacao`) REFERENCES `tb_associacoes` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_taxas`
--

LOCK TABLES `tb_taxas` WRITE;
/*!40000 ALTER TABLE `tb_taxas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_taxas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 23:23:57
