-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Feb 27, 2018 at 09:11 PM
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
DROP PROCEDURE IF EXISTS `enquiry_del_bkup`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `enquiry_del_bkup` (IN `eq` VARCHAR(15), IN `mail` VARCHAR(30), IN `dates` DATE, IN `cmp` VARCHAR(10), IN `rea` VARCHAR(50))  NO SQL
BEGIN 
SELECT eqno,Date1,cmpname,subject,cid into @eno, @d, @c, @s, @cd
FROM `enquiry` NATURAL JOIN `customer` 
WHERE eqno=eq and email=mail and cmpname=cmp and Date1=dates; 
INSERT INTO `enquiryBin`(`Eqno`, `Date1`, `Cmpname`, `Subject`, `CID`, `Reason`) 
VALUES (@eno,@d,@c,@s,@cd,rea); 
DELETE FROM `enquiry` 
WHERE eqno=eq and cid=@cd and cmpname=cmp and Date1=dates; 
END$$

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

DROP PROCEDURE IF EXISTS `quotelastdigitautogen`$$
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
-- Stand-in structure for view `abc`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `abc`;
CREATE TABLE `abc` (
`Eno` varchar(15)
,`QNo` varchar(25)
,`Date1` date
,`Cmpname` varchar(10)
,`CID` int(15)
);

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
(40, 'fmyrhmn', 'bfdngsns', 'fdngndgng', 6547754),
(41, 'sdfsdfsdf', 'sdfsdf', 'sdfsdf', 234234),
(42, 'asdfasdf', 'asdfasdf', 'asdasdaf', 345345),
(43, 'mncmjc', 'nbmgkfk', 'hgjhghg', 87876767),
(44, 'sadvsadvsadva', 'sdvsdvsd', 'vsvsadvasv', 32423423),
(45, 'GOTHAM CITY', 'JOKER', 'JOKER@GMAIL.COM', 2342342),
(46, 'sfdgsdfgsdfgsdfg', 'sdfgsdfgs', 'zadfgsdfgsdfg', 34534534),
(47, 'asdfasdfasdfasdf', 'sadfasdfa', 'asdfasdfsdafa', 234234),
(48, 'rwgrvsrbgtrbn4heb', 'yellapa', 'befberbeab@ge', 452525413),
(49, 'bangalore', 'abc', 'abc@abc.com', 23453245),
(50, 'adfavfvadfv', 'bgsbgb', 'vasvadfv', 34245);

-- --------------------------------------------------------

--
-- Table structure for table `enquiry`
--

