/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pinellia

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-06-20 09:38:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tc_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_menu`;
CREATE TABLE `tc_auth_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(16) NOT NULL COMMENT '菜单编码',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `func` varchar(256) DEFAULT NULL COMMENT '前端功能，如果是菜单的话对应菜单路径，如果是按钮的话，对应按钮编码',
  `mapping` varchar(256) DEFAULT NULL COMMENT '后台接口对应的路径，即springmvc的requestMapping',
  `parentCode` varchar(16) DEFAULT NULL COMMENT '上级菜单，顶层菜单的上级菜单为空',
  `level` int(3) DEFAULT NULL COMMENT '层级',
  `sequence` int(3) DEFAULT NULL COMMENT '顺序',
  `type` int(1) DEFAULT NULL COMMENT '是否在菜单上展示，0：不展示为菜单（按钮或者纯后台api），1：展示为菜单',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态,0：正常，1：删除，2：停用',
  `authType` int(1) DEFAULT NULL COMMENT '权限类型，0：无需权限，1：超级管理员，2：操作员',
  PRIMARY KEY (`id`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_menu
-- ----------------------------
INSERT INTO `tc_auth_menu` VALUES ('1', 'SYS', '系统管理', '', '', '', '0', '21', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('2', 'SYS_USER', '用户管理', 'console/user/userIndex.html', '/user/**', 'SYS', '1', '1', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('3', 'SYS_ROLE', '角色管理', 'console/role/roleIndex.html', '/role/**', 'SYS', '1', '2', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('4', 'SYS_ORG', '组织机构', 'console/org/orgIndex.html', '/org/**', 'SYS', '1', '3', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('5', 'SYS_MENU', '菜单管理', 'console/menu/menuIndex.html', '/menu/**', 'SYS', '1', '4', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('6', 'SYS_DICT', '数据字典', 'console/dict/dictIndex.html', '/dict/**', 'SYS', '1', '6', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('7', 'SYS_AUTH', '权限管理', '', '/auth/**', 'SYS', '1', '8', '0', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('8', 'SYS_LOG', '操作日志', 'console/log/operationLogIndex.html', '/operationLog/findOperationLogPage', 'SYS', '1', '7', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('9', 'SYS_PARAM', '系统参数', 'console/sysParam/sysParamIndex.html', '/sysParam/**', 'SYS', '1', '6', '1', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('21', 'COMMON', '通用功能', '', '', '', '0', '22', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('22', 'FIND_MENU', '查询菜单信息', 'READ', '/auth/findMenu', 'COMMON', '1', '1', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('23', 'FIND_DICT', '查询数据字典', 'READ', '/dict/findDict', 'COMMON', '1', '2', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('24', 'UPDATE_PASSWD', '修改密码', 'UPDATE', '/user/updatePassword', 'COMMON', '1', '3', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('25', 'FIND_USER_INFO', '查询当前登录的用户信息', '', '/auth/findUserInfo', 'COMMON', '1', '4', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('26', 'FIND_USER_ORG', '查询登录用户的组织信息', '', '/org/findOrg', 'COMMON', '1', '5', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('31', 'Bind_WX', '帐号绑定微信', '', '/common/bindWxId', 'COMMON', '1', '6', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('32', 'Unbind_WX', '解绑微信帐号', '', '/common/unbindWxId', 'COMMON', '1', '7', '0', '0', '0');
INSERT INTO `tc_auth_menu` VALUES ('41', 'OCR', 'ocr识别', 'READ', '/ocr/**', '', '0', '23', '0', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('42', 'BAIDU_OCR_TOKEN', '获取百度OCR的TOKEN', 'READ', '/ocr/findBaiduOcrToken', 'OCR', '1', '1', '0', '0', '1');
INSERT INTO `tc_auth_menu` VALUES ('43', 'BAIDU_OCR_WORD', '百度OCR图片识别文字', 'READ', '/ocr/iamgeRecognition', 'OCR', '1', '2', '0', '0', '1');

-- ----------------------------
-- Table structure for tc_auth_org
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_org`;
CREATE TABLE `tc_auth_org` (
  `code` varchar(64) NOT NULL COMMENT '组织编码，每层编码在上层的编码下增加4位数字',
  `name` varchar(64) NOT NULL COMMENT '组织名称',
  `parentCode` varchar(64) DEFAULT NULL COMMENT '上层组织编码',
  `level` int(3) DEFAULT NULL COMMENT '层级',
  `sequence` int(3) DEFAULT NULL COMMENT '顺序',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态,0：正常，1：删除，2：停用',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_org
-- ----------------------------
INSERT INTO `tc_auth_org` VALUES ('0001', '根节点', '', '1', '1', '0');

-- ----------------------------
-- Table structure for tc_auth_org_user
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_org_user`;
CREATE TABLE `tc_auth_org_user` (
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `orgCode` varchar(64) NOT NULL COMMENT '组织编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_org_user
-- ----------------------------
INSERT INTO `tc_auth_org_user` VALUES ('admin', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('car', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('user', '00010001');
INSERT INTO `tc_auth_org_user` VALUES ('user', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('userdf', '00010001');
INSERT INTO `tc_auth_org_user` VALUES ('market', '00010001');
INSERT INTO `tc_auth_org_user` VALUES ('qpf', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('a', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('q', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('w', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('er', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('r', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('t', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('y', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('u', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('i', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('o', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('pt', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('p', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('aaq', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('aaa', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('aq', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('s', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('sa', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('dd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('d', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ff', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('f', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('g', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('gf', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('h', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ht', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('htt', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('tq', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('j', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('jy', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('k', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ku', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('leifengyang', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('l', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ls', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('z', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('zx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('zxx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('zs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('zss', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ze', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('fe', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('xz', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('xs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('fv', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('wd', '0001');
INSERT INTO `tc_auth_org_user` VALUES (' fbb', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('bv', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('bfefsd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('vfsd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('dv', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('mmn ', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('mghng', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('m', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('nn', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('bfgb', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('cc', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('dcsd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('frfsdc', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('bbcx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('vdvs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ds', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('geds', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('rgefs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('gefsd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('cdfw', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ffsds', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('sdce', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ddw', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('evd', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('fsdc', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('vdcsc', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('asdx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('fsds', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('scxcs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('fsdcs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ntb', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('hgfg', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('jyjj', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ukukj', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('kumhjnhn', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('njbx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('saxs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('azs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('sxa', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('ssscs', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('nnfvcx', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('bgnhy', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('rfsdwr', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('grtgdf', '0001');
INSERT INTO `tc_auth_org_user` VALUES (',ujkuik', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('2201703976', '0001');
INSERT INTO `tc_auth_org_user` VALUES ('aa', '0001');

-- ----------------------------
-- Table structure for tc_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_role`;
CREATE TABLE `tc_auth_role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `roleName` varchar(64) NOT NULL COMMENT '角色名称',
  `orgCode` varchar(64) DEFAULT NULL COMMENT '组织结构编码',
  `authType` int(1) DEFAULT NULL COMMENT '权限类型，1：超级管理员，2：操作员',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_role
-- ----------------------------
INSERT INTO `tc_auth_role` VALUES ('1', '超级管理员', '0001', '1');

-- ----------------------------
-- Table structure for tc_auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_role_menu`;
CREATE TABLE `tc_auth_role_menu` (
  `roleId` int(10) NOT NULL COMMENT '角色Id',
  `menuCode` varchar(16) NOT NULL COMMENT '菜单编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_role_menu
-- ----------------------------
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'FIND_MENU');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'COMMON');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'FIND_DICT');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_LOG');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_PARAM');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'UPDATE_PASSWD');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'BAIDU_OCR_TOKEN');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'OCR');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'BAIDU_OCR_WORD');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_USER');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'Bind_WX');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_ROLE');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'Unbind_WX');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_ORG');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'FIND_USER_INFO');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_MENU');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_DICT');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'SYS_AUTH');
INSERT INTO `tc_auth_role_menu` VALUES ('1', 'FIND_USER_ORG');

-- ----------------------------
-- Table structure for tc_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_user`;
CREATE TABLE `tc_auth_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态,0：正常，1：删除，2：停用',
  `idNo` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `type` varchar(16) DEFAULT NULL COMMENT '用户类型',
  `wxId` varchar(128) DEFAULT NULL COMMENT '微信Id',
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_user
-- ----------------------------
INSERT INTO `tc_auth_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '', null, 'oqlDI5bRnu8DMcubX66eD-_d2Iik');
INSERT INTO `tc_auth_user` VALUES ('6', 'qpf', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('7', 'a', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('9', 'w', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('10', 'er', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('11', 'r', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('12', 't', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('13', 'y', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('14', 'u', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('15', 'i', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('16', 'o', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('17', 'pt', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('18', 'p', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('19', 'aa', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('20', 'aaq', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('21', 'aaa', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('22', 'aq', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('23', '2201703976', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('24', 's', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('25', 'sa', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('26', 'dd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('27', 'd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('28', 'ff', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('29', 'f', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('30', 'g', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('31', 'gf', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('32', 'h', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('33', 'ht', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('34', 'htt', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('35', 'tq', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('36', 'j', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('37', 'jy', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('38', 'k', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('39', 'ku', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('40', 'leifengyang', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('41', 'l', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('42', 'ls', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('43', 'z', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('44', 'zx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('45', 'zxx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('46', 'zs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('47', 'zss', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('48', 'ze', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('49', 'fe', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('50', 'xz', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('51', 'xs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('52', 'fv', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('53', 'wd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('54', ' fbb', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('55', 'bv', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('56', 'bfefsd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('57', 'vfsd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('58', 'dv', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('59', 'mmn ', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('60', 'mghng', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('61', 'm', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('62', 'nn', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('63', 'bfgb', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('64', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('65', 'dcsd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('66', 'frfsdc', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('67', 'bbcx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('68', 'vdvs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('69', 'ds', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('70', 'geds', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('71', 'rgefs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('72', 'gefsd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('73', 'cdfw', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('74', 'ffsds', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('75', 'sdce', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('76', 'ddw', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('77', 'evd', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('78', 'fsdc', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('79', 'vdcsc', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('80', 'asdx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('81', 'fsds', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('82', 'scxcs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('83', 'fsdcs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('84', 'ntb', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('85', 'hgfg', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('86', 'jyjj', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('87', 'ukukj', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('88', 'kumhjnhn', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('89', 'njbx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('90', 'saxs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('91', 'azs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('92', 'sxa', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('93', 'ssscs', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('94', 'nnfvcx', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('95', 'bgnhy', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('96', ',ujkuik', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('97', 'rfsdwr', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);
INSERT INTO `tc_auth_user` VALUES ('98', 'grtgdf', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, null);

-- ----------------------------
-- Table structure for tc_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tc_auth_user_role`;
CREATE TABLE `tc_auth_user_role` (
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `roleId` int(10) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_user_role
-- ----------------------------
INSERT INTO `tc_auth_user_role` VALUES ('admin', '1');
INSERT INTO `tc_auth_user_role` VALUES ('qpf', '1');
INSERT INTO `tc_auth_user_role` VALUES ('a', '1');
INSERT INTO `tc_auth_user_role` VALUES ('q', '1');
INSERT INTO `tc_auth_user_role` VALUES ('w', '1');
INSERT INTO `tc_auth_user_role` VALUES ('er', '1');
INSERT INTO `tc_auth_user_role` VALUES ('r', '1');
INSERT INTO `tc_auth_user_role` VALUES ('t', '1');
INSERT INTO `tc_auth_user_role` VALUES ('y', '1');
INSERT INTO `tc_auth_user_role` VALUES ('u', '1');
INSERT INTO `tc_auth_user_role` VALUES ('i', '1');
INSERT INTO `tc_auth_user_role` VALUES ('o', '1');
INSERT INTO `tc_auth_user_role` VALUES ('pt', '1');
INSERT INTO `tc_auth_user_role` VALUES ('p', '1');
INSERT INTO `tc_auth_user_role` VALUES ('aaq', '1');
INSERT INTO `tc_auth_user_role` VALUES ('aaa', '1');
INSERT INTO `tc_auth_user_role` VALUES ('aq', '1');
INSERT INTO `tc_auth_user_role` VALUES ('s', '1');
INSERT INTO `tc_auth_user_role` VALUES ('sa', '1');
INSERT INTO `tc_auth_user_role` VALUES ('dd', '1');
INSERT INTO `tc_auth_user_role` VALUES ('d', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ff', '1');
INSERT INTO `tc_auth_user_role` VALUES ('f', '1');
INSERT INTO `tc_auth_user_role` VALUES ('g', '1');
INSERT INTO `tc_auth_user_role` VALUES ('gf', '1');
INSERT INTO `tc_auth_user_role` VALUES ('h', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ht', '1');
INSERT INTO `tc_auth_user_role` VALUES ('htt', '1');
INSERT INTO `tc_auth_user_role` VALUES ('tq', '1');
INSERT INTO `tc_auth_user_role` VALUES ('j', '1');
INSERT INTO `tc_auth_user_role` VALUES ('jy', '1');
INSERT INTO `tc_auth_user_role` VALUES ('k', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ku', '1');
INSERT INTO `tc_auth_user_role` VALUES ('leifengyang', '1');
INSERT INTO `tc_auth_user_role` VALUES ('l', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ls', '1');
INSERT INTO `tc_auth_user_role` VALUES ('z', '1');
INSERT INTO `tc_auth_user_role` VALUES ('zx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('zxx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('zs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('zss', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ze', '1');
INSERT INTO `tc_auth_user_role` VALUES ('fe', '1');
INSERT INTO `tc_auth_user_role` VALUES ('xz', '1');
INSERT INTO `tc_auth_user_role` VALUES ('xs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('fv', '1');
INSERT INTO `tc_auth_user_role` VALUES ('wd', '1');
INSERT INTO `tc_auth_user_role` VALUES (' fbb', '1');
INSERT INTO `tc_auth_user_role` VALUES ('bv', '1');
INSERT INTO `tc_auth_user_role` VALUES ('vfsd', '1');
INSERT INTO `tc_auth_user_role` VALUES ('dv', '1');
INSERT INTO `tc_auth_user_role` VALUES ('mmn ', '1');
INSERT INTO `tc_auth_user_role` VALUES ('mghng', '1');
INSERT INTO `tc_auth_user_role` VALUES ('m', '1');
INSERT INTO `tc_auth_user_role` VALUES ('nn', '1');
INSERT INTO `tc_auth_user_role` VALUES ('bfgb', '1');
INSERT INTO `tc_auth_user_role` VALUES ('cc', '1');
INSERT INTO `tc_auth_user_role` VALUES ('dcsd', '1');
INSERT INTO `tc_auth_user_role` VALUES ('frfsdc', '1');
INSERT INTO `tc_auth_user_role` VALUES ('bbcx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('vdvs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ds', '1');
INSERT INTO `tc_auth_user_role` VALUES ('geds', '1');
INSERT INTO `tc_auth_user_role` VALUES ('rgefs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('gefsd', '1');
INSERT INTO `tc_auth_user_role` VALUES ('cdfw', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ffsds', '1');
INSERT INTO `tc_auth_user_role` VALUES ('sdce', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ddw', '1');
INSERT INTO `tc_auth_user_role` VALUES ('evd', '1');
INSERT INTO `tc_auth_user_role` VALUES ('fsdc', '1');
INSERT INTO `tc_auth_user_role` VALUES ('vdcsc', '1');
INSERT INTO `tc_auth_user_role` VALUES ('asdx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('fsds', '1');
INSERT INTO `tc_auth_user_role` VALUES ('scxcs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('fsdcs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ntb', '1');
INSERT INTO `tc_auth_user_role` VALUES ('hgfg', '1');
INSERT INTO `tc_auth_user_role` VALUES ('jyjj', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ukukj', '1');
INSERT INTO `tc_auth_user_role` VALUES ('kumhjnhn', '1');
INSERT INTO `tc_auth_user_role` VALUES ('njbx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('saxs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('azs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('sxa', '1');
INSERT INTO `tc_auth_user_role` VALUES ('ssscs', '1');
INSERT INTO `tc_auth_user_role` VALUES ('nnfvcx', '1');
INSERT INTO `tc_auth_user_role` VALUES ('bgnhy', '1');
INSERT INTO `tc_auth_user_role` VALUES ('rfsdwr', '1');
INSERT INTO `tc_auth_user_role` VALUES ('grtgdf', '1');
INSERT INTO `tc_auth_user_role` VALUES (',ujkuik', '1');
INSERT INTO `tc_auth_user_role` VALUES ('2201703976', '1');
INSERT INTO `tc_auth_user_role` VALUES ('aa', '1');

-- ----------------------------
-- Table structure for tc_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `tc_sys_dict`;
CREATE TABLE `tc_sys_dict` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '编码，相同的category下，code不能重复',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `des` varchar(128) DEFAULT NULL COMMENT '说明',
  `category` varchar(32) NOT NULL COMMENT '类型编码，对应tc_sys_dict_category表',
  `sequence` int(3) DEFAULT NULL COMMENT '顺序',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态,0：正常，1：删除，2：停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_sys_dict
-- ----------------------------
INSERT INTO `tc_sys_dict` VALUES ('1', '0', '正常', '正常', 'DATA_STATE', '1', '0');
INSERT INTO `tc_sys_dict` VALUES ('2', '1', '删除', '删除', 'DATA_STATE', '2', '0');
INSERT INTO `tc_sys_dict` VALUES ('3', '2', '停用', '停用', 'DATA_STATE', '3', '0');
INSERT INTO `tc_sys_dict` VALUES ('5', 'ADD', '新增', '新增', 'BUTTON', '2', '0');
INSERT INTO `tc_sys_dict` VALUES ('6', 'UPDATE', '修改', '修改', 'BUTTON', '3', '0');
INSERT INTO `tc_sys_dict` VALUES ('7', 'DELETE', '删除', '删除', 'BUTTON', '4', '0');
INSERT INTO `tc_sys_dict` VALUES ('8', 'READ', '查询', '查询', 'BUTTON', '1', '0');
INSERT INTO `tc_sys_dict` VALUES ('9', '汉族', '汉族', '汉族', 'NATION', '1', '0');
INSERT INTO `tc_sys_dict` VALUES ('10', '满族', '满族', '满族', 'NATION', '2', '0');
INSERT INTO `tc_sys_dict` VALUES ('11', '蒙古族', '蒙古族', '蒙古族', 'NATION', '3', '0');
INSERT INTO `tc_sys_dict` VALUES ('12', '回族', '回族', '回族', 'NATION', '4', '0');
INSERT INTO `tc_sys_dict` VALUES ('13', '藏族', '藏族', '藏族', 'NATION', '5', '0');
INSERT INTO `tc_sys_dict` VALUES ('14', '维吾尔族', '维吾尔族', '维吾尔族', 'NATION', '6', '0');
INSERT INTO `tc_sys_dict` VALUES ('15', '苗族', '苗族', '苗族', 'NATION', '7', '0');
INSERT INTO `tc_sys_dict` VALUES ('16', '彝族', '彝族', '彝族', 'NATION', '8', '0');
INSERT INTO `tc_sys_dict` VALUES ('17', '壮族', '壮族', '壮族', 'NATION', '9', '0');
INSERT INTO `tc_sys_dict` VALUES ('18', '布依族', '布依族', '布依族', 'NATION', '10', '0');
INSERT INTO `tc_sys_dict` VALUES ('19', '侗族', '侗族', '侗族', 'NATION', '11', '0');
INSERT INTO `tc_sys_dict` VALUES ('20', '瑶族', '瑶族', '瑶族', 'NATION', '12', '0');
INSERT INTO `tc_sys_dict` VALUES ('21', '白族', '白族', '白族', 'NATION', '13', '0');
INSERT INTO `tc_sys_dict` VALUES ('22', '土家族', '土家族', '土家族', 'NATION', '14', '0');
INSERT INTO `tc_sys_dict` VALUES ('23', '哈尼族', '哈尼族', '哈尼族', 'NATION', '15', '0');
INSERT INTO `tc_sys_dict` VALUES ('24', '哈萨克族', '哈萨克族', '哈萨克族', 'NATION', '16', '0');
INSERT INTO `tc_sys_dict` VALUES ('25', '傣族', '傣族', '傣族', 'NATION', '17', '0');
INSERT INTO `tc_sys_dict` VALUES ('26', '黎族', '黎族', '黎族', 'NATION', '18', '0');
INSERT INTO `tc_sys_dict` VALUES ('27', '傈僳族', '傈僳族', '傈僳族', 'NATION', '19', '0');
INSERT INTO `tc_sys_dict` VALUES ('28', '佤族', '佤族', '佤族', 'NATION', '20', '0');
INSERT INTO `tc_sys_dict` VALUES ('29', '畲族', '畲族', '畲族', 'NATION', '21', '0');
INSERT INTO `tc_sys_dict` VALUES ('30', '高山族', '高山族', '高山族', 'NATION', '22', '0');
INSERT INTO `tc_sys_dict` VALUES ('31', '拉祜族', '拉祜族', '拉祜族', 'NATION', '23', '0');
INSERT INTO `tc_sys_dict` VALUES ('32', '水族', '水族', '水族', 'NATION', '24', '0');
INSERT INTO `tc_sys_dict` VALUES ('33', '东乡族', '东乡族', '东乡族', 'NATION', '25', '0');
INSERT INTO `tc_sys_dict` VALUES ('34', '纳西族', '纳西族', '纳西族', 'NATION', '26', '0');
INSERT INTO `tc_sys_dict` VALUES ('35', '景颇族', '景颇族', '景颇族', 'NATION', '27', '0');
INSERT INTO `tc_sys_dict` VALUES ('36', '柯尔克孜族', '柯尔克孜族', '柯尔克孜族', 'NATION', '28', '0');
INSERT INTO `tc_sys_dict` VALUES ('37', '土族', '土族', '土族', 'NATION', '29', '0');
INSERT INTO `tc_sys_dict` VALUES ('38', '达斡尔族', '达斡尔族', '达斡尔族', 'NATION', '30', '0');
INSERT INTO `tc_sys_dict` VALUES ('39', '仫佬族', '仫佬族', '仫佬族', 'NATION', '31', '0');
INSERT INTO `tc_sys_dict` VALUES ('40', '羌族', '羌族', '羌族', 'NATION', '32', '0');
INSERT INTO `tc_sys_dict` VALUES ('41', '布朗族', '布朗族', '布朗族', 'NATION', '33', '0');
INSERT INTO `tc_sys_dict` VALUES ('42', '撒拉族', '撒拉族', '撒拉族', 'NATION', '34', '0');
INSERT INTO `tc_sys_dict` VALUES ('43', '毛南族', '毛南族', '毛南族', 'NATION', '35', '0');
INSERT INTO `tc_sys_dict` VALUES ('44', '仡佬族', '仡佬族', '仡佬族', 'NATION', '36', '0');
INSERT INTO `tc_sys_dict` VALUES ('45', '锡伯族', '锡伯族', '锡伯族', 'NATION', '37', '0');
INSERT INTO `tc_sys_dict` VALUES ('46', '阿昌族', '阿昌族', '阿昌族', 'NATION', '38', '0');
INSERT INTO `tc_sys_dict` VALUES ('47', '普米族', '普米族', '普米族', 'NATION', '39', '0');
INSERT INTO `tc_sys_dict` VALUES ('48', '朝鲜族', '朝鲜族', '朝鲜族', 'NATION', '40', '0');
INSERT INTO `tc_sys_dict` VALUES ('49', '塔吉克族', '塔吉克族', '塔吉克族', 'NATION', '41', '0');
INSERT INTO `tc_sys_dict` VALUES ('50', '怒族', '怒族', '怒族', 'NATION', '42', '0');
INSERT INTO `tc_sys_dict` VALUES ('51', '乌孜别克族', '乌孜别克族', '乌孜别克族', 'NATION', '43', '0');
INSERT INTO `tc_sys_dict` VALUES ('52', '俄罗斯族', '俄罗斯族', '俄罗斯族', 'NATION', '44', '0');
INSERT INTO `tc_sys_dict` VALUES ('53', '鄂温克族', '鄂温克族', '鄂温克族', 'NATION', '45', '0');
INSERT INTO `tc_sys_dict` VALUES ('54', '德昂族', '德昂族', '德昂族', 'NATION', '46', '0');
INSERT INTO `tc_sys_dict` VALUES ('55', '保安族', '保安族', '保安族', 'NATION', '47', '0');
INSERT INTO `tc_sys_dict` VALUES ('56', '裕固族', '裕固族', '裕固族', 'NATION', '48', '0');
INSERT INTO `tc_sys_dict` VALUES ('57', '京族', '京族', '京族', 'NATION', '49', '0');
INSERT INTO `tc_sys_dict` VALUES ('58', '塔塔尔族', '塔塔尔族', '塔塔尔族', 'NATION', '50', '0');
INSERT INTO `tc_sys_dict` VALUES ('59', '独龙族', '独龙族', '独龙族', 'NATION', '51', '0');
INSERT INTO `tc_sys_dict` VALUES ('60', '鄂伦春族', '鄂伦春族', '鄂伦春族', 'NATION', '52', '0');
INSERT INTO `tc_sys_dict` VALUES ('61', '赫哲族', '赫哲族', '赫哲族', 'NATION', '53', '0');
INSERT INTO `tc_sys_dict` VALUES ('62', '门巴族', '门巴族', '门巴族', 'NATION', '54', '0');
INSERT INTO `tc_sys_dict` VALUES ('63', '珞巴族', '珞巴族', '珞巴族', 'NATION', '55', '0');
INSERT INTO `tc_sys_dict` VALUES ('64', '基诺族', '基诺族', '基诺族', 'NATION', '56', '0');

-- ----------------------------
-- Table structure for tc_sys_dict_category
-- ----------------------------
DROP TABLE IF EXISTS `tc_sys_dict_category`;
CREATE TABLE `tc_sys_dict_category` (
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '类型编码',
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_sys_dict_category
-- ----------------------------
INSERT INTO `tc_sys_dict_category` VALUES ('BUTTON', '按钮');
INSERT INTO `tc_sys_dict_category` VALUES ('DATA_STATE', '数据状态');
INSERT INTO `tc_sys_dict_category` VALUES ('NATION', '民族');
INSERT INTO `tc_sys_dict_category` VALUES ('OPERATION_TYPE', '操作类型');

-- ----------------------------
-- Table structure for tc_sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tc_sys_operation_log`;
CREATE TABLE `tc_sys_operation_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator` varchar(64) DEFAULT NULL COMMENT '操作人',
  `remoteAddr` varchar(64) DEFAULT NULL COMMENT '发起访问的主机地址',
  `method` varchar(1024) DEFAULT NULL COMMENT '调用方法',
  `args` text COMMENT '传入的参数',
  `operateTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operateType` varchar(1) DEFAULT NULL COMMENT '操作类型，C：新增，R：查询，U：修改，D：删除',
  `operateDesc` varchar(256) DEFAULT NULL COMMENT '操作描述',
  `state` int(1) DEFAULT NULL COMMENT '操作结果，0：成功，1：异常',
  `exception` varchar(1024) DEFAULT NULL COMMENT '异常信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_sys_operation_log
-- ----------------------------
INSERT INTO `tc_sys_operation_log` VALUES ('1', 'admin', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"qpf\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:07:15', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('2', 'admin', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"qpf\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"],\"id\":[\"6\"]}', '2020-04-24 09:07:21', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('3', 'admin', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"a\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:08:33', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('4', 'admin', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"q\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:08:45', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('5', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"w\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:10:45', 'S', '保存用户信息', '1', 'w，用户名已经存在');
INSERT INTO `tc_sys_operation_log` VALUES ('6', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"w\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:04', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('7', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"er\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:18', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('8', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"r\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:26', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('9', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"t\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:32', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('10', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"y\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:40', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('11', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"u\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:46', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('12', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"i\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:53', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('13', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"o\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:11:59', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('14', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"pt\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:07', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('15', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"p\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:15', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('16', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"a\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:32', 'S', '保存用户信息', '1', 'a，用户名已经存在');
INSERT INTO `tc_sys_operation_log` VALUES ('17', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"aa\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:37', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('18', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"aaq\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:45', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('19', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"aaa\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:12:55', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('20', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"aq\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:02', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('21', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"2201703976\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:11', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('22', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"s\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:18', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('23', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"sa\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:24', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('24', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"dd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:30', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('25', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"d\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:38', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('26', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ff\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:46', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('27', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"f\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:13:56', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('28', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"g\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:04', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('29', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"gf\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:11', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('30', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"h\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:17', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('31', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ht\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:24', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('32', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"htt\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:34', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('33', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"tq\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:40', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('34', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"j\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:50', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('35', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"jy\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:14:57', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('36', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"k\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:03', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('37', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ku\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:13', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('38', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"leifengyang\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:27', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('39', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"l\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:35', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('40', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ls\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:42', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('41', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"z\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:48', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('42', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"zx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:15:55', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('43', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"zxx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:03', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('44', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"zs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:09', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('45', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"zss\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:15', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('46', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ze\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:22', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('47', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"fe\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:28', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('48', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"xz\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:36', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('49', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"xs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:42', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('50', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"xs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:42', 'S', '保存用户信息', '1', 'xs，用户名已经存在');
INSERT INTO `tc_sys_operation_log` VALUES ('51', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"fv\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:49', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('52', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"wd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:16:55', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('53', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\" fbb\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:03', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('54', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"bv\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:12', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('55', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"bfefsd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"\"]}', '2020-04-24 09:17:29', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('56', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"vfsd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:36', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('57', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"dv\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:41', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('58', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"mmn \"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:50', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('59', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"mghng\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:17:59', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('60', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"m\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:05', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('61', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"nn\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:11', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('62', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"bfgb\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:17', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('63', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"cc\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:23', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('64', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"dcsd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:28', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('65', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"frfsdc\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:35', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('66', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"bbcx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:42', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('67', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"vdvs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:48', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('68', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ds\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:18:55', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('69', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"geds\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:02', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('70', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"rgefs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:09', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('71', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"gefsd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:15', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('72', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"cdfw\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:21', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('73', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ffsds\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:28', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('74', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"sdce\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:34', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('75', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ddw\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:41', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('76', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"evd\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:47', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('77', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"fsdc\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:19:53', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('78', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"vdcsc\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:00', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('79', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"asdx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:06', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('80', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"fsds\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:13', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('81', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"scxcs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:20', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('82', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"fsdcs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:26', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('83', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ntb\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:33', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('84', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"hgfg\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:42', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('85', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"jyjj\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:50', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('86', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ukukj\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:20:56', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('87', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"kumhjnhn\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:02', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('88', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"njbx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:09', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('89', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"saxs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:15', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('90', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"azs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:24', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('91', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"sxa\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:30', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('92', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"ssscs\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:36', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('93', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"nnfvcx\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:42', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('94', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"bgnhy\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:48', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('95', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\",ujkuik\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:21:54', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('96', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"rfsdwr\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:22:01', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('97', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"grtgdf\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"]}', '2020-04-24 09:22:09', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('98', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.menu.controller.impl.MenuControllerImpl.enableMenu(Integer)', '{\"id\":[\"2\"]}', '2020-04-24 09:23:47', 'U', '启用菜单', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('99', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.menu.controller.impl.MenuControllerImpl.enableMenu(Integer)', '{\"id\":[\"7\"]}', '2020-04-24 09:24:02', 'U', '启用菜单', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('100', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.menu.controller.impl.MenuControllerImpl.enableMenu(Integer)', '{\"id\":[\"22\"]}', '2020-04-24 09:24:11', 'U', '启用菜单', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('101', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.common.sysParam.controller.impl.SysParamControllerImpl.saveSysParam(SysParam)', '{\"code\":[\"baidu_orc_id_car_url_DL\"],\"name\":[\"123\"],\"value\":[\"https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license\"]}', '2020-04-25 02:33:28', 'S', '保存系统参数', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('102', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.common.sysParam.controller.impl.SysParamControllerImpl.saveSysParam(SysParam)', '{\"code\":[\"baidu_orc_id_car_url_DL\"],\"name\":[\"123dffsd\"],\"value\":[\"https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license\"]}', '2020-04-25 02:33:33', 'S', '保存系统参数', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('103', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\",ujkuik\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"],\"id\":[\"96\"]}', '2020-04-28 07:57:52', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('104', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"2201703976\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"],\"id\":[\"23\"]}', '2020-04-28 08:14:12', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('105', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"2201703976\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"],\"id\":[\"23\"]}', '2020-04-28 08:14:57', 'S', '保存用户信息', '0', null);
INSERT INTO `tc_sys_operation_log` VALUES ('106', 'qpf', '0:0:0:0:0:0:0:1', 'com.jokerchen.pinellia.console.user.controller.impl.UserControllerImpl.saveUser(UserVO)', '{\"username\":[\"aa\"],\"orgCode\":[\"0001\"],\"roleId\":[\"1\"],\"id\":[\"19\"]}', '2020-04-28 08:16:41', 'S', '保存用户信息', '0', null);

-- ----------------------------
-- Table structure for tc_sys_param
-- ----------------------------
DROP TABLE IF EXISTS `tc_sys_param`;
CREATE TABLE `tc_sys_param` (
  `code` varchar(32) NOT NULL COMMENT '系统参数代码',
  `name` varchar(64) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(2048) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_sys_param
-- ----------------------------
INSERT INTO `tc_sys_param` VALUES ('baidu_ocr_access_token_url', '获取百度ocr访问token的url', 'https://aip.baidubce.com/oauth/2.0/token');
INSERT INTO `tc_sys_param` VALUES ('baidu_ocr_api_key', '百度ocr创建的项目api key', '');
INSERT INTO `tc_sys_param` VALUES ('baidu_ocr_app_id', '百度ocr创建项目的app id', '');
INSERT INTO `tc_sys_param` VALUES ('baidu_ocr_secret_key', '百度ocr创建项目的Secret Key', '');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_C3', '百度ocr营业执照识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/business_license');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_DL', '123dffsd', 'https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_P1', '百度ocr身份证识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/idcard');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_VIN', '百度ocr车架号识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/vin_code');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_VL', '百度ocr行驶证校验路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license');
INSERT INTO `tc_sys_param` VALUES ('INIT_USER_PASSWORD', '用户初始密码', '123456');
INSERT INTO `tc_sys_param` VALUES ('linux_image_url', 'Linux系统下图片的保存路径', '/opt/tc/images');
INSERT INTO `tc_sys_param` VALUES ('wechat_app_id', '微信小程序id', '');
INSERT INTO `tc_sys_param` VALUES ('wechat_jscode2session_url', '微信获取相关信息的接口路径', 'https://api.weixin.qq.com/sns/jscode2session');
INSERT INTO `tc_sys_param` VALUES ('wechat_secret', '微信小程序密钥', '');
INSERT INTO `tc_sys_param` VALUES ('window_image_url', 'window系统下图片的保存路径', 'D:\\document\\test');
