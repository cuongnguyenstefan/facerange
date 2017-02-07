-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 07, 2017 at 12:47 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `facerance_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `basicinfo`
--

CREATE TABLE IF NOT EXISTS `basicinfo` (
  `componentid` int(20) NOT NULL,
  `city` varchar(50) NOT NULL,
  `job` varchar(50) NOT NULL,
  `from` varchar(50) NOT NULL,
  `age` int(4) NOT NULL,
  `basicinfoid` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`basicinfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `commentid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `postid` int(20) unsigned NOT NULL,
  `comment` text COLLATE utf8_bin NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `component`
--

CREATE TABLE IF NOT EXISTS `component` (
  `componentid` int(20) NOT NULL AUTO_INCREMENT,
  `componenettype` varchar(50) NOT NULL,
  `userid` int(20) NOT NULL,
  PRIMARY KEY (`componentid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `componentimage`
--

CREATE TABLE IF NOT EXISTS `componentimage` (
  `imageid` int(20) NOT NULL AUTO_INCREMENT,
  `componentid` int(20) NOT NULL,
  `image1` varchar(50) NOT NULL,
  `image2` varchar(500) NOT NULL,
  `image3` varchar(500) NOT NULL,
  PRIMARY KEY (`imageid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE IF NOT EXISTS `likes` (
  `likeid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `postid` int(20) unsigned NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`likeid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
  `postid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(20) unsigned NOT NULL,
  `post` text COLLATE utf8_bin NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`postid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Table structure for table `socialmedia`
--

CREATE TABLE IF NOT EXISTS `socialmedia` (
  `socialmediaid` int(20) NOT NULL AUTO_INCREMENT,
  `componentid` int(20) NOT NULL,
  `facebook` varchar(500) NOT NULL,
  `instagram` varchar(500) NOT NULL,
  `twitter` varchar(500) NOT NULL,
  `youtube` varchar(500) NOT NULL,
  PRIMARY KEY (`socialmediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dob` date DEFAULT NULL,
  `gender` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=9 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `fullname`, `email`, `password`, `datecreated`, `dateupdated`, `dob`, `gender`) VALUES
(8, 'henricharles', '2kkhenricharles@gmail.com', '12345', '2017-02-06 22:58:28', '2017-02-06 22:58:28', NULL, 'mal');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
