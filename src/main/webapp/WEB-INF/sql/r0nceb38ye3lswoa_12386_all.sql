DROP TABLE IF EXISTS  `sys_bapp`;
CREATE TABLE `sys_bapp` (
	`BAP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
	`BAP_BUR_ID` int(11) NOT NULL COMMENT '应用客户ID',
	`BAP_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
	`BAP_KEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '约定KEY',
	`BAP_DEL_FLAG` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识',
	`BAP_CREATION_DT` datetime NULL COMMENT '创建时间',
	`BAP_CREATION_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '创建来源',
	`BAP_UPDATE_DT` datetime NULL,
	`BAP_UPDATE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`BAP_DELETE_DT` datetime NULL,
	`BAP_DELETE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	PRIMARY KEY (`BAP_ID`),
	KEY `BAP_BUR_ID`(`BAP_BUR_ID`) USING BTREE,
	KEY `BAP_ID`(`BAP_ID`) USING BTREE
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
ROW_FORMAT=COMPACT;
insert into `sys_bapp`(`BAP_ID`,`BAP_BUR_ID`,`BAP_NAME`,`BAP_KEY`,`BAP_DEL_FLAG`,`BAP_CREATION_DT`,`BAP_CREATION_ID`,`BAP_UPDATE_DT`,`BAP_UPDATE_ID`,`BAP_DELETE_DT`,`BAP_DELETE_ID`) values
('1','1','app_test','app_test','0','2014-07-28 14:48:45','minws',null,null,null,null);
DROP TABLE IF EXISTS  `sys_buser`;
CREATE TABLE `sys_buser` (
	`BUR_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`BUR_NICK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
	`BUR_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户名',
	`BUR_GENDER` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户性别，0男，1女',
	`BUR_BIRTHDAY` date NULL COMMENT '生日',
	`BUR_MOBILE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户手机',
	`BUR_EMAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户邮箱',
	PRIMARY KEY (`BUR_ID`),
	KEY `BUR_ID`(`BUR_ID`) USING BTREE
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='User基本信息'
AUTO_INCREMENT=7
ROW_FORMAT=COMPACT;
insert into `sys_buser`(`BUR_ID`,`BUR_NICK`,`BUR_NAME`,`BUR_GENDER`,`BUR_BIRTHDAY`,`BUR_MOBILE`,`BUR_EMAIL`) values
('1','hadong','董昊',null,null,'15262731821',null),
('2','wengel','王佶',null,null,'15262731822',null),
('3','wengel1','王佶1',null,null,'15262731822',null),
('4','wengel2','王佶2',null,null,'15262731822',null),
('5','wengel3','王佶3',null,null,'15262731823',null),
('6','wengel4','王佶4',null,null,'15262731824',null);
DROP TABLE IF EXISTS  `sys_xuser`;
CREATE TABLE `sys_xuser` (
	`XUR_BUR_ID` int(11) NOT NULL COMMENT '用户ID',
	`XUR_PASSWORD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
	`XUR_LEVEL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '等级',
	`XUR_DEL_FLAG` int(1) NULL DEFAULT 0 COMMENT '逻辑删除标识',
	`XUR_DEV_FLAG` int(1) NULL DEFAULT 0 COMMENT '是否是开发者',
	`XUR_CREATION_DT` datetime NULL COMMENT '创建时间',
	`XUR_CREATION_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '创建来源',
	`XUR_UPDATE_DT` datetime NULL COMMENT '更新时间',
	`XUR_UPDATE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '更新来源',
	`XUR_DELETE_DT` datetime NULL COMMENT '删除时间',
	`XUR_DELETE_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '删除来源',
	KEY `XUR_BUR_ID`(`XUR_BUR_ID`) USING BTREE
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='USER扩展表'
ROW_FORMAT=COMPACT;
insert into `sys_xuser`(`XUR_BUR_ID`,`XUR_PASSWORD`,`XUR_LEVEL`,`XUR_DEL_FLAG`,`XUR_DEV_FLAG`,`XUR_CREATION_DT`,`XUR_CREATION_ID`,`XUR_UPDATE_DT`,`XUR_UPDATE_ID`,`XUR_DELETE_DT`,`XUR_DELETE_ID`) values
('1','123456','0','0',null,'2014-06-25 20:39:57','minws',null,null,null,null),
('2','1234567','0','0',null,'2014-06-25 20:43:36','minws',null,null,null,null),
('3','1234567','0','0','0','2014-07-05 19:32:31','minws',null,null,null,null),
('4','1234567','0','0','0','2014-07-06 02:31:07','minws',null,null,null,null),
('5','1234567','0','0','0','2014-07-27 20:50:09','minws',null,null,null,null),
('6','123456','0','0','0','2014-07-27 21:15:15','minws',null,null,null,null);
ALTER TABLE `sys_bapp`
 ADD CONSTRAINT `BAP_BUR_ID` FOREIGN KEY (`BAP_BUR_ID`) REFERENCES `r0nceb38ye3lswoa`.`sys_buser` (`BUR_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `sys_xuser`
 ADD CONSTRAINT `XUR_BUR_ID` FOREIGN KEY (`XUR_BUR_ID`) REFERENCES `r0nceb38ye3lswoa`.`sys_buser` (`BUR_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
/* PROCEDURES */;
DROP PROCEDURE IF EXISTS `proc_add_user`;
DELIMITER $$
CREATE PROCEDURE `proc_add_user`(IN `ibur_nick` VARCHAR(20),IN `ibur_name` VARCHAR(20),IN `ibur_gender` varchar(1),IN `ibur_birthday` date,IN `ibur_mobile` VARCHAR(15),IN `ibur_email` VARCHAR(32),IN `ixur_password` VARCHAR(32))
    SQL SECURITY INVOKER
    COMMENT '创建用户'
BEGIN
DECLARE uid INT DEFAULT 0;
INSERT INTO sys_BUSER(BUR_NICK,BUR_NAME,BUR_GENDER,BUR_BIRTHDAY,BUR_MOBILE,BUR_EMAIL) VALUES (ibur_nick,ibur_name,ibur_gender,ibur_birthday,ibur_mobile,ibur_email);
SELECT BUR_ID INTO uid FROM sys_BUSER WHERE BUR_NICK = ibur_nick;
INSERT INTO sys_XUSER(XUR_BUR_ID,XUR_PASSWORD,XUR_CREATION_DT,XUR_CREATION_ID) VALUES (uid,ixur_password,now(),'proc_add_user');
END
$$
DELIMITER ;

