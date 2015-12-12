/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : tpm

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-12-12 14:10:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tpm_equipment_info`
-- ----------------------------
DROP TABLE IF EXISTS `tpm_equipment_info`;
CREATE TABLE `tpm_equipment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `encoding_equipment` varchar(20) DEFAULT NULL COMMENT '设备编码',
  `number_equipment` varchar(20) DEFAULT NULL COMMENT '设备型号',
  `name_equipment` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `number_materiel` varchar(20) DEFAULT NULL COMMENT '物料编码',
  `factory` varchar(20) DEFAULT NULL COMMENT '出厂厂家',
  `use_location` varchar(20) DEFAULT NULL COMMENT '使用地点',
  `kind_equipment` varchar(20) DEFAULT NULL COMMENT '设备种类',
  `create_userName` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_userNO` varchar(20) DEFAULT NULL COMMENT '创建人编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modify_userName` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `last_modify_userNO` varchar(20) DEFAULT NULL COMMENT '最后修改人编号',
  `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpm_equipment_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `resourceID` int(10) NOT NULL,
  `resourceGrade` int(1) NOT NULL,
  `accessPath` varchar(50) DEFAULT '',
  `checked` varchar(5) DEFAULT '',
  `delFlag` int(1) DEFAULT '0',
  `parentID` int(10) DEFAULT NULL,
  `resourceCode` varchar(20) DEFAULT '',
  `resourceDesc` varchar(20) DEFAULT '',
  `resourceName` varchar(20) DEFAULT '',
  `resourceOrder` varchar(20) DEFAULT '',
  `resourceType` varchar(20) DEFAULT '',
  PRIMARY KEY (`resourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('2', '2', '', 'false', '0', '1', '', '', '空压站设备', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('3', '2', '', 'false', '0', '1', '', '', '制冷制设备(卷烟区)', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('4', '2', '', 'false', '0', '1', '', '', '制冷制设备(办公区)', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('5', '2', '', 'false', '0', '1', '', '', '中水站设备', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('6', '2', 'airCompressorStation/electricAppliance.do', 'false', '0', '1', '', '', '抽水组设备', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('7', '3', 'airCompressorStation/controlSystem.do', 'false', '0', '2', '', '', '电器设备', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('8', '3', '', 'false', '0', '2', '', '', '控制系统', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('9', '3', '', 'false', '0', '2', '', '', '机器设备', '0', 'ServiceNode');
INSERT INTO `t_menu` VALUES ('10', '2', '', 'false', '0', '1', '', '', '用户管理', '0', 'SystemNode');
INSERT INTO `t_menu` VALUES ('11', '2', '', 'false', '0', '1', '', '', '角色管理', '0', 'SystemNode');
INSERT INTO `t_menu` VALUES ('12', '2', '', 'false', '0', '1', '', '', '权限管理', '0', 'SystemNode');
INSERT INTO `t_menu` VALUES ('13', '2', '', 'false', '0', '1', '', '', '组织管理', '0', 'SystemNode');
INSERT INTO `t_menu` VALUES ('14', '2', '', 'false', '0', '1', '', '', '邮件服务', '0', 'OtherNode');
INSERT INTO `t_menu` VALUES ('15', '2', '', 'false', '0', '1', '', '', '其他服务', '0', 'OtherNode');
INSERT INTO `t_menu` VALUES ('16', '3', '', 'false', '0', '14', '', '', '发送邮件', '0', 'OtherNode');
INSERT INTO `t_menu` VALUES ('17', '3', '', 'false', '0', '14', '', '', '自动发送', '0', 'OtherNode');
INSERT INTO `t_menu` VALUES ('18', '3', '', 'false', '0', '14', '', '', '邮件管理', '0', 'OtherNode');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `user_age` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '20');
