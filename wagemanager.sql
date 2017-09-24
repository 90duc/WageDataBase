/*
Navicat MySQL Data Transfer

Source Server         : cs
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : wagemanager

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-07-08 10:36:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for allowance
-- ----------------------------
DROP TABLE IF EXISTS `allowance`;
CREATE TABLE `allowance` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '加班号',
  `employeeID` int(11) DEFAULT NULL,
  `day` date DEFAULT NULL COMMENT '加班日期',
  `workHours` decimal(10,2) DEFAULT NULL COMMENT '加班时长',
  `type` varchar(255) DEFAULT NULL COMMENT '加班类型',
  `perk` decimal(8,2) DEFAULT NULL COMMENT '津贴',
  PRIMARY KEY (`id`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `allowance_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allowance
-- ----------------------------
INSERT INTO `allowance` VALUES ('1', '15', '2017-05-10', '2.50', '加班', '10.00');
INSERT INTO `allowance` VALUES ('2', '15', '2017-05-10', '10.00', '补偿', '20.00');
INSERT INTO `allowance` VALUES ('3', '1', '2017-05-31', '2.00', '补偿', '20.00');
INSERT INTO `allowance` VALUES ('4', '3', '2017-05-28', '2.00', '加班', '200.00');
INSERT INTO `allowance` VALUES ('5', '3', '2017-05-14', '1.00', '路费', '100.00');
INSERT INTO `allowance` VALUES ('6', '2', '2017-05-17', '2.00', '加班', '200.00');
INSERT INTO `allowance` VALUES ('7', '20', '2017-05-28', '2.00', '加班', '250.00');
INSERT INTO `allowance` VALUES ('8', '2', '2017-03-21', '1.00', '路费', '200.00');
INSERT INTO `allowance` VALUES ('9', '2', '2017-06-15', '1.00', '路费', '200.00');

-- ----------------------------
-- Table structure for annualbonus
-- ----------------------------
DROP TABLE IF EXISTS `annualbonus`;
CREATE TABLE `annualbonus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年终奖号',
  `employeeID` int(11) DEFAULT NULL COMMENT '员工号',
  `year` varchar(255) DEFAULT NULL COMMENT '年',
  `award` double(8,2) DEFAULT NULL COMMENT '年终奖',
  PRIMARY KEY (`id`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `annualbonus_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of annualbonus
-- ----------------------------
INSERT INTO `annualbonus` VALUES ('7', '3', '2017', '222.50');
INSERT INTO `annualbonus` VALUES ('9', '2', '2017', '655.77');

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工号',
  `employeeID` int(11) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL COMMENT '月份',
  `late` int(11) DEFAULT '0' COMMENT '迟到次数',
  `absence` int(11) DEFAULT '0' COMMENT '缺席次数',
  `sign` int(11) DEFAULT '0' COMMENT '签到次数',
  PRIMARY KEY (`id`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '2', '2017-05', '0', '0', '30');
INSERT INTO `attendance` VALUES ('2', '15', '2017-05', '2', '1', '27');
INSERT INTO `attendance` VALUES ('3', '3', '2017-05', '0', '3', '27');
INSERT INTO `attendance` VALUES ('4', '20', '2017-06', '2', '1', '24');
INSERT INTO `attendance` VALUES ('5', '2', '2017-04', '1', '2', '23');
INSERT INTO `attendance` VALUES ('6', '2', '2017-03', '1', '0', '25');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '部门号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '数信部门');
INSERT INTO `department` VALUES ('2', '经管部门');
INSERT INTO `department` VALUES ('3', '农林部门');
INSERT INTO `department` VALUES ('4', '外语部门');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工号',
  `name` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` enum('other','female','male') DEFAULT NULL COMMENT '性别',
  `departNo` int(255) DEFAULT NULL COMMENT '部门号',
  `hireTime` date DEFAULT NULL COMMENT '雇佣时间',
  `joblevel` int(11) DEFAULT NULL COMMENT '雇佣类型',
  PRIMARY KEY (`id`),
  KEY `departNo` (`departNo`),
  KEY `joblevel` (`joblevel`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`departNo`) REFERENCES `department` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`joblevel`) REFERENCES `joblevel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '张世春', '2017-05-01', 'male', '1', '2017-05-09', '3');
INSERT INTO `employee` VALUES ('2', '李思', '1991-06-13', 'female', '1', '2017-05-22', '2');
INSERT INTO `employee` VALUES ('3', '张武', '2017-05-17', 'male', '2', '2017-05-22', '2');
INSERT INTO `employee` VALUES ('15', '李世春', '2017-05-02', 'female', '1', '2017-05-19', '4');
INSERT INTO `employee` VALUES ('18', '赵六', '2017-05-09', 'male', '2', '2017-05-11', '3');
INSERT INTO `employee` VALUES ('19', '吴刚', '2017-05-15', 'male', '4', '2017-05-17', '2');
INSERT INTO `employee` VALUES ('20', '章斯文', '2017-05-02', 'female', '1', '2017-05-17', '2');

-- ----------------------------
-- Table structure for joblevel
-- ----------------------------
DROP TABLE IF EXISTS `joblevel`;
CREATE TABLE `joblevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作类型',
  `typeName` varchar(255) DEFAULT NULL COMMENT '工作名称',
  `grade` int(4) DEFAULT NULL COMMENT '等级',
  `salary` decimal(8,2) DEFAULT '0.00' COMMENT '基础工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of joblevel
-- ----------------------------
INSERT INTO `joblevel` VALUES ('1', '员工', '1', '2000.00');
INSERT INTO `joblevel` VALUES ('2', '经理', '2', '2500.00');
INSERT INTO `joblevel` VALUES ('3', '保安', '3', '1000.00');
INSERT INTO `joblevel` VALUES ('4', '总经理', '4', '3000.00');

-- ----------------------------
-- Table structure for wage
-- ----------------------------
DROP TABLE IF EXISTS `wage`;
CREATE TABLE `wage` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '员工号',
  `employeeID` int(11) DEFAULT NULL,
  `month` varchar(255) DEFAULT NULL COMMENT '月份',
  `salary` decimal(8,2) DEFAULT NULL COMMENT '工资',
  `allowance` decimal(8,2) DEFAULT NULL COMMENT '津贴',
  PRIMARY KEY (`id`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `wage_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wage
-- ----------------------------
INSERT INTO `wage` VALUES ('1', '18', '2017-05', '2500.00', '250.00');
INSERT INTO `wage` VALUES ('6', '3', '2017-05', '2250.00', '300.00');
INSERT INTO `wage` VALUES ('10', '2', '2017-05', '2500.00', '400.00');
INSERT INTO `wage` VALUES ('11', '15', '2017-05', '2383.33', '225.00');
INSERT INTO `wage` VALUES ('14', '2', '2017-04', '2288.46', '0.00');
INSERT INTO `wage` VALUES ('15', '2', '2017-03', '2480.77', '200.00');
