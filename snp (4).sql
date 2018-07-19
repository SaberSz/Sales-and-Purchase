-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 19, 2018 at 09:33 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCustomer` (IN `name` VARCHAR(100), IN `addr` VARCHAR(200), IN `mail` VARCHAR(100), IN `ph` VARCHAR(100))  MODIFIES SQL DATA
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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `CmpName` varchar(40) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `GST` float NOT NULL DEFAULT '0',
  `Fax` varchar(20) DEFAULT NULL,
  `CompRegNo` varchar(40) NOT NULL,
  `GSTRegNo` varchar(40) NOT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Website` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`CmpName`, `Address`, `Phone`, `GST`, `Fax`, `CompRegNo`, `GSTRegNo`, `Email`, `Website`) VALUES
('AWIN ENGINEERING PTE LTD', 'No. 109, Tuas South Ave. 8, Singapore 637 037.', '+65 6778 8271', 7, '+65 6265 768333', '201012187G', '201012187G', 'bubnows', 'gwgrw'),
('STEEL COAT PTE LTD', 'No. 12, Tuas View Place, Singapore - 637865.', '+65 6265 9476', 8, '+65 6265 7685', '201410749G', '201410749G', 'Enquires@steelcoahello', 'www.steelcoat.com.sg');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `CID` int(15) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CID`, `Address`, `Name`, `email`, `phone`) VALUES
(1, 'sdfasdfasdafd', 'asdfasdf', 'dfasdfa', '1234324'),
(2, 'sdasdasdasdasd', 'asdasdas', 'sadasdasa', '23423424'),
(3, 'dgdfgdfgdfgdfgdg', 'dfgdfgd', 'fdgdfgdg', '234234234'),
(4, 'sdfsdfsdfsdfs', 'fgdfgdfgd', 'gsfsdfsdf', '131312312'),
(5, 'sfsdfsdfsdfsdfsdf', 'sdfsdfsdf', 'xcfsdfsdfsd', '24324234'),
(6, 'bub road', 'bubby', 'bub@bgmail.com', '656357'),
(7, '9bullet street', '50cent', '50centgunshots@geg', '9999999'),
(8, 'bfdddddddddddddddd', 'pitbull', 'pitbulcardio@bdbeh', '5346536'),
(9, 'ndgbdbdggdn', 'vegan', 'veganrg34', '445365636'),
(10, 'jtyjrjrjryjryjj', '51cent', '51shots@chest', '10000000'),
(11, 'sdfgsdgf', 'dsfgsdfg', 'dsfgdfg', '324523'),
(12, 'asdfasdfas', 'sdfas', 'sadfsad', '34523'),
(13, 'sdfasdf', 'werwe', 'dfdsaf', '32423'),
(14, 'sdfsdfsfsdf', 'dsfsdf', 'sdfsdf', '345345'),
(15, 'bfdgbsbsfb', 'hetheh', 'gdbethttreff', '363566'),
(16, 'htrhheth', 'ttttt', 'hthetdjrygnfrn', '547563'),
(17, 'fewgvdvv.', 'jcole', 'bornsinner@gbe', '3424234'),
(18, 'fwebgwrwvw', 'eminem', 'afcewffaf', '2445245'),
(19, 'gerhbrefb', 'kendrick', 'gdbhdfb@bdfb', '242524551'),
(20, 'gfbebreb', 'chance', 'htehbeh3', '435325'),
(21, 'gsfhfsgs', 'gerrhew', 'fssnsns', '46356'),
(22, 'bsf', 'gfdbsgs', 'gsfgeh', '653737'),
(23, 'gegrererg', 'slim', 'gvefbebe', '324324'),
(24, 'htrhethe', 'hetheth', 'geethe', '5362462'),
(25, 'kykmymky', 'hhyttr', 'hgwrrw', '52452577'),
(26, 'jryjrjrjryj', 'grgwg', 'htehethjkuku', '5639368'),
(27, 'gewgwg', 'gwrgrw', 'etgergegr', '34232'),
(28, 'gyrjjeh', 'grgwgw', 'gegwgwg', '4542552'),
(29, 'gegrgegergerg', 'jerry', 'ergrergegrg', '4245234'),
(30, 'fvsbsbsbs', 'grsgsree', 'safaffffff', '32245'),
(31, 'bgbgsbsb', 'hjtjjry', 'bfdbfsbb', '563663'),
(32, 'vdsvvav', 'fsvsvs', 'vsdvsdvvsv', '3563563'),
(33, 'htehwthhwh', 'regrgwg', 'regrwwgwh', '4225225'),
(34, 'hethetheh', 'kuykilll', 'ththtehe', '3662626'),
(35, 'grwrwgwg', 'fegfqeg', 'gfrgbwrw', '4324552'),
(36, 'hethetheh', 'hethethe', 'ehethethe', '3466336'),
(37, 'htrneet', 'jryjr', 'hethethe', '4352452'),
(38, 'gwrgrw', 'rwbwbwb', 'erbewbwb', '542242'),
(39, 'kuyktnrnyry', 'hykkjjk', 'bdgnhkuyrjn', '43536398'),
(40, 'fmyrhmn', 'bfdngsns', 'fdngndgng', '6547754'),
(41, 'sdfsdfsdf', 'sdfsdf', 'sdfsdf', '234234'),
(42, 'asdfasdf', 'asdfasdf', 'asdasdaf', '345345'),
(43, 'mncmjc', 'nbmgkfk', 'hgjhghg', '87876767'),
(44, 'sadvsadvsadva', 'sdvsdvsd', 'vsvsadvasv', '32423423'),
(45, 'GOTHAM CITY', 'JOKER', 'JOKER@GMAIL.COM', '2342342'),
(46, 'sfdgsdfgsdfgsdfg', 'sdfgsdfgs', 'zadfgsdfgsdfg', '34534534'),
(47, 'Loyang Offshore Supply Base\r\n25 Loyang Crescent\r\nTOPS Avenue 1, Block 103 #01-06 Singapore 508988', 'Aquatic Asia Pacific Pte Ltd', 'Aquatic@gamil.com', '+65 6778 8271 / +65 81899206'),
(48, 'rwgrvsrbgtrbn4heb', 'yellapa', 'befberbeab@ge', '452525413'),
(49, 'bangalore', 'abc', 'abc@abc.com', '23453245'),
(50, 'adfavfvadfv', 'bgsbgb', 'vasvadfv', '34245'),
(51, 'rebetbrsbgrswbe', 'rwgwgrwg', 'betbebedvsrbg', '2352424'),
(52, 'dfgsdfgsdfgsdfg', 'sdfgsdfgs', 'dfgsfgsdfg', '34534534'),
(53, 'gwgrgwgrwwrwgwgwrggrwrrw', 'gregwgwg', 'vswrfqefqefsaqf', '4312431415'),
(54, 'banaglore', 'tom', 'tom@gmail.com', '123456789'),
(55, 'Alder Street, Compton\nHong Kong', 'Food Suppliers', 'food@big.com', '12343222'),
(56, 'Apollo Pharma,\n221 B,\nBaker Street,\nLondon', 'Apollo  Pharmacy', 'apollo@gmail.com', '12321231'),
(57, 'sjfhkjsdhf!@#$$%^&*\'select\"', 'awin', 'absdhs.vcc@ssd.com', '8989894'),
(58, 'judkduyfuyfoiyfoiyfyutfdkfuyfuyf', 'fuykfuyfuy', 'cghjkhgfdkuyf', '58768976'),
(59, 'Awin', 'Awin', 'Awin', '95858'),
(60, 'Hallo Babi', 'Amon', 'amanda@gmail.com', '9874189658'),
(61, 'baker street\nlondon', 'food boy', 'sdfdsfasd', '12312312'),
(62, 'asdASDAEDEdeadE', 'aesfasefa', 'adasdasd', '2324234'),
(63, 'Delhi Noida India', 'Ar India', 'abc.AirIndia@gmail.com', '123234233414'),
(64, 'asdasdasdasd', 'awdawd', 'awdadwa', '12312312'),
(65, 'waefawefawefawefa', 'awefawef', 'sfsfwsfawwsef', '2342342'),
(66, 'dfgsdfgsdfgsdgsdgs', 'sdfgsdfgsfsdf', 'dfgsdfgs', '2344532452'),
(67, 'dfgsdfgsdfgsdgsdgcvxbs', 'sdfgsdfgsfsddfgdff', 'dfgsdfgscvb', '2344532452435'),
(68, 'dfgsdfgsdfgsdgsdgcvxbsasfd', 'sdfgsdfgsfsddfgdffsdfg', 'dfgsdfgscvbsdfa', '2344532452'),
(69, 'sdasdasdadad', 'asdasda', 'adsdasdasda', '23423423'),
(70, 'fsfdasdfasdfas', 'asefasef', 'safsadfasf', '234234'),
(71, 'mats', 'mats', 'mats', '1231231'),
(72, 'matter', 'matter', 'matter', '1231231'),
(73, 'dxfg dxfgxdnf', 'fctymt', 'dxfgnxdffng', '345345'),
(74, 'zsdfzsdfzdsfsdf', 'zxdfzf', 'zfszdfzsdf', '23123'),
(75, 'mi a1 street', 'mr y', 'efeavqefqvqvqvqv', '235234344'),
(76, 'feqfeqfeqfqe', 'mr z', 'feqfqefqemrz', '2341343'),
(77, 'vadvdavadvad', 'dvavfsvfsbsb', 'rwvvvadva', '34324'),
(78, 'vadvdavadvad', 'kabab', 'rwvvvadva', '34324dcacfea+');

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
('1234', '2018-02-08', 'Awin', '5 litres of oil', 54),
('1234554', '2018-02-18', 'Steels', 'HAPPY BIRTHDAY', 45),
('123456', '2018-01-15', 'Awin', 'bsfnfs bs', 40),
('132123', '2018-02-18', 'Steels', 'Provision of Skilled Manpowers for Strcutrual Fabrication @ Aquatic Asia Pacific Yard at Loyang.', 47),
('1341', '2018-01-17', 'Awin', 'grwgwgw', 39),
('172', '2018-01-02', 'Awin', 'fsbvsbsfs', 18),
('2133', '2018-01-16', 'Awin', 'wrgvrww', 31),
('223', '2018-01-01', 'Awin', 'bfdbfbdb', 19),
('231', '2018-01-11', 'Awin', 'grgwgrwgw', 32),
('2314', '2018-01-05', 'Steels', 'sweg  sweg serg', 8),
('2342', '2018-02-14', 'Awin', 'gebtrbrgbrtbwbw', 48),
('23423', '2018-02-15', 'Awin', 'dvdsvdfsvsd', 44),
('23423', '2018-03-06', 'Awin', '2341234234dsgfdsg', 52),
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
('34141231414', '2018-03-08', 'Awin', 'ton of iron.', 53),
('34141231414_r', '2018-03-08', 'Awin', 'ton of iron.', 53),
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
  `Reason` varchar(50) NOT NULL,
  `delDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enquirybin`
--

INSERT INTO `enquirybin` (`Eqno`, `Date1`, `Cmpname`, `Subject`, `CID`, `Reason`, `delDate`) VALUES
('111', '2018-01-04', 'Awin', 'plis send', 9, 'yoga practice', '2018-03-01 18:01:48'),
('121', '2018-01-30', 'Awin', 'bgfrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 7, 'lack of equipments', '2018-03-01 18:01:48'),
('1234_r', '2018-03-09', 'Awin', '5 litres of oil', 54, 'not profitable', '2018-03-09 18:07:19'),
('kksdfugiudgdfgi', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-10-01 18:01:48'),
('kkugiudgdfgiu', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-10-01 18:01:48'),
('kkugiudgiu', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-08-01 18:01:48'),
('kkugiugiu', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-07-01 18:01:48'),
('sdfugiudgdfgiu', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-10-01 18:01:48'),
('sdfuudgdfgiu', '2018-03-12', 'awin', 'lklugluguig', 1, 'yoga', '2018-10-01 18:01:48');

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
('23423', '18-AE-QT-035.Rev.0', '2018-03-06', 'Awin', 52),
('23423', '18-AE-QT-035.Rev.1', '2018-03-06', 'Awin', 52),
('23423', '18-AE-QT-035.Rev.2', '2018-03-06', 'Awin', 52),
('1234', '18-AE-QT-045.Rev.0', '2018-02-08', 'AWIN', 54),
('1234', '18-AE-QT-046.Rev.0', '2018-02-08', 'AWIN', 54),
('1234', '18-AE-QT-047.Rev.0', '2018-02-08', 'AWIN', 54),
('1234', '18-AE-QT-048.Rev.0', '2018-02-08', 'AWIN', 54),
('1234', '18-AE-QT-049.Rev.0', '2018-02-08', 'AWIN', 54),
('1234', '18-AE-QT-049.Rev.1', '2018-02-08', 'tom', 54),
('1234554', '18-SC-QT-026', '2018-02-18', 'Steels', 45),
('132123', '18-SC-QT-028', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.1', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.2', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.3', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.4', '2018-02-18', 'Steels', 47),
('132123', '18-SC-QT-028.Rev.5', '2018-02-18', 'Steels', 47);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `INo` varchar(15) NOT NULL,
  `Total_amt` double NOT NULL DEFAULT '0',
  `Date` date DEFAULT NULL,
  `Duedate` date DEFAULT NULL,
  `Salesperson` varchar(25) NOT NULL,
  `Acc No` varchar(20) NOT NULL,
  `Termofpay` varchar(25) NOT NULL,
  `addedgst` float NOT NULL DEFAULT '0',
  `Amount_paid` double NOT NULL DEFAULT '0',
  `invgen` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`INo`, `Total_amt`, `Date`, `Duedate`, `Salesperson`, `Acc No`, `Termofpay`, `addedgst`, `Amount_paid`, `invgen`) VALUES
('1801-AE-INV-001', 75, NULL, NULL, 'abc', '34534534', '30', 5.25, 0, 0),
('1802-AE-INV-002', 209057777, '2018-07-15', '2018-08-24', 'Sri Vidya', '345345', '40', 1463.35, 0, 1),
('1804-SC-INV-001', 268147, '2018-07-15', '2018-08-14', 'Voma ha', '234523454', '30', 2145.12, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details`
--

