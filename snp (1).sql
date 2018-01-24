-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 24, 2018 at 09:13 PM
-- Server version: 5.6.35
-- PHP Version: 7.0.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `snp`
--
CREATE DATABASE IF NOT EXISTS `snp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `snp`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `insertCustomer`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCustomer` (IN `name` VARCHAR(20), IN `addr` VARCHAR(100), IN `mail` VARCHAR(30), IN `ph` BIGINT(20))  MODIFIES SQL DATA
    COMMENT 'Insert into Customer table'
BEGIN
select max(k.CID)+1 into @x
from customer as k; 
IF @x IS NULL THEN
	SET @x = 1;
END IF;
INSERT INTO `customer`(`CID`, `Name`, `Address`, `email`,`phone`) VALUES (@x,name,addr,mail,ph);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `CmpName` varchar(20) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` bigint(20) NOT NULL,
  `cmpcode` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `CID` int(15) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CID`, `Address`, `Name`, `email`, `phone`) VALUES
(1, 'sdfasdfasdafd', 'asdfasdf', 'dfasdfa', 1234324),
(2, 'sdasdasdasdasd', 'asdasdas', 'sadasdasa', 23423424),
(3, 'dgdfgdfgdfgdfgdg', 'dfgdfgd', 'fdgdfgdg', 234234234),
(4, 'sdfsdfsdfsdfs', 'fgdfgdfgd', 'gsfsdfsdf', 131312312),
(5, 'sfsdfsdfsdfsdfsdf', 'sdfsdfsdf', 'xcfsdfsdfsd', 24324234);

-- --------------------------------------------------------

--
-- Table structure for table `enquiry`
--

DROP TABLE IF EXISTS `enquiry`;
CREATE TABLE `enquiry` (
  `Eqno` varchar(15) NOT NULL,
  `Date` date NOT NULL,
  `Cmpname` varchar(10) NOT NULL,
  `Subject` varchar(100) NOT NULL,
  `CID` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enquiry`
--

INSERT INTO `enquiry` (`Eqno`, `Date`, `Cmpname`, `Subject`, `CID`) VALUES
('dsfsdfsdfsd', '2018-01-02', 'Awin', 'sdfsdfsdfsdf', 5);

-- --------------------------------------------------------

--
-- Table structure for table `eqrel`
--

DROP TABLE IF EXISTS `eqrel`;
CREATE TABLE `eqrel` (
  `Eno` varchar(15) NOT NULL,
  `QNo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `INo` varchar(15) NOT NULL,
  `Amount` double NOT NULL,
  `Type` tinyint(1) NOT NULL,
  `Date` date NOT NULL,
  `Subject` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pirel`
--

DROP TABLE IF EXISTS `pirel`;
CREATE TABLE `pirel` (
  `PjNo` int(11) NOT NULL,
  `INo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `PNo` varchar(15) NOT NULL,
  `PjNo` int(11) NOT NULL,
  `Value` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `qoutation`
--

DROP TABLE IF EXISTS `qoutation`;
CREATE TABLE `qoutation` (
  `Qno` varchar(15) NOT NULL,
  `EstPrice` double NOT NULL,
  `RevNo` int(11) NOT NULL,
  `Subject` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `qprel`
--

DROP TABLE IF EXISTS `qprel`;
CREATE TABLE `qprel` (
  `Qno` varchar(15) NOT NULL,
  `PjNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CID`);

--
-- Indexes for table `enquiry`
--
ALTER TABLE `enquiry`
  ADD PRIMARY KEY (`Eqno`,`Cmpname`,`CID`,`Date`) USING BTREE;

--
-- Indexes for table `eqrel`
--
ALTER TABLE `eqrel`
  ADD PRIMARY KEY (`Eno`,`QNo`),
  ADD KEY `Eno` (`Eno`,`QNo`),
  ADD KEY `QNo` (`QNo`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`INo`);

--
-- Indexes for table `pirel`
--
ALTER TABLE `pirel`
  ADD PRIMARY KEY (`PjNo`,`INo`),
  ADD KEY `PjNo` (`PjNo`,`INo`),
  ADD KEY `INo` (`INo`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`PjNo`);

--
-- Indexes for table `qoutation`
--
ALTER TABLE `qoutation`
  ADD PRIMARY KEY (`Qno`);

--
-- Indexes for table `qprel`
--
ALTER TABLE `qprel`
  ADD PRIMARY KEY (`Qno`,`PjNo`),
  ADD KEY `Qno` (`Qno`,`PjNo`),
  ADD KEY `PjNo` (`PjNo`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eqrel`
--
ALTER TABLE `eqrel`
  ADD CONSTRAINT `eqrel_ibfk_1` FOREIGN KEY (`Eno`) REFERENCES `enquiry` (`Eqno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `eqrel_ibfk_2` FOREIGN KEY (`QNo`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pirel`
--
ALTER TABLE `pirel`
  ADD CONSTRAINT `pirel_ibfk_1` FOREIGN KEY (`INo`) REFERENCES `invoice` (`INo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pirel_ibfk_2` FOREIGN KEY (`PjNo`) REFERENCES `product` (`PjNo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `qprel`
--
ALTER TABLE `qprel`
  ADD CONSTRAINT `qprel_ibfk_2` FOREIGN KEY (`Qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `qprel_ibfk_3` FOREIGN KEY (`PjNo`) REFERENCES `product` (`PjNo`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
