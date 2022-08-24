DROP DATABASE IF EXISTS ye_community;
CREATE DATABASE IF NOT EXISTS ye_community;
USE ye_community;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `ye_user`;
CREATE TABLE `ye_user`  (
                            `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
                            `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
                            `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
                            `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
                            `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                            `score` int NOT NULL DEFAULT 0 COMMENT '积分',
                            `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'token',
                            `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
                            `active` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否激活，1：是，0：否',
                            `status` bit(1) NULL DEFAULT b'1' COMMENT '状态，1：使用，0：停用',
                            `role_id` int NULL DEFAULT NULL COMMENT '用户角色',
                            `create_time` datetime NOT NULL COMMENT '加入时间',
                            `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `user_name`(`username`) USING BTREE,
                            INDEX `user_email`(`email`) USING BTREE,
                            INDEX `user_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;
