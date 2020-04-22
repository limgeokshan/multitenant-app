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
-- Database: `dbtenant1`
--
CREATE DATABASE IF NOT EXISTS `dbtenant1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `dbtenant1`;

-- --------------------------------------------------------

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `claim_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `claimDate` datetime DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`claim_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `claim`
--

INSERT INTO `claim` (`claim_id`, `amount`, `claimDate`, `details`) VALUES
(1, 100, NULL, 'test'),
(2, 200, NULL, 'user2'),
(44, 1000, NULL, 'xxx'),
(45, 1000, NULL, 'xxx');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(46),
(46),
(46),
(46),
(46);

-- --------------------------------------------------------

--
-- Table structure for table `lookup`
--

DROP TABLE IF EXISTS `lookup`;
CREATE TABLE IF NOT EXISTS `lookup` (
  `lookup_id` int(11) NOT NULL,
  `process` varchar(255) DEFAULT NULL,
  `Workflow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`lookup_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lookup`
--

INSERT INTO `lookup` (`lookup_id`, `process`, `Workflow`) VALUES
(0, 'claim', '2');

-- --------------------------------------------------------

--
-- Table structure for table `manager_claim`
--

DROP TABLE IF EXISTS `manager_claim`;
CREATE TABLE IF NOT EXISTS `manager_claim` (
  `approving_id` int(11) NOT NULL,
  `claim_id` int(11) NOT NULL,
  PRIMARY KEY (`approving_id`,`claim_id`),
  UNIQUE KEY `UK_27mnajo5oagwg6pxb21onpd90` (`claim_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `manager_claim`
--

INSERT INTO `manager_claim` (`approving_id`, `claim_id`) VALUES
(3, 1);

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

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'employee'),
(2, 'manager');

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
(1, b'1', '$2a$10$.oNj1RP582LeCrM//qEbD.VaSU.z7CDhoFbag/kxHZG6.9Sk/mL2G', 'tenant_1', 'user'),
(2, b'1', '$2a$10$.oNj1RP582LeCrM//qEbD.VaSU.z7CDhoFbag/kxHZG6.9Sk/mL2G', 'tenant_1', 'user2'),
(3, b'1', '$2a$10$.oNj1RP582LeCrM//qEbD.VaSU.z7CDhoFbag/kxHZG6.9Sk/mL2G', 'tenant_1', 'manager'),
(43, b'1', '$2a$10$n8wumxq9DZsPakVbxBfnLuOpFLI7tmWrsXJ3o50urrgxpWCIq7T/i', 'tenant_1', 'testsave');

-- --------------------------------------------------------

--
-- Table structure for table `user_claim`
--

DROP TABLE IF EXISTS `user_claim`;
CREATE TABLE IF NOT EXISTS `user_claim` (
  `user_id` int(11) NOT NULL,
  `claim_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`claim_id`),
  UNIQUE KEY `UK_tobs91lcdprot1eyh2yr5d3h3` (`claim_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_claim`
--

INSERT INTO `user_claim` (`user_id`, `claim_id`) VALUES
(1, 1),
(2, 2);

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

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(3, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
