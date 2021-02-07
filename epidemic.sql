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

 Date: 07/02/2021 14:07:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of real_time_data
-- ----------------------------
INSERT INTO `real_time_data` VALUES (1, '国内疫情', '2021-02-04', 2157, 101167, 4758, 788, 1, 49, 94180, 4830, NULL);
INSERT INTO `real_time_data` VALUES (6, '国内疫情', '2021-02-07', 1844, 101272, 4790, 682, 3, 25, 94597, 4831, '2021-02-07 10:09:10');

SET FOREIGN_KEY_CHECKS = 1;
