-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 17, 2018 at 07:36 PM
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `quotelastdigitautogen` (OUT `num` INT(15), IN `prev` INT(15))  NO SQL
BEGIN
SET @x=prev;
IF @x IS NULL THEN
	SET @x = 1;
ELSE SET @x=prev+1;
END IF;
SET num=@x;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

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
(5, 'sfsdfsdfsdfsdfsdf', 'sdfsdfsdf', 'xcfsdfsdfsd', 24324234),
(6, 'bub road', 'bubby', 'bub@bgmail.com', 656357),
(7, '9bullet street', '50cent', '50centgunshots@geg', 9999999),
(8, 'bfdddddddddddddddd', 'pitbull', 'pitbulcardio@bdbeh', 5346536),
(9, 'ndgbdbdggdn', 'vegan', 'veganrg34', 445365636),
(10, 'jtyjrjrjryjryjj', '51cent', '51shots@chest', 10000000),
(11, 'sdfgsdgf', 'dsfgsdfg', 'dsfgdfg', 324523),
(12, 'asdfasdfas', 'sdfas', 'sadfsad', 34523),
(13, 'sdfasdf', 'werwe', 'dfdsaf', 32423),
(14, 'sdfsdfsfsdf', 'dsfsdf', 'sdfsdf', 345345),
(15, 'bfdgbsbsfb', 'hetheh', 'gdbethttreff', 363566),
(16, 'htrhheth', 'ttttt', 'hthetdjrygnfrn', 547563),
(17, 'fewgvdvv.', 'jcole', 'bornsinner@gbe', 3424234),
(18, 'fwebgwrwvw', 'eminem', 'afcewffaf', 2445245),
(19, 'gerhbrefb', 'kendrick', 'gdbhdfb@bdfb', 242524551),
(20, 'gfbebreb', 'chance', 'htehbeh3', 435325),
(21, 'gsfhfsgs', 'gerrhew', 'fssnsns', 46356),
(22, 'bsf', 'gfdbsgs', 'gsfgeh', 653737),
(23, 'gegrererg', 'slim', 'gvefbebe', 324324),
(24, 'htrhethe', 'hetheth', 'geethe', 5362462),
(25, 'kykmymky', 'hhyttr', 'hgwrrw', 52452577),
(26, 'jryjrjrjryj', 'grgwg', 'htehethjkuku', 5639368),
(27, 'gewgwg', 'gwrgrw', 'etgergegr', 34232),
(28, 'gyrjjeh', 'grgwgw', 'gegwgwg', 4542552),
(29, 'gegrgegergerg', 'jerry', 'ergrergegrg', 4245234),
(30, 'fvsbsbsbs', 'grsgsree', 'safaffffff', 32245),
(31, 'bgbgsbsb', 'hjtjjry', 'bfdbfsbb', 563663),
(32, 'vdsvvav', 'fsvsvs', 'vsdvsdvvsv', 3563563),
(33, 'htehwthhwh', 'regrgwg', 'regrwwgwh', 4225225),
(34, 'hethetheh', 'kuykilll', 'ththtehe', 3662626),
(35, 'grwrwgwg', 'fegfqeg', 'gfrgbwrw', 4324552),
(36, 'hethetheh', 'hethethe', 'ehethethe', 3466336),
(37, 'htrneet', 'jryjr', 'hethethe', 4352452),
(38, 'gwrgrw', 'rwbwbwb', 'erbewbwb', 542242),
(39, 'kuyktnrnyry', 'hykkjjk', 'bdgnhkuyrjn', 43536398),
(40, 'fmyrhmn', 'bfdngsns', 'fdngndgng', 6547754);

-- --------------------------------------------------------

