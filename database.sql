DROP DATABASE IF EXISTS ye_community;
CREATE DATABASE IF NOT EXISTS ye_community;
USE ye_community;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
                            `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
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


INSERT INTO `ye_user` VALUES ('1349290158897311745', 'admin', 'admin', '123456',  '23456@qq.com', 2,'我是超级管理员哦！！', b'1', b'1', NULL, '2021-01-13 17:40:17', NULL);
INSERT INTO `ye_user` VALUES ('1349618748226658305', 'zhangsan', 'zhangsan', '12345', '123456@qq.com', 0,'哥没有简介', b'1', b'1', NULL, '2021-01-14 15:25:59', NULL);


DROP TABLE IF EXISTS `ye_question`;
CREATE TABLE `ye_question`  (
                            `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题ID',
                            `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
                            `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题内容',
                            `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提问者ID',
                            `answer_num` int NOT NULL DEFAULT 0 COMMENT '回答数目',
                            `create_time` datetime NOT NULL COMMENT '创建时间',
                            `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `title`(`title`) USING BTREE,
                            INDEX `user_id`(`user_id`) USING BTREE,
                            INDEX `create_time`(`create_time`) USING BTREE

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提问表' ROW_FORMAT = DYNAMIC;

INSERT INTO `ye_question` VALUES ('1349618748226654587', '第一啊', '这是第一个问题的内容哦！', '1349290158897311745', 0,'2021-01-14 15:25:59',NULL);


DROP TABLE IF EXISTS `ye_answer`;
CREATE TABLE `ye_answer`  (
                                `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答ID',
                                `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答内容',
                                `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答者ID',
                                `question_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题ID',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                INDEX `user_id`(`user_id`) USING BTREE,
                                INDEX `question_id`(`question_id`) USING BTREE,
                                INDEX `create_time`(`create_time`) USING BTREE

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回答表' ROW_FORMAT = DYNAMIC;

INSERT INTO `ye_answer` VALUES ('1349618748556654587',  '回答了第一个问题哦！', '1349618748226658305', '1349618748226654587','2021-01-15 15:25:59',NULL);


DROP TABLE IF EXISTS `ye_comment`;
CREATE TABLE `ye_comment`  (
                              `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论ID',
                              `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
                              `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者ID',
                              `question_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题ID',
                              `answer_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回答ID',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `user_id`(`user_id`) USING BTREE,
                              INDEX `question_id`(`question_id`) USING BTREE,
                              INDEX `answer_id`(`answer_id`) USING BTREE,
                              INDEX `create_time`(`create_time`) USING BTREE

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

INSERT INTO `ye_comment` VALUES ('1343418748556654587',  '评论了第一个问题的回答哦！', '1349290158897311745','1349618748226654587', '1349618748556654587','2021-01-16 15:25:59',NULL);
