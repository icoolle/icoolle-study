DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_user_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_user_id` bigint(20) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` tinyint(1) NULL DEFAULT 0,
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `ums_admin_user`;
CREATE TABLE `ums_admin_user`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `user_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户code',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_type` tinyint(1) NULL DEFAULT NULL COMMENT '用户类型',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_login_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `display` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `jobs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `job_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
  `extension_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分机号',
  `work_site` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办工地点',
  `hiredate` datetime(0) NULL DEFAULT NULL COMMENT '入职',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_user_id` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `remember_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否记住token',
  `multiple_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否多个用户登录',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user_del_flag`(`del_flag`) USING BTREE,
  INDEX `sys_user_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_user
-- ----------------------------
INSERT INTO `ums_admin_user` VALUES (5763448979734976, '10000000', 'admin', '$2a$10$cP/mU0S5.28haZklmSE1q.ET6DF6Eyr.RkmlyUB88dMr9b.r.fnYq', '', '', '', '', 0, '头像', '', '2021-01-01 00:00:00', 0, '备注', '', NULL, NULL, NULL, NULL, NULL, 1, '2021-01-01 00:00:00', 5763448979734976, '2021-01-01 00:00:00', 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
