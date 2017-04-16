/*
Navicat MySQL Data Transfer

Source Server         : report4gzhmc
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-08-27 17:12:01
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
INSERT INTO `gy_report_college` VALUES ('11', '生命科学学院');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gy_report_experimental_test
-- ----------------------------
INSERT INTO `gy_report_experimental_test` VALUES ('1', 'PCR 实验原理', 'PCR THEORY &OPERATION', '2016年12月', '0');
INSERT INTO `gy_report_experimental_test` VALUES ('2', '生物技术生化实验', 'PCR THEORY &OPERATION', '2016年9月', '1');

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
INSERT INTO `gy_report_report` VALUES ('40', '20160827105831', '38', '31', '1', '/生命科学学院/2011级/生物技术/3班/PCR 实验原理/html/1_20001.html', '2016-08-27 12:36:46', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\3班\\PCR 实验原理\\pdf\\40_20001.pdf', '啊发撒出多少', '/生命科学学院/2011级/生物技术/3班/PCR 实验原理/qrcode/40_20001.jpg', '32', '1');
INSERT INTO `gy_report_report` VALUES ('41', '20160827111809', '39', '33', '1', '/生命科学学院/2011级/生物技术/3班/生物技术生化实验/html/2_20002.html', '2016-08-27 11:18:09', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\3班\\生物技术生化实验\\pdf\\41_20002.pdf', '热敷撒出多少', '/生命科学学院/2011级/生物技术/3班/生物技术生化实验/qrcode/41_20002.jpg', '32', '2');
INSERT INTO `gy_report_report` VALUES ('42', '20160827111826', '40', '33', '1', '/生命科学学院/2011级/生物技术/3班/PCR 实验原理/html/1_20002.html', '2016-08-27 11:18:26', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\3班\\PCR 实验原理\\pdf\\42_20002.pdf', '啊上次坐车Z', '/生命科学学院/2011级/生物技术/3班/PCR 实验原理/qrcode/42_20002.jpg', '32', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_scoresheet
-- ----------------------------
INSERT INTO `gy_report_scoresheet` VALUES ('38', null, '3', '2', '3', '1', '3', '89', '但是你一下阿卡尼西撒擦貌似看错', '2016-08-27 11:19:53', null, '3');
INSERT INTO `gy_report_scoresheet` VALUES ('39', null, '3', '3', '3', '3', '3', '100', '啊倒萨一大口子', '2016-08-27 11:20:07', null, '3');
INSERT INTO `gy_report_scoresheet` VALUES ('40', null, '3', '3', '3', '3', '3', '78', '这才是正常下载', '2016-08-27 11:20:34', null, '3');

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
INSERT INTO `gy_report_student` VALUES ('31', '小丸子', '20001', '00000000000000000000000000000002', '/生命科学学院/2011级/生物技术/3班/20001.jpg', '7965348562384');
INSERT INTO `gy_report_student` VALUES ('33', '20002', '20002', '00000000000000000000000000000002', '/生命科学学院/2011级/生物技术/3班/20002.jpeg', '54657687');

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
INSERT INTO `gy_report_teacher` VALUES ('32', '教师', '198809081', '11', '1');

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
INSERT INTO `gy_report_teacher_experimental` VALUES ('20', '32', '2');
INSERT INTO `gy_report_teacher_experimental` VALUES ('22', '32', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('25', '1000', '1000', '1', '2016-08-17 11:23:12');
INSERT INTO `gy_report_user` VALUES ('26', '2014154001', '2014154001', '3', '2016-08-17 11:39:13');
INSERT INTO `gy_report_user` VALUES ('27', '199001', '199001', '2', '2016-08-17 11:40:00');
INSERT INTO `gy_report_user` VALUES ('28', '111111', '111111', '3', '2016-08-25 08:58:31');
INSERT INTO `gy_report_user` VALUES ('29', '1999999', '1999999', '2', '2016-08-25 09:00:42');
INSERT INTO `gy_report_user` VALUES ('30', '1888888', '1888888', '2', '2016-08-25 10:09:09');
INSERT INTO `gy_report_user` VALUES ('31', '20001', '123456', '3', '2016-08-27 10:52:50');
INSERT INTO `gy_report_user` VALUES ('32', '198809081', '123456', '2', '2016-08-27 11:02:05');
INSERT INTO `gy_report_user` VALUES ('33', '20002', '123456', '3', '2016-08-27 11:17:25');
