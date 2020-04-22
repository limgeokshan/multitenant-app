-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 13, 2020 at 12:05 AM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `masterdb`
--
CREATE DATABASE IF NOT EXISTS `masterdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `masterdb`;

-- --------------------------------------------------------

--
-- Table structure for table `master_tenant`
--

DROP TABLE IF EXISTS `master_tenant`;
CREATE TABLE IF NOT EXISTS `master_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `master_tenant`
--

INSERT INTO `master_tenant` (`id`, `password`, `tenant_id`, `url`, `username`, `version`) VALUES
(1, NULL, 'tenant_1', 'jdbc:mysql://localhost:3306/dbtenant1?useSSL=false&serverTimezone=UTC', 'root', 0),
(5, NULL, 'tenant_2', 'jdbc:mysql://localhost:3306/dbtenant2?useSSL=false&serverTimezone=UTC', 'root', 0);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `tenant` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `active`, `password`, `tenant`, `username`) VALUES
(0, b'1', '$2a$10$.oNj1RP582LeCrM//qEbD.VaSU.z7CDhoFbag/kxHZG6.9Sk/mL2G', 'admin', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
