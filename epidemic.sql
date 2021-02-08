/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : epidemic

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/02/2021 11:21:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `community_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '社区名称',
  `community_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '社区地址',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------

-- ----------------------------
-- Table structure for condition
-- ----------------------------
DROP TABLE IF EXISTS `condition`;
CREATE TABLE `condition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `is_abnormal` tinyint(1) NULL DEFAULT 0 COMMENT '是否身体异常:0为正常，1为异常',
  `is_high_risk` tinyint(1) NULL DEFAULT 0 COMMENT '是否高风险地区回来0为否，1为是',
  `home_quarantine_day` int(2) NULL DEFAULT 0 COMMENT '居家隔离天数',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of condition
-- ----------------------------

-- ----------------------------
-- Table structure for estate
-- ----------------------------
DROP TABLE IF EXISTS `estate`;
CREATE TABLE `estate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `estate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区名称',
  `estate_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区地址',
  `community_id` int(11) NOT NULL COMMENT '所属社区id',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estate
-- ----------------------------

-- ----------------------------
-- Table structure for house_number
-- ----------------------------
DROP TABLE IF EXISTS `house_number`;
CREATE TABLE `house_number`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门牌号',
  `estate_id` int(11) NULL DEFAULT NULL COMMENT '所属小区id',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_number
-- ----------------------------

-- ----------------------------
-- Table structure for out_record
-- ----------------------------
DROP TABLE IF EXISTS `out_record`;
CREATE TABLE `out_record`  (
  `id` int(11) NOT NULL COMMENT '外出记录主键，不自动递增，代码生成',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `thing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外出事项',
  `out_start_time` datetime(0) NULL DEFAULT NULL COMMENT '外出时间',
  `out_back_time` datetime(0) NULL DEFAULT NULL COMMENT '返回时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of out_record
-- ----------------------------

-- ----------------------------
-- Table structure for real_time_data
-- ----------------------------
DROP TABLE IF EXISTS `real_time_data`;
CREATE TABLE `real_time_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地区',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `e_diagnosis` int(255) NULL DEFAULT NULL COMMENT '现存确诊',
  `c_diagnosis` int(255) NULL DEFAULT NULL COMMENT '累计确诊',
  `abroad` int(255) NULL DEFAULT NULL COMMENT '境外输入',
  `asymptomatic` int(255) NULL DEFAULT NULL COMMENT '无症状',
  `e_suspected` int(255) NULL DEFAULT NULL COMMENT '现存疑似',
  `e_severe` int(255) NULL DEFAULT NULL COMMENT '现存重症',
  `c_cure` int(255) NULL DEFAULT NULL COMMENT '累计治愈',
  `c_death` int(255) NULL DEFAULT NULL COMMENT '累计死亡',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of real_time_data
-- ----------------------------
INSERT INTO `real_time_data` VALUES (1, '国内疫情', '2021-02-04', 2157, 101167, 4758, 788, 1, 49, 94180, 4830, NULL);
INSERT INTO `real_time_data` VALUES (6, '国内疫情', '2021-02-07', 1844, 101272, 4790, 682, 3, 25, 94597, 4831, '2021-02-07 10:09:10');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `start_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发地',
  `end_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达地',
  `start_date` date NULL DEFAULT NULL COMMENT '出发日期',
  `end_date` date NULL DEFAULT NULL COMMENT '到达日期',
  `vehicle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交通工具',
  `vehicle_seat_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座位号',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route
-- ----------------------------

-- ----------------------------
-- Table structure for temperature
-- ----------------------------
DROP TABLE IF EXISTS `temperature`;
CREATE TABLE `temperature`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `temperature` float NULL DEFAULT NULL COMMENT '体温',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temperature
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` tinyint(1) NOT NULL DEFAULT 0 COMMENT '角色，1为管理员，0为普通',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `community` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区',
  `estate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区',
  `house_number_id` int(11) NULL DEFAULT NULL COMMENT '门牌号id',
  `house_number_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门牌号信息',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号,不用int是因为有的末位为X',
  `gender` tinyint(1) NULL DEFAULT 0 COMMENT '性别:1为男，0为女',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_base_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
