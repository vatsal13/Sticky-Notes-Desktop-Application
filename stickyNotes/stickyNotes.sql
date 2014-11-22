-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 28, 2014 at 07:10 PM
-- Server version: 5.5.36-MariaDB
-- PHP Version: 5.5.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stickyNotes`
--

-- --------------------------------------------------------

--
-- Table structure for table `flashCard`
--

CREATE TABLE IF NOT EXISTS `flashCard` (
  `userId` int(11) NOT NULL,
  `packId` int(11) NOT NULL,
  `cardId` int(11) NOT NULL AUTO_INCREMENT,
  `front` varchar(1000) NOT NULL,
  `back` varchar(1000) NOT NULL,
  PRIMARY KEY (`cardId`),
  KEY `userId` (`userId`,`packId`),
  KEY `packId` (`packId`),
  KEY `cardId` (`cardId`),
  KEY `cardId_2` (`cardId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `flashCard`
--

INSERT INTO `flashCard` (`userId`, `packId`, `cardId`, `front`, `back`) VALUES
(21, 9, 26, 'Dummy card...Edit it', 'Place Your Answer'),
(23, 10, 29, 'Your dummy card..Edit it', 'Place Your Answer '),
(15, 3, 30, 'Your dummy card..Edit it', 'Place Your Answer '),
(15, 3, 31, '1', 'first');

-- --------------------------------------------------------

--
-- Table structure for table `flashCardPack`
--

CREATE TABLE IF NOT EXISTS `flashCardPack` (
  `userId` int(11) NOT NULL,
  `packId` int(11) NOT NULL AUTO_INCREMENT,
  `packName` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  UNIQUE KEY `packId` (`packId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `flashCardPack`
--

INSERT INTO `flashCardPack` (`userId`, `packId`, `packName`) VALUES
(15, 3, 'Vocabulary'),
(21, 9, 'Vocab'),
(23, 10, 'vocab');

-- --------------------------------------------------------

--
-- Table structure for table `Notes`
--

CREATE TABLE IF NOT EXISTS `Notes` (
  `userId` int(11) NOT NULL,
  `noteId` int(20) NOT NULL AUTO_INCREMENT,
  `x` int(11) NOT NULL DEFAULT '0',
  `y` int(11) NOT NULL DEFAULT '0',
  `priority` int(11) NOT NULL DEFAULT '0',
  `content` varchar(1000) NOT NULL DEFAULT '0',
  `deadline` varchar(100) NOT NULL DEFAULT '0',
  `notified` int(10) NOT NULL DEFAULT '0',
  UNIQUE KEY `noteId` (`noteId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53 ;

--
-- Dumping data for table `Notes`
--

INSERT INTO `Notes` (`userId`, `noteId`, `x`, `y`, `priority`, `content`, `deadline`, `notified`) VALUES
(15, 32, 890, 306, 0, 'THis i sefsdfkjds', '0', 0),
(15, 34, 49, 196, 0, '0', '0', 0),
(15, 35, 395, 414, 0, '0', '0', 0),
(15, 36, 764, 27, 0, '0', '2014-05-01', 1),
(16, 39, 0, 0, 0, '0', '0', 0),
(21, 44, 556, 156, 0, 'Hie this is my notes', '2014-04-29', 1),
(21, 45, 908, 292, 0, 'This is my second note.', '2014-04-29', 1),
(22, 46, 556, 156, 0, '0', '0', 0),
(15, 47, 774, 37, 0, '0', '0', 0),
(15, 48, 405, 414, 0, '0', '0', 0),
(15, 49, 415, 414, 0, '0', '0', 0),
(15, 50, 425, 414, 0, '0', '0', 0),
(15, 51, 435, 414, 0, '0', '0', 0),
(23, 52, 556, 156, 0, '0', '0', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Properties`
--

CREATE TABLE IF NOT EXISTS `Properties` (
  `userId` int(11) NOT NULL,
  `fontFamily` varchar(100) NOT NULL DEFAULT 'Serif',
  `fontSize` int(11) NOT NULL DEFAULT '12',
  `fontColor` varchar(100) NOT NULL DEFAULT 'rgb(0,0,0)',
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`userId`,`name`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Properties`
--

INSERT INTO `Properties` (`userId`, `fontFamily`, `fontSize`, `fontColor`, `name`) VALUES
(15, 'Denemo', 14, 'rgb(26,51,153)', 'cards'),
(15, 'Courier 10 Pitch', 22, 'rgb(153,77,102)', 'notes'),
(16, 'Serif', 12, 'rgb(0,0,0)', 'cards'),
(16, 'Times New Roman', 12, 'rgb(0,0,0)', 'notes'),
(21, 'Serif', 16, 'rgb(153,102,153)', 'cards'),
(21, 'Carlito', 18, 'rgb(230,77,77)', 'notes'),
(22, 'Serif', 12, 'rgb(0,0,0)', 'cards'),
(22, 'Serif', 12, 'rgb(0,0,0)', 'notes'),
(23, 'DejaVu Serif', 12, 'rgb(51,77,179)', 'cards'),
(23, 'Serif', 12, 'rgb(0,0,0)', 'notes');

-- --------------------------------------------------------

--
-- Table structure for table `Reminder`
--

CREATE TABLE IF NOT EXISTS `Reminder` (
  `userId` int(11) NOT NULL,
  `timeForNotification` int(11) NOT NULL DEFAULT '1',
  `mailId` varchar(100) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_2` (`userId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Reminder`
--

INSERT INTO `Reminder` (`userId`, `timeForNotification`, `mailId`, `status`) VALUES
(15, 4, 'djthecorporate@gmail.com', 1),
(16, 1, 'deepdwi@gmail.com', 0),
(21, 3, 'veerKumar', 1),
(22, 1, 'jalajMinda', 0),
(23, 1, 'jalajMinda@gmail.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `mail_id` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `contact` varchar(20) NOT NULL,
  `password` varchar(20) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `mail_id` (`mail_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`userId`, `mail_id`, `contact`, `password`) VALUES
(15, 'djthecorporate@gmail.com', '8883141089', 'jainDilip460'),
(16, 'deepdwi@gmail.com', '8144654543', 'DeepJain460'),
(21, 'veerKumar', '387192379812', 'kumarVeer460'),
(22, 'jalajMinda', '823217389', 'jalajMinda123'),
(23, 'jalajMinda@gmail.com', '983712739812', 'mindaJalaj460');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `flashCard`
--
ALTER TABLE `flashCard`
  ADD CONSTRAINT `flashCard_ibfk_1` FOREIGN KEY (`packId`) REFERENCES `flashCardPack` (`packId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flashCard_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `flashCardPack`
--
ALTER TABLE `flashCardPack`
  ADD CONSTRAINT `flashCardPack_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Notes`
--
ALTER TABLE `Notes`
  ADD CONSTRAINT `Notes_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Properties`
--
ALTER TABLE `Properties`
  ADD CONSTRAINT `Properties_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Reminder`
--
ALTER TABLE `Reminder`
  ADD CONSTRAINT `Reminder_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