DROP TABLE IF EXISTS `enquiry`;
CREATE TABLE `enquiry` (
  `Eqno` varchar(15) NOT NULL,
  `Date1` date NOT NULL,
  `Cmpname` varchar(10) NOT NULL,
  `Subject` varchar(100) NOT NULL,
  `CID` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enquiry`
--

INSERT INTO `enquiry` (`Eqno`, `Date1`, `Cmpname`, `Subject`, `CID`) VALUES
('11', '2018-01-30', 'Steels', 'verbgveb', 23),
('1223', '2018-01-11', 'Awin', 'hghhehnet', 15),
('1223', '2018-01-17', 'Awin', 'tegegewg', 33),
('1234554', '2018-02-18', 'Steels', 'HAPPY BIRTHDAY', 45),
('123456', '2018-01-15', 'Awin', 'bsfnfs bs', 40),
('132123', '2018-02-18', 'Steels', 'asfsdafadsf', 47),
('1341', '2018-01-17', 'Awin', 'grwgwgw', 39),
('172', '2018-01-02', 'Awin', 'fsbvsbsfs', 18),
('2133', '2018-01-16', 'Awin', 'wrgvrww', 31),
('223', '2018-01-01', 'Awin', 'bfdbfbdb', 19),
('231', '2018-01-11', 'Awin', 'grgwgrwgw', 32),
('2314', '2018-01-05', 'Steels', 'sweg  sweg serg', 8),
('2342', '2018-02-14', 'Awin', 'gebtrbrgbrtbwbw', 48),
('23423', '2018-02-15', 'Awin', 'dvdsvdfsvsd', 44),
('2342342', '2018-02-18', 'Awin', 'sdfgsdfgsfgsdfg', 46),
('23542', '2018-01-12', 'Awin', 'erbgerbebb', 38),
('2431', '2018-01-14', 'Awin', 'etnehgne', 26),
('3143', '2018-01-17', 'Awin', 'fwgvw', 35),
('3213', '2018-01-18', 'Awin', 'sgsrgrwgrwg', 30),
('324', '2018-01-18', 'Steels', 'grw3wggw', 27),
('32423', '2018-02-22', 'Awin', 'sdfsdfs', 41),
('32425', '2018-01-08', 'Awin', 'ethteheh', 36),
('33453', '2018-02-14', 'Awin', 'sdfsdfas', 42),
('341', '2018-01-19', 'Awin', 'rwgrgrwgw', 29),
('342', '2018-01-04', 'Awin', 'grsgrs', 16),
('34345', '2018-02-15', 'Awin', 'f  fsgbsdbobs', 50),
('345', '2018-01-17', 'Awin', 'hteheth', 34),
('345634', '2018-02-20', 'Awin', 'i want to buy', 49),
('421', '2018-01-03', 'Steels', 'bghhhhhhhhhhhhhh.', 17),
('425', '2018-01-26', 'Awin', 'hthetheth', 25),
('425', '2018-01-12', 'Awin', 'gegregwg', 28),
('4324', '2018-01-27', 'Awin', 'fsggw', 22),
('454', '2018-01-26', 'Steels', 'hrtnrnr', 24),
('619', '2018-01-10', 'Awin', 'gedgberb', 37),
('676868', '2018-02-08', 'Steels', 'nmcmncm', 43),
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
-- Table structure for table `enquirybin`
--

DROP TABLE IF EXISTS `enquirybin`;
CREATE TABLE `enquirybin` (
  `Eqno` varchar(15) NOT NULL,
  `Date1` date NOT NULL,
  `Cmpname` varchar(10) NOT NULL,
  `Subject` varchar(100) NOT NULL,
  `CID` int(15) NOT NULL,
  `Reason` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enquirybin`
--

INSERT INTO `enquirybin` (`Eqno`, `Date1`, `Cmpname`, `Subject`, `CID`, `Reason`) VALUES
('111', '2018-01-04', 'Awin', 'plis send', 9, 'yoga practice'),
('121', '2018-01-30', 'Awin', 'bgfrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 7, 'lack of equipments');

-- --------------------------------------------------------

--
-- Table structure for table `eqrel`
--

DROP TABLE IF EXISTS `eqrel`;
CREATE TABLE `eqrel` (
  `Eno` varchar(15) NOT NULL,
  `QNo` varchar(25) NOT NULL,
  `Date1` date NOT NULL,
  `Cmpname` varchar(10) NOT NULL,
  `CID` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eqrel`
--

INSERT INTO `eqrel` (`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES
('223', '18-AE-QT-001', '2018-01-01', 'Awin', 19),
('1223', '18-AE-QT-020', '2018-01-11', 'Awin', 15),
('231', '18-AE-QT-020', '2018-01-11', 'Awin', 32),
('619', '18-AE-QT-021', '2018-01-10', 'Awin', 37),
('23542', '18-AE-QT-022', '2018-01-12', 'Awin', 38),
('1341', '18-AE-QT-023', '2018-01-17', 'Awin', 39),
('123456', '18-AE-QT-024', '2018-01-15', 'Awin', 40),
('23423', '18-AE-QT-025', '2018-02-15', 'Awin', 44),
('2342342', '18-AE-QT-027', '2018-02-18', 'Awin', 46),
('2342', '18-AE-QT-029', '2018-02-14', 'Awin', 48),
('2342', '18-AE-QT-029.Rev.1', '2018-02-14', 'Awin', 48),
('2342', '18-AE-QT-029.Rev.2', '2018-02-14', 'Awin', 48),
('345634', '18-AE-QT-030', '2018-02-20', 'Awin', 49),
('345634', '18-AE-QT-030.Rev.1', '2018-02-20', 'Awin', 49),
('34345', '18-AE-QT-034', '2018-02-15', 'Awin', 50),
('1234554', '18-SC-QT-026', '2018-02-18', 'Steels', 45),
('132123', '18-SC-QT-028', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.1', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.2', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.3', '2018-02-18', 'Steels', 47);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `INo` varchar(15) NOT NULL,
  `Total_amt` double NOT NULL,
  `Date` date DEFAULT NULL,
  `Duedate` date DEFAULT NULL,
  `Salesperson` varchar(25) NOT NULL,
  `Acc No` varchar(20) NOT NULL,
  `Termofpay` varchar(25) NOT NULL,
  `addedgst` float NOT NULL,
  `Amount_paid` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details`
--

DROP TABLE IF EXISTS `invoice_details`;
CREATE TABLE `invoice_details` (
  `Item/No` int(10) NOT NULL,
  `Descr` varchar(100) DEFAULT NULL,
  `Qty` int(10) NOT NULL,
  `UnitPrice` int(10) NOT NULL,
  `Invno` varchar(15) NOT NULL
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
  `Date` date NOT NULL,
  `EstDate` date DEFAULT NULL,
  `Des` varchar(5000) NOT NULL DEFAULT 'None'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PNo`, `PjNo`, `Value`, `Date`, `EstDate`, `Des`) VALUES
('456345', 1, 5466547, '2018-02-14', '2018-02-08', 'fsghfdghfgdhdfg');

-- --------------------------------------------------------

--
-- Table structure for table `qoutation`
--

DROP TABLE IF EXISTS `qoutation`;
CREATE TABLE `qoutation` (
  `Qno` varchar(25) NOT NULL,
  `EstPrice` double DEFAULT NULL,
  `RevNo` int(11) NOT NULL DEFAULT '0',
  `Subject` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qoutation`
--

INSERT INTO `qoutation` (`Qno`, `EstPrice`, `RevNo`, `Subject`) VALUES
('18-AE-QT-001', NULL, 0, NULL),
('18-AE-QT-002', NULL, 0, NULL),
('18-AE-QT-020', NULL, 0, NULL),
('18-AE-QT-021', NULL, 0, NULL),
('18-AE-QT-022', NULL, 0, NULL),
('18-AE-QT-023', NULL, 0, NULL),
('18-AE-QT-024', NULL, 0, NULL),
('18-AE-QT-025', NULL, 0, NULL),
('18-AE-QT-027', NULL, 0, NULL),
('18-AE-QT-029', NULL, 0, NULL),
('18-AE-QT-029.Rev.1', NULL, 1, NULL),
('18-AE-QT-029.Rev.2', NULL, 2, NULL),
('18-AE-QT-030', NULL, 0, NULL),
('18-AE-QT-030.Rev.1', NULL, 1, NULL),
('18-AE-QT-031', NULL, 0, NULL),
('18-AE-QT-032', NULL, 0, NULL),
('18-AE-QT-033', NULL, 0, NULL),
('18-AE-QT-034', NULL, 0, NULL),
('18-SC-QT-026', NULL, 0, NULL),
('18-SC-QT-028', NULL, 0, NULL),
('18-SC-QT-028.Rev.1', NULL, 1, NULL),
('18-SC-QT-028.Rev.2', NULL, 2, NULL),
('18-SC-QT-028.Rev.3', NULL, 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `qprel`
--

DROP TABLE IF EXISTS `qprel`;
CREATE TABLE `qprel` (
  `Qno` varchar(25) NOT NULL,
  `PjNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qprel`
--

INSERT INTO `qprel` (`Qno`, `PjNo`) VALUES
('18-AE-QT-030.Rev.1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `quotationdetails_awin`
--

DROP TABLE IF EXISTS `quotationdetails_awin`;
CREATE TABLE `quotationdetails_awin` (
  `Sno` int(11) NOT NULL,
  `Des` varchar(1000) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `qno` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quotationdetails_awin`
--

INSERT INTO `quotationdetails_awin` (`Sno`, `Des`, `quantity`, `unit`, `total`, `qno`) VALUES
(0, 'dsfgsdfgsdfgsdfg', 34534, 34534, 345345, '18-AE-QT-025'),
(1, 'sdfgsdsdfgsdsdfg', 345345, 345345, 345345, '18-AE-QT-025'),
(1, 'bub send plssdfsdfsd3', 3, 33, 342432, '18-AE-QT-029'),
(1, 'bub send pussylssdfsdfsd3', 3, 33, 342432, '18-AE-QT-029.Rev.1'),
(1, 'bub send pucciylssdfsdfsd3', 3, 33, 342432, '18-AE-QT-029.Rev.2'),
(1, 'dfgdsfg', 3453, 345, 565, '18-AE-QT-030'),
(1, 'dfgdsfg', 3453, 345, 565, '18-AE-QT-030.Rev.1'),
(2, 'dsfgdfgdfg', 345345345, 345345345, 345345345, '18-AE-QT-025'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029.Rev.1'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029.Rev.2'),
(2, 'dfgsfdg', 43534, 345, 3245, '18-AE-QT-030'),
(2, 'dfgsfdg', 43534, 345, 3245, '18-AE-QT-030.Rev.1'),
(3, 'sdfgsdfgsdfgsdf', 34534534, 34534534, 34534534, '18-AE-QT-025'),
(3, 'grvrfrvbeverbmorreeeeee', 4, 13, 54322234, '18-AE-QT-029'),
(3, 'grvrfrvbeverbmorreeeeee', 4, 13, 54322234, '18-AE-QT-029.Rev.1'),
(3, 'grvrfrvbeverbmorreeeeee', 4, 13, 54322234, '18-AE-QT-029.Rev.2'),
(3, 'sdfas', 3454, 34534, 45634, '18-AE-QT-030'),
(3, 'sdfas', 3454, 34534, 45634, '18-AE-QT-030.Rev.1'),
(4, 'doog', 4, 535, 543255, '18-AE-QT-029.Rev.1'),
(4, 'doog', 4, 535, 543255, '18-AE-QT-029.Rev.2'),
(4, 'dsgfdf', 3456, 6543634, 45645, '18-AE-QT-030');

-- --------------------------------------------------------

--
-- Table structure for table `quotationdetails_steels`
--

DROP TABLE IF EXISTS `quotationdetails_steels`;
CREATE TABLE `quotationdetails_steels` (
  `Sno` int(11) NOT NULL,
  `Pos` varchar(1000) DEFAULT NULL,
  `NormalRate` varchar(100) DEFAULT NULL,
  `BeyondNormalRate` varchar(100) DEFAULT NULL,
  `Holidays` varchar(100) DEFAULT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `qno` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quotationdetails_steels`
--

INSERT INTO `quotationdetails_steels` (`Sno`, `Pos`, `NormalRate`, `BeyondNormalRate`, `Holidays`, `Remarks`, `qno`) VALUES
(1, 'doggy style', 'asdfasdfssd', 'asdf324234', 'd', 'hdfghdfgh', '18-SC-QT-028'),
(1, 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.1'),
(1, 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.2'),
(1, 'dogg stylesssssssss', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.3'),
(2, 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd', 'dfghd23', 'dfg', 'dfghdfghdfgh', '18-SC-QT-028'),
(2, 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.1'),
(2, 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.2'),
(2, 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.3'),
(3, 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf', 'sdfgsd', 'sdfgsdfg345345345', '18-SC-QT-028'),
(3, 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.1'),
(3, 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.2'),
(3, 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.3');

-- --------------------------------------------------------

--
-- Structure for view `abc`
--
DROP TABLE IF EXISTS `abc`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `abc`  AS  select `eqrel`.`Eno` AS `Eno`,`eqrel`.`QNo` AS `QNo`,`eqrel`.`Date1` AS `Date1`,`eqrel`.`Cmpname` AS `Cmpname`,`eqrel`.`CID` AS `CID` from `eqrel` ;

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
  ADD PRIMARY KEY (`Eqno`,`Cmpname`,`CID`,`Date1`) USING BTREE;

--
-- Indexes for table `enquirybin`
--
ALTER TABLE `enquirybin`
  ADD PRIMARY KEY (`Eqno`,`Cmpname`,`CID`,`Date1`) USING BTREE;

--
-- Indexes for table `eqrel`
--
ALTER TABLE `eqrel`
  ADD PRIMARY KEY (`Eno`,`QNo`,`Date1`,`Cmpname`,`CID`),
  ADD KEY `Eno` (`Eno`,`QNo`),
  ADD KEY `QNo` (`QNo`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`INo`);

--
-- Indexes for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD PRIMARY KEY (`Item/No`,`Invno`),
  ADD KEY `Invno` (`Invno`);

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
-- Indexes for table `quotationdetails_awin`
--
ALTER TABLE `quotationdetails_awin`
  ADD PRIMARY KEY (`Sno`,`qno`),
  ADD KEY `qno` (`qno`);

--
-- Indexes for table `quotationdetails_steels`
--
ALTER TABLE `quotationdetails_steels`
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
-- Constraints for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD CONSTRAINT `invoice_details_ibfk_1` FOREIGN KEY (`Invno`) REFERENCES `invoice` (`INo`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `qprel_ibfk_3` FOREIGN KEY (`PjNo`) REFERENCES `product` (`PjNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `qprel_ibfk_4` FOREIGN KEY (`Qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `quotationdetails_awin`
--
ALTER TABLE `quotationdetails_awin`
  ADD CONSTRAINT `quotationdetails_awin_ibfk_1` FOREIGN KEY (`qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `quotationdetails_steels`
--
ALTER TABLE `quotationdetails_steels`
  ADD CONSTRAINT `quotationdetails_steels_ibfk_1` FOREIGN KEY (`qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
