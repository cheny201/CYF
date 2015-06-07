/*
Navicat MySQL Data Transfer

Source Server         : ROOT
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2014-11-07 18:36:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cyf_resource
-- ----------------------------
DROP TABLE IF EXISTS t_cyf_resource;
CREATE TABLE t_cyf_resource (
  RESOURCE_CODE bigint(20) NOT NULL DEFAULT '0',
  RESOURCE_NAME varchar(255) DEFAULT NULL,
  RESOURCE_TYPE varchar(255) DEFAULT NULL,
  URL varchar(255) DEFAULT NULL,
  ENABLED char(1) DEFAULT NULL,
  SORT_ORDER int(11) DEFAULT NULL,
  INSERT_TIME timestamp NULL DEFAULT NULL,
  UPDATE_TIME timestamp NULL DEFAULT NULL,
  INSERT_USER varchar(255) DEFAULT NULL,
  UPDATE_USER varchar(255) DEFAULT NULL,
  PARENT_ID varchar(255) DEFAULT NULL,
  ICON1 varchar(255) DEFAULT NULL,
  ICON2 varchar(255) DEFAULT NULL,
  TARGET varchar(255) DEFAULT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  PRIMARY KEY (RESOURCE_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cyf_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_cyf_role
-- ----------------------------
DROP TABLE IF EXISTS t_cyf_role;
CREATE TABLE t_cyf_role (
  ROLE_CODE varchar(255) NOT NULL,
  ROLE_NAME varchar(255) DEFAULT NULL,
  DESCRIPTION varchar(255) DEFAULT NULL,
  ORG_CODE varchar(255) DEFAULT NULL,
  ENABLED char(1) NOT NULL,
  INSERT_USER varchar(255) DEFAULT NULL,
  UPDATE_USER varchar(255) DEFAULT NULL,
  INSERT_TIME timestamp NULL DEFAULT NULL,
  UPDATE_TIME timestamp NULL DEFAULT NULL,
  PRIMARY KEY (ROLE_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cyf_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_cyf_role_resource
-- ----------------------------
DROP TABLE IF EXISTS t_cyf_role_resource;
CREATE TABLE t_cyf_role_resource (
  ROLE_CODE bigint(20) NOT NULL DEFAULT '0',
  RESOURCE_CODE bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (ROLE_CODE,RESOURCE_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cyf_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_cyf_user
-- ----------------------------
DROP TABLE IF EXISTS t_cyf_user;
CREATE TABLE t_cyf_user (
  USER_CODE varchar(255) NOT NULL,
  PASSWORD varchar(255) NOT NULL,
  USER_NAME varchar(255) DEFAULT NULL,
  USER_TYPE varchar(255) DEFAULT NULL,
  COM_CODE varchar(255) DEFAULT NULL,
  ENABLED char(1) DEFAULT NULL,
  INSERT_TIME timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UPDATE_TIME timestamp NULL DEFAULT NULL,
  PRIMARY KEY (USER_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cyf_user
-- ----------------------------
INSERT INTO t_cyf_user VALUES ('admin', '202CB962AC59075B964B07152D234B70', '超级管理员', '0', '00000', '1', '2014-11-01 21:11:41', null);

-- ----------------------------
-- Table structure for t_cyf_user_role
-- ----------------------------
DROP TABLE IF EXISTS t_cyf_user_role;
CREATE TABLE t_cyf_user_role (
  USER_CODE varchar(255) NOT NULL DEFAULT '',
  ROLE_CODE bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (USER_CODE,ROLE_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cyf_user_role
-- ----------------------------
