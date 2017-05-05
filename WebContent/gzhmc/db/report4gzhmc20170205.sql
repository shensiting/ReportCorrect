/*
Navicat MySQL Data Transfer

Source Server         : report4gzhmc
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : report4gzhmc

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-02-05 17:28:20
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_college
-- ----------------------------
INSERT INTO `gy_report_college` VALUES ('3', '生命科学学院');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of gy_report_experimental_test
-- ----------------------------
INSERT INTO `gy_report_experimental_test` VALUES ('2', 'PCR实验', 'PCR EXPERIMENT', '2016年2月', '1');
INSERT INTO `gy_report_experimental_test` VALUES ('3', '测试1测试1测试1测试1测试1', '测试1', '2016年5月', '0');
INSERT INTO `gy_report_experimental_test` VALUES ('4', '测试2', '测试2', '2016年9月', '0');
INSERT INTO `gy_report_experimental_test` VALUES ('5', '测试3', '测试3', '1998年6月', '0');
INSERT INTO `gy_report_experimental_test` VALUES ('6', '测试4测试4', 'YIWQNDXUGQIEWNUWQ', '2017年8月', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_grade
-- ----------------------------
INSERT INTO `gy_report_grade` VALUES ('4', '2011级', '2', '1班', '3');
INSERT INTO `gy_report_grade` VALUES ('5', '2012级', '2', '1班', '3');
INSERT INTO `gy_report_grade` VALUES ('6', '2013级', '2', '1班', '3');

-- ----------------------------
-- Table structure for `gy_report_major`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_major`;
CREATE TABLE `gy_report_major` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cMajorName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_major
-- ----------------------------
INSERT INTO `gy_report_major` VALUES ('2', '生物技术');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_report
-- ----------------------------
INSERT INTO `gy_report_report` VALUES ('5', '20160909101722', '47', '3', '1', '/生命科学学院/2011级/生物技术/1班/PCR实验/html/2_2014153001.html', '2016-09-09 22:17:22', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\PCR实验\\pdf\\5_2014153001.pdf', '4543657685', null, '7', '2');
INSERT INTO `gy_report_report` VALUES ('6', '20170113083334', '51', '11', '1', '/生命科学学院/2013级/生物技术/1班/测试2/html/4_3003.html', '2017-01-13 20:33:34', null, '你操唉衣服回来撒卡读奥数', null, '7', '4');
INSERT INTO `gy_report_report` VALUES ('7', '20170113083436', '49', '11', '1', '/生命科学学院/2013级/生物技术/1班/测试1/html/3_3003.html', '2017-01-13 20:34:36', null, '34254', null, '7', '3');
INSERT INTO `gy_report_report` VALUES ('8', '20170115113743', '48', '13', '1', '/生命科学学院/2013级/生物技术/1班/测试2/html/4_3005.html', '2017-01-15 11:37:43', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2013级\\生物技术\\1班\\测试2\\pdf\\8_3005.pdf', '与第一梯队', null, '7', '4');
INSERT INTO `gy_report_report` VALUES ('9', '20170115113808', '52', '13', '1', '/生命科学学院/2013级/生物技术/1班/PCR实验/html/2_3005.html', '2017-01-15 11:38:08', null, '遇到同样', null, '7', '2');
INSERT INTO `gy_report_report` VALUES ('10', '20170115113844', '50', '13', '1', '/生命科学学院/2013级/生物技术/1班/测试1测试1测试1测试1测试1/html/3_3005.html', '2017-01-15 11:38:44', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2013级\\生物技术\\1班\\测试1测试1测试1测试1测试1\\pdf\\10_3005.pdf', '狗四人御多', null, '7', '3');
INSERT INTO `gy_report_report` VALUES ('11', '20170204074521', '53', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试2/html/4_123456.html', '2017-02-04 19:45:21', null, '电话的公司烦死人', null, '7', '4');
INSERT INTO `gy_report_report` VALUES ('12', '20170204074541', '54', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试1测试1测试1测试1测试1/html/3_123456.html', '2017-02-04 19:45:41', null, '司法所大神', null, '7', '3');
INSERT INTO `gy_report_report` VALUES ('13', '20170204074607', '55', '14', '1', '/生命科学学院/2011级/生物技术/1班/PCR实验/html/2_123456.html', '2017-02-04 19:46:07', null, '舒服撒地方的', null, '7', '2');
INSERT INTO `gy_report_report` VALUES ('15', '20170204080600', '59', '9', '1', '/生命科学学院/2011级/生物技术/1班/测试3/html/5_3001.html', '2017-02-04 20:06:00', null, 'DCasDADa', null, '7', '5');
INSERT INTO `gy_report_report` VALUES ('16', '20170204080705', '56', '9', '1', '/生命科学学院/2011级/生物技术/1班/测试2/html/4_3001.html', '2017-02-04 20:07:05', null, 'ADASDFSAFAS', null, '7', '4');
INSERT INTO `gy_report_report` VALUES ('17', '20170204080724', '57', '9', '1', '/生命科学学院/2011级/生物技术/1班/测试1测试1测试1测试1测试1/html/3_3001.html', '2017-02-04 20:07:24', null, 'SASDAS', null, '7', '3');
INSERT INTO `gy_report_report` VALUES ('18', '20170204080740', '58', '9', '1', '/生命科学学院/2011级/生物技术/1班/PCR实验/html/2_3001.html', '2017-02-04 20:07:40', null, 'WDADSA', null, '7', '2');
INSERT INTO `gy_report_report` VALUES ('25', '20170205102531', '62', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试1测试1测试1测试1测试1/html/3_123456_1.html', '2017-02-05 10:25:31', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试1测试1测试1测试1测试1\\pdf\\25_123456.pdf', 'dsdasfas', null, '7', '3');
INSERT INTO `gy_report_report` VALUES ('26', '20170205102749', '60', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试3/html/5_123456_0.html', '2017-02-05 10:27:49', null, '342353453', null, '7', '5');
INSERT INTO `gy_report_report` VALUES ('27', '20170205103406', '61', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试3/html/5_123456_1.html', '2017-02-05 10:34:06', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试3\\pdf\\27_123456.pdf', '243254', null, '7', '5');
INSERT INTO `gy_report_report` VALUES ('28', '20170205112812', '66', '15', '1', '/生命科学学院/2011级/生物技术/1班/测试3/html/5_1234567_1.html', '2017-02-05 11:56:12', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试3\\pdf\\28_1234567.pdf', '431224123', null, '7', '5');
INSERT INTO `gy_report_report` VALUES ('30', '20170205122309', '63', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试2/html/4_123456_2.html', '2017-02-05 12:23:48', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试2\\pdf\\30_123456.pdf', '1241231', null, '7', '4');
INSERT INTO `gy_report_report` VALUES ('31', '20170205123151', '64', '14', '1', '/生命科学学院/2011级/生物技术/1班/测试4测试4/html/6_123456_0.html', '2017-02-05 12:31:51', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试4测试4\\pdf\\31_123456.pdf', '35246365', null, '8', '6');
INSERT INTO `gy_report_report` VALUES ('32', '20170205044414', '67', '9', '1', '/生命科学学院/2011级/生物技术/1班/测试4测试4/html/6_3001_1.html', '2017-02-05 16:46:31', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试4测试4\\pdf\\32_3001.pdf', '75686856745634', null, '8', '6');
INSERT INTO `gy_report_report` VALUES ('33', '20170205044535', '65', '9', '1', '/生命科学学院/2011级/生物技术/1班/测试2/html/4_3001_2.html', '2017-02-05 16:45:59', 'C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\生命科学学院\\2011级\\生物技术\\1班\\测试2\\pdf\\33_3001.pdf', '3425345364', null, '7', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_resit
-- ----------------------------
INSERT INTO `gy_report_resit` VALUES ('1', '13', '2', '3');
INSERT INTO `gy_report_resit` VALUES ('2', '13', '2', '3');
INSERT INTO `gy_report_resit` VALUES ('3', '11', '3', '0');
INSERT INTO `gy_report_resit` VALUES ('4', '11', '4', '0');
INSERT INTO `gy_report_resit` VALUES ('5', '13', '2', '0');
INSERT INTO `gy_report_resit` VALUES ('6', '14', '4', '30');
INSERT INTO `gy_report_resit` VALUES ('7', '14', '3', '25');
INSERT INTO `gy_report_resit` VALUES ('8', '14', '2', '0');
INSERT INTO `gy_report_resit` VALUES ('9', '9', '4', '33');
INSERT INTO `gy_report_resit` VALUES ('10', '9', '3', '0');
INSERT INTO `gy_report_resit` VALUES ('11', '9', '2', '0');
INSERT INTO `gy_report_resit` VALUES ('12', '9', '5', '0');
INSERT INTO `gy_report_resit` VALUES ('13', '14', '5', '27');

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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_scoresheet
-- ----------------------------
INSERT INTO `gy_report_scoresheet` VALUES ('44', '3', '3', '3', '3', '3', '90', 'kfjhdrszwear', '2016-09-08 18:05:40', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('45', '1', '1', '1', '1', '1', '78', 'irfhgxezxdsa', '2016-09-08 18:06:11', '1');
INSERT INTO `gy_report_scoresheet` VALUES ('46', '3', '3', '3', '3', '3', '90', 'afsadgadhhhfgjhgkj', '2016-09-08 18:09:31', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('47', '3', '3', '3', '3', '3', '78', '的V字形从VC吧保险成本部分地方刚收到', '2016-11-30 19:13:15', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('48', '3', '3', '3', '3', '3', '89', '不错', '2017-01-15 11:47:25', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('49', '0', '1', '0', '0', '0', '48', '和大福获得过哈尔哈尔', '2017-02-04 19:36:01', '2');
INSERT INTO `gy_report_scoresheet` VALUES ('50', '3', '3', '3', '3', '3', '90', '浴室柜福所热', '2017-02-04 19:37:46', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('51', '0', '0', '0', '0', '0', '45', '看见傅一道题一定要', '2017-02-04 19:41:42', '0');
INSERT INTO `gy_report_scoresheet` VALUES ('52', '0', '0', '0', '0', '0', '59', 'ADASFDSFSD', '2017-02-04 19:56:31', '0');
INSERT INTO `gy_report_scoresheet` VALUES ('53', '0', '0', '0', '0', '3', '59', 'FAEWSFSDVSD', '2017-02-04 19:59:36', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('54', '0', '3', '0', '3', '0', '45', 'SADsDFDFZ', '2017-02-04 20:01:51', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('55', '3', '3', '3', '3', '3', '23', 'SFDSVSZDV', '2017-02-04 20:02:47', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('56', '0', '3', '3', '3', '3', '45', 'ASDsSF是否', '2017-02-04 20:10:24', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('57', '3', '3', '3', '3', '3', '34', '阿大扫除撒的发生', '2017-02-04 20:15:10', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('58', '3', '3', '3', '3', '3', '34', '是否是范德萨', '2017-02-04 20:16:30', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('59', '0', '3', '0', '3', '0', '23', '325254', '2017-02-05 10:30:34', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('60', '0', '3', '0', '3', '3', '23', 'sfdgzsvs', '2017-02-05 10:31:36', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('61', '3', '3', '3', '3', '3', '78', '沙发沙发的沙发的方式', '2017-02-05 10:47:15', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('62', '3', '3', '3', '3', '3', '78', '千万人WDF啊', '2017-02-05 12:32:46', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('63', '3', '3', '3', '3', '3', '89', '人完全燃烧成', '2017-02-05 12:33:01', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('64', '3', '3', '3', '3', '3', '78', '阿萨斯', '2017-02-05 12:36:08', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('65', '3', '3', '3', '3', '3', '89', '个个UV镜个月内', '2017-02-05 16:47:58', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('66', '3', '3', '3', '3', '3', '78', '53532423252', '2017-02-05 16:48:33', '3');
INSERT INTO `gy_report_scoresheet` VALUES ('67', '3', '3', '3', '3', '3', '89', 'sadACS', '2017-02-05 16:55:29', '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_student
-- ----------------------------
INSERT INTO `gy_report_student` VALUES ('3', '2014153001', '2014153001', '00000000000000000000000000000004', '/生命科学学院/2011级/生物技术/1班/2014153001.jpeg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('9', '3001', '3001', '00000000000000000000000000000004', '/生命科学学院/2011级/生物技术/1班/3001.jpeg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('10', '3002', '3002', '00000000000000000000000000000005', '/生命科学学院/2012级/生物技术/1班/3002.jpeg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('11', '3003', '3003', '00000000000000000000000000000006', '/生命科学学院/2013级/生物技术/1班/3003.jpeg', '443532456789098765');
INSERT INTO `gy_report_student` VALUES ('12', '3004', '3004', '00000000000000000000000000000006', '/生命科学学院/2013级/生物技术/1班/3004.jpg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('13', '3005', '3005', '00000000000000000000000000000006', '/生命科学学院/2013级/生物技术/1班/3005.jpg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('14', '123456', '123456', '00000000000000000000000000000004', '/生命科学学院/2011级/生物技术/1班/123456.jpg', '123456789098765432');
INSERT INTO `gy_report_student` VALUES ('15', '1234567', '1234567', '00000000000000000000000000000004', '/生命科学学院/2011级/生物技术/1班/1234567.jpg', '123456789098765432');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_teacher
-- ----------------------------
INSERT INTO `gy_report_teacher` VALUES ('7', '1988001', '1988001', '3', '1');
INSERT INTO `gy_report_teacher` VALUES ('8', '1988002', '1988002', '3', '1');

-- ----------------------------
-- Table structure for `gy_report_teacher_experimental`
-- ----------------------------
DROP TABLE IF EXISTS `gy_report_teacher_experimental`;
CREATE TABLE `gy_report_teacher_experimental` (
  `cId` int(32) NOT NULL AUTO_INCREMENT,
  `cTeacherId` int(32) NOT NULL,
  `cExperimentalTestId` int(32) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gy_report_teacher_experimental
-- ----------------------------
INSERT INTO `gy_report_teacher_experimental` VALUES ('1', '7', '2');
INSERT INTO `gy_report_teacher_experimental` VALUES ('2', '8', '4');
INSERT INTO `gy_report_teacher_experimental` VALUES ('3', '8', '3');
INSERT INTO `gy_report_teacher_experimental` VALUES ('4', '7', '4');
INSERT INTO `gy_report_teacher_experimental` VALUES ('5', '7', '3');
INSERT INTO `gy_report_teacher_experimental` VALUES ('6', '7', '5');
INSERT INTO `gy_report_teacher_experimental` VALUES ('7', '8', '6');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_report_user
-- ----------------------------
INSERT INTO `gy_report_user` VALUES ('1', '1000', 'bd2d9e16b1c40a0b0a26c3ca956320fd', '1', '2016-08-28 10:37:55');
INSERT INTO `gy_report_user` VALUES ('2', '1001', '01e92ba15aa2b3361fd5ac1b5d72298e', '1', '2016-08-28 10:38:25');
INSERT INTO `gy_report_user` VALUES ('3', '2014153001', 'b95d0e76d0994f4ae8cea3a05e7b75fe', '3', '2016-09-09 20:56:03');
INSERT INTO `gy_report_user` VALUES ('7', '1988001', 'f04b40ca7e4d5df2ceb6a891df97cc82', '2', '2016-09-09 21:53:26');
INSERT INTO `gy_report_user` VALUES ('8', '1988002', '025a2acdcc2a4af269464c15a20013ea', '2', '2016-09-10 10:33:15');
INSERT INTO `gy_report_user` VALUES ('9', '3001', '3fa964c0f8a86ac59e4bb15cca3bca19', '3', '2017-01-13 20:30:56');
INSERT INTO `gy_report_user` VALUES ('10', '3002', '9487fee51c34187292462f1b87b3627b', '3', '2017-01-13 20:31:10');
INSERT INTO `gy_report_user` VALUES ('11', '3003', 'f696e9301cdbb671bad995a52982b591', '3', '2017-01-13 20:31:23');
INSERT INTO `gy_report_user` VALUES ('12', '3004', '0512df52da7fd3dadca284fb3c5ff85c', '3', '2017-01-13 22:10:15');
INSERT INTO `gy_report_user` VALUES ('13', '3005', '4e57af654e70a3da06d5f6ddf8f48f53', '3', '2017-01-15 11:35:20');
INSERT INTO `gy_report_user` VALUES ('14', '123456', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-02-03 21:34:15');
INSERT INTO `gy_report_user` VALUES ('15', '1234567', 'f04b40ca7e4d5df2ceb6a891df97cc82', '3', '2017-02-05 11:27:19');
