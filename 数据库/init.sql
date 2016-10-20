-- MySQL dump 10.13  Distrib 5.6.21, for Win64 (x86_64)
--
-- Host: 192.168.40.27    Database: o2o
-- ------------------------------------------------------
-- Server version	5.1.66

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acc_role`
--

DROP TABLE IF EXISTS `acc_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_role` (
  `acc_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`acc_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `accountName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `trueName` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `phoneNumber` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `unitCode` varchar(12) DEFAULT NULL COMMENT '单位代码',
  `state` varchar(2) DEFAULT NULL,
  `policeNumber` varchar(6) DEFAULT NULL COMMENT '警员编号',
  `duty` varchar(100) DEFAULT NULL COMMENT '职务',
  `createTime` timestamp NULL DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `rev3` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_account_detail`
--

DROP TABLE IF EXISTS `ali_account_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_account_detail` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `trans_out_order_no` varchar(128) DEFAULT NULL,
  `trans_account` varchar(64) DEFAULT NULL,
  `other_account` varchar(64) DEFAULT NULL,
  `trans_date` varchar(32) DEFAULT NULL,
  `trans_dt` varchar(32) DEFAULT NULL,
  `trans_amount_d` varchar(128) DEFAULT NULL,
  `trans_amount_c` varchar(128) DEFAULT NULL,
  `balance` varchar(128) DEFAULT NULL,
  `sub_trans_code_desc` varchar(32) DEFAULT NULL,
  `trade_area` varchar(64) DEFAULT NULL,
  `show_name` varchar(64) DEFAULT NULL,
  `trans_memo` varchar(512) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_login_history`
--

DROP TABLE IF EXISTS `ali_login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_login_history` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `login_id` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `name` varchar(36) DEFAULT NULL,
  `client_ip` varchar(64) DEFAULT NULL,
  `action_dt` varchar(32) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_register_info`
--

DROP TABLE IF EXISTS `ali_register_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_register_info` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `iw_user_id` varchar(64) DEFAULT NULL,
  `real_name` varchar(36) DEFAULT NULL,
  `gender` varchar(4) DEFAULT NULL,
  `cert_no` varchar(64) DEFAULT NULL,
  `cert_type` varchar(16) DEFAULT NULL,
  `firm_name` varchar(128) DEFAULT NULL,
  `login_email` varchar(64) DEFAULT NULL,
  `login_mobile` varchar(36) DEFAULT NULL,
  `inst_id` varchar(64) DEFAULT NULL,
  `inst_account_no` varchar(32) DEFAULT NULL,
  `balance` varchar(128) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `nick` varchar(64) DEFAULT NULL,
  `mobile` varchar(64) DEFAULT NULL,
  `alipay_id` varchar(64) DEFAULT NULL,
  `register_time` varchar(64) DEFAULT NULL,
  `bind_email` varchar(64) DEFAULT NULL,
  `last_login` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_transaction_records`
--

DROP TABLE IF EXISTS `ali_transaction_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_transaction_records` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `trade_no` varchar(128) DEFAULT NULL,
  `out_trade_no` varchar(128) DEFAULT NULL,
  `trade_status_desc` varchar(8) DEFAULT NULL,
  `partner_id` varchar(100) DEFAULT NULL,
  `trade_buyer_id` varchar(64) DEFAULT NULL,
  `buyer_info` varchar(256) DEFAULT NULL,
  `trade_seller_id` varchar(64) DEFAULT NULL,
  `seller_info` varchar(256) DEFAULT NULL,
  `trade_total_amt` varchar(128) DEFAULT NULL,
  `trade_receive_pay_date` varchar(32) DEFAULT NULL,
  `gmt_modified` varchar(32) DEFAULT NULL,
  `trade_create_date` varchar(32) DEFAULT NULL,
  `trade_type_desc` varchar(64) DEFAULT NULL,
  `trade_from_desc` varchar(64) DEFAULT NULL,
  `goods_title` varchar(256) DEFAULT NULL,
  `receive_address` varchar(256) DEFAULT NULL,
  `data_create_user` int(11) DEFAULT NULL,
  `data_create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ali_transfer_detail`
--

