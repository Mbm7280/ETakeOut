/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : cli

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 30/10/2023 21:25:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏0 有效 1 删除',
  `status` int(4) DEFAULT NULL COMMENT '0 有效 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
BEGIN;
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (1, 0, '2020-02-02 14:50:36', '商品', 0, 0, 'pms', 'product', 1, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (2, 1, '2020-02-02 14:51:50', '商品列表', 1, 0, 'product', 'product-list', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (3, 1, '2020-02-02 14:52:44', '添加商品', 1, 0, 'addProduct', 'product-add', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (4, 1, '2020-02-02 14:53:51', '商品分类', 1, 0, 'productCate', 'product-cate', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (5, 1, '2020-02-02 14:54:51', '商品类型', 1, 0, 'productAttr', 'product-attr', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (6, 1, '2020-02-02 14:56:29', '品牌管理', 1, 0, 'brand', 'product-brand', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (7, 0, '2020-02-02 16:54:07', '订单', 0, 0, 'oms', 'order', 1, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (8, 7, '2020-02-02 16:55:18', '订单列表', 1, 0, 'order', 'product-list', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (9, 7, '2020-02-02 16:56:46', '订单设置', 1, 0, 'orderSetting', 'order-setting', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (10, 7, '2020-02-02 16:57:39', '退货申请处理', 1, 0, 'returnApply', 'order-return', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (11, 7, '2020-02-02 16:59:40', '退货原因设置', 1, 0, 'returnReason', 'order-return-reason', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (12, 0, '2020-02-04 16:18:00', '营销', 0, 0, 'sms', 'sms', 1, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (13, 12, '2020-02-04 16:19:22', '秒杀活动列表', 1, 0, 'flash', 'sms-flash', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (14, 12, '2020-02-04 16:20:16', '优惠券列表', 1, 0, 'coupon', 'sms-coupon', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (16, 12, '2020-02-07 16:22:38', '品牌推荐', 1, 0, 'homeBrand', 'product-brand', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (17, 12, '2020-02-07 16:23:14', '新品推荐', 1, 0, 'homeNew', 'sms-new', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (18, 12, '2020-02-07 16:26:38', '人气推荐', 1, 0, 'homeHot', 'sms-hot', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (19, 12, '2020-02-07 16:28:16', '专题推荐', 1, 0, 'homeSubject', 'sms-subject', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (20, 12, '2020-02-07 16:28:42', '广告列表', 1, 0, 'homeAdvertise', 'sms-ad', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (21, 0, '2020-02-07 16:29:13', '权限', 0, 0, 'ums', 'ums', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (22, 21, '2020-02-07 16:29:51', '用户列表', 1, 0, 'admin', 'ums-admin', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (23, 21, '2020-02-07 16:30:13', '角色列表', 1, 0, 'role', 'ums-role', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (24, 21, '2020-02-07 16:30:53', '菜单列表', 1, 0, 'menu', 'ums-menu', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (25, 21, '2020-02-07 16:31:13', '资源列表', 1, 0, 'resource', 'ums-resource', 0, 0);
INSERT INTO `ums_menu` (`id`, `parent_id`, `create_time`, `title`, `level`, `sort`, `name`, `icon`, `hidden`, `status`) VALUES (26, 1, '2023-10-28 10:39:02', 'ttt', 1, 0, 'ttt', 'string', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(4) DEFAULT NULL COMMENT '0有效 1无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Records of ums_resource
-- ----------------------------
BEGIN;
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (1, '商品品牌管理', NULL, '/brand/**', 1, '2020-02-04 17:04:55', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (2, '商品属性分类管理', NULL, '/productAttribute/**', 1, '2020-02-04 17:05:35', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (3, '商品属性管理', NULL, '/productAttribute/**', 1, '2020-02-04 17:06:13', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (4, '商品分类管理', NULL, '/productCategory/**', 1, '2020-02-04 17:07:15', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (5, '商品管理', NULL, '/product/**', 1, '2020-02-04 17:09:16', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (6, '商品库存管理', NULL, '/sku/**', 1, '2020-02-04 17:09:53', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (8, '订单管理', '', '/order/**', 2, '2020-02-05 14:43:37', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (9, ' 订单退货申请管理', '', '/returnApply/**', 2, '2020-02-05 14:44:22', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (10, '退货原因管理', '', '/returnReason/**', 2, '2020-02-05 14:45:08', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (11, '订单设置管理', '', '/orderSetting/**', 2, '2020-02-05 14:45:43', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (12, '收货地址管理', '', '/companyAddress/**', 2, '2020-02-05 14:46:23', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (13, '优惠券管理', '', '/coupon/**', 3, '2020-02-07 16:37:22', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (14, '优惠券领取记录管理', '', '/couponHistory/**', 3, '2020-02-07 16:37:59', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (15, '限时购活动管理', '', '/flash/**', 3, '2020-02-07 16:38:28', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (16, '限时购商品关系管理', '', '/flashProductRelation/**', 3, '2020-02-07 16:38:59', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (17, '限时购场次管理', '', '/flashSession/**', 3, '2020-02-07 16:39:22', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (18, '首页轮播广告管理', '', '/home/advertise/**', 3, '2020-02-07 16:40:07', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (19, '首页品牌管理', '', '/home/brand/**', 3, '2020-02-07 16:40:34', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (20, '首页新品管理', '', '/home/newProduct/**', 3, '2020-02-07 16:41:06', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (21, '首页人气推荐管理', '', '/home/recommendProduct/**', 3, '2020-02-07 16:42:16', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (22, '首页专题推荐管理', '', '/home/recommendSubject/**', 3, '2020-02-07 16:42:48', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (23, ' 商品优选管理', '', '/prefrenceArea/**', 5, '2020-02-07 16:44:56', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (24, '商品专题管理', '', '/subject/**', 5, '2020-02-07 16:45:39', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (25, '后台用户管理', '', '/admin/**', 4, '2020-02-07 16:47:34', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (26, '后台用户角色管理', '', '/role/**', 4, '2020-02-07 16:48:24', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (27, '后台菜单管理', '', '/menu/**', 4, '2020-02-07 16:48:48', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (28, '后台资源分类管理', '', '/resourceCategory/**', 4, '2020-02-07 16:49:18', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (29, '后台资源管理', '', '/resource/**', 4, '2020-02-07 16:49:45', 0);
INSERT INTO `ums_resource` (`id`, `name`, `description`, `url`, `category_id`, `create_time`, `status`) VALUES (30, 'ttt', 'ttt', '/ttttt', 7, '2023-10-28 14:10:15', 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `status` int(10) DEFAULT NULL COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
BEGIN;
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (1, '2020-02-05 10:21:44', '商品模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (2, '2020-02-05 10:22:34', '订单模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (3, '2020-02-05 10:22:48', '营销模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (4, '2020-02-05 10:23:04', '权限模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (5, '2020-02-07 16:34:27', '内容模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (6, '2020-02-07 16:35:49', '其他模块', 0, NULL);
INSERT INTO `ums_resource_category` (`id`, `create_time`, `name`, `sort`, `status`) VALUES (7, '2023-10-25 20:52:34', 't1', 2, 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `role_desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
BEGIN;
INSERT INTO `ums_role` (`id`, `role_name`, `role_desc`, `user_count`, `create_time`, `status`) VALUES (5, '超级管理员', '拥有所有查看和操作功能', 0, '2020-02-02 15:11:05', 1);
INSERT INTO `ums_role` (`id`, `role_name`, `role_desc`, `user_count`, `create_time`, `status`) VALUES (8, '权限管理员', '用于权限模块所有操作功能', 0, '2020-08-24 10:57:35', 1);
INSERT INTO `ums_role` (`id`, `role_name`, `role_desc`, `user_count`, `create_time`, `status`) VALUES (9, 'test', '测试1', 0, '2023-10-26 19:07:08', 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (33, 1, 1);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (34, 1, 2);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (35, 1, 3);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (36, 1, 4);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (37, 1, 5);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (38, 1, 6);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (53, 2, 7);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (54, 2, 8);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (55, 2, 9);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (56, 2, 10);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (57, 2, 11);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (96, 6, 21);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (97, 6, 22);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (98, 6, 23);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (99, 6, 24);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (100, 6, 25);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (101, 7, 21);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (102, 7, 22);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (103, 7, 23);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (104, 7, 24);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (105, 7, 25);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (106, 8, 21);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (107, 8, 22);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (108, 8, 23);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (109, 8, 24);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (110, 8, 25);
INSERT INTO `ums_role_menu_relation` (`id`, `role_id`, `menu_id`) VALUES (111, 5, 25);
COMMIT;

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8mb4 COMMENT='角色资源关系表';

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (103, 2, 8);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (104, 2, 9);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (105, 2, 10);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (106, 2, 11);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (107, 2, 12);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (170, 1, 1);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (171, 1, 2);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (172, 1, 3);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (173, 1, 4);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (174, 1, 5);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (175, 1, 6);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (176, 1, 23);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (177, 1, 24);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (178, 6, 25);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (179, 6, 26);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (180, 6, 27);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (181, 6, 28);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (182, 6, 29);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (205, 7, 25);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (206, 7, 26);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (207, 7, 27);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (208, 7, 28);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (209, 7, 29);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (210, 7, 31);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (211, 8, 25);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (212, 8, 26);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (213, 8, 27);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (214, 8, 28);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (215, 8, 29);
INSERT INTO `ums_role_resource_relation` (`id`, `role_id`, `resource_id`) VALUES (216, 5, 29);
COMMIT;

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of ums_user
-- ----------------------------
BEGIN;
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (1, 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'test@qq.com', '测试账号', NULL, '2018-09-29 13:55:30', '2018-09-29 13:55:39', 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (3, 'admin', '$2a$10$oEtiQd3FmDEl3B09Quy2gOY.q84GR74u5gd1ucbjxqGtyl7JVpAgC', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-04-20 12:45:16', 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (4, 'macro', '$2a$10$Bx4jZPR7GhEpIQfefDQtVeS58GfT5n6mxs/b4nLLK65eMFa16topa', 'string', 'macro@qq.com', 'macro', 'macro专用', '2019-10-06 15:53:51', '2020-02-03 14:55:55', 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (6, 'productAdmin', '$2a$10$6/.J.p.6Bhn7ic4GfoB5D.pGd7xSiD1a9M6ht6yO0fxzlKJPjRAGm', NULL, 'product@qq.com', '商品管理员', '只有商品权限', '2020-02-07 16:15:08', NULL, 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (7, 'orderAdmin', '$2a$10$UqEhA9UZXjHHA3B.L9wNG.6aerrBjC6WHTtbv1FdvYPUI.7lkL6E.', '123', 'order@qq.com', '订单管理员', '只有订单管理权限', '2020-02-07 16:15:50', NULL, 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (10, 'ceshi', '$2a$10$RaaNo9CC0RSms8mc/gJpCuOWndDT4pHH0u5XgZdAAYFs1Uq4sOPRi', NULL, 'ceshi@qq.com', 'ceshi', NULL, '2020-03-13 16:23:30', NULL, 1);
INSERT INTO `ums_user` (`id`, `username`, `password`, `icon`, `email`, `nick_name`, `note`, `create_time`, `login_time`, `status`) VALUES (16, 'echo', '$2a$10$gnUOBjOJ5pUcppuKTUsbQ.prRXfNyvaajaBFPuNqFR2l06KgpVQKO', 'string', 'mbm1216@163.com', 'echo', 'echo', '2023-10-21 19:59:42', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for ums_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role_relation`;
CREATE TABLE `ums_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关系表';

-- ----------------------------
-- Records of ums_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (26, 3, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (27, 6, 1);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (28, 7, 2);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (29, 1, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (30, 4, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (31, 8, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (34, 12, 6);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (38, 13, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (39, 10, 8);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (40, 16, 5);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (41, 16, 8);
INSERT INTO `ums_user_role_relation` (`id`, `user_id`, `role_id`) VALUES (42, 16, 9);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
