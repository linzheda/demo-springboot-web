/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-03-12 13:59:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_function
-- ----------------------------
DROP TABLE IF EXISTS `auth_function`;
CREATE TABLE `auth_function` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT,
  `fname` varchar(64) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `url` varchar(64) DEFAULT NULL,
  `serial_num` bigint(20) NOT NULL,
  `accordion` bigint(20) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '是',
  `buttonId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_function
-- ----------------------------
INSERT INTO `auth_function` VALUES ('1', '菜单项', '-1', null, '1', '1', '是', null);
INSERT INTO `auth_function` VALUES ('2', '权限管理', '1', null, '1', '2', '是', null);
INSERT INTO `auth_function` VALUES ('3', '用户管理', '2', 'back/user/toUserManager.action', '1', '3', '是', null);
INSERT INTO `auth_function` VALUES ('4', '角色管理', '2', 'back/role/toRoleManager.action', '2', '3', '是', null);
INSERT INTO `auth_function` VALUES ('5', '菜单管理', '2', 'back/function/toFunctionManager.action', '3', '3', '是', null);
INSERT INTO `auth_function` VALUES ('6', '用户授权', '2', 'back/userRole/toUserGrantManager.action', '4', '3', '是', null);
INSERT INTO `auth_function` VALUES ('7', '用户管理_添加', '3', null, '1', '4', '否', 'button_user_add');
INSERT INTO `auth_function` VALUES ('8', '用户管理_删除', '3', null, '2', '4', '是', 'button_user_delete');
INSERT INTO `auth_function` VALUES ('9', '用户管理_修改', '3', null, '3', '4', '是', 'button_user_update');
INSERT INTO `auth_function` VALUES ('10', '角色管理_添加', '4', null, '1', '4', '是', 'button_role_add');
INSERT INTO `auth_function` VALUES ('11', '角色管理_修改', '4', null, '2', '4', '是', 'button_role_update');
INSERT INTO `auth_function` VALUES ('12', '角色管理_删除', '4', null, '3', '4', '是', 'button_role_delete');
INSERT INTO `auth_function` VALUES ('13', '菜单管理_修改', '5', null, '1', '4', '是', 'button_function_update');
INSERT INTO `auth_function` VALUES ('14', '用户授权_重置', '6', null, '1', '4', '是', 'button_usergrant_reset');
INSERT INTO `auth_function` VALUES ('15', '用户授权_设置', '6', null, '2', '4', '是', 'button_usergrant_set');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL,
  `addTime` date NOT NULL,
  `updateTime` date DEFAULT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rname` (`rname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', 'admin', '2017-07-10', null);
INSERT INTO `auth_role` VALUES ('11', 'test', '2018-03-11', null);

-- ----------------------------
-- Table structure for auth_role_function
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_function`;
CREATE TABLE `auth_role_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL,
  `status` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `function_id` (`function_id`),
  KEY `auth_role_function_ibfk_1` (`role_id`),
  CONSTRAINT `auth_role_function_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `auth_role_function_ibfk_2` FOREIGN KEY (`function_id`) REFERENCES `auth_function` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_function
-- ----------------------------
INSERT INTO `auth_role_function` VALUES ('2', '1', '2', '1');
INSERT INTO `auth_role_function` VALUES ('3', '1', '3', '1');
INSERT INTO `auth_role_function` VALUES ('4', '1', '4', '1');
INSERT INTO `auth_role_function` VALUES ('5', '1', '5', '1');
INSERT INTO `auth_role_function` VALUES ('6', '1', '6', '1');
INSERT INTO `auth_role_function` VALUES ('7', '1', '7', '1');
INSERT INTO `auth_role_function` VALUES ('8', '1', '8', '1');
INSERT INTO `auth_role_function` VALUES ('9', '1', '9', '1');
INSERT INTO `auth_role_function` VALUES ('10', '1', '10', '1');
INSERT INTO `auth_role_function` VALUES ('11', '1', '11', '1');
INSERT INTO `auth_role_function` VALUES ('12', '1', '12', '1');
INSERT INTO `auth_role_function` VALUES ('13', '1', '13', '1');
INSERT INTO `auth_role_function` VALUES ('14', '1', '14', '1');
INSERT INTO `auth_role_function` VALUES ('15', '1', '15', '1');
INSERT INTO `auth_role_function` VALUES ('20', '11', '2', '1');
INSERT INTO `auth_role_function` VALUES ('21', '11', '3', '1');
INSERT INTO `auth_role_function` VALUES ('22', '11', '7', '1');
INSERT INTO `auth_role_function` VALUES ('23', '11', '8', '1');
INSERT INTO `auth_role_function` VALUES ('24', '11', '9', '1');
INSERT INTO `auth_role_function` VALUES ('25', '11', '4', '1');
INSERT INTO `auth_role_function` VALUES ('26', '11', '10', '1');
INSERT INTO `auth_role_function` VALUES ('27', '11', '11', '1');
INSERT INTO `auth_role_function` VALUES ('28', '11', '12', '1');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `auth_user_role_ibfk_1` (`user_id`),
  KEY `auth_user_role_ibfk_2` (`role_id`),
  CONSTRAINT `auth_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `systemuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `auth_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for systemuser
-- ----------------------------
DROP TABLE IF EXISTS `systemuser`;
CREATE TABLE `systemuser` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL DEFAULT 'accpteacher',
  `remark` varchar(255) DEFAULT NULL,
  `registerTime` date DEFAULT NULL,
  `updateTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_systemuser_username` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemuser
-- ----------------------------
INSERT INTO `systemuser` VALUES ('1', 'admin', 'df884a17124cdab61457dfc58fed25caa125d2d8', ' ', '2017-07-10', '2017-09-06');
INSERT INTO `systemuser` VALUES ('7', 'test', '4028a0e356acc947fcd2bfbf00cef11e128d484a', null, '2018-03-11', null);
