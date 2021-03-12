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

 Date: 12/03/2021 13:12:17
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1, '东兴路社区', '重庆市南岸区南坪东路', '2021-03-10 15:34:33');
INSERT INTO `community` VALUES (2, '响水路社区', '重庆市南岸区响水路', '2021-03-04 14:43:43');
INSERT INTO `community` VALUES (3, '阳光社区', '重庆市南岸区宏声路', '2021-03-04 14:43:54');

-- ----------------------------
-- Table structure for estate
-- ----------------------------
DROP TABLE IF EXISTS `estate`;
CREATE TABLE `estate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `estate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区名称',
  `estate_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小区地址',
  `community_id` int(11) NULL DEFAULT NULL COMMENT '所属社区id',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estate
-- ----------------------------
INSERT INTO `estate` VALUES (1, '东原锦悦', '重庆市南岸区南坪街道南坪东路2巷2号', 1, '2021-02-20 10:09:01');
INSERT INTO `estate` VALUES (2, '星宇花园', '重庆市南岸区响水路72号', 2, '2021-03-04 14:41:04');
INSERT INTO `estate` VALUES (3, '明发江南景苑', '\r\n重庆南岸区南坪东路二巷16号', 1, '2021-03-04 14:40:08');
INSERT INTO `estate` VALUES (4, '响水景苑', '重庆南岸区响水路68号', 2, '2021-03-04 14:40:36');
INSERT INTO `estate` VALUES (5, '洋河·南滨花园', '重庆市南岸区宏声路37号', 3, '2021-03-04 14:42:17');
INSERT INTO `estate` VALUES (6, '阳光南滨', '重庆市南岸区南坪宏声路46号', 3, '2021-03-04 14:42:50');

-- ----------------------------
-- Table structure for out_record
-- ----------------------------
DROP TABLE IF EXISTS `out_record`;
CREATE TABLE `out_record`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外出记录主键，不自动递增，代码生成',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `thing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外出事项',
  `out_start_time` datetime(0) NULL DEFAULT NULL COMMENT '外出时间',
  `out_back_time` datetime(0) NULL DEFAULT NULL COMMENT '返回时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of out_record
-- ----------------------------
INSERT INTO `out_record` VALUES ('2021011517102', 2, NULL, '购物', '2021-01-15 10:19:00', '2021-01-15 17:19:09', '2021-03-07 16:13:24');
INSERT INTO `out_record` VALUES ('2021030715492', 2, NULL, '工作', '2021-03-07 10:19:00', '2021-03-07 17:26:00', '2021-03-07 15:49:59');

