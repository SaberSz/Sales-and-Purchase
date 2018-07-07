-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 07, 2018 at 09:13 PM
-- Server version: 5.6.38
-- PHP Version: 7.2.1

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
  `email` varchar(30) NOT NULL,
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
(54, 'banaglore', 'tom', 'tom@gmail.com', '123456789');

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
('132123', '18-SC-QT-028.Rev.3', '2018-02-18', 'Steels', 47);

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
('1802-AE-INV-002', 20905, NULL, NULL, 'abcd', '345345', '40', 1463.35, 0, 0),
('1804-SC-INV-001', 26814, NULL, NULL, 'Voma ha', '234523454', '30', 2145.12, 0, 0);

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
(1, 'Sand Blasting / Coating of AF SN63177', '1', 4469, 4469, '1804-SC-INV-001'),
(2, 'asdf fdsf sadf', '37', 565, 20905, '1802-AE-INV-002'),
(2, 'Sand Blasting / Coating of AF SN63178', '2', 4469, 8938, '1804-SC-INV-001');

-- --------------------------------------------------------

--
-- Table structure for table `Invoice_Payments`
--

DROP TABLE IF EXISTS `Invoice_Payments`;
CREATE TABLE `Invoice_Payments` (
  `INo` varchar(15) NOT NULL,
  `Amount` double NOT NULL DEFAULT '0',
  `DatePaid` date NOT NULL,
  `Late` int(11) NOT NULL DEFAULT '0'
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
('18-SC-QT-028.Rev.3', 3, '2018-03-01 19:09:54', 1, '2018-03-03');

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
(1, 'ewrtwe', 345, 3453, 34, '18-AE-QT-035.Rev.0'),
(1, 'ewrtwe', 345, 3453, 34, '18-AE-QT-035.Rev.1'),
(1, 'qwertyuia', 45, 6, 78, '18-AE-QT-049.Rev.0'),
(1, 'qwertyuia', 45, 6, 79, '18-AE-QT-049.Rev.1'),
(2, 'dsfgdfgdfg', 345345345, 345345345, 345345345, '18-AE-QT-025'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029.Rev.1'),
(2, 'bebgfbdrb', 2, 4, 42523432, '18-AE-QT-029.Rev.2'),
(2, 'dfgsfdg', 43534, 345, 3245, '18-AE-QT-030'),
(2, 'dfgsfdg', 43534, 345, 3245, '18-AE-QT-030.Rev.1'),
(2, 'sdfgsdf', 4353, 34534, 435, '18-AE-QT-035.Rev.1'),
(2, 'rtyu', 5, 67, 78, '18-AE-QT-049.Rev.0'),
(2, 'rtyu', 5, 67, 78, '18-AE-QT-049.Rev.1'),
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
-- Table structure for table `QuotationDetails_Steels`
--

DROP TABLE IF EXISTS `QuotationDetails_Steels`;
CREATE TABLE `QuotationDetails_Steels` (
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
-- Dumping data for table `QuotationDetails_Steels`
--

INSERT INTO `QuotationDetails_Steels` (`Sno`, `Pos`, `NormalRate`, `BeyondNormalRate`, `Holidays`, `Remarks`, `qno`, `RowOrder`) VALUES
('1', 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.1', 1),
('1', 'dogg', 'asdfasdfssd', 'asdf324234dfgdfgdfg43563456', 'ddfgdfgdfgdfgdfg', 'hdfghdfgh', '18-SC-QT-028.Rev.2', 2),
('1', 'doggy style', 'asdfasdfssd', 'asdf324234', 'd', 'hdfghdfgh', '18-SC-QT-028', 3),
('1', 'Skilled / Certified Blaster', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 159),
('1', 'Skilled Fitter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 153),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd', 'dfghd23', 'dfg', 'dfghdfghdfgh', '18-SC-QT-028', 6),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.1', 7),
('2', 'dfghdfghdfghdfdfgsdfgsdfg34534534', 'dfghd34563456354', 'dfghd2334563456', 'dfg456456346534563sdfgdfgs', 'dfghdfghdfgh', '18-SC-QT-028.Rev.2', 8),
('2', 'Skilled / Certified Painter', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 160),
('2', 'Skilled /Certified Welder', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 154),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf', 'sdfgsd', 'sdfgsdfg345345345', '18-SC-QT-028', 11),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.1', 12),
('3', 'dsgsdfgsdfgsdfgsdfgs3453453453', 'sdfgsdfgssssdsfgdfgsdfgsdfg345345345', 'sdf546345634', 'sdfgsdgfhdfghfdgh', 'sdfgsdfg345345345sdfgs', '18-SC-QT-028.Rev.2', 13),
('3', 'Painting Helper', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 161),
('3', 'Skilled Grinder / General Workers', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 155),
('4', 'Power Tool Men', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 162),
('4', 'Supervisor (Steel & Piping)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 156),
('5', 'Nace Inspector Level.2', '$18.00', '1.5 x Normal Rate', '3 x Normal Rate', '', '18-SC-QT-028.Rev.3', 163),
('5', 'QC Inspector / Engineer (AWS)', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 157),
('6', 'Safety Supervisor', '$18.00', '1.5 x Normal Rate', '2 x Normal Rate', '', '18-SC-QT-028.Rev.3', 158);

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
-- Indexes for table `Invoice_Payments`
--
ALTER TABLE `Invoice_Payments`
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
-- Indexes for table `quotationdetails_awin`
--
ALTER TABLE `quotationdetails_awin`
  ADD PRIMARY KEY (`Sno`,`qno`),
  ADD KEY `qno` (`qno`);

--
-- Indexes for table `QuotationDetails_Steels`
--
ALTER TABLE `QuotationDetails_Steels`
  ADD PRIMARY KEY (`Sno`,`Pos`,`qno`),
  ADD UNIQUE KEY `UniqueRow` (`RowOrder`),
  ADD KEY `qno` (`qno`),
  ADD KEY `Pos` (`Pos`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `QuotationDetails_Steels`
--
ALTER TABLE `QuotationDetails_Steels`
  MODIFY `RowOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

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
-- Constraints for table `Invoice_Payments`
--
ALTER TABLE `Invoice_Payments`
  ADD CONSTRAINT `invoice_payments_ibfk_1` FOREIGN KEY (`INo`) REFERENCES `invoice` (`INo`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Constraints for table `QuotationDetails_Steels`
--
ALTER TABLE `QuotationDetails_Steels`
  ADD CONSTRAINT `quotationdetails_steels_ibfk_1` FOREIGN KEY (`qno`) REFERENCES `qoutation` (`Qno`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
