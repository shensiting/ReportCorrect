/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50551
Source Host           : localhost:3306
Source Database       : report4gzhmu

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2017-10-16 19:36:02
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
INSERT INTO `gy_report_college` VALUES ('5', '基础学院');
INSERT INTO `gy_report_college` VALUES ('10', '临床学院');
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
  `cClassify` int(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gy_report_experimental_test
-- ----------------------------
INSERT INTO `gy_report_experimental_test` VALUES ('1', 'PCR实验', 'PCR EXPERIMENT', '2015年6月', '1');
INSERT INTO `gy_report_experimental_test` VALUES ('5', '实验2', 'fsafgsdfs', 'asaf', '2');
INSERT INTO `gy_report_experimental_test` VALUES ('6', '实验实验', '234', '4535', '1');
INSERT INTO `gy_report_experimental_test` VALUES ('7', '机能实验4', 'fgASfasfdsf', '2015年', '2');
INSERT INTO `gy_report_experimental_test` VALUES ('9', 'TEST3', 'awdas', '2054-8', '2');
INSERT INTO `gy_report_experimental_test` VALUES ('12', 'PCR3', '234', '4535', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade
-- ----------------------------
INSERT INTO `gy_report_grade` VALUES ('1', '2014级', '4', '1班', '5');
INSERT INTO `gy_report_grade` VALUES ('2', '2014级', '4', '2班', '10');

-- ----------------------------
-- Table structure for `gy_report_grade_exam`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_grade_exam`;
CREATE TABLE `gy_report_grade_exam` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cGradeId` int(11) NOT NULL,
  `cExperimentId` int(11) NOT NULL,
  `cCreateTime` date NOT NULL,
  `cStatus` int(11) NOT NULL DEFAULT '0',
  `cSubmitForm` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade_exam
-- ----------------------------
INSERT INTO `gy_report_grade_exam` VALUES ('3', '1', '7', '2017-10-10', '0', '1');
INSERT INTO `gy_report_grade_exam` VALUES ('5', '1', '6', '2017-10-03', '0', '0');
INSERT INTO `gy_report_grade_exam` VALUES ('6', '1', '5', '2017-10-03', '1', '0');
INSERT INTO `gy_report_grade_exam` VALUES ('10', '1', '12', '2017-10-14', '0', '1');

-- ----------------------------
-- Table structure for `gy_report_major`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_major`;
CREATE TABLE `gy_report_major` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cMajorName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_major
-- ----------------------------
INSERT INTO `gy_report_major` VALUES ('4', '信息管理');
INSERT INTO `gy_report_major` VALUES ('15', '生物技术');

-- ----------------------------
-- Table structure for `gy_report_report`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_report`;
CREATE TABLE `gy_report_report` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cReportNum` varchar(32) DEFAULT NULL,
  `cScoreId` int(32) DEFAULT NULL,
  `cStudentId` int(32) DEFAULT NULL,
  `cStatu` int(4) DEFAULT NULL,
  `cPath` text,
  `cCreateTime` datetime DEFAULT NULL,
  `cPdfPath` varchar(200) DEFAULT NULL,
  `cContent` text,
  `cProcess` varchar(300) DEFAULT NULL,
  `cQRcode` varchar(200) DEFAULT NULL,
  `cTeacherId` int(32) DEFAULT NULL,
  `cExperimentTextId` int(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------
INSERT INTO `gy_report_report` VALUES ('2', '20171001042627', '0', '18', '0', null, '2017-10-14 19:45:28', null, '<p><img src=\"/ReportCorrect/ueditor/jsp/upload/image/20171012/1507809430444020884.png\" title=\"1507809430444020884.png\" alt=\"微信截图_20170526180932.png\" width=\"634\" height=\"488\" style=\"width: 634px; height: 488px;\"/></p><p>fhiasdhfosfhiaspghdilfjghlidfhgspidfh</p><p>fga8isgfkasjfksfsjdgbskjedf<img src=\"http://img.baidu.com/hi/jx2/j_0037.gif\"/></p><p><br/></p><p><img src=\"/ReportCorrect/../ueditor/jsp/upload/image/20171014/1507981471278050655.jpg\" title=\"1507981471278050655.jpg\" alt=\"timg.jpg\" width=\"704\" height=\"435\" style=\"width: 704px; height: 435px;\"/></p>', '54218765428541', null, '0', '5');
INSERT INTO `gy_report_report` VALUES ('4', '20171001055043', '0', '18', '0', '/基础学院/2014级/信息管理/1班/机能实验4/word/7_2014153003_1.docx', '2017-10-14 19:36:04', null, null, '121451544654635', null, '0', '7');
INSERT INTO `gy_report_report` VALUES ('5', '20171014065835', '0', '18', '0', '/基础学院/2014级/信息管理/1班/PCR3/word/12_2014153003_0.docx', '2017-10-14 18:58:35', null, null, '45643746184135', null, '0', '12');
INSERT INTO `gy_report_report` VALUES ('6', '20171014074602', '0', '18', '0', null, '2017-10-14 19:46:26', null, '<p><img src=\"/ReportCorrect/../ueditor/jsp/upload/image/20171014/1507981581238057640.jpg\" title=\"1507981581238057640.jpg\" alt=\"timg.jpg\" width=\"839\" height=\"486\" style=\"width: 839px; height: 486px;\"/></p>', '438541895689525', null, '0', '6');

-- ----------------------------
-- Table structure for `gy_report_resit`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_resit`;
CREATE TABLE `gy_report_resit` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cStudentId` int(11) NOT NULL,
  `cExperiment` int(11) NOT NULL,
  `cReportId` int(11) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_resit
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
  `cName` varchar(12) NOT NULL,
  `cStudentNumber` varchar(12) NOT NULL,
  `cGradeId` int(32) unsigned zerofill NOT NULL DEFAULT '00000000000000000000000000000001',
  `cPicturePath` varchar(300) DEFAULT NULL,
  `cPhoneNum` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------
INSERT INTO `gy_report_student` VALUES ('18', '王小明', '2014153003', '00000000000000000000000000000001', '/基础学院/2014级/信息管理/1班/2014153003.jpg', '12345678963');
INSERT INTO `gy_report_student` VALUES ('20', '小红', '2014153005', '00000000000000000000000000000002', null, '12457893789');
INSERT INTO `gy_report_student` VALUES ('21', '王小明', '1111111111', '00000000000000000000000000000001', null, '12345678965');
INSERT INTO `gy_report_student` VALUES ('22', '张三', '2222222222', '00000000000000000000000000000001', null, '12345678963');
INSERT INTO `gy_report_student` VALUES ('23', '李四', '3333333333', '00000000000000000000000000000001', null, '123456789456');
INSERT INTO `gy_report_student` VALUES ('24', '测试1', '4444444444', '00000000000000000000000000000001', null, '12345678962');
INSERT INTO `gy_report_student` VALUES ('25', '测试2', '5555555555', '00000000000000000000000000000001', null, '12345663214');
INSERT INTO `gy_report_student` VALUES ('27', '张三', '1234567890', '00000000000000000000000000000002', null, null);
INSERT INTO `gy_report_student` VALUES ('28', '测试4', '6666666666', '00000000000000000000000000000001', null, null);

-- ----------------------------
-- Table structure for `gy_report_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher`;
CREATE TABLE `gy_report_teacher` (
  `cUserId` int(32) NOT NULL AUTO_INCREMENT,
  `cName` varchar(12) NOT NULL,
  `cTeacherId` varchar(12) NOT NULL,
  `cCollegeId` int(32) NOT NULL,
  PRIMARY KEY (`cUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=13224 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------
INSERT INTO `gy_report_teacher` VALUES ('13218', '小明', '234', '11');
INSERT INTO `gy_report_teacher` VALUES ('13223', '王大明', '464646', '5');

-- ----------------------------
-- Table structure for `gy_report_teacher_grade`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher_grade`;
CREATE TABLE `gy_report_teacher_grade` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTeacherId` int(32) NOT NULL,
  `cGradeId` int(32) NOT NULL,
  `cCreateTime` date NOT NULL,
  `cStatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_teacher_grade
-- ----------------------------
INSERT INTO `gy_report_teacher_grade` VALUES ('1', '13223', '1', '2017-09-06', '1');
INSERT INTO `gy_report_teacher_grade` VALUES ('3', '13223', '2', '2017-10-02', '1');

-- ----------------------------
-- Table structure for `gy_report_test`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_test`;
CREATE TABLE `gy_report_test` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cTestName` varchar(100) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_test
-- ----------------------------
INSERT INTO `gy_report_test` VALUES ('1', '机能实验1');
INSERT INTO `gy_report_test` VALUES ('2', '机能实验2');

-- ----------------------------
-- Table structure for `gy_report_topictheme`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_topictheme`;
CREATE TABLE `gy_report_topictheme` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cTitle` varchar(100) NOT NULL,
  `cContent` text NOT NULL,
  `cLaunchId` int(11) NOT NULL,
  `cCreateTime` date NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_topictheme
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_topic_response`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_topic_response`;
CREATE TABLE `gy_report_topic_response` (
  `cId` bigint(20) NOT NULL AUTO_INCREMENT,
  `cTopicId` bigint(20) NOT NULL,
  `cContent` text NOT NULL,
  `cCreateTime` date NOT NULL,
  `cLaunchId` int(11) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_topic_response
-- ----------------------------

-- ----------------------------
-- Table structure for `gy_report_user`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_user`;
CREATE TABLE `gy_report_user` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cUserName` varchar(12) NOT NULL,
  `cPassword` varchar(64) NOT NULL,
  `cRole` int(4) NOT NULL,
  `cCreateTime` datetime NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=13224 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('18', '2014153003', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-05-08 20:54:53');
INSERT INTO `gy_report_user` VALUES ('19', '1000', 'bd2d9e16b1c40a0b0a26c3ca956320fd', '1', '2015-10-05 00:00:00');
INSERT INTO `gy_report_user` VALUES ('20', '2014153005', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-09-29 19:35:03');
INSERT INTO `gy_report_user` VALUES ('21', '1111111111', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 18:43:53');
INSERT INTO `gy_report_user` VALUES ('22', '2222222222', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 18:44:33');
INSERT INTO `gy_report_user` VALUES ('23', '3333333333', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 18:44:41');
INSERT INTO `gy_report_user` VALUES ('24', '4444444444', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 19:00:46');
INSERT INTO `gy_report_user` VALUES ('25', '5555555555', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 19:00:46');
INSERT INTO `gy_report_user` VALUES ('28', '6666666666', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-10-16 19:34:44');
INSERT INTO `gy_report_user` VALUES ('13217', '46876', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-09-25 21:17:13');
INSERT INTO `gy_report_user` VALUES ('13220', '234', 'f04b40ca7e4d5df2ceb6a891df97cc82', '2', '2017-09-25 21:38:04');
INSERT INTO `gy_report_user` VALUES ('13221', '234', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-09-25 21:39:58');
INSERT INTO `gy_report_user` VALUES ('13223', '464646', 'f04b40ca7e4d5df2ceb6a891df97cc82', '2', '2017-09-28 20:16:38');
