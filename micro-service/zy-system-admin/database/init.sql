SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for r_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `r_dept_role`;
CREATE TABLE `r_dept_role` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `DEPT_ID` varchar(255) NOT NULL COMMENT '部门ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门-角色，中间表';

-- ----------------------------
-- Table structure for r_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `r_menu_role`;
CREATE TABLE `r_menu_role` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单-角色中间表';

-- ----------------------------
-- Table structure for r_menu_security
-- ----------------------------
DROP TABLE IF EXISTS `r_menu_security`;
CREATE TABLE `r_menu_security` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单ID',
  `SECURITY_ID` varchar(255) NOT NULL COMMENT '安全资源ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单-安全资源中间表';

-- ----------------------------
-- Table structure for r_role_group
-- ----------------------------
DROP TABLE IF EXISTS `r_role_group`;
CREATE TABLE `r_role_group` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `GROUP_ID` varchar(255) NOT NULL COMMENT '分组ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-分组中间表';

-- ----------------------------
-- Table structure for r_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `r_user_dept`;
CREATE TABLE `r_user_dept` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `DEPT_ID` varchar(255) NOT NULL COMMENT '部门ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门中间表';

-- ----------------------------
-- Table structure for r_user_group
-- ----------------------------
DROP TABLE IF EXISTS `r_user_group`;
CREATE TABLE `r_user_group` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `GROUP_ID` varchar(255) NOT NULL COMMENT '分组ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-分组中间表';

-- ----------------------------
-- Table structure for r_user_group_role_group
-- ----------------------------
DROP TABLE IF EXISTS `r_user_group_role_group`;
CREATE TABLE `r_user_group_role_group` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_GROUP_ID` varchar(255) NOT NULL COMMENT '用户分组ID',
  `ROLE_GROUP_ID` varchar(255) NOT NULL COMMENT '角色角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户分组-角色分组，中间表';

-- ----------------------------
-- Table structure for r_user_role
-- ----------------------------
DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE `r_user_role` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色中间表';

-- ----------------------------
-- Table structure for r_user_role_group
-- ----------------------------
DROP TABLE IF EXISTS `r_user_role_group`;
CREATE TABLE `r_user_role_group` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `ROLE_GROUP_ID` varchar(255) NOT NULL COMMENT '角色角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色分组，中间表';

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级部门',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '机构序号，可调整该值，用于排序',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '机构类型，取自数据字典',
  `NAME` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '机构描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `SYSTEM_ONLY` varchar(20) DEFAULT NULL COMMENT '是否为系统字典，枚举BooleanEnum值',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `CATEGORY` varchar(255) DEFAULT NULL COMMENT '类型，枚举值： 1：字典分类, 2：单级业务字典, 3：多级业务字典',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '字典分类ID',
  `LEVEL` int(10) DEFAULT NULL COMMENT '字典层级，从1开始',
  `IS_LEAF` varchar(30) DEFAULT NULL COMMENT '是否为叶子节点，枚举值',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `CODE` varchar(255) DEFAULT NULL COMMENT '规则码',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务字典（含业务字典分类）表';

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `CATEGORY` varchar(255) NOT NULL COMMENT '分组类型，枚举值',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分组信息表';

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `ICON` varchar(255) DEFAULT NULL COMMENT '图标名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='前端路由菜单信息表';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Table structure for t_security
-- ----------------------------
DROP TABLE IF EXISTS `t_security`;
CREATE TABLE `t_security` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `SERVICE_ID` varchar(255) NOT NULL COMMENT '服务ID标识',
  `SECURITY_DEF` varchar(255) NOT NULL COMMENT '安全资源标识',
  `FROM_SYSTEM` int(1) NOT NULL COMMENT '是否为系统内置，系统内置资源不可编辑，用户自定义的可自由编辑',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `UNIQUE_SECURITY_DEF` (`SECURITY_DEF`) USING BTREE COMMENT '安全资源定义不允许重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全资源定义信息表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `DISABLED` int(1) NOT NULL COMMENT '是否停用',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `LOGIN_NAME` varchar(255) NOT NULL COMMENT '登陆用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '登陆密码',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `NICK_NAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `AVATAR` varchar(255) DEFAULT NULL COMMENT '肖像地址',
  `ID_NUMBER` varchar(64) DEFAULT NULL COMMENT '证件号码',
  `GENDER` varchar(255) DEFAULT NULL COMMENT '性别，枚举值，字典值',
  `BIRTHDAY` varchar(255) DEFAULT NULL COMMENT '出生日期YYYY-MM-DD',
  `PHONE` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `TAG` varchar(255) DEFAULT NULL COMMENT '用户标签',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `STATIC_TOKEN` varchar(500) DEFAULT NULL COMMENT '静态token值',
  `TOKEN_EXPIRE_TIME` varchar(20) DEFAULT NULL COMMENT 'token有效期至yyyy-MM-dd HH:mm:ss',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表，STATIC_TOKEN';

-- admin 密码为7ygv*UHB
INSERT INTO `t_user`(`ID`, `DELETED`, `DISABLED`, `SORT_NUM`, `LOGIN_NAME`, `PASSWORD`, `NAME`, `NICK_NAME`, `AVATAR`, `ID_NUMBER`, `GENDER`, `BIRTHDAY`, `PHONE`, `EMAIL`, `ADDRESS`, `TAG`, `REMARK`, `STATIC_TOKEN`, `TOKEN_EXPIRE_TIME`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('admin', 0, 0, '1', 'admin', '8816b854c5c406ceed1b77f5c789414b', 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2019-02-23 11:14:51', NULL, NULL);
INSERT INTO `t_role`(`ID`, `DELETED`, `SORT_NUM`, `NAME`, `REMARK`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('admin', 0, 'ADMIN', 'ADMIN', '系统内置的超级角色', 'admin', '2019-03-09 23:43:50', NULL, NULL);
INSERT INTO `r_user_role`(`ID`, `DELETED`, `USER_ID`, `ROLE_ID`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('562b8fea5911b4ad80fe1c4402f5efa5', 0, 'admin', 'admin', 'admin', '2019-03-14 20:04:39', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;