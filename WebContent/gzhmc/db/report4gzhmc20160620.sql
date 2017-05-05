/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-06-20 22:37:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gy_report_college`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_college`;
CREATE TABLE `gy_report_college` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cCollegeName` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_college
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_course`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_course`;
CREATE TABLE `gy_report_course` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cCourseName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_course
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_department`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_department`;
CREATE TABLE `gy_report_department` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cCollegeId` int(32) DEFAULT NULL,
  `cMajorId` int(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_department
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_grade`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_grade`;
CREATE TABLE `gy_report_grade` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cYearClass` varchar(12) NOT NULL,
  `cMajorId` int(32) NOT NULL,
  `cClass` varchar(12) NOT NULL,
  `cCollegeId` int(32) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_major`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_major`;
CREATE TABLE `gy_report_major` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cMajorName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_major
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_report`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_report`;
CREATE TABLE `gy_report_report` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cReportName` varchar(32) DEFAULT NULL,
  `cScoreId` int(32) DEFAULT NULL,
  `cStudentId` int(32) DEFAULT NULL,
  `cTeacherId` int(32) DEFAULT NULL,
  `cStatus` int(4) DEFAULT NULL,
  `cPath` varchar(32) DEFAULT NULL,
  `cCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_scoresheet`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_scoresheet`;
CREATE TABLE `gy_report_scoresheet` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cLabprogress` double(4,0) DEFAULT NULL,
  `cTherory` double(4,0) DEFAULT NULL,
  `cReagen` double(4,0) DEFAULT NULL,
  `cInserument` double(4,0) DEFAULT NULL,
  `cExperiment` double(4,0) DEFAULT NULL,
  `cLabresult` double(4,0) DEFAULT NULL,
  `cSum` double(4,0) DEFAULT NULL,
  `cComment` varchar(300) DEFAULT NULL,
  `cCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_scoresheet
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_student`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_student`;
CREATE TABLE `gy_report_student` (
  `cUserId` int(32) NOT NULL,
  `cName` varchar(12) DEFAULT NULL,
  `cStudentNumber` varchar(12) DEFAULT NULL,
  `cGradeId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher`;
CREATE TABLE `gy_report_teacher` (
  `cUserId` int(32) DEFAULT NULL,
  `cName` varchar(12) DEFAULT NULL,
  `cTeacherId` varchar(12) DEFAULT NULL,
  `cDepartmentId` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_user`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_user`;
CREATE TABLE `gy_report_user` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cUserName` varchar(12) NOT NULL,
  `cPassword` varchar(12) NOT NULL,
  `cRole` int(4) NOT NULL,
  `cCreateTime` datetime NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
