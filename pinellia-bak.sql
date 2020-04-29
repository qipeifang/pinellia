/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : pinellia

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-25 17:16:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tc_auth_menu`
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
-- Table structure for `tc_auth_org`
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
-- Table structure for `tc_auth_org_user`
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

-- ----------------------------
-- Table structure for `tc_auth_role`
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
-- Table structure for `tc_auth_role_menu`
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
-- Table structure for `tc_auth_user`
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_auth_user
-- ----------------------------
INSERT INTO `tc_auth_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '', null, 'oqlDI5bRnu8DMcubX66eD-_d2Iik');

-- ----------------------------
-- Table structure for `tc_auth_user_role`
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

-- ----------------------------
-- Table structure for `tc_sys_dict`
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
-- Table structure for `tc_sys_dict_category`
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
-- Table structure for `tc_sys_operation_log`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tc_sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tc_sys_param`
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
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_DL', '百度ocr驾驶证识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_P1', '百度ocr身份证识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/idcard');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_VIN', '百度ocr车架号识别路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/vin_code');
INSERT INTO `tc_sys_param` VALUES ('baidu_orc_id_car_url_VL', '百度ocr行驶证校验路径', 'https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license');
INSERT INTO `tc_sys_param` VALUES ('INIT_USER_PASSWORD', '用户初始密码', '123456');
INSERT INTO `tc_sys_param` VALUES ('linux_image_url', 'Linux系统下图片的保存路径', '/opt/tc/images');
INSERT INTO `tc_sys_param` VALUES ('wechat_app_id', '微信小程序id', '');
INSERT INTO `tc_sys_param` VALUES ('wechat_jscode2session_url', '微信获取相关信息的接口路径', 'https://api.weixin.qq.com/sns/jscode2session');
INSERT INTO `tc_sys_param` VALUES ('wechat_secret', '微信小程序密钥', '');
INSERT INTO `tc_sys_param` VALUES ('window_image_url', 'window系统下图片的保存路径', 'D:\\document\\test');
