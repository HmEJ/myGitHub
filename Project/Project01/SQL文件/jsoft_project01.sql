/*
 Navicat Premium Data Transfer

 Source Server         : Jsoft_Project
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : jsoft_project01

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 21/08/2023 17:02:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account`  (
  `accountId` int NOT NULL AUTO_INCREMENT COMMENT '账户id',
  `accountPwd` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '收银账户密码',
  `money` double(8, 2) NULL DEFAULT NULL COMMENT '收银总金额',
  PRIMARY KEY (`accountId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '收银账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES (1, 'administrator', -153.10);

-- ----------------------------
-- Table structure for tb_activity
-- ----------------------------
DROP TABLE IF EXISTS `tb_activity`;
CREATE TABLE `tb_activity`  (
  `activityId` int NOT NULL AUTO_INCREMENT COMMENT '活动标识id',
  `activityName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `criticalTotal` double(6, 2) NULL DEFAULT NULL COMMENT '折扣临界金额',
  `startDate` date NULL DEFAULT NULL COMMENT '活动开始日期',
  `endDate` date NULL DEFAULT NULL COMMENT '结束日期',
  `discount` double(4, 2) NULL DEFAULT NULL COMMENT '活动折扣',
  PRIMARY KEY (`activityId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 500017 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '活动表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_activity
-- ----------------------------
INSERT INTO `tb_activity` VALUES (500001, '!!!夏季促销全场满200九折优惠!!!', 200.00, '2023-08-17', '2023-08-22', 0.90);
INSERT INTO `tb_activity` VALUES (500002, '!!!夏季促销全场满250八五折优惠!!!', 250.00, '2023-08-17', '2023-08-22', 0.85);
INSERT INTO `tb_activity` VALUES (500003, '!!!夏季促销全场满300八折优惠!!!', 300.00, '2023-08-16', '2023-08-22', 0.80);
INSERT INTO `tb_activity` VALUES (500004, '!!!夏季促销全场满350七五折优惠!!!', 350.00, '2023-08-18', '2023-08-22', 0.75);
INSERT INTO `tb_activity` VALUES (500005, '!!!夏季促销全场满399七折优惠!!!', 399.00, '2023-08-19', '2023-08-22', 0.70);
INSERT INTO `tb_activity` VALUES (500006, '!!!夏季促销全场满449六五折优惠!!!', 449.00, '2023-08-23', '2023-09-01', 0.65);
INSERT INTO `tb_activity` VALUES (500012, '!!!夏季促销全场满499六折优惠!!!', 499.00, '2023-09-02', '2023-09-10', 0.60);
INSERT INTO `tb_activity` VALUES (500013, '!!!夏季促销全场满549五五折优惠!!!', 549.00, '2023-09-11', '2023-09-12', 0.55);
INSERT INTO `tb_activity` VALUES (500014, '!!!夏季促销全场满599五折优惠!!!', 599.00, '2023-09-13', '2023-09-13', 0.50);
INSERT INTO `tb_activity` VALUES (500015, '!!!夏季促销全场满499四五折优惠!!!', 649.00, '2023-09-14', '2023-09-15', 0.45);

-- ----------------------------
-- Table structure for tb_empuser
-- ----------------------------
DROP TABLE IF EXISTS `tb_empuser`;
CREATE TABLE `tb_empuser`  (
  `empUserId` int NOT NULL AUTO_INCREMENT COMMENT '员工用户id',
  `empUserName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工用户姓名',
  `empUserDuty` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工用户职位',
  `empUserPwd` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '员工用户登录密码',
  PRIMARY KEY (`empUserId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100014 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '员工用户表登录收银系统' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_empuser
-- ----------------------------
INSERT INTO `tb_empuser` VALUES (100001, '王五', '总经理', 'qwer123456');
INSERT INTO `tb_empuser` VALUES (100002, '张三', '普通员工', '12345678');
INSERT INTO `tb_empuser` VALUES (100003, '李四', '普通员工', '88888888');
INSERT INTO `tb_empuser` VALUES (100004, '赵六', '普通员工', 'sdfd511f');
INSERT INTO `tb_empuser` VALUES (100005, '航航', '普通员工', '1');
INSERT INTO `tb_empuser` VALUES (100006, '瓜瓜', '普通员工', '1545sf5df');
INSERT INTO `tb_empuser` VALUES (100007, '蛋蛋', '普通员工', 'dfdf52020');
INSERT INTO `tb_empuser` VALUES (100008, '碧碧', '普通员工', '20215df');
INSERT INTO `tb_empuser` VALUES (100009, '逊毙了', '普通员工', 'xunbile123');
INSERT INTO `tb_empuser` VALUES (100010, '蒸汽', '普通员工', 'steam');
INSERT INTO `tb_empuser` VALUES (100011, '加一', '普通员工', 'xujiayi');
INSERT INTO `tb_empuser` VALUES (100012, '百度', '普通员工', 'baidu');
INSERT INTO `tb_empuser` VALUES (100013, 'hjh', '普通员工', 'hjh');
INSERT INTO `tb_empuser` VALUES (100015, '钢铁侠', '普通员工', 'tttieren');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `orderTotal` double(6, 2) NULL DEFAULT NULL COMMENT '订单总价',
  `orderTrueTotal` double(6, 2) NULL DEFAULT NULL COMMENT '订单实际价格',
  `orderDateTime` datetime NULL DEFAULT NULL COMMENT '订单生成日期',
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (1, 100.00, 100.00, '2023-08-14 00:00:00');
INSERT INTO `tb_order` VALUES (2, 61.00, 61.00, '2023-09-10 12:00:00');
INSERT INTO `tb_order` VALUES (11, 200.00, 191.00, '2023-08-20 14:19:32');
INSERT INTO `tb_order` VALUES (12, 123.00, 123.00, '2023-08-20 14:18:13');
INSERT INTO `tb_order` VALUES (24, 219.00, 197.10, '2023-08-21 15:31:33');

-- ----------------------------
-- Table structure for tb_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem`  (
  `orderId` int NOT NULL COMMENT '订单编号外键',
  `productId` int NOT NULL COMMENT '商品编号外键',
  `productName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `productCount` int NULL DEFAULT NULL COMMENT '商品购买数量',
  `productPrice` double(6, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `productTotal` double(6, 2) NULL DEFAULT NULL COMMENT '商品总价',
  INDEX `tb_orderitem_order_orderId_fk`(`orderId` ASC) USING BTREE,
  INDEX `tb_orderitem_product_productId_fk`(`productId` ASC) USING BTREE,
  CONSTRAINT `tb_orderitem_order_orderId_fk` FOREIGN KEY (`orderId`) REFERENCES `tb_order` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_orderitem_product_productId_fk` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '订单项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_orderitem
-- ----------------------------
INSERT INTO `tb_orderitem` VALUES (1, 200001, '可口可乐', 10, 3.00, 30.00);
INSERT INTO `tb_orderitem` VALUES (2, 200002, '百事可乐', 3, 3.00, 9.00);
INSERT INTO `tb_orderitem` VALUES (11, 200002, '百事可乐', 10, 3.00, 30.00);
INSERT INTO `tb_orderitem` VALUES (12, 200002, '百事可乐', 10, 3.00, 30.00);
INSERT INTO `tb_orderitem` VALUES (11, 200003, '巧乐兹', 20, 8.00, 160.00);
INSERT INTO `tb_orderitem` VALUES (2, 200008, '卫龙辣条', 10, 4.00, 40.00);
INSERT INTO `tb_orderitem` VALUES (2, 200006, '可比克', 13, 4.00, 52.00);
INSERT INTO `tb_orderitem` VALUES (24, 200010, '老干妈', 0, 12.00, 0.00);
INSERT INTO `tb_orderitem` VALUES (24, 200009, '舒肤佳洗手液', 10, 19.90, 199.00);
INSERT INTO `tb_orderitem` VALUES (24, 200006, '可比克', 5, 4.00, 20.00);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `productId` int NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `productName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `productPrice` double(6, 2) NULL DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`productId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200011 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (200001, '可口可乐', 3.00);
INSERT INTO `tb_product` VALUES (200002, '百事可乐', 3.00);
INSERT INTO `tb_product` VALUES (200003, '巧乐兹', 8.00);
INSERT INTO `tb_product` VALUES (200004, '百威啤酒', 24.90);
INSERT INTO `tb_product` VALUES (200005, '达利园饼干', 3.20);
INSERT INTO `tb_product` VALUES (200006, '可比克', 4.00);
INSERT INTO `tb_product` VALUES (200007, '心相印', 19.00);
INSERT INTO `tb_product` VALUES (200008, '卫龙辣条', 4.00);
INSERT INTO `tb_product` VALUES (200009, '舒肤佳洗手液', 19.90);
INSERT INTO `tb_product` VALUES (200010, '老干妈', 12.00);

SET FOREIGN_KEY_CHECKS = 1;
