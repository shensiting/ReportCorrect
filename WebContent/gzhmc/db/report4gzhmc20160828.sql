/*
Navicat MySQL Data Transfer

Source Server         : report4gzhmc
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-08-28 20:03:31
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_college
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_experimental_test`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_experimental_test`;
CREATE TABLE `gy_report_experimental_test` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cExperimentName` varchar(50) DEFAULT NULL,
  `cExperimentEnglishName` varchar(50) DEFAULT NULL,
  `cExperimentTime` varchar(32) DEFAULT NULL,
  `cStatu` int(32) DEFAULT '0',
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gy_report_experimental_test
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_major
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_report`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_report`;
CREATE TABLE `gy_report_report` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cReportNum` varchar(32) DEFAULT NULL,
  `cScoreId` int(32) DEFAULT NULL,
  `cStudentId` int(32) DEFAULT NULL,
  `cStatus` int(4) DEFAULT NULL,
  `cPath` varchar(200) DEFAULT NULL,
  `cCreateTime` datetime DEFAULT NULL,
  `cPdfPath` varchar(200) DEFAULT NULL,
  `cProcess` varchar(300) DEFAULT NULL,
  `cQRcode` varchar(200) DEFAULT NULL,
  `cTeacherId` int(32) DEFAULT NULL,
  `cExperimentTextId` int(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_scoresheet`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_scoresheet`;
CREATE TABLE `gy_report_scoresheet` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTherory` double(4,0) DEFAULT NULL,
  `cReagen` double(4,0) DEFAULT NULL,
  `cInserument` double(4,0) DEFAULT NULL,
  `cExperiment` double(4,0) DEFAULT NULL,
  `cLabresult` double(4,0) DEFAULT NULL,
  `cSum` double(4,0) DEFAULT NULL,
  `cComment` varchar(300) DEFAULT NULL,
  `cCreateTime` datetime DEFAULT NULL,
  `cConclution` double(32,0) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_scoresheet
-- ----------------------------
INSERT INTO `gy_report_scoresheet` VALUES ('41', '3', '3', '3', '3', '3', '89', '撒大声地', '2016-08-28 10:23:30', '3');

-- ----------------------------
-- Table structure for `gy_report_student`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_student`;
CREATE TABLE `gy_report_student` (
  `cUserId` int(32) NOT NULL AUTO_INCREMENT,
  `cName` varchar(12) DEFAULT NULL,
  `cStudentNumber` varchar(12) DEFAULT NULL,
  `cGradeId` int(32) unsigned zerofill DEFAULT '00000000000000000000000000000001',
  `cPicturePath` varchar(300) DEFAULT NULL,
  `cIDNumber` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher`;
CREATE TABLE `gy_report_teacher` (
  `cUserId` int(32) NOT NULL AUTO_INCREMENT,
  `cName` varchar(12) DEFAULT NULL,
  `cTeacherId` varchar(12) DEFAULT NULL,
  `cCollegeId` int(32) DEFAULT NULL,
  `cVerify` int(12) DEFAULT '0',
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_teacher_experimental`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher_experimental`;
CREATE TABLE `gy_report_teacher_experimental` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTeacherId` int(32) NOT NULL,
  `cExperimentalTestId` int(32) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_teacher_experimental
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
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('1', '1000', '1000', '1', '2016-08-28 10:37:55');
INSERT INTO `gy_report_user` VALUES ('2', '1001', '1001', '1', '2016-08-28 10:38:25');
