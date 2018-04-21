/*
Navicat MySQL Data Transfer

Source Server         : zstiu
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-04-22 03:00:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for datapointhistory
-- ----------------------------
DROP TABLE IF EXISTS `datapointhistory`;
CREATE TABLE `datapointhistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `value` varchar(255) NOT NULL,
  `datastream_id` varchar(20) DEFAULT NULL,
  `datastream_device_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4cc5jgl9yr329k67rsm5mxksm` (`datastream_id`),
  KEY `datapointhistory_datatstream_device` (`datastream_device_id`),
  CONSTRAINT `datapointhistory_datatstream` FOREIGN KEY (`datastream_id`) REFERENCES `datastream` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `datapointhistory_datatstream_device` FOREIGN KEY (`datastream_device_id`) REFERENCES `datastream` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datapointhistory
-- ----------------------------

-- ----------------------------
-- Table structure for datastream
-- ----------------------------
DROP TABLE IF EXISTS `datastream`;
CREATE TABLE `datastream` (
  `id` varchar(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `current_value` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `unit_symbol` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `device_id` bigint(20) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`device_id`),
  KEY `FKash7nfb57sbo9u2r5xx3794lj` (`device_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKash7nfb57sbo9u2r5xx3794lj` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datastream
-- ----------------------------
INSERT INTO `datastream` VALUES ('humidity', null, null, null, 'Relative Humidity', 'RH%', '2018-04-22 02:58:51', '29566363', 'e42aefc4-8fe2-5eb8-8198-7e333d699647');
INSERT INTO `datastream` VALUES ('humidity', null, null, null, 'Relative Humidity', 'RH%', '2018-04-22 02:58:51', '29566372', 'be918c86-fa5c-5aac-a6a6-2fd8724b68d3');
INSERT INTO `datastream` VALUES ('obliquity', null, null, null, '度', '°', '2018-04-22 02:58:51', '29566363', 'c868d81a-595f-54e7-bfb7-f5995d072ebb');
INSERT INTO `datastream` VALUES ('obliquity', null, null, null, '度', '°', '2018-04-22 02:58:51', '29566372', '7567ef03-7d1e-517b-86ca-20b138f709d9');
INSERT INTO `datastream` VALUES ('position', null, null, null, '', '', '2018-04-22 02:58:51', '29566363', '8ee5818c-e462-57ae-a728-b1477dbc22d3');
INSERT INTO `datastream` VALUES ('position', null, null, null, '', '', '2018-04-22 02:58:51', '29566372', 'e3cbc30e-67a8-50d1-ac6c-d5a4e20a3d48');
INSERT INTO `datastream` VALUES ('pressure', null, null, null, '帕', 'Pa', '2018-04-22 02:58:51', '29566363', '2b6dbf87-3b34-5a72-8b21-eb6d0188ddd2');
INSERT INTO `datastream` VALUES ('pressure', null, null, null, '帕', 'Pa', '2018-04-22 02:58:51', '29566372', '5cfb3776-a124-593b-a8e7-30982140798e');
INSERT INTO `datastream` VALUES ('temperature', null, null, null, 'celsius', 'C', '2018-04-22 02:58:51', '29566363', '87989383-99f4-5086-a2e1-22fe2e141ab9');
INSERT INTO `datastream` VALUES ('temperature', null, null, null, 'celsius', 'C', '2018-04-22 02:58:51', '29566372', '430ff55d-a6fb-54c1-bf8b-09367993bcc8');

-- ----------------------------
-- Table structure for datastreams
-- ----------------------------
DROP TABLE IF EXISTS `datastreams`;
CREATE TABLE `datastreams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `current_value` varchar(255) DEFAULT NULL,
  `tags` varchar(255) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `unit_symbol` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKimpo7iyfnj9e0ti1ule09m8be` (`device_id`),
  CONSTRAINT `FKimpo7iyfnj9e0ti1ule09m8be` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of datastreams
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `lastmodified_time` datetime DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `auth_info` varchar(255) DEFAULT NULL,
  `online` bit(1) DEFAULT NULL,
  `protocol` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjcfjqqqa4v1linjqxno5lblo0` (`product_id`),
  CONSTRAINT `FKjcfjqqqa4v1linjqxno5lblo0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29566373 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('29566363', null, '2018-04-22 02:58:51', null, null, 'C89346DA4373', '\0', 'HTTP', 'myedpdevice', null, null, null);
INSERT INTO `device` VALUES ('29566372', null, '2018-04-22 02:58:50', null, null, '11111111', '\0', 'HTTP', 'myedpdevice', null, null, null);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `lastmodified_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtjf3xph7vnxwx0fyryph1ngv9` (`product_id`),
  CONSTRAINT `FKtjf3xph7vnxwx0fyryph1ngv9` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('9', '2018-04-19 14:02:50', '2018-04-19 14:02:50', 'test1', '123456', '1');
INSERT INTO `manager` VALUES ('10', '2018-04-20 15:22:07', '2018-04-20 15:22:07', 'test2', '123456', '1');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apikey` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `lastmodified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'yFklxQD=vZhpdKTQYkU=FQ65Txo=', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `lastmodified_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_manager` (`manager_id`),
  CONSTRAINT `user_manager` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', null, null, null, 'test1', '123456', null, 'Cargo company');
INSERT INTO `user` VALUES ('10', '2018-04-20 12:21:44', null, '2018-04-20 12:21:44', '1', '2', null, '3');
INSERT INTO `user` VALUES ('11', '2018-04-20 15:48:37', null, '2018-04-20 15:48:37', 'test2', '123456', '9', 'Cargo company');