DROP TABLE IF EXISTS `ali_transfer_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ali_transfer_detail` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `transfer_no` varchar(128) DEFAULT NULL,
  `sender_userid` varchar(64) DEFAULT NULL,
  `receiver_userid` varchar(64) DEFAULT NULL,
  `receiver_inst` varchar(128) DEFAULT NULL,
  `trans_succ_date` varchar(32) DEFAULT NULL,
  `trans_amount` varchar(128) DEFAULT NULL,
  `product_name` varchar(10) DEFAULT NULL,
  `trade_area` varchar(32) DEFAULT NULL,
  `withdraw_no` varchar(128) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dic`
--

DROP TABLE IF EXISTS `dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dic` (
  `id` int(11) NOT NULL,
  `dicTypeId` int(11) DEFAULT NULL,
  `dicKey` varchar(36) DEFAULT NULL,
  `dicName` varchar(64) DEFAULT NULL,
  `dicParentKey` varchar(36) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `dicLevel` varchar(2) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  `datareource` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_dicTypeId` (`dicTypeId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dic_type`
--

DROP TABLE IF EXISTS `dic_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dic_type` (
  `id` int(11) NOT NULL,
  `dicTypeKey` varchar(36) DEFAULT NULL,
  `dicTypeName` varchar(64) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_dicTypeKey` (`dicTypeKey`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrus_attachment`
--

DROP TABLE IF EXISTS `entrus_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrus_attachment` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(128) DEFAULT NULL,
  `upload_time` timestamp NULL DEFAULT NULL,
  `upload_user` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `file_content` mediumblob,
  `entrust_no` varchar(36) DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrus_investigation_info`
--

DROP TABLE IF EXISTS `entrus_investigation_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrus_investigation_info` (
  `id` varchar(36) NOT NULL,
  `query_type` varchar(256) DEFAULT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrus_investigation_typeinfo`
--

DROP TABLE IF EXISTS `entrus_investigation_typeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrus_investigation_typeinfo` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `invest_type` varchar(36) DEFAULT NULL,
  `invest_content` varchar(128) DEFAULT NULL,
  `invest_desc` varchar(256) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_base_info`
--

DROP TABLE IF EXISTS `entrust_base_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_base_info` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `entrust_ser` varchar(36) DEFAULT NULL,
  `entrust_number` int(11) DEFAULT NULL,
  `entrust_type` varchar(2) DEFAULT NULL,
  `province_code` varchar(36) DEFAULT NULL,
  `city_code` varchar(36) DEFAULT NULL,
  `unit` varchar(128) DEFAULT NULL,
  `person_name` varchar(80) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `istake` varchar(2) DEFAULT NULL,
  `visitor_name` varchar(64) DEFAULT NULL,
  `visitor_telephone` varchar(32) DEFAULT NULL,
  `visitor_unit` varchar(128) DEFAULT NULL,
  `visitor_title` varchar(64) DEFAULT NULL,
  `case_stage` varchar(36) DEFAULT NULL,
  `is_reply` varchar(2) DEFAULT NULL,
  `memo` varchar(256) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `data_resource` varchar(36) DEFAULT NULL,
  `process_state` varchar(36) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  `is_share_caseinfo` varchar(2) DEFAULT NULL,
  `is_share_queryresult` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrustno` (`entrust_no`) USING BTREE,
  KEY `index_process_state` (`process_state`) USING BTREE,
  KEY `index_is_share_caseinfo` (`is_share_caseinfo`) USING BTREE,
  KEY `index_is_share_queryresult` (`is_share_queryresult`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_case_info`
--

DROP TABLE IF EXISTS `entrust_case_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_case_info` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `case_name` varchar(64) DEFAULT NULL,
  `case_occur_date` timestamp NULL DEFAULT NULL,
  `case_no` varchar(64) DEFAULT NULL,
  `case_money_amout` int(11) DEFAULT NULL,
  `brand` varchar(64) DEFAULT NULL,
  `case_type` varchar(36) DEFAULT NULL,
  `case_info` mediumtext CHARACTER SET utf8 COLLATE utf8_bin,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  `is_end` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE,
  KEY `index_case_type` (`case_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_check_list`
--

DROP TABLE IF EXISTS `entrust_check_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_check_list` (
  `id` varchar(32) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `check_key` varchar(36) DEFAULT NULL,
  `check_user` int(11) DEFAULT NULL,
  `check_date` timestamp NULL DEFAULT NULL,
  `check_ispass` int(11) DEFAULT NULL,
  `check_desdc` varchar(256) DEFAULT NULL,
  `is_exec` varchar(2) DEFAULT NULL,
  `exec_no` int(11) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_satisfactory_results`
--

DROP TABLE IF EXISTS `entrust_satisfactory_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_satisfactory_results` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `satisfactory_key` varchar(36) DEFAULT NULL,
  `satisfactory_result` varchar(2) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `advice` varchar(512) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_suspect_info`
--

DROP TABLE IF EXISTS `entrust_suspect_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_suspect_info` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `suspect_name` varchar(36) DEFAULT NULL,
  `suspect_card_no` varchar(30) DEFAULT NULL,
  `suspect_telephone` varchar(36) DEFAULT NULL,
  `suspect_taobao_no` varchar(64) DEFAULT NULL,
  `suspect_alipay` varchar(64) DEFAULT NULL,
  `suspect_qq` varchar(16) DEFAULT NULL,
  `suspect_email` varchar(32) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrust_victim_info`
--

DROP TABLE IF EXISTS `entrust_victim_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrust_victim_info` (
  `id` varchar(36) NOT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `victim_name` varchar(36) DEFAULT NULL,
  `victim_card_no` varchar(30) DEFAULT NULL,
  `victim_telephone` varchar(36) DEFAULT NULL,
  `victim_taobao_no` varchar(64) DEFAULT NULL,
  `victim_alipay` varchar(64) DEFAULT NULL,
  `victim_address` varchar(256) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_entrust_no` (`entrust_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `action` varchar(64) DEFAULT NULL,
  `module` varchar(64) DEFAULT NULL,
  `actionTime` varchar(32) DEFAULT NULL,
  `userIP` varchar(32) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT NULL,
  `actiondesc` varchar(1024) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`) USING BTREE,
  KEY `index_action` (`action`) USING BTREE,
  KEY `index_module` (`module`) USING BTREE,
  KEY `index_userIP` (`userIP`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8339 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `res_roles`
--

DROP TABLE IF EXISTS `res_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res_roles` (
  `role_id` int(11) NOT NULL,
  `resc_id` int(11) NOT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resKey` varchar(64) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `resUrl` varchar(256) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  `roleKey` varchar(64) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seq_id`
--

DROP TABLE IF EXISTS `seq_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seq_id` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `val` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10262 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_alipay`
--

DROP TABLE IF EXISTS `series_case_alipay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_alipay` (
  `id` varchar(36) NOT NULL,
  `entrust_case_id` varchar(36) DEFAULT NULL,
  `alipay_card` varchar(64) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_bankcard`
--

DROP TABLE IF EXISTS `series_case_bankcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_bankcard` (
  `id` varchar(36) NOT NULL,
  `entrust_case_id` varchar(36) DEFAULT NULL,
  `bankcard` varchar(64) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_certificate`
--

DROP TABLE IF EXISTS `series_case_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_certificate` (
  `id` varchar(36) NOT NULL,
  `entrust_case_id` varchar(36) DEFAULT NULL,
  `certificate_no` varchar(64) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_detail`
--

DROP TABLE IF EXISTS `series_case_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_detail` (
  `id` varchar(36) NOT NULL,
  `series_case_id` varchar(36) DEFAULT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `entrust_no` varchar(36) DEFAULT NULL,
  `series_person` varchar(256) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_info`
--

DROP TABLE IF EXISTS `series_case_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_info` (
  `id` varchar(36) NOT NULL,
  `series_case_no` varchar(36) DEFAULT NULL,
  `series_case_name` varchar(64) DEFAULT NULL,
  `series_reason` varchar(256) DEFAULT NULL,
  `series_desc` varchar(256) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_flag` varchar(2) DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_series_case_no` (`series_case_no`) USING BTREE,
  KEY `index_series_reason` (`series_reason`(255)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `series_case_telephone`
--

DROP TABLE IF EXISTS `series_case_telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `series_case_telephone` (
  `id` varchar(36) NOT NULL,
  `entrust_case_id` varchar(36) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `rev1` varchar(256) DEFAULT NULL,
  `rev2` varchar(256) DEFAULT NULL,
  `rev3` varchar(256) DEFAULT NULL,
  `rev4` varchar(256) DEFAULT NULL,
  `rev5` varchar(256) DEFAULT NULL,
  `rev6` varchar(256) DEFAULT NULL,
  `rev7` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-11  8:52:48
