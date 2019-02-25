/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost
 Source Database       : salesSystem

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : utf-8

 Date: 02/20/2019 18:45:21 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `db_goods`
-- ----------------------------
DROP TABLE IF EXISTS `db_goods`;
CREATE TABLE `db_goods` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `goods_img` varchar(300) DEFAULT NULL,
  `goods_abstract` varchar(200) DEFAULT NULL,
  `goods_text` varchar(1000) DEFAULT NULL,
  `is_sold` int(4) NOT NULL DEFAULT '0',
  `goods_num` int(11) DEFAULT '10',
  `sold_num` int(11) DEFAULT '0',
  `seller_uid` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `db_order`
-- ----------------------------
DROP TABLE IF EXISTS `db_order`;
CREATE TABLE `db_order` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `order_number` bigint(50) NOT NULL,
  `uid` int(10) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `buy_num` int(4) DEFAULT NULL,
  `goods_price` double(20,2) DEFAULT NULL,
  `pay_price` double(20,2) DEFAULT NULL,
  `is_pay` int(4) DEFAULT '1',
  `status` int(4) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `goods_shopcar`
-- ----------------------------
DROP TABLE IF EXISTS `goods_shopcar`;
CREATE TABLE `goods_shopcar` (
  `sid` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `goods_id` int(10) NOT NULL,
  `num` int(10) DEFAULT NULL,
  `status` int(4) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `order_goods`
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) DEFAULT NULL,
  `goods_id` int(10) DEFAULT NULL,
  `goods_num` int(10) DEFAULT NULL,
  `goods_price` double(20,2) DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_t`
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_seller` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_t`
-- ----------------------------
BEGIN;
INSERT INTO `user_t` VALUES ('1', 'seller', MD5('relles'), '123456@qq.com', '1'), ('2', 'buyer', MD5('reyub'), '123456@qq.com', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
