/*
Navicat MySQL Data Transfer

Source Server         : report4gzhmc
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-08-07 21:53:04
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_college
-- ----------------------------
INSERT INTO `gy_report_college` VALUES ('1', '临床三院');
INSERT INTO `gy_report_college` VALUES ('2', '临床学院');
INSERT INTO `gy_report_college` VALUES ('3', '软件学院');
INSERT INTO `gy_report_college` VALUES ('4', '土木工程学院');
INSERT INTO `gy_report_college` VALUES ('5', '大数据学院');
INSERT INTO `gy_report_college` VALUES ('6', 'Web学院');

-- ----------------------------
-- Table structure for `gy_report_course`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_course`;
CREATE TABLE `gy_report_course` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cCourseName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_course
-- ----------------------------
INSERT INTO `gy_report_course` VALUES ('1', '数据结构');
INSERT INTO `gy_report_course` VALUES ('2', '高等数学');

-- ----------------------------
-- Table structure for `gy_report_department`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_department`;
CREATE TABLE `gy_report_department` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cCollegeId` int(32) DEFAULT NULL,
  `cMajorId` int(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_department
-- ----------------------------
INSERT INTO `gy_report_department` VALUES ('1', '3', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade
-- ----------------------------
INSERT INTO `gy_report_grade` VALUES ('1', '2016级', '1', '（1）班', '1');
INSERT INTO `gy_report_grade` VALUES ('2', '2011 级', '18', '3 班', '6');
INSERT INTO `gy_report_grade` VALUES ('6', '2014级', '3', '（2）班', '5');
INSERT INTO `gy_report_grade` VALUES ('10', '2013 级', '14', '（3 ）班', '3');

-- ----------------------------
-- Table structure for `gy_report_major`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_major`;
CREATE TABLE `gy_report_major` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cMajorName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_major
-- ----------------------------
INSERT INTO `gy_report_major` VALUES ('2', '生物工程');
INSERT INTO `gy_report_major` VALUES ('3', '信息管理与信息系统');
INSERT INTO `gy_report_major` VALUES ('4', '护理');
INSERT INTO `gy_report_major` VALUES ('6', '生物技术');
INSERT INTO `gy_report_major` VALUES ('12', '生物技术');
INSERT INTO `gy_report_major` VALUES ('14', '市场营销');
INSERT INTO `gy_report_major` VALUES ('15', '护理');
INSERT INTO `gy_report_major` VALUES ('20', '测试');
INSERT INTO `gy_report_major` VALUES ('21', '添加测试');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------
INSERT INTO `gy_report_report` VALUES ('1', '测试名字2', '1', '3', '2', '2', 'c:t/er/j.jpg', '2016-08-03 22:28:49');

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
  `cUserId` int(32) NOT NULL AUTO_INCREMENT,
  `cName` varchar(12) DEFAULT NULL,
  `cStudentNumber` varchar(12) DEFAULT NULL,
  `cGradeId` int(32) unsigned zerofill DEFAULT '00000000000000000000000000000001',
  `cPicturePath` varchar(32) DEFAULT NULL,
  `cIDNumber` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------
INSERT INTO `gy_report_student` VALUES ('1', '李琦', '1234567890', '00000000000000000000000000000001', 'c:/text/jpl.word', '32312425');
INSERT INTO `gy_report_student` VALUES ('2', '张三', '1234567890', '00000000000000000000000000000002', 'c:/text/jpl.word', '2123543534');
INSERT INTO `gy_report_student` VALUES ('4', '赵六', '2014153003', '00000000000000000000000000000001', 'zsd', '441486987099080');
INSERT INTO `gy_report_student` VALUES ('6', '取得', '2014153003', '00000000000000000000000000000010', null, '2142354');

-- ----------------------------
-- Table structure for `gy_report_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher`;
CREATE TABLE `gy_report_teacher` (
  `cUserId` int(32) NOT NULL AUTO_INCREMENT,
  `cName` varchar(12) DEFAULT NULL,
  `cTeacherId` varchar(12) DEFAULT NULL,
  `cDepartmentId` int(32) DEFAULT NULL,
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------
INSERT INTO `gy_report_teacher` VALUES ('1', '王五', '19880102', '1');
INSERT INTO `gy_report_teacher` VALUES ('2', '张三', '12424', '1');

-- ----------------------------
-- Table structure for `gy_report_teacher_course`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher_course`;
CREATE TABLE `gy_report_teacher_course` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTeacherId` int(32) NOT NULL,
  `cCourseId` int(32) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_teacher_course
-- ----------------------------
INSERT INTO `gy_report_teacher_course` VALUES ('1', '2', '2');
INSERT INTO `gy_report_teacher_course` VALUES ('4', '1', '2');
INSERT INTO `gy_report_teacher_course` VALUES ('5', '2', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('198809081', 'admin', '333333', '1', '2016-06-23 14:35:36');
INSERT INTO `gy_report_user` VALUES ('2014132001', 'root', '122345', '2', '2016-10-12 13:12:12');
INSERT INTO `gy_report_user` VALUES ('2014153003', '张三', '12345678', '2', '2016-08-06 18:40:46');
