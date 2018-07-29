-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 29, 2018 at 08:12 AM
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
  `Address` varchar(10000) NOT NULL,
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
('Awin Engineering Pte. Ltd', 'Regd.office: No.12, Tuas View Place,\nSingapore. 637864 \nWorks: No. 109, Tuas South Avenue 8, \nSingapore 637037', '+65 6778 8271', 7, '+65 6265 768333', '201012187G', '201012187G', 'bubnows', 'gwgrw'),
('Steel Coat PTE LTD', 'No. 12, Tuas View Place, Singapore - 637864.\nNo.109 Tuas South Ave.8 Singapore -637037', '+65 6265 9476', 8, '+65 6265 7685', '201410749G', '201410749G', 'Enquires@steelcoat.com.sg', 'www.steelcoat.com.sg'),
('Steel Coat Pte. Ltd', 'No. 12, Tuas View Place, Singapore - 637864.\nNo.109 Tuas South Ave.8 Singapore -637037', '+65 6265 9476', 8, '+65 6265 7685', '201410749G', '201410749G', 'Enquires@steelcoat.com.sg', 'www.steelcoat.com.sg');

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
(1, '#10, 1str Cross Road,\nLakappa Layout, Singapura Layout,\nNear Singapura Bus Stand , MS Palya \nBangalore - 560097', 'Aman Gupta', 'aman.iv0012@gmail.com', '9886598733'),
(2, '#10, 1str Cross Road,\nLakappa Layout, Singapura Layout,\nNear Singapura Bus Stand , MS Palya \nBangalore - 560097', 'Aman', 'aman.iv0012@gmail.com', '9480221970');

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
('amans1', '2018-07-26', 'Awin', '1 kg steel parcel \n1 litre of sprite and pepsi', 1);

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
('aman0', '2017-07-26', 'Awin', 'In need of spaceship to send Booms(:()) home', 2, 'lack of man power', '2018-07-26 06:23:19');

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
('amans1', '18-AE-QT-001.Rev.0', '2018-07-26', 'Awin', 1),
('amans1', '18-AE-QT-001.Rev.1', '2018-07-26', 'Awin', 1),
('amans1', '18-AE-QT-001.Rev.2', '2018-07-26', 'Awin', 1);

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
  `Des` varchar(5000) NOT NULL DEFAULT 'None',
  `Comp` tinyint(1) NOT NULL DEFAULT '0',
  `Compdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Table structure for table `purchase_eprel`
--

DROP TABLE IF EXISTS `purchase_eprel`;
CREATE TABLE `purchase_eprel` (
  `Eqno` varchar(25) NOT NULL,
  `Pjno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoice`
--

DROP TABLE IF EXISTS `purchase_invoice`;
CREATE TABLE `purchase_invoice` (
  `Ino` varchar(100) NOT NULL,
  `AmtwoGST` double NOT NULL,
  `PaymentTerm` int(11) DEFAULT NULL,
  `AmtwithGST` double NOT NULL,
  `date_recv` date NOT NULL,
  `paid` tinyint(4) NOT NULL DEFAULT '0',
  `amtpaid` double NOT NULL DEFAULT '0',
  `Location` varchar(1000) DEFAULT NULL,
  `PayDueDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoicepayments`
--

DROP TABLE IF EXISTS `purchase_invoicepayments`;
CREATE TABLE `purchase_invoicepayments` (
  `Ino` varchar(100) NOT NULL,
  `paidDate` date NOT NULL,
  `amount` double NOT NULL,
  `Timestmp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Late` int(11) NOT NULL DEFAULT '0'
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
  `GST` double DEFAULT NULL,
  `Rate` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Sentdate` date DEFAULT NULL,
  `Delivery` varchar(100) DEFAULT '-',
  `Terms` varchar(100) DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `qoutation`
--

INSERT INTO `qoutation` (`Qno`, `RevNo`, `times`, `Sent`, `Sentdate`, `Delivery`, `Terms`) VALUES
('18-AE-QT-001.Rev.0', 0, '2018-07-26 06:10:45', 1, '2018-07-26', '-', '-'),
('18-AE-QT-001.Rev.1', 1, '2018-07-28 11:31:31', 1, '2018-07-28', '-', '-'),
('18-AE-QT-001.Rev.2', 2, '2018-07-29 07:17:50', 1, '2018-07-29', '12 weeks', '10 days');

-- --------------------------------------------------------

--
-- Table structure for table `qprel`
--

DROP TABLE IF EXISTS `qprel`;
CREATE TABLE `qprel` (
  `Qno` varchar(25) NOT NULL,
  `PjNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(1, 'Fresh Steel', '1 kg', 30, 30, '18-AE-QT-001.Rev.0'),
(1, 'Fresh Steel', '1 kg', 30, 30, '18-AE-QT-001.Rev.1'),
(1, 'Fresh Steel', '1 kg', 30, 30, '18-AE-QT-001.Rev.2'),
(2, 'Sprite', '1 litre', 45, 45, '18-AE-QT-001.Rev.0'),
(2, 'Sprites', '2 litre', 45, 90, '18-AE-QT-001.Rev.1'),
(2, 'Sprites', '2 litre', 45, 90, '18-AE-QT-001.Rev.2'),
(3, ' Pepsi or Coke', '1 litre', 50, 50, '18-AE-QT-001.Rev.0'),
(3, ' Pepsi or Coke', '1 litre', 50, 50, '18-AE-QT-001.Rev.1'),
(3, ' Pepsi or Coke', '1 litre', 50, 50, '18-AE-QT-001.Rev.2'),
(4, 'Fanta', '1 litre', 300, 300, '18-AE-QT-001.Rev.0'),
(4, 'Fanta', '1 litre', 300, 300, '18-AE-QT-001.Rev.1'),
(4, 'Fanta', '1 litre', 300, 300, '18-AE-QT-001.Rev.2');

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
  ADD PRIMARY KEY (`Timestmp`),
  ADD KEY `Ino` (`Ino`),
  ADD KEY `Ino_2` (`Ino`);

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
  MODIFY `RC` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `quotationdetails_steels`
--
ALTER TABLE `quotationdetails_steels`
  MODIFY `RowOrder` int(11) NOT NULL AUTO_INCREMENT;
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
-- Constraints for table `purchase_invoicepayments`
--
ALTER TABLE `purchase_invoicepayments`
  ADD CONSTRAINT `purchase_invoicepayments_ibfk_1` FOREIGN KEY (`Ino`) REFERENCES `purchase_invoice` (`Ino`) ON DELETE CASCADE ON UPDATE CASCADE;

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