--
-- Table structure for table `enquiry`
--

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
('11', '2018-01-30', 'Steels', 'verbgveb', 23),
('111', '2018-01-04', 'Awin', 'plis send', 9),
('121', '2018-01-30', 'Awin', 'bgfrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 7),
('1223', '2018-01-11', 'Awin', 'hghhehnet', 15),
('1223', '2018-01-17', 'Awin', 'tegegewg', 33),
('123456', '2018-01-15', 'Awin', 'bsfnfs bs', 40),
('1341', '2018-01-17', 'Awin', 'grwgwgw', 39),
('172', '2018-01-02', 'Awin', 'fsbvsbsfs', 18),
('2133', '2018-01-16', 'Awin', 'wrgvrww', 31),
('223', '2018-01-01', 'Awin', 'bfdbfbdb', 19),
('231', '2018-01-11', 'Awin', 'grgwgrwgw', 32),
('2314', '2018-01-05', 'Steels', 'sweg  sweg serg', 8),
('23542', '2018-01-12', 'Awin', 'erbgerbebb', 38),
('2431', '2018-01-14', 'Awin', 'etnehgne', 26),
('3143', '2018-01-17', 'Awin', 'fwgvw', 35),
('3213', '2018-01-18', 'Awin', 'sgsrgrwgrwg', 30),
('324', '2018-01-18', 'Steels', 'grw3wggw', 27),
('32425', '2018-01-08', 'Awin', 'ethteheh', 36),
('341', '2018-01-19', 'Awin', 'rwgrgrwgw', 29),
('342', '2018-01-04', 'Awin', 'grsgrs', 16),
('345', '2018-01-17', 'Awin', 'hteheth', 34),
('421', '2018-01-03', 'Steels', 'bghhhhhhhhhhhhhh.', 17),
('425', '2018-01-26', 'Awin', 'hthetheth', 25),
('425', '2018-01-12', 'Awin', 'gegregwg', 28),
('4324', '2018-01-27', 'Awin', 'fsggw', 22),
('454', '2018-01-26', 'Steels', 'hrtnrnr', 24),
('619', '2018-01-10', 'Awin', 'gedgberb', 37),
('6969', '2018-01-19', 'Steels', 'vgfdddddddd', 20),
('778', '2018-01-05', 'Awin', 'ttttttttttttttttttt', 10),
('997', '2018-01-10', 'Steels', 'fffffffffwse', 21),
('dsfsdfsdfsd', '2018-01-02', 'Awin', 'sdfsdfsdfsdf', 5),
('fasdfasdf', '2018-01-31', 'Awin', 'asdfasdf', 13),
('fsdfs', '2018-01-31', 'Awin', 'asdfasdf', 12),
('gdhdfbgsfhs', '2018-01-18', 'Steels', 'bub', 6),
('hsgfsf', '2018-01-31', 'Awin', 'dsgdfgdfsg', 11),
('sdfsdfs', '2018-01-31', 'Awin', 'sdfsdfsdf', 14);

-- --------------------------------------------------------

--
-- Table structure for table `eqrel`
--

CREATE TABLE `eqrel` (
  `Eno` varchar(15) NOT NULL,
  `QNo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eqrel`
--

INSERT INTO `eqrel` (`Eno`, `QNo`) VALUES
('223', '18-AE-QT-001'),
('1223', '18-AE-QT-020'),
('231', '18-AE-QT-020'),
('619', '18-AE-QT-021'),
('23542', '18-AE-QT-022'),
('1341', '18-AE-QT-023'),
('123456', '18-AE-QT-024');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

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

CREATE TABLE `pirel` (
  `PjNo` int(11) NOT NULL,
  `INo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

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

CREATE TABLE `qoutation` (
  `Qno` varchar(15) NOT NULL,
  `EstPrice` double DEFAULT NULL,
  `RevNo` int(11) DEFAULT NULL,
  `Subject` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qoutation`
--

INSERT INTO `qoutation` (`Qno`, `EstPrice`, `RevNo`, `Subject`) VALUES
('18-AE-QT-001', NULL, NULL, NULL),
('18-AE-QT-002', NULL, NULL, NULL),
('18-AE-QT-020', NULL, NULL, NULL),
('18-AE-QT-021', NULL, NULL, NULL),
('18-AE-QT-022', NULL, NULL, NULL),
('18-AE-QT-023', NULL, NULL, NULL),
('18-AE-QT-024', NULL, NULL, NULL),
('18-SC-QT-019', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `qprel`
--

CREATE TABLE `qprel` (
  `Qno` varchar(15) NOT NULL,
  `PjNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `QuotationDetails_Awin`
--

CREATE TABLE `QuotationDetails_Awin` (
  `Sno` int(11) NOT NULL,
  `Des` varchar(1000) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `qno` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `QuotationDetails_Steels`
--

CREATE TABLE `QuotationDetails_Steels` (
  `Sno` int(11) NOT NULL,
  `Pos` varchar(1000) DEFAULT NULL,
  `NormalRate` varchar(100) DEFAULT NULL,
  `BeyondNormalRate` varchar(100) DEFAULT NULL,
  `Holidays` varchar(100) DEFAULT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `qno` varchar(15) NOT NULL
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
-- Indexes for table `QuotationDetails_Awin`
--
ALTER TABLE `QuotationDetails_Awin`
  ADD PRIMARY KEY (`Sno`,`qno`),
  ADD KEY `qno` (`qno`);

--
-- Indexes for table `QuotationDetails_Steels`
--
ALTER TABLE `QuotationDetails_Steels`
  ADD PRIMARY KEY (`Sno`,`qno`),
  ADD KEY `qno` (`qno`);

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

--
-- Constraints for table `QuotationDetails_Awin`
--
ALTER TABLE `QuotationDetails_Awin`
  ADD CONSTRAINT `quotationdetails_awin_ibfk_1` FOREIGN KEY (`qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `QuotationDetails_Steels`
--
ALTER TABLE `QuotationDetails_Steels`
  ADD CONSTRAINT `quotationdetails_steels_ibfk_1` FOREIGN KEY (`qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
