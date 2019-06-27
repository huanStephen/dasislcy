/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.123
Source Server Version : 50640
Source Host           : 192.168.1.123:3306
Source Database       : dasislcy

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-06-27 21:48:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for choice_question
-- ----------------------------
DROP TABLE IF EXISTS `choice_question`;
CREATE TABLE `choice_question` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` tinyint(2) DEFAULT NULL COMMENT '类型：1、选择题',
  `TITLE` varchar(1024) DEFAULT NULL COMMENT '标题',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2243 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for choice_question_option
-- ----------------------------
DROP TABLE IF EXISTS `choice_question_option`;
CREATE TABLE `choice_question_option` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_ID` int(11) DEFAULT NULL COMMENT '选择题ID',
  `ANSWER` varchar(128) DEFAULT NULL COMMENT '选项',
  `SORT` tinyint(1) DEFAULT NULL COMMENT '顺序',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6789 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `CNT` tinyint(3) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_outline
-- ----------------------------
DROP TABLE IF EXISTS `exam_outline`;
CREATE TABLE `exam_outline` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) DEFAULT NULL COMMENT '学生ID',
  `EXAMPAPER_ID` int(11) DEFAULT NULL COMMENT '试卷ID',
  `EXAMQUESTION_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `OUTLINE_ID` int(11) DEFAULT NULL COMMENT '大纲ID',
  `DESERVED` tinyint(3) DEFAULT NULL COMMENT '应得分数',
  `ACTUALLY` tinyint(3) DEFAULT NULL COMMENT '实得分数',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exampaper
-- ----------------------------
DROP TABLE IF EXISTS `exampaper`;
CREATE TABLE `exampaper` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL COMMENT '名称',
  `SUBJECT_ID` int(11) DEFAULT NULL COMMENT '科目ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for examquestion
-- ----------------------------
DROP TABLE IF EXISTS `examquestion`;
CREATE TABLE `examquestion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXAMPAPER_ID` int(11) DEFAULT NULL COMMENT '试卷ID',
  `SORT` tinyint(3) DEFAULT NULL COMMENT '顺序',
  `QUESTION_TYPE` tinyint(2) DEFAULT NULL COMMENT '题型 1、选择题',
  `QUESTION_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for outline
-- ----------------------------
DROP TABLE IF EXISTS `outline`;
CREATE TABLE `outline` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_ID` int(11) DEFAULT NULL COMMENT '科目ID',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父级ID',
  `SORT` tinyint(3) DEFAULT NULL COMMENT '顺序',
  `TITLE` varchar(128) DEFAULT NULL COMMENT '标题',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '描述',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question_outline
-- ----------------------------
DROP TABLE IF EXISTS `question_outline`;
CREATE TABLE `question_outline` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_TYPE` tinyint(2) DEFAULT NULL COMMENT '题型 1、选择题',
  `QUESTION_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `OUTLINE_ID` int(11) DEFAULT NULL COMMENT '大纲ID',
  `WEIGHT` tinyint(2) DEFAULT NULL COMMENT '权重',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` char(50) DEFAULT NULL COMMENT '姓名',
  `CLASS_ID` int(11) DEFAULT NULL COMMENT '班级ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_exam_result
-- ----------------------------
DROP TABLE IF EXISTS `student_exam_result`;
CREATE TABLE `student_exam_result` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) DEFAULT NULL COMMENT '学生ID',
  `EXAMPAPER_ID` int(11) DEFAULT NULL COMMENT '试卷ID',
  `SORT` tinyint(3) DEFAULT NULL COMMENT '顺序',
  `QUESTION_TYPE` tinyint(3) DEFAULT NULL COMMENT '题型 1、选择题',
  `EXAMQUESTION_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `ANSWER` varchar(128) DEFAULT NULL COMMENT '答案',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL COMMENT '科目名称',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '科目描述',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subject_question
-- ----------------------------
DROP TABLE IF EXISTS `subject_question`;
CREATE TABLE `subject_question` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_ID` int(11) DEFAULT NULL COMMENT '科目ID',
  `QUESTION_TYPE` tinyint(3) DEFAULT NULL COMMENT '题目类型 1、单选题',
  `QUESTION_ID` int(11) DEFAULT NULL COMMENT '题目ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2243 DEFAULT CHARSET=utf8;
