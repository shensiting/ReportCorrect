/*
Navicat MySQL Data Transfer

Source Server         : report4gzhmc
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-08-18 20:02:58
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
INSERT INTO `gy_report_college` VALUES ('1', '临床三院');
INSERT INTO `gy_report_college` VALUES ('2', '临床学院');
INSERT INTO `gy_report_college` VALUES ('3', '软件学院');
INSERT INTO `gy_report_college` VALUES ('4', '土木工程学院');
INSERT INTO `gy_report_college` VALUES ('5', '大数据学院');
INSERT INTO `gy_report_college` VALUES ('6', 'Web学院');
INSERT INTO `gy_report_college` VALUES ('11', '基础学院');

-- ----------------------------
-- Table structure for `gy_report_experimental_test`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_experimental_test`;
CREATE TABLE `gy_report_experimental_test` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cExperimentName` varchar(50) DEFAULT NULL,
  `cExperimentEnglishName` varchar(50) DEFAULT NULL,
  `cExperimentTime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gy_report_experimental_test
-- ----------------------------
INSERT INTO `gy_report_experimental_test` VALUES ('1', 'PCR 实验原理', 'PCR EXPERIENCEMENT', '2016年12月');
INSERT INTO `gy_report_experimental_test` VALUES ('2', '生物技术生化实验', 'BIOGRAPHI TEST', '2016年9月');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade
-- ----------------------------
INSERT INTO `gy_report_grade` VALUES ('1', '2016级', '1', '1班', '11');
INSERT INTO `gy_report_grade` VALUES ('2', '2011级', '2', '3班', '11');
INSERT INTO `gy_report_grade` VALUES ('6', '2014级', '3', '2班', '11');
INSERT INTO `gy_report_grade` VALUES ('10', '2013级', '2', '3班', '11');

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
INSERT INTO `gy_report_major` VALUES ('2', '生物技术');
INSERT INTO `gy_report_major` VALUES ('3', '信息管理与信息系统');
INSERT INTO `gy_report_major` VALUES ('4', '护理');
INSERT INTO `gy_report_major` VALUES ('14', '市场营销');
INSERT INTO `gy_report_major` VALUES ('15', '护理');
INSERT INTO `gy_report_major` VALUES ('22', '生物工程');

-- ----------------------------
-- Table structure for `gy_report_report`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_report`;
CREATE TABLE `gy_report_report` (
  `cId` varchar(32) NOT NULL,
  `cReportName` varchar(32) DEFAULT NULL,
  `cScoreId` int(32) DEFAULT NULL,
  `cStudentId` int(32) DEFAULT NULL,
  `cTeacherId` int(32) DEFAULT NULL,
  `cStatus` int(4) DEFAULT NULL,
  `cPath` varchar(32) DEFAULT NULL,
  `cCreateTime` datetime DEFAULT NULL,
  `cPdfPath` varchar(50) DEFAULT NULL,
  `cProcess` varchar(300) DEFAULT NULL,
  `cQRcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------
INSERT INTO `gy_report_report` VALUES ('1', '测试名字2', '1', '3', '2', '2', 'c:t/er/j.jpg', '2016-08-03 22:28:49', null, null, null);
INSERT INTO `gy_report_report` VALUES ('2', '测试.word', '1', '3', '2', '2', 'c:t/er/j.jpg', '2016-08-11 18:12:25', 'c://pdf', 'PCR实验技术与应用', 'c://QRcode');

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
  `cCharSum` varchar(6) DEFAULT NULL,
  `cConclution` double(32,0) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------
INSERT INTO `gy_report_student` VALUES ('1', '王五五', '2014153001', '00000000000000000000000000000006', null, '68753273');
INSERT INTO `gy_report_student` VALUES ('2', '刘亦菲', '2014153002', '00000000000000000000000000000002', null, '441476823878498736');
INSERT INTO `gy_report_student` VALUES ('3', '赵六', '2015153003', '00000000000000000000000000000002', null, '68753273');
INSERT INTO `gy_report_student` VALUES ('6', '张绍', '2014153004', '00000000000000000000000000000010', null, null);
INSERT INTO `gy_report_student` VALUES ('7', '2014153005', '2014153005', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('8', '2014153006', '2014153006', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('10', '2014153008', '2014153008', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('11', '2014153009', '2014153009', '00000000000000000000000000000010', null, null);
INSERT INTO `gy_report_student` VALUES ('12', '2014153010', '2014153010', '00000000000000000000000000000006', null, null);
INSERT INTO `gy_report_student` VALUES ('13', '2015153010', '2015153010', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('14', '2014153011', '2014153011', '00000000000000000000000000000006', null, null);
INSERT INTO `gy_report_student` VALUES ('17', '2014153012', '2014153012', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('24', '30001', '30001', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('26', '2014154001', '2014154001', '00000000000000000000000000000002', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------
INSERT INTO `gy_report_teacher` VALUES ('4', '赵六', '1988001', '11', '1');
INSERT INTO `gy_report_teacher` VALUES ('5', '王小明', '1988002', '11', '0');
INSERT INTO `gy_report_teacher` VALUES ('15', '1988003', '1988003', '11', '1');
INSERT INTO `gy_report_teacher` VALUES ('16', '1998004', '1998004', '4', '1');
INSERT INTO `gy_report_teacher` VALUES ('27', '199001', '199001', '11', '0');

-- ----------------------------
-- Table structure for `gy_report_teacher_experimental`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher_experimental`;
CREATE TABLE `gy_report_teacher_experimental` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTeacherId` int(32) NOT NULL,
  `cExperimentalTestId` int(32) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_teacher_experimental
-- ----------------------------
INSERT INTO `gy_report_teacher_experimental` VALUES ('5', '2', '1');
INSERT INTO `gy_report_teacher_experimental` VALUES ('12', '1', '2');
INSERT INTO `gy_report_teacher_experimental` VALUES ('13', '2', '2');
INSERT INTO `gy_report_teacher_experimental` VALUES ('14', '4', '1');
INSERT INTO `gy_report_teacher_experimental` VALUES ('15', '5', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('2', '2014153002', '000000000', '3', '2016-08-12 10:22:59');
INSERT INTO `gy_report_user` VALUES ('3', '2014153003', '123243', '3', '2016-08-12 10:22:50');
INSERT INTO `gy_report_user` VALUES ('4', '1988001', '1988001', '2', '2016-08-12 10:26:23');
INSERT INTO `gy_report_user` VALUES ('5', '1988002', '1988002', '2', '2016-08-12 10:26:45');
INSERT INTO `gy_report_user` VALUES ('6', '2014153004', '098765', '3', '2016-08-12 10:51:02');
INSERT INTO `gy_report_user` VALUES ('7', '2014153005', '2014153005', '3', '2016-08-12 11:22:13');
INSERT INTO `gy_report_user` VALUES ('8', '2014153006', '2014153006', '3', '2016-08-12 11:23:20');
INSERT INTO `gy_report_user` VALUES ('9', '2014153007', '2014153007', '3', '2016-08-12 11:25:00');
INSERT INTO `gy_report_user` VALUES ('10', '2014153008', '2014153008', '3', '2016-08-12 11:28:39');
INSERT INTO `gy_report_user` VALUES ('11', '2014153009', '2014153009', '3', '2016-08-12 11:37:13');
INSERT INTO `gy_report_user` VALUES ('12', '2014153010', '2014153010', '3', '2016-08-12 11:40:12');
INSERT INTO `gy_report_user` VALUES ('13', '2015153010', '2015153010', '3', '2016-08-12 11:41:10');
INSERT INTO `gy_report_user` VALUES ('14', '2014153011', '2014153011', '3', '2016-08-12 11:41:39');
INSERT INTO `gy_report_user` VALUES ('15', '1988003', '1988003', '2', '2016-08-12 11:49:56');
INSERT INTO `gy_report_user` VALUES ('16', '1998004', '1998004', '2', '2016-08-12 12:56:18');
INSERT INTO `gy_report_user` VALUES ('17', '2014153012', '2014153012', '3', '2016-08-12 13:04:04');
INSERT INTO `gy_report_user` VALUES ('24', '30001', '3000130001', '3', '2016-08-17 10:15:50');
INSERT INTO `gy_report_user` VALUES ('25', '1000', '1000', '1', '2016-08-17 11:23:12');
INSERT INTO `gy_report_user` VALUES ('26', '2014154001', '2014154001', '3', '2016-08-17 11:39:13');
INSERT INTO `gy_report_user` VALUES ('27', '199001', '199001', '2', '2016-08-17 11:40:00');
