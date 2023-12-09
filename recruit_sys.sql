/*
 Navicat Premium Data Transfer

 Source Server         : yanqing
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : 127.0.0.1:3306
 Source Schema         : recruit_sys

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 09/12/2023 12:53:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分类的id',
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名字',
  `create_user` int UNSIGNED NOT NULL COMMENT '创建该分类的用户id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_user`(`create_user` ASC) USING BTREE,
  CONSTRAINT `fk_category_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'python开发', 6, '2023-11-26 11:41:53', '2023-12-02 15:46:10');
INSERT INTO `category` VALUES (4, 'Java开发', 6, '2023-11-26 11:58:55', '2023-12-02 15:35:24');
INSERT INTO `category` VALUES (7, 'C++开发', 6, '2023-11-27 21:16:30', '2023-12-02 15:39:06');
INSERT INTO `category` VALUES (13, '嵌入式开发', 6, '2023-11-29 11:12:24', '2023-12-02 15:45:43');
INSERT INTO `category` VALUES (14, '后端开发', 17, '2023-12-02 14:43:06', '2023-12-07 09:40:06');
INSERT INTO `category` VALUES (15, 'Python开发', 17, '2023-12-02 14:43:24', '2023-12-07 09:39:55');
INSERT INTO `category` VALUES (16, '教育', 17, '2023-12-02 14:43:48', '2023-12-07 09:39:39');
INSERT INTO `category` VALUES (17, '前端开发', 19, '2023-12-02 15:58:55', '2023-12-02 15:58:55');
INSERT INTO `category` VALUES (18, '平面设计', 19, '2023-12-02 15:59:09', '2023-12-02 15:59:09');
INSERT INTO `category` VALUES (19, '小程序开发', 19, '2023-12-02 15:59:29', '2023-12-02 15:59:29');
INSERT INTO `category` VALUES (20, '金融管理', 19, '2023-12-02 15:59:56', '2023-12-02 15:59:56');
INSERT INTO `category` VALUES (21, '医疗', 19, '2023-12-02 16:00:07', '2023-12-02 16:00:07');
INSERT INTO `category` VALUES (22, '房地产', 19, '2023-12-02 16:00:14', '2023-12-04 21:46:46');
INSERT INTO `category` VALUES (23, '高中教师', 6, '2023-12-07 09:40:28', '2023-12-07 09:40:28');
INSERT INTO `category` VALUES (24, '行政管理', 6, '2023-12-07 09:40:36', '2023-12-07 09:40:36');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (2, 'SVIP会员', 'svip', 29.98, '2023-12-03 19:49:00', '2023-12-03 19:49:04');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL COMMENT '商品id',
  `user_id` int NOT NULL,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单名称',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `alipay_no` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '阿里支付订单号·',
  `state` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付状态\"待支付\",\"已支付待审核\",\"未支付\"----\"已通过\",\"未通过\"',
  `total` decimal(20, 2) NULL DEFAULT NULL COMMENT '价格',
  `create_time` datetime NULL DEFAULT NULL COMMENT '订单创建的时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (19, 2, 6, 'SVIP会员', '8419904620662394880', '2023120422001428240501521382', '已通过', 29.98, '2023-12-04 16:54:19', '2023-12-04 14:38:48');
INSERT INTO `orders` VALUES (20, 2, 26, 'SVIP会员', '8419971203332108288', '2023120422001428240501526687', '已通过', 29.98, '2023-12-04 19:04:43', '2023-12-04 19:04:15');
INSERT INTO `orders` VALUES (21, 2, 17, 'SVIP会员', '8419999561334452224', NULL, '待支付', 29.98, '2023-12-04 20:55:45', NULL);
INSERT INTO `orders` VALUES (22, 2, 27, 'SVIP会员', '8420024060697702400', '2023120422001428240501528482', '已通过', 29.98, '2023-12-07 09:03:45', '2023-12-04 22:34:20');
INSERT INTO `orders` VALUES (23, 2, 23, 'SVIP会员', '8420291164854874112', '2023120522001428240501537222', '已通过', 29.98, '2023-12-05 16:20:54', '2023-12-05 16:20:32');
INSERT INTO `orders` VALUES (24, 2, 19, 'SVIP会员', '8420303051881508864', NULL, '待支付', 29.98, '2023-12-05 17:01:43', NULL);
INSERT INTO `orders` VALUES (25, 2, 32, 'SVIP会员', '8420906916977373184', '2023120722001428240501542855', '已通过', 29.98, '2023-12-07 09:02:48', '2023-12-07 09:02:09');
INSERT INTO `orders` VALUES (26, 2, 33, 'SVIP会员', '8420915752912089088', '2023120722001428240501544228', '已通过', 29.98, '2023-12-07 09:38:37', '2023-12-07 09:38:28');
INSERT INTO `orders` VALUES (27, 2, 34, 'SVIP会员', '8420917079704662016', '2023120722001428240501544229', '已通过', 29.98, '2023-12-07 09:44:09', '2023-12-07 09:42:05');
INSERT INTO `orders` VALUES (28, 2, 35, 'SVIP会员', '8421000217933967360', '2023120722001428240501545909', '已通过', 29.98, '2023-12-07 17:30:13', '2023-12-07 15:12:48');
INSERT INTO `orders` VALUES (29, 2, 36, 'SVIP会员', '8421033012538961920', '2023120722001428240501550961', '已通过', 29.98, '2023-12-07 17:27:54', '2023-12-07 17:27:24');
INSERT INTO `orders` VALUES (30, 2, 38, 'SVIP会员', '8421037832062693376', '2023120722001428240501552274', '已通过', 29.98, '2023-12-07 17:42:13', '2023-12-07 17:41:57');

-- ----------------------------
-- Table structure for recruit_info
-- ----------------------------
DROP TABLE IF EXISTS `recruit_info`;
CREATE TABLE `recruit_info`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '招聘信息表的id',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位标题',
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位地址',
  `pay_min` int NOT NULL COMMENT '薪资最小值，单位是k',
  `pay_max` int NOT NULL COMMENT '薪资最大值，单位是k',
  `company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名称',
  `condition` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位条件，默认是\"不限\"，以数组转为字符串的形式存储',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位的详细要求，富文本',
  `tag` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位的标签，以数组转为字符串的形式存储',
  `state` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '0，1，2三个值分别表示待审核，审核通过、审核不通过',
  `img_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位信息封面图',
  `type` int UNSIGNED NOT NULL COMMENT '0，1分别表示招聘信息和求职信息',
  `category_id` int UNSIGNED NULL DEFAULT NULL COMMENT '属于的类别',
  `create_user` int UNSIGNED NOT NULL COMMENT '创建的用户id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_recruit_category`(`category_id` ASC) USING BTREE,
  INDEX `fk_recruit_user`(`create_user` ASC) USING BTREE,
  CONSTRAINT `fk_recruit_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_recruit_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruit_info
-- ----------------------------
INSERT INTO `recruit_info` VALUES (48, '爱思助手', '萨达', 123, 12, '实打实大师', '', '<p>大叔大婶</p>', '', 1, 'http://niu.atcat.cn/691080195be34f7fa6b6b0b34315cf36.png', 1, 17, 23, '2023-12-05 16:22:09', '2023-12-07 09:51:41');
INSERT INTO `recruit_info` VALUES (49, '怀化学院洁厕人员', '怀化学院', 3, 5, '怀化学院', '身体健康,18岁以上,65岁以下,', '<p>怀化学院因人才缺失，导致洁厕人员不足，特此面向全国各大地区招聘可用人员</p>', '1111,', 1, 'http://niu.atcat.cn/30d4d2c5575c4dfa9abeb6d215d98ca7.jpg', 0, 4, 33, '2023-12-07 09:44:22', '2023-12-07 09:44:45');
INSERT INTO `recruit_info` VALUES (50, 'java开发工程师', '湖南省怀化市鹤城区怀化学院东区', 2, 16, '怀化学院', '不能加班,要有双休,', '<p><strong style=\"color: rgb(52, 52, 52);\">在 校 经 历</strong></p><p><strong style=\"color: rgb(48, 49, 53);\">2020.09 – 至今&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;怀化学院ACM实训基地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></p><p><strong style=\"color: rgb(84, 84, 84);\">工作描述：</strong></p><p><span style=\"color: rgb(84, 84, 84);\">n&nbsp;竞赛组织：&nbsp;负责组织怀化学院大学生程序设计竞赛，组织基地学生参加各级程序设计竞赛等；</span></p><p><span style=\"color: rgb(84, 84, 84);\">n&nbsp;日常管理：&nbsp;负责基地日常集训制度落实，基地招新等；</span></p><p>&nbsp;</p><p><strong style=\"color: rgb(52, 52, 52);\">荣 誉 奖 励</strong></p><p><span style=\"color: rgb(84, 84, 84);\">怀化学院优秀共产党员、怀化学院优秀共青团干部、ACM-ICPC国际大学生程序设计大赛（丝绸之路中国邀请赛）银牌、湖南省大学生程序设计竞赛二等奖、湖南省大学生程序设计竞赛三等奖、团体程序设计天梯赛全国二等奖、团体程序设计天梯赛全国三等奖、团体程序设计天梯赛湖南省二等奖、蓝桥杯c/c++（大学B组）湖南省一等奖、蓝桥杯c/c++（大学B组）全国二等奖</span></p><p>&nbsp;</p><p><strong style=\"color: rgb(52, 52, 52);\">自 我 评 价</strong></p><p><span style=\"color: rgb(84, 84, 84);\">本人具备部队基层工作经验，有很强的团队意识，能够按时保质保量完成上级任务，能熟练操作计算机进行软件编写，能熟练使用C，C++，Java，曾多次在各级算法比赛中获奖，具有一定的算法设计与实现能力，可以胜任助理工程师岗位。</span></p>', '00后,', 1, 'http://niu.atcat.cn/d6bdfdfb82d248a39463b4dde889c386.jpg', 1, 4, 34, '2023-12-07 09:50:47', '2023-12-07 09:51:41');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `role` int NULL DEFAULT NULL COMMENT '角色, 0系统管理员,1招聘人员,2应聘人员',
  `level` int NULL DEFAULT 0 COMMENT '等级,0普通用户, 1VIP用户,2SVIP用户(默认0)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '用户创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '用户最近修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'yanqing', 'e10adc3949ba59abbe56e057f20f883e', '檐晴大7', '2325552660@qq.com', 'http://niu.atcat.cn/ef392c0acb4649a5b2921be06561e72c.jpg', 0, 3, '2023-11-25 21:15:24', '2023-12-07 16:01:19');
INSERT INTO `user` VALUES (12, 'abcdwe', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 2, 0, '2023-11-30 21:11:06', '2023-11-30 21:11:06');
INSERT INTO `user` VALUES (13, 'dsaddsadsa', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 1, 0, '2023-11-30 21:12:00', '2023-11-30 21:12:00');
INSERT INTO `user` VALUES (16, 'yanqing8', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 1, 0, '2023-12-02 14:39:55', '2023-12-02 14:39:55');
INSERT INTO `user` VALUES (17, 'luo666', 'e10adc3949ba59abbe56e057f20f883e', 'sadffsd', '3020151411@qq.com', 'http://niu.atcat.cn/6a542ac4bb064f2abf537d3fe7f6645f.jpg', 1, 0, '2023-12-02 14:41:18', '2023-12-02 14:42:17');
INSERT INTO `user` VALUES (18, 'gzy11440', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 1, 0, '2023-12-02 14:46:15', '2023-12-02 14:46:15');
INSERT INTO `user` VALUES (19, 'zhou123', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 1, 0, '2023-12-02 15:53:02', '2023-12-02 15:53:02');
INSERT INTO `user` VALUES (20, 'yanqing1', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 2, 0, '2023-12-02 15:58:48', '2023-12-02 15:58:48');
INSERT INTO `user` VALUES (21, 'caozhuyou', '8ddcff3a80f4189ca1c9d4d902c3c909', '曹zhu友', '8888888@qq.com', 'http://niu.atcat.cn/14b16f134e8b496aaf9f2588f461e4b7.jpg', 1, 0, '2023-12-02 16:26:17', '2023-12-02 16:28:42');
INSERT INTO `user` VALUES (22, 'zhuping', 'e10adc3949ba59abbe56e057f20f883e', '我是大朱平', '13@qq.com', 'http://niu.atcat.cn/60aa3d24c40f4a259d1637fda65a0ed9.jpg', 2, 0, '2023-12-02 22:20:15', '2023-12-02 22:20:58');
INSERT INTO `user` VALUES (23, 'luo555', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 2, 3, '2023-12-03 19:42:12', '2023-12-03 19:42:12');
INSERT INTO `user` VALUES (24, 'jiangcao', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 1, 0, '2023-12-03 22:30:51', '2023-12-03 22:30:51');
INSERT INTO `user` VALUES (25, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 2, 0, '2023-12-04 17:16:02', '2023-12-04 17:16:02');
INSERT INTO `user` VALUES (26, 'gzy1144', 'b3862d8970b477b4e6076a03fa09d589', '', '', 'http://niu.atcat.cn/705f2b0322de4dcba508ec2d7dfa8dba.jpg', 1, 3, '2023-12-04 19:02:47', '2023-12-04 19:04:51');
INSERT INTO `user` VALUES (27, 'zxc123', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', 2, 3, '2023-12-04 22:32:58', '2023-12-04 22:32:58');
INSERT INTO `user` VALUES (28, 'luotianwang', 'e10adc3949ba59abbe56e057f20f883e', '傻逼', NULL, '', 2, 0, '2023-12-05 20:49:42', '2023-12-05 20:49:42');
INSERT INTO `user` VALUES (29, 'sdsadasadsa', 'e10adc3949ba59abbe56e057f20f883e', 'sadsa12', NULL, '', 2, 0, '2023-12-05 20:56:29', '2023-12-05 20:56:29');
INSERT INTO `user` VALUES (30, '5sa4dds', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '', 1, 0, '2023-12-05 21:12:34', '2023-12-06 10:51:28');
INSERT INTO `user` VALUES (31, 'smsmsm', 'e10adc3949ba59abbe56e057f20f883e', '傻逼', NULL, '', 1, 0, '2023-12-05 21:29:05', '2023-12-06 11:01:02');
INSERT INTO `user` VALUES (32, 'qwe123', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '', 2, 3, '2023-12-07 09:01:02', '2023-12-07 09:01:02');
INSERT INTO `user` VALUES (33, 'tzz666', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, 'http://niu.atcat.cn/8f1c154df9984bfa8605f929c6e88014.jpg', 1, 3, '2023-12-07 09:35:48', '2023-12-07 09:45:18');
INSERT INTO `user` VALUES (34, 'luo333', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '', 2, 3, '2023-12-07 09:41:24', '2023-12-07 09:41:24');
INSERT INTO `user` VALUES (35, '132456', 'e10adc3949ba59abbe56e057f20f883e', '123', '123@qq.com', '', 2, 3, '2023-12-07 15:11:41', '2023-12-07 15:11:53');
INSERT INTO `user` VALUES (36, '123456', 'e10adc3949ba59abbe56e057f20f883e', 'YYDS', 'krelpj1989@sandbox.com', '', 1, 3, '2023-12-07 17:21:45', '2023-12-07 17:29:09');
INSERT INTO `user` VALUES (38, 'qwertyu', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '', 2, 3, '2023-12-07 17:41:18', '2023-12-07 17:41:18');

SET FOREIGN_KEY_CHECKS = 1;