-- ----------------------------
-- Table structure for real_time_data
-- ----------------------------
DROP TABLE IF EXISTS `real_time_data`;
CREATE TABLE `real_time_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地区',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `e_diagnosis` int(11) NULL DEFAULT NULL COMMENT '现存确诊',
  `c_diagnosis` int(11) NULL DEFAULT NULL COMMENT '累计确诊',
  `abroad` int(11) NULL DEFAULT NULL COMMENT '境外输入',
  `asymptomatic` int(11) NULL DEFAULT NULL COMMENT '无症状',
  `e_suspected` int(11) NULL DEFAULT NULL COMMENT '现存疑似',
  `e_severe` int(11) NULL DEFAULT NULL COMMENT '现存重症',
  `c_cure` int(11) NULL DEFAULT NULL COMMENT '累计治愈',
  `c_death` int(11) NULL DEFAULT NULL COMMENT '累计死亡',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of real_time_data
-- ----------------------------
INSERT INTO `real_time_data` VALUES (8, '国内疫情', '2021-02-18', 893, 101614, 4903, 357, 6, 10, 95879, 4842, '2021-02-18 18:00:26');
INSERT INTO `real_time_data` VALUES (10, '国内疫情', '2021-02-19', 833, 101638, 4913, 338, 4, 8, 95963, 4842, '2021-02-19 18:00:19');
INSERT INTO `real_time_data` VALUES (12, '国内疫情', '2021-02-20', 811, 101662, 4921, 331, 2, 5, 96009, 4842, '2021-02-20 18:00:18');
INSERT INTO `real_time_data` VALUES (14, '国内疫情', '2021-02-21', 729, 101689, 4928, 316, 3, 2, 96118, 4842, '2021-02-21 18:00:18');
INSERT INTO `real_time_data` VALUES (16, '国内疫情', '2021-02-22', 713, 101700, 4939, 309, 3, 2, 96145, 4842, '2021-02-22 18:00:18');
INSERT INTO `real_time_data` VALUES (18, '国内疫情', '2021-02-23', 690, 101738, 4949, 302, 1, 1, 96206, 4842, '2021-02-23 18:00:18');
INSERT INTO `real_time_data` VALUES (20, '国内疫情', '2021-02-24', 663, 101771, 4961, 291, 3, 1, 96266, 4842, '2021-02-24 18:00:18');
INSERT INTO `real_time_data` VALUES (22, '国内疫情', '2021-02-25', 563, 101796, 4968, 279, 4, 1, 96390, 4843, '2021-02-25 18:00:18');
INSERT INTO `real_time_data` VALUES (24, '国内疫情', '2021-02-26', 535, 101826, 4974, 270, 1, 1, 96448, 4843, '2021-02-26 18:00:17');
INSERT INTO `real_time_data` VALUES (26, '国内疫情', '2021-02-27', 523, 101872, 4984, 258, 2, 1, 96506, 4843, '2021-02-27 18:00:18');
INSERT INTO `real_time_data` VALUES (28, '国内疫情', '2021-02-28', 512, 101901, 4990, 257, 3, 1, 96545, 4844, '2021-02-28 18:00:18');
INSERT INTO `real_time_data` VALUES (30, '国内疫情', '2021-03-01', 518, 101934, 5009, 254, 1, 1, 96571, 4845, '2021-03-01 18:00:17');
INSERT INTO `real_time_data` VALUES (32, '国内疫情', '2021-03-02', 492, 101958, 5020, 243, 1, 0, 96621, 4845, '2021-03-02 18:00:18');
INSERT INTO `real_time_data` VALUES (34, '国内疫情', '2021-03-03', 489, 101985, 5030, 249, 2, 0, 96651, 4845, '2021-03-03 18:00:17');
INSERT INTO `real_time_data` VALUES (36, '国内疫情', '2021-03-04', 463, 102006, 5040, 247, 2, 1, 96697, 4846, '2021-03-04 18:00:19');
INSERT INTO `real_time_data` VALUES (38, '国内疫情', '2021-03-05', 462, 102026, 5049, 255, 2, 1, 96718, 4846, '2021-03-05 18:00:18');
INSERT INTO `real_time_data` VALUES (40, '国内疫情', '2021-03-06', 452, 102051, 5059, 256, 0, 1, 96751, 4848, '2021-03-06 18:00:18');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL COMMENT '用户id',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 1, 'role_admin', '2021-02-22 16:10:55');
INSERT INTO `role` VALUES (2, 2, 'role_user', '2021-02-22 16:11:05');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `start_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发地',
  `end_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达地',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '出发日期',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '到达日期',
  `vehicle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交通工具',
  `vehicle_seat_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座位号',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, 2, NULL, '成都', '重庆', '2021-01-01 00:00:00', '2021-01-01 00:00:00', '高铁', '3车11F', '2021-02-20 10:14:21');

-- ----------------------------
-- Table structure for states
-- ----------------------------
DROP TABLE IF EXISTS `states`;
CREATE TABLE `states`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户名',
  `is_abnormal` tinyint(1) NULL DEFAULT 0 COMMENT '是否身体异常:0为正常，1为异常',
  `is_high_risk` tinyint(1) NULL DEFAULT 0 COMMENT '是否高风险地区回来0为否，1为是',
  `home_quarantine_day` int(11) NULL DEFAULT 0 COMMENT '居家隔离天数',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of states
-- ----------------------------
INSERT INTO `states` VALUES (1, 1, 'admin', 0, 1, 0, '2021-03-10 15:52:18');
INSERT INTO `states` VALUES (3, 2, 'ls', 1, 0, 0, '2021-03-07 15:47:00');

-- ----------------------------
-- Table structure for temperature
-- ----------------------------
DROP TABLE IF EXISTS `temperature`;
CREATE TABLE `temperature`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户名',
  `temperature` float NULL DEFAULT NULL COMMENT '体温',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of temperature
-- ----------------------------
INSERT INTO `temperature` VALUES (1, 2, NULL, 36.5, '2021-01-03', '2021-02-20 10:14:42');
INSERT INTO `temperature` VALUES (3, 1, 'admin', 36.5, '2021-03-05', '2021-03-06 14:57:32');
INSERT INTO `temperature` VALUES (4, 1, 'admin', 36.4, '2021-03-06', '2021-03-06 14:59:35');
INSERT INTO `temperature` VALUES (5, 2, 'ls', 36.6, '2021-03-10', '2021-03-10 15:48:25');
INSERT INTO `temperature` VALUES (6, 2, 'ls', 38, '2021-03-10', '2021-03-10 15:48:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$qsfgtTcpYoOn9gPyPk9YPeUaFoha5f5nuPSRK7eozHAJnUZ5G3cSy', '2021-02-23 17:51:20');
INSERT INTO `user` VALUES (2, 'ls', '$2a$10$PRPvIS.NvMwyx8.6TkVnZe9B5uXtKy2bVkaUmsaTHpFTRUaX/DKri', '2021-02-23 17:51:24');

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
  `house_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门牌号信息',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号,不用int是因为有的末位为X',
  `gender` tinyint(1) NULL DEFAULT 0 COMMENT '性别:1为男，0为女',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_base_info
-- ----------------------------
INSERT INTO `user_base_info` VALUES (1, 1, '张三', '东兴路社区', '东原锦悦', '2-24-1', '500123199005234671', 1, 31, '13545612384', '2021-02-20 10:18:38');
INSERT INTO `user_base_info` VALUES (15, 2, '李四', '响水路社区', '响水景苑', '1-5-1', '500113200011214623', 0, 21, '13986414641', '2021-03-06 18:11:27');

SET FOREIGN_KEY_CHECKS = 1;