DROP TABLE IF EXISTS `invoice_details`;
CREATE TABLE `invoice_details` (
  `Item/No` int(10) NOT NULL,
  `Descr` varchar(100) DEFAULT NULL,
  `Qty` varchar(20) DEFAULT NULL,
  `UnitPrice` float NOT NULL DEFAULT '0',
  `total` double DEFAULT NULL,
  `Invno` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice_details`
--

INSERT INTO `invoice_details` (`Item/No`, `Descr`, `Qty`, `UnitPrice`, `total`, `Invno`) VALUES
(1, 'apples', '3', 25, 75, '1801-AE-INV-001'),
(1, 'Provision for Supply of Manpower', '174', 18, 3132, '1802-AE-INV-002'),
(1, 'Sand Blasting / Coating of AF SN63177', '1', 4469, 4469, '1804-SC-INV-001'),
(2, 'Sand Blasting / Coating of AF SN63178', '2', 4469, 8938, '1804-SC-INV-001');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_payments`
--

DROP TABLE IF EXISTS `invoice_payments`;
CREATE TABLE `invoice_payments` (
  `DateEntry` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `INo` varchar(15) NOT NULL,
  `Amount` double NOT NULL DEFAULT '0',
  `DatePaid` date NOT NULL,
  `Late` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice_payments`
--

INSERT INTO `invoice_payments` (`DateEntry`, `INo`, `Amount`, `DatePaid`, `Late`) VALUES
('2018-07-08 18:01:31', '1804-SC-INV-001', 2681, '2018-07-08', 0),
('2018-07-08 18:01:44', '1804-SC-INV-001', 2413, '2018-07-08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pirel`
--

DROP TABLE IF EXISTS `pirel`;
CREATE TABLE `pirel` (
  `PjNo` int(11) NOT NULL,
  `INo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pirel`
--

INSERT INTO `pirel` (`PjNo`, `INo`) VALUES
(1, '1801-AE-INV-001'),
(2, '1802-AE-INV-002'),
(4, '1804-SC-INV-001');

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
  `Des` varchar(5000) NOT NULL DEFAULT 'None',
  `Comp` tinyint(1) NOT NULL DEFAULT '0',
  `Compdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`PNo`, `PjNo`, `Value`, `Date`, `EstDate`, `Des`, `Comp`, `Compdate`) VALUES
('456345', 1, 5466547, '2018-02-14', '2018-02-08', 'fsghfdgh apples', 0, '2018-07-06'),
('34534', 2, 345345, '2018-03-06', '2018-03-15', 'sdfgsdf[gisdfjgpsdfoghsdpfgoshsdasdfgaslkdfhgalsfiasgdflkuag', 1, '2018-03-03'),
('3456', 3, 3456778, '2018-03-09', '2018-03-15', 'creation of oil and memory.', 0, '2018-03-10'),
('345434', 4, 234234, '2018-07-02', '2018-07-27', 'apples and oranges for construction', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_enquiry`
--

DROP TABLE IF EXISTS `purchase_enquiry`;
CREATE TABLE `purchase_enquiry` (
  `Eqno` varchar(25) NOT NULL,
  `edate` date NOT NULL,
  `SID` int(15) NOT NULL,
  `Subject` varchar(500) NOT NULL,
  `Cmpname` varchar(10) NOT NULL,
  `Type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_enquiry`
--

INSERT INTO `purchase_enquiry` (`Eqno`, `edate`, `SID`, `Subject`, `Cmpname`, `Type`) VALUES
('18-AE-EQ-001', '2018-07-17', 55, 'Apples and Oranges for that daily Vitamuin A and Vitamin C', 'Awin', 'Project Related'),
('18-AE-EQ-002', '2018-07-17', 66, 'sddrhsfdhbsdfgsdfgsdfg', 'Awin', 'Regular'),
('18-AE-EQ-003', '2018-07-18', 68, 'sddrhsfdhbsdfgsdfgsdfgcbcgfdgdfgfdfg', 'Awin', 'Project Related'),
('18-AE-EQ-005', '2018-07-18', 70, 'safasefasefaewf', 'Awin', 'Project Related'),
('18-AE-EQ-006', '2018-07-18', 71, 'cSDcSCsdcSD', 'Awin', 'Project Related'),
('18-AE-EQ-007', '2018-07-18', 72, 'sfgdfdgdsg', 'Awin', 'Regular'),
('18-AE-EQ-008', '2018-07-18', 73, 'xftgyhxfymhfdt', 'Awin', 'Project Related'),
('18-AE-EQ-009', '2018-07-18', 74, 'asdfzsdfzdsf', 'Awin', 'Project Related'),
('18-AE-EQ-010', '2018-07-18', 74, 'asdfzsdfzdsf', 'Awin', 'Project Related'),
('18-AE-EQ-011', '2018-07-18', 74, 'asdfzsdfzdsf', 'Awin', 'Regular'),
('18-AE-EQ-012', '2018-07-06', 75, 'befbwbwb', 'Awin', 'Project Related'),
('18-AE-EQ-013', '2018-07-12', 76, 'dcacadca', 'Awin', 'Project Related'),
('18-AE-EQ-014', '2018-07-03', 77, 'dcadvdvvda', 'Awin', 'Project Related'),
('18-SC-EQ-002', '2018-07-17', 62, 'wedawedawegawesefawefwaegwgfas', 'Steel', 'Regular'),
('18-SC-EQ-003', '2018-07-17', 67, 'sddrhsfdhbsdfgsdfgsdfgcbc', 'Steel', 'Project Related'),
('18-SC-EQ-004', '2018-07-18', 74, 'asdfzsdfzdsf', 'Steel', 'Project Related'),
('18-SC-EQ-005', '2018-07-18', 74, 'asdfzsdfzdsf', 'Steel', 'Regular'),
('18-SC-EQ-006', '2018-07-02', 78, 'dcadvdvvdavadvav', 'Steel', 'Regular');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_eprel`
--

DROP TABLE IF EXISTS `purchase_eprel`;
CREATE TABLE `purchase_eprel` (
  `Eqno` varchar(25) NOT NULL,
  `Pjno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_eprel`
--

INSERT INTO `purchase_eprel` (`Eqno`, `Pjno`) VALUES
('18-AE-EQ-005', 1),
('18-AE-EQ-006', 1),
('18-AE-EQ-013', 1),
('18-AE-EQ-009', 2),
('18-AE-EQ-010', 2),
('18-AE-EQ-014', 2),
('18-AE-EQ-001', 3),
('18-AE-EQ-003', 3),
('18-AE-EQ-008', 3),
('18-AE-EQ-012', 3),
('18-SC-EQ-003', 4),
('18-SC-EQ-004', 4);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoice`
--

DROP TABLE IF EXISTS `purchase_invoice`;
CREATE TABLE `purchase_invoice` (
  `Ino` varchar(100) NOT NULL,
  `AmtwoGST` double NOT NULL,
  `PaymentTerm` int(11) NOT NULL,
  `AmtwithGST` double NOT NULL,
  `date_recv` date NOT NULL,
  `paid` tinyint(4) NOT NULL DEFAULT '0',
  `amtpaid` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoicepayments`
--

DROP TABLE IF EXISTS `purchase_invoicepayments`;
CREATE TABLE `purchase_invoicepayments` (
  `Ino` varchar(100) NOT NULL,
  `paidDate` date NOT NULL,
  `amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_pirel`
--

DROP TABLE IF EXISTS `purchase_pirel`;
CREATE TABLE `purchase_pirel` (
  `Po_NO` varchar(25) NOT NULL,
  `Ino` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_po`
--

DROP TABLE IF EXISTS `purchase_po`;
CREATE TABLE `purchase_po` (
  `Po_NO` varchar(25) NOT NULL,
  `Sentdate` date DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `DeliveryDate` date DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `Sent` tinyint(4) NOT NULL DEFAULT '0',
  `SubTotal` double DEFAULT NULL,
  `PaymentTerm` varchar(50) DEFAULT NULL,
  `GST` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_po`
--

INSERT INTO `purchase_po` (`Po_NO`, `Sentdate`, `Description`, `DeliveryDate`, `Total`, `Sent`, `SubTotal`, `PaymentTerm`, `GST`) VALUES
('18CONS-AE-PO-001', NULL, 'ASDAssdaSDasd', '2018-07-24', 289585.06, 0, 235435, 'sfdsadas', 54150.05);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_potabledetails`
--

DROP TABLE IF EXISTS `purchase_potabledetails`;
CREATE TABLE `purchase_potabledetails` (
  `RC` int(11) NOT NULL,
  `Po_NO` varchar(25) NOT NULL,
  `UOM` varchar(100) DEFAULT NULL,
  `Description` varchar(10000) DEFAULT NULL,
  `Qty` varchar(100) DEFAULT NULL,
  `Price` varchar(10) DEFAULT NULL,
  `TotalAmt` varchar(10) DEFAULT NULL,
  `Discount` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_potabledetails`
--

INSERT INTO `purchase_potabledetails` (`RC`, `Po_NO`, `UOM`, `Description`, `Qty`, `Price`, `TotalAmt`, `Discount`) VALUES
(8, '18CONS-AE-PO-001', 'asdf', 'sdcsdca', '23', '23434', '415016.14', '23'),
(9, '18CONS-AE-PO-001', 'asdfas', 'sdfsfafdas', '23', '34545', '611791.95', '23');

-- --------------------------------------------------------

--
-- Table structure for table `purchase_qprel`
--

DROP TABLE IF EXISTS `purchase_qprel`;
CREATE TABLE `purchase_qprel` (
  `Qno` varchar(100) NOT NULL,
  `Po_NO` varchar(25) NOT NULL,
  `Eqno` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_quotation`
--

DROP TABLE IF EXISTS `purchase_quotation`;
CREATE TABLE `purchase_quotation` (
  `Qno` varchar(100) NOT NULL,
  `date_recv` date NOT NULL,
  `location` varchar(1000) NOT NULL,
  `EQno` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_quotation`
--

INSERT INTO `purchase_quotation` (`Qno`, `date_recv`, `location`, `EQno`) VALUES
('123123123', '2018-07-18', 'D:\\Github Repos\\Sales-and-Purchase\\results\\Quotation\\Steel\\18-SC-QT-028-Rev-4.pdf', '18-AE-EQ-011'),
('213', '2018-07-17', 'C:\\Users\\Admin\\Desktop\\pdf files\\hello_world.pdf', '18-AE-EQ-011'),
('23423', '2018-07-11', 'C:\\Users\\dylan\\Documents\\SNP Docs\\18-AE-QT-014 (Aquatic).pdf', '18-AE-EQ-011'),
('5421', '2018-07-30', 'C:\\Users\\Admin\\Desktop\\pdf files\\cns.pdf', '18-SC-EQ-004');

-- --------------------------------------------------------

--
-- Table structure for table `qoutation`
--

DROP TABLE IF EXISTS `qoutation`;
CREATE TABLE `qoutation` (
  `Qno` varchar(25) NOT NULL,
  `RevNo` int(11) NOT NULL DEFAULT '0',
  `times` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Sent` tinyint(1) NOT NULL DEFAULT '0',
  `Sentdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qoutation`
--

INSERT INTO `qoutation` (`Qno`, `RevNo`, `times`, `Sent`, `Sentdate`) VALUES
('18-AE-QT-001', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-002', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-020', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-021', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-022', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-023', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-024', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-025', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-027', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-029', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-029.Rev.1', 1, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-029.Rev.2', 2, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-030', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-030.Rev.1', 1, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-031', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-032', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-033', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-034', 0, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-035.Rev.0', 0, '2018-03-01 19:09:54', 0, NULL),
('18-AE-QT-035.Rev.1', 1, '2018-03-01 19:09:54', 1, '2018-03-03'),
('18-AE-QT-035.Rev.2', 2, '2018-07-13 22:49:47', 1, '2018-07-15'),
('18-AE-QT-036.Rev.0', 0, '2018-03-09 17:51:57', 0, NULL),
('18-AE-QT-037.Rev.0', 0, '2018-03-09 17:52:40', 0, NULL),
('18-AE-QT-038.Rev.0', 0, '2018-03-09 17:53:03', 0, NULL),
('18-AE-QT-039.Rev.0', 0, '2018-03-09 17:54:52', 0, NULL),
('18-AE-QT-040.Rev.0', 0, '2018-03-09 17:58:37', 0, NULL),
('18-AE-QT-041.Rev.0', 0, '2018-03-09 17:59:36', 0, NULL),
('18-AE-QT-042.Rev.0', 0, '2018-03-09 18:02:52', 0, NULL),
('18-AE-QT-043.Rev.0', 0, '2018-03-09 18:07:43', 0, NULL),
('18-AE-QT-044.Rev.0', 0, '2018-03-09 18:09:15', 0, NULL),
('18-AE-QT-045.Rev.0', 0, '2018-03-09 18:28:18', 0, NULL),
('18-AE-QT-046.Rev.0', 0, '2018-03-09 18:31:39', 0, NULL),
('18-AE-QT-047.Rev.0', 0, '2018-03-09 18:40:21', 0, NULL),
('18-AE-QT-048.Rev.0', 0, '2018-03-09 18:40:28', 0, NULL),
('18-AE-QT-049.Rev.0', 0, '2018-03-09 18:51:20', 0, NULL),
('18-AE-QT-049.Rev.1', 1, '2018-03-09 18:56:37', 0, '2018-03-09'),
('18-SC-QT-026', 0, '2018-03-01 19:09:54', 0, NULL),
('18-SC-QT-028', 0, '2018-03-01 19:09:54', 0, NULL),
('18-SC-QT-028.Rev.1', 1, '2018-03-01 19:09:54', 0, NULL),
('18-SC-QT-028.Rev.2', 2, '2018-03-01 19:09:54', 0, NULL),
('18-SC-QT-028.Rev.3', 3, '2018-03-01 19:09:54', 1, '2018-07-08'),
('18-SC-QT-028.Rev.4', 4, '2018-07-13 22:33:57', 1, '2018-07-15'),
('18-SC-QT-028.Rev.5', 5, '2018-07-13 22:40:22', 0, NULL);

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
('18-AE-QT-030.Rev.1', 1),
('18-AE-QT-035.Rev.1', 2),
('18-AE-QT-049.Rev.1', 3),
('18-SC-QT-028.Rev.3', 4);

-- --------------------------------------------------------

--
-- Table structure for table `quotationdetails_awin`
--

DROP TABLE IF EXISTS `quotationdetails_awin`;
CREATE TABLE `quotationdetails_awin` (
  `Sno` int(11) NOT NULL,
  `Des` varchar(1000) DEFAULT NULL,
  `quantity` varchar(20) DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `total` bigint(20) DEFAULT NULL,
  `qno` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quotationdetails_awin`
--

INSERT INTO `quotationdetails_awin` (`Sno`, `Des`, `quantity`, `unit`, `total`, `qno`) VALUES
(0, 'dsfgsdfgsdfgsdfg', '34534', 34534, 345345, '18-AE-QT-025'),
(1, 'sdfgsdsdfgsdsdfg', '345345', 345345, 345345, '18-AE-QT-025'),
(1, 'bub send plssdfsdfsd3', '3', 33, 342432, '18-AE-QT-029'),
(1, 'bub send pussylssdfsdfsd3', '3', 33, 342432, '18-AE-QT-029.Rev.1'),
(1, 'bub send pucciylssdfsdfsd3', '3', 33, 342432, '18-AE-QT-029.Rev.2'),
(1, 'dfgdsfg', '3453', 345, 565, '18-AE-QT-030'),
(1, 'dfgdsfg', '3453', 345, 565, '18-AE-QT-030.Rev.1'),
(1, 'ewrtwe', '345', 3453, 34, '18-AE-QT-035.Rev.0'),
(1, 'ewrtwe', '345', 3453, 34, '18-AE-QT-035.Rev.1'),
(1, 'Chute - Blasting & Painting\n\nScope of Work:\nChute - Topside \"sliding part\" entire surface to be blast at SA2.5 and applictaion of two coat painiting system as per Aquatic painting spec.\nChute external - side wall and undesr side will be spot blast at SA2.5 and application of two caot painting system as per Aquatic painting spec.\n\n02 Nos of base frame will be blast at SA2.5 and application of two coat painting system as per Aquatic painiting spec', '01 Lots', 2760, 2670, '18-AE-QT-035.Rev.2'),
(1, 'qwertyuia', '45', 6, 78, '18-AE-QT-049.Rev.0'),
(1, 'qwertyuia', '45', 6, 79, '18-AE-QT-049.Rev.1'),
(2, 'dsfgdfgdfg', '345345345', 345345345, 345345345, '18-AE-QT-025'),
(2, 'bebgfbdrb', '2', 4, 42523432, '18-AE-QT-029'),
(2, 'bebgfbdrb', '2', 4, 42523432, '18-AE-QT-029.Rev.1'),
(2, 'bebgfbdrb', '2', 4, 42523432, '18-AE-QT-029.Rev.2'),
(2, 'dfgsfdg', '43534', 345, 3245, '18-AE-QT-030'),
(2, 'dfgsfdg', '43534', 345, 3245, '18-AE-QT-030.Rev.1'),
(2, 'sdfgsdf', '4353', 34534, 435, '18-AE-QT-035.Rev.1'),
(2, 'Chute - Transportation\nTransportation to and from Loyang Crescent to Tuas \"40ft Trailer\".\nLoading / Unloading at Aquatic yard by client.\nloading / Unloading at Awin eEng yard by Awin Eng.', '2', 200, 400, '18-AE-QT-035.Rev.2'),
(2, 'rtyu', '5', 67, 78, '18-AE-QT-049.Rev.0'),
(2, 'rtyu', '5', 67, 78, '18-AE-QT-049.Rev.1'),
(3, 'sdfgsdfgsdfgsdf', '34534534', 34534534, 34534534, '18-AE-QT-025'),
(3, 'grvrfrvbeverbmorreeeeee', '4', 13, 54322234, '18-AE-QT-029'),
(3, 'grvrfrvbeverbmorreeeeee', '4', 13, 54322234, '18-AE-QT-029.Rev.1'),
(3, 'grvrfrvbeverbmorreeeeee', '4', 13, 54322234, '18-AE-QT-029.Rev.2'),
(3, 'sdfas', '3454', 34534, 45634, '18-AE-QT-030'),
(3, 'sdfas', '3454', 34534, 45634, '18-AE-QT-030.Rev.1'),
(3, 'sdasdas', '23423', 2342342, 34, '18-AE-QT-035.Rev.1'),
(4, 'doog', '4', 535, 543255, '18-AE-QT-029.Rev.1'),
(4, 'doog', '4', 535, 543255, '18-AE-QT-029.Rev.2'),
(4, 'dsgfdf', '3456', 6543634, 45645, '18-AE-QT-030');

-- --------------------------------------------------------

--
-- Table structure for table `quotationdetails_steels`
--

DROP TABLE IF EXISTS `quotationdetails_steels`;
CREATE TABLE `quotationdetails_steels` (
  `Sno` varchar(11) NOT NULL,
  `Pos` varchar(150) NOT NULL DEFAULT '',
  `NormalRate` varchar(100) DEFAULT NULL,
  `BeyondNormalRate` varchar(100) DEFAULT NULL,
  `Holidays` varchar(100) DEFAULT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `qno` varchar(25) NOT NULL,
  `RowOrder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quotationdetails_steels`
--

INSERT INTO `quotationdetails_steels` (`Sno`, `Pos`, `NormalRate`, `BeyondNormalRate`, `Holidays`, `Remarks`, `qno`, `RowOrder`) VALUES
('1', 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.1', 1),
('1', 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.2', 2),
('1', 'doggy style', 'asdfasdfssd', 'asdf324234', 'd', 'hdfghdfgh', '18-SC-QT-028', 3),
('1', 'Skilled / Certified Blaster', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 291),
('1', 'Skilled / Certified Blaster', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 335),
('1', 'Skilled / Certified Blaster', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 324),
('1', 'Skilled Fitter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 285),
('1', 'Skilled Fitter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 329),
('1', 'Skilled Fitter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 318),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd', 'dfghd23', 'dfg', 'dfghdfghdfgh', '18-SC-QT-028', 6),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.1', 7),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.2', 8),
('2', 'Skilled / Certified Painter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 292),
('2', 'Skilled / Certified Painter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 336),
('2', 'Skilled / Certified Painter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 325),
('2', 'Skilled /Certified Welder', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 286),
('2', 'Skilled /Certified Welder', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 330),
('2', 'Skilled /Certified Welder', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 319),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf', 'sdfgsd', 'sdfgsdfg345345345', '18-SC-QT-028', 11),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.1', 12),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.2', 13),
('3', 'Painting Helper', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 293),
('3', 'Painting Helper', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 337),
('3', 'Painting Helper', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 326),
('3', 'Skilled Grinder / General Workers', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 287),
('3', 'Skilled Grinder / General Workers', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 331),
('3', 'Skilled Grinder / General Workers', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 320),
('4', 'Power Tool Men', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 294),
('4', 'Power Tool Men', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 338),
('4', 'Power Tool Men', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', 'Power', '18-SC-QT-028.Rev.5', 327),
('4', 'Supervisor (Steel & Piping)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 288),
('4', 'Supervisor (Steel & Piping)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 332),
('4', 'Supervisor (Steel & Piping)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 321),
('5', 'Nace Inspector Level.2', '$18.00', '1.5 x Normal Rate', '5 x Normal Rate', '', '18-SC-QT-028.Rev.3', 295),
('5', 'Nace Inspector Level.2', '$18.00', '1.5 x Normal Rate', '5 x Normal Rate', 'Nace', '18-SC-QT-028.Rev.4', 339),
('5', 'Nace Inspector Level.2', '$18.00', '1.5 x Normal Rate', '5 x Normal Rate', 'Nace', '18-SC-QT-028.Rev.5', 328),
('5', 'QC Inspector / Engineer (AWS)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 289),
('5', 'QC Inspector / Engineer (AWS)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 333),
('5', 'QC Inspector / Engineer (AWS)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 322),
('6', 'Safety Supervisor', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 290),
('6', 'Safety Supervisor', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.4', 334),
('6', 'Safety Supervisor', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.5', 323);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`CmpName`);

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
-- Indexes for table `invoice_payments`
--
ALTER TABLE `invoice_payments`
  ADD PRIMARY KEY (`DateEntry`) USING BTREE,
  ADD KEY `INo` (`INo`);

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
-- Indexes for table `purchase_enquiry`
--
ALTER TABLE `purchase_enquiry`
  ADD PRIMARY KEY (`Eqno`),
  ADD KEY `SID` (`SID`);

--
-- Indexes for table `purchase_eprel`
--
ALTER TABLE `purchase_eprel`
  ADD PRIMARY KEY (`Eqno`,`Pjno`),
  ADD KEY `Pjno` (`Pjno`);

--
-- Indexes for table `purchase_invoice`
--
ALTER TABLE `purchase_invoice`
  ADD PRIMARY KEY (`Ino`);

--
-- Indexes for table `purchase_invoicepayments`
--
ALTER TABLE `purchase_invoicepayments`
  ADD PRIMARY KEY (`Ino`);

--
-- Indexes for table `purchase_pirel`
--
ALTER TABLE `purchase_pirel`
  ADD PRIMARY KEY (`Po_NO`,`Ino`),
  ADD KEY `Ino` (`Ino`);

--
-- Indexes for table `purchase_po`
--
ALTER TABLE `purchase_po`
  ADD PRIMARY KEY (`Po_NO`);

--
-- Indexes for table `purchase_potabledetails`
--
ALTER TABLE `purchase_potabledetails`
  ADD PRIMARY KEY (`RC`,`Po_NO`),
  ADD KEY `RC` (`RC`);

--
-- Indexes for table `purchase_qprel`
--
ALTER TABLE `purchase_qprel`
  ADD PRIMARY KEY (`Qno`,`Po_NO`,`Eqno`),
  ADD KEY `Po_NO` (`Po_NO`),
  ADD KEY `Eqno` (`Eqno`);

--
-- Indexes for table `purchase_quotation`
--
ALTER TABLE `purchase_quotation`
  ADD PRIMARY KEY (`Qno`,`EQno`),
  ADD KEY `rqef` (`EQno`);

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
  ADD PRIMARY KEY (`Sno`,`Pos`,`qno`),
  ADD UNIQUE KEY `UniqueRow` (`RowOrder`),
  ADD KEY `qno` (`qno`),
  ADD KEY `Pos` (`Pos`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `purchase_potabledetails`
--
ALTER TABLE `purchase_potabledetails`
  MODIFY `RC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `quotationdetails_steels`
--
ALTER TABLE `quotationdetails_steels`
  MODIFY `RowOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=340;
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
-- Constraints for table `purchase_enquiry`
--
ALTER TABLE `purchase_enquiry`
  ADD CONSTRAINT `purchase_enquiry_ibfk_1` FOREIGN KEY (`SID`) REFERENCES `customer` (`CID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `purchase_eprel`
--
ALTER TABLE `purchase_eprel`
  ADD CONSTRAINT `purchase_eprel_ibfk_1` FOREIGN KEY (`Eqno`) REFERENCES `purchase_enquiry` (`Eqno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_eprel_ibfk_2` FOREIGN KEY (`Pjno`) REFERENCES `product` (`PjNo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchase_pirel`
--
ALTER TABLE `purchase_pirel`
  ADD CONSTRAINT `purchase_pirel_ibfk_1` FOREIGN KEY (`Ino`) REFERENCES `purchase_invoice` (`Ino`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_pirel_ibfk_2` FOREIGN KEY (`Po_NO`) REFERENCES `purchase_po` (`Po_NO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchase_qprel`
--
ALTER TABLE `purchase_qprel`
  ADD CONSTRAINT `purchase_qprel_ibfk_1` FOREIGN KEY (`Po_NO`) REFERENCES `purchase_po` (`Po_NO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_qprel_ibfk_2` FOREIGN KEY (`Qno`) REFERENCES `purchase_quotation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_qprel_ibfk_3` FOREIGN KEY (`Eqno`) REFERENCES `purchase_enquiry` (`Eqno`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `purchase_quotation`
--
ALTER TABLE `purchase_quotation`
  ADD CONSTRAINT `rqef` FOREIGN KEY (`EQno`) REFERENCES `purchase_enquiry` (`Eqno`) ON DELETE CASCADE ON UPDATE CASCADE;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
