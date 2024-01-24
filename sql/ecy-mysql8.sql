CREATE
DATABASE /*!32312 IF NOT EXISTS*/`ecy-exam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE
`ecy-exam`;


DROP TABLE IF EXISTS `e_exam_dept`;

CREATE TABLE `e_exam_dept`
(
    `id`            bigint                                                       NOT NULL AUTO_INCREMENT,
    `exam_id`       bigint                                                       NOT NULL COMMENT '考试id',
    `department_id` bigint                                                       NOT NULL COMMENT '部门id',
    `create_time`   datetime DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime DEFAULT NULL COMMENT '修改时间',
    `create_by`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `e_exam_dept` */

/*Table structure for table `e_exam_info` */

DROP TABLE IF EXISTS `e_exam_info`;

CREATE TABLE `e_exam_info`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `exam_name`   varchar(100) NOT NULL COMMENT '考试名称',
    `exam_desc`   varchar(100) DEFAULT NULL COMMENT '考试描述',
    `open_type`   int          DEFAULT NULL COMMENT '开放形式',
    `duration`    int          NOT NULL COMMENT '考试时长(分钟)',
    `start_time`  datetime     NOT NULL COMMENT '开始时间',
    `end_time`    datetime     NOT NULL COMMENT '结束时间',
    `total_score` int          DEFAULT NULL COMMENT '考试总分',
    `pass_score`  int          DEFAULT NULL COMMENT '及格分',
    `status`      int          NOT NULL COMMENT '状态',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50)  NOT NULL COMMENT '创建人',
    `update_by`   varchar(50)  NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `e_exam_info` */

/*Table structure for table `e_exam_question` */

DROP TABLE IF EXISTS `e_exam_question`;

CREATE TABLE `e_exam_question`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `exam_id`     bigint                                                       NOT NULL COMMENT '考试id',
    `question_id` bigint                                                       NOT NULL COMMENT '题目id',
    `score`       int                                                          NOT NULL COMMENT '分数',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `e_exam_question` */

/*Table structure for table `e_exam_user` */

DROP TABLE IF EXISTS `e_exam_user`;

CREATE TABLE `e_exam_user`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `exam_id`     bigint                                                       NOT NULL COMMENT '考试id',
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `e_exam_user` */

/*Table structure for table `e_question` */

DROP TABLE IF EXISTS `e_question`;

CREATE TABLE `e_question`
(
    `id`               bigint                                                      NOT NULL AUTO_INCREMENT,
    `question_bank_id` bigint                                                      NOT NULL COMMENT '题库id',
    `content`          longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目',
    `type`             int                                                         NOT NULL COMMENT '类型',
    `level`            varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目难度',
    `answer`           longtext COMMENT '正确答案',
    `create_time`      datetime                                                    NOT NULL COMMENT '创建时间',
    `update_time`      datetime                                                    NOT NULL COMMENT '修改时间',
    `create_by`        varchar(50)                                                 NOT NULL COMMENT '创建人',
    `update_by`        varchar(50)                                                 NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `e_question` */

/*Table structure for table `e_question_bank` */

DROP TABLE IF EXISTS `e_question_bank`;

CREATE TABLE `e_question_bank`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='题库';

/*Data for the table `e_question_bank` */

/*Table structure for table `e_question_option` */

DROP TABLE IF EXISTS `e_question_option`;

CREATE TABLE `e_question_option`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT,
    `question_id` bigint      NOT NULL COMMENT '题目id',
    `content`     longtext    NOT NULL COMMENT '内容',
    `is_true`     tinyint(1) NOT NULL COMMENT '是否正确',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='答案选项';

/*Data for the table `e_question_option` */

/*Table structure for table `e_user_paper` */

DROP TABLE IF EXISTS `e_user_paper`;

CREATE TABLE `e_user_paper`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `exam_id`     bigint                                                       NOT NULL COMMENT '考试id',
    `status`      int      DEFAULT NULL COMMENT '试卷状态',
    `enter_time`  datetime DEFAULT NULL COMMENT '进入时间',
    `finish_time` datetime DEFAULT NULL COMMENT '交卷时间',
    `score`       float    DEFAULT NULL COMMENT '得分',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `e_user_paper` */

/*Table structure for table `e_user_question` */

DROP TABLE IF EXISTS `e_user_question`;

CREATE TABLE `e_user_question`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `exam_id`     bigint                                                       NOT NULL COMMENT '考试id',
    `paper_id`    bigint                                                       NOT NULL COMMENT '试卷id',
    `question_id` bigint                                                       NOT NULL COMMENT '问题id',
    `answer`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '答案',
    `score`       float DEFAULT NULL COMMENT '得分',
    `is_true`     tinyint(1) DEFAULT NULL COMMENT '是否正确',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `basic_department`;

CREATE TABLE `basic_department`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint                                                       NOT NULL COMMENT '父id',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门名称',
    `leader`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '负责人',
    `phone`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '联系电话',
    `enabled`     tinyint(1) NOT NULL COMMENT '是否启用',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

insert into `basic_department`(`id`, `parent_id`, `name`, `leader`, `phone`, `enabled`, `create_time`, `update_time`,
                               `create_by`, `update_by`)
values (1, 0, '董事会', 'yongoe', '10086', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (2, 1, '销售部门', 'yongoe', '10086', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (3, 1, '研发部门', 'yongoe', '10086', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (4, 1, '行政部门', 'yongoe', '10086', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe');


DROP TABLE IF EXISTS `basic_dict`;

CREATE TABLE `basic_dict`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
    `type`        varchar(50) COLLATE utf8mb4_unicode_ci NULL COMMENT '字典类型',
    `remark`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

insert into `basic_dict`(`id`, `name`, `type`, `remark`, `create_time`, `update_time`, `create_by`, `update_by`)
values (3, '题目类型', '', NULL, '2024-01-08 17:30:58', '2024-01-08 17:32:35', 'yongoe', 'yongoe'),
       (4, '题目难度', '', NULL, '2024-01-08 17:31:31', '2024-01-08 17:36:16', 'yongoe', 'yongoe'),
       (5, '考试范围', NULL, NULL, '2024-01-08 18:16:10', '2024-01-08 18:16:10', 'yongoe', 'yongoe'),
       (6, '考试状态', NULL, NULL, '2024-01-08 18:20:30', '2024-01-08 18:20:30', 'yongoe', 'yongoe'),
       (7, '试卷状态', NULL, NULL, '2024-01-09 17:38:46', '2024-01-09 17:38:46', 'yongoe', 'yongoe');



DROP TABLE IF EXISTS `basic_dict_data`;

CREATE TABLE `basic_dict_data`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `dict_id`     bigint                                                       NOT NULL COMMENT '字典id',
    `dict_key`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典键',
    `dict_value`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典值',
    `color`       varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
    `sort`        int                                                          NOT NULL COMMENT '字典顺序',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `basic_dict_data`(`id`, `dict_id`, `dict_key`, `dict_value`, `color`, `sort`, `create_time`, `update_time`,
                              `create_by`, `update_by`)
values (5, 3, '单选', '1', 'rgba(93, 164, 234, 1)', 0, '2024-01-08 17:31:07', '2024-01-08 18:18:02', 'yongoe',
        'yongoe'),
       (6, 3, '多选', '2', 'rgba(93, 234, 220, 1)', 0, '2024-01-08 17:31:11', '2024-01-08 18:18:10', 'yongoe',
        'yongoe'),
       (7, 3, '判断', '3', 'rgba(138, 218, 67, 1)', 0, '2024-01-08 17:31:16', '2024-01-08 18:18:15', 'yongoe',
        'yongoe'),
       (8, 3, '简答', '4', 'rgba(235, 205, 34, 1)', 0, '2024-01-08 17:31:20', '2024-01-08 18:18:21', 'yongoe',
        'yongoe'),
       (9, 4, '正常', '正常', 'rgba(228, 219, 219, 1)', 0, '2024-01-08 17:31:57', '2024-01-08 18:17:45', 'yongoe',
        'yongoe'),
       (10, 5, '所有人', '1', 'rgba(255, 69, 0, 1)', 0, '2024-01-08 18:16:38', '2024-01-08 18:16:59', 'yongoe',
        'yongoe'),
       (11, 5, '指定部门', '2', 'rgba(203, 176, 35, 1)', 0, '2024-01-08 18:16:46', '2024-01-08 18:17:31', 'yongoe',
        'yongoe'),
       (12, 5, '指定用户', '3', 'rgba(45, 128, 210, 1)', 0, '2024-01-08 18:16:51', '2024-01-08 18:17:27', 'yongoe',
        'yongoe'),
       (13, 6, '未组卷', '1', 'rgba(100, 177, 251, 1)', 0, '2024-01-08 18:20:55', '2024-01-09 18:19:33', 'yongoe',
        'yongoe'),
       (15, 6, '未开始', '2', 'rgba(250, 212, 0, 1)', 0, '2024-01-08 18:21:14', '2024-01-09 18:19:07', 'yongoe',
        'yongoe'),
       (16, 6, '进行中', '3', 'rgba(255, 69, 0, 1)', 0, '2024-01-08 18:21:21', '2024-01-09 18:19:21', 'yongoe',
        'yongoe'),
       (17, 6, '已结束', '4', 'rgba(238, 228, 224, 1)', 0, '2024-01-08 18:21:29', '2024-01-09 18:19:26', 'yongoe',
        'yongoe'),
       (18, 7, '未参加', '1', 'rgba(239, 227, 227, 1)', 0, '2024-01-09 17:39:03', '2024-01-09 18:17:57', 'yongoe',
        'yongoe'),
       (19, 7, '考试中', '2', 'rgba(250, 212, 0, 1)', 0, '2024-01-09 17:39:16', '2024-01-09 18:18:41', 'yongoe',
        'yongoe'),
       (20, 7, '已交卷', '3', 'rgba(97, 164, 230, 1)', 0, '2024-01-09 17:39:28', '2024-01-09 18:18:13', 'yongoe',
        'yongoe'),
       (21, 7, '通过', '4', 'rgba(144, 238, 144, 1)', 0, '2024-01-09 17:39:34', '2024-01-09 18:18:20', 'yongoe',
        'yongoe'),
       (22, 7, '未通过', '5', 'rgba(227, 114, 72, 1)', 0, '2024-01-09 17:39:40', '2024-01-09 18:18:28', 'yongoe',
        'yongoe');

DROP TABLE IF EXISTS `basic_position`;

CREATE TABLE `basic_position`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职位',
    `enabled`     tinyint(1) NOT NULL COMMENT '是否启用',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `basic_position`(`id`, `name`, `enabled`, `create_time`, `update_time`, `create_by`, `update_by`)
values (1, '首席执行官(CEO)', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (2, '首席财务官(CFO)', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (3, '首席运营官(COO)', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (4, '首席技术官(CTO)', 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe');


DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config`
(
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT,
    `config_key`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
    `config_value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '值',
    `remark`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `create_time`  datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time`  datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`    varchar(50) COLLATE utf8mb4_unicode_ci                       NOT NULL COMMENT '创建人',
    `update_by`    varchar(50) COLLATE utf8mb4_unicode_ci                       NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `key` (`config_key`) COMMENT 'key'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint                                                       NOT NULL COMMENT '父id',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
    `type`        int                                                          NOT NULL COMMENT '类型',
    `url`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接口路径',
    `component`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件位置',
    `icon`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
    `sort`        int                                                          NOT NULL COMMENT '顺序',
    `keep_alive`  tinyint(1) NOT NULL COMMENT '是否缓存',
    `is_show`     tinyint(1) NOT NULL COMMENT '是否显示',
    `enabled`     tinyint(1) NOT NULL COMMENT '是否启用',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `sys_menu`(`id`, `parent_id`, `name`, `type`, `url`, `component`, `icon`, `sort`, `keep_alive`, `is_show`,
                       `enabled`, `create_time`, `update_time`, `create_by`, `update_by`)
values (1, 0, '系统管理', 1, '', '', 'fa fa-cog', 0, 0, 1, 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe',
        'yongoe'),
       (2, 0, '基础信息', 1, '', '', 'fa fa-table', 0, 0, 1, 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe',
        'yongoe'),
       (3, 1, '用户管理', 2, '/system/user/**', '/system/User', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (4, 1, '角色管理', 2, '/system/role/**', '/system/Role', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (5, 1, '菜单管理', 2, '/system/menu/**', '/system/Menu', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (6, 1, '系统配置', 2, '/system/config/**', '/system/Config', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (7, 2, '部门', 2, '/basic/department/**', '/basic/Department', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (8, 2, '职位', 2, '/basic/position/**', '/basic/Position', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (9, 2, '数据字典', 2, '/basic/dict/**', '/basic/Dict', '', 0, 0, 1, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (10, 2, '数据字典-数据', 2, '/basic/dict/data/**', '/basic/DictData', '', 0, 0, 0, 1, '2023-01-01 00:00:00',
        '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (11, 1, '系统日志', 2, '/system/log/**', '/system/Log', '', 0, 0, 1, 1, '2023-09-19 05:51:08',
        '2023-09-19 05:51:08', 'yongoe', 'yongoe'),
       (100, 0, '考试', 1, '', '', 'fa fa-pencil', 1, 0, 1, 1, '2024-01-08 16:40:48', '2024-01-08 17:18:41', 'yongoe',
        'yongoe'),
       (108, 100, '考试管理', 2, '', '/exam/ExamInfo', '', 3, 0, 1, 1, '2024-01-08 08:45:41', '2024-01-09 21:09:52',
        'yongoe', 'yongoe'),
       (109, 108, '查', 3, '/exam/examinfo/page*', '', '', 0, 0, 1, 1, '2024-01-08 08:45:41', '2024-01-08 08:45:41',
        'yongoe', 'yongoe'),
       (110, 108, '增', 3, '/exam/examinfo/add', '', '', 0, 0, 1, 1, '2024-01-08 08:45:41', '2024-01-08 08:45:41',
        'yongoe', 'yongoe'),
       (111, 108, '改', 3, '/exam/examinfo/update', '', '', 0, 0, 1, 1, '2024-01-08 08:45:41', '2024-01-08 08:45:41',
        'yongoe', 'yongoe'),
       (112, 108, '删', 3, '/exam/examinfo/delete/*', '', '', 0, 0, 1, 1, '2024-01-08 08:45:41', '2024-01-08 08:45:41',
        'yongoe', 'yongoe'),
       (115, 143, '题库管理', 2, '', '/question/QuestionBank', '', 2, 0, 1, 1, '2024-01-08 08:46:00',
        '2024-01-08 17:18:28', 'yongoe', 'yongoe'),
       (116, 115, '查', 3, '/exam/questionbank/page*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:00', '2024-01-08 08:46:00',
        'yongoe', 'yongoe'),
       (117, 115, '增', 3, '/exam/questionbank/add', '', '', 0, 0, 1, 1, '2024-01-08 08:46:00', '2024-01-08 08:46:00',
        'yongoe', 'yongoe'),
       (118, 115, '改', 3, '/exam/questionbank/update', '', '', 0, 0, 1, 1, '2024-01-08 08:46:00',
        '2024-01-08 08:46:00', 'yongoe', 'yongoe'),
       (119, 115, '删', 3, '/exam/questionbank/delete/*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:00',
        '2024-01-08 08:46:00', 'yongoe', 'yongoe'),
       (122, 143, '题目管理', 2, '', '/question/Question', '', 1, 1, 1, 1, '2024-01-08 08:46:08', '2024-01-09 09:17:29',
        'yongoe', 'yongoe'),
       (123, 122, '查', 3, '/exam/question/page*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:08', '2024-01-08 08:46:08',
        'yongoe', 'yongoe'),
       (124, 122, '增', 3, '/exam/question/add', '', '', 0, 0, 1, 1, '2024-01-08 08:46:08', '2024-01-08 08:46:08',
        'yongoe', 'yongoe'),
       (125, 122, '改', 3, '/exam/question/update', '', '', 0, 0, 1, 1, '2024-01-08 08:46:08', '2024-01-08 08:46:08',
        'yongoe', 'yongoe'),
       (126, 122, '删', 3, '/exam/question/delete/*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:08', '2024-01-08 08:46:08',
        'yongoe', 'yongoe'),
       (129, 143, '题目答案', 2, '', '/question/QuestionOption', '', 0, 0, 0, 1, '2024-01-08 08:46:15',
        '2024-01-08 17:13:30', 'yongoe', 'yongoe'),
       (130, 129, '查', 3, '/exam/questionoption/page*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:15',
        '2024-01-08 08:46:15', 'yongoe', 'yongoe'),
       (131, 129, '增', 3, '/exam/questionoption/add', '', '', 0, 0, 1, 1, '2024-01-08 08:46:15', '2024-01-08 08:46:15',
        'yongoe', 'yongoe'),
       (132, 129, '改', 3, '/exam/questionoption/update', '', '', 0, 0, 1, 1, '2024-01-08 08:46:15',
        '2024-01-08 08:46:15', 'yongoe', 'yongoe'),
       (133, 129, '删', 3, '/exam/questionoption/delete/*', '', '', 0, 0, 1, 1, '2024-01-08 08:46:15',
        '2024-01-08 08:46:15', 'yongoe', 'yongoe'),
       (136, 100, '我的考试', 2, '', '/paper/UserPaper', '', 0, 0, 1, 1, '2024-01-08 08:46:25', '2024-01-09 21:11:05',
        'yongoe', 'yongoe'),
       (143, 0, '试题', 1, '', '', 'fa fa-question-circle', 2, 0, 1, 1, '2024-01-08 17:00:11', '2024-01-08 21:24:06',
        'yongoe', 'yongoe'),
       (144, 100, '组卷', 2, NULL, '/exam/config/Config', NULL, 0, 0, 0, 1, '2024-01-08 21:23:38',
        '2024-01-08 21:24:39', 'yongoe', 'yongoe'),
       (145, 100, '查看试卷', 2, NULL, '/paper/ExamDetails', NULL, 0, 0, 0, 1, '2024-01-09 20:59:16',
        '2024-01-09 20:59:16', 'yongoe', 'yongoe'),
       (146, 100, '阅卷', 2, NULL, '/exam/CheckPaper', NULL, 0, 0, 0, 1, '2024-01-10 09:12:56', '2024-01-10 09:12:56',
        'yongoe', 'yongoe'),
       (147, 100, '批改', 2, NULL, '/exam/CheckQuestion', NULL, 0, 0, 0, 1, '2024-01-10 09:34:13',
        '2024-01-10 09:34:13', 'yongoe', 'yongoe');


DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色代码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '中文名',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

/*Data for the table `sys_role` */

insert into `sys_role`(`id`, `code`, `name`, `create_time`, `update_time`, `create_by`, `update_by`)
values (1, 'admin', '超级管理员', '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe'),
       (2, 'register', '学生', '2023-01-01 00:00:00', '2024-01-11 13:23:30', 'yongoe', 'yongoe'),
       (4, 'teacher', '教师', '2024-01-11 13:24:00', '2024-01-11 13:24:00', 'yongoe', 'yongoe');

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `role_id`     bigint                                                       NOT NULL COMMENT '角色id',
    `menu_id`     bigint                                                       NOT NULL COMMENT '菜单id',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `sys_role_menu`(`id`, `role_id`, `menu_id`, `create_time`, `update_time`, `create_by`, `update_by`)
values (25, 2, 100, '2024-01-11 13:54:02', '2024-01-11 13:54:02', 'yongoe', 'yongoe'),
       (26, 2, 136, '2024-01-11 13:54:02', '2024-01-11 13:54:02', 'yongoe', 'yongoe'),
       (27, 2, 145, '2024-01-11 13:54:02', '2024-01-11 13:54:02', 'yongoe', 'yongoe'),
       (28, 4, 143, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (29, 4, 115, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (30, 4, 119, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (31, 4, 117, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (32, 4, 118, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (33, 4, 116, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (34, 4, 122, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (35, 4, 123, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (36, 4, 124, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (37, 4, 125, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (38, 4, 126, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (39, 4, 129, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (40, 4, 130, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (41, 4, 131, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (42, 4, 132, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (43, 4, 133, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (44, 4, 100, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (45, 4, 108, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (46, 4, 109, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (47, 4, 110, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (48, 4, 111, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (49, 4, 112, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (50, 4, 144, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (51, 4, 146, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe'),
       (52, 4, 147, '2024-01-11 14:19:48', '2024-01-11 14:19:48', 'yongoe', 'yongoe');


DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT,
    `department_id` bigint                                                       DEFAULT NULL COMMENT '部门id',
    `position_id`   bigint                                                       DEFAULT NULL COMMENT '职位id',
    `username`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '账号',
    `password`      varchar(50) COLLATE utf8mb4_unicode_ci                        NOT NULL COMMENT '密码',
    `name`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '姓名',
    `avatar`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头像',
    `email`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '邮箱',
    `phone`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '联系电话',
    `remark`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `last_ip`       varchar(50) COLLATE utf8mb4_unicode_ci                       DEFAULT NULL COMMENT '上次登录ip',
    `last_time`     datetime                                                     DEFAULT NULL COMMENT '上次登录时间',
    `enabled`       tinyint(1) NOT NULL COMMENT '是否启用',
    `create_time`   datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                                      NOT NULL COMMENT '修改时间',
    `create_by`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '创建人',
    `update_by`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) COMMENT '用户名唯一'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `sys_user`(`id`, `department_id`, `position_id`, `username`, `password`, `name`, `avatar`, `email`, `phone`,
                       `remark`, `last_ip`,
                       `last_time`, `enabled`, `create_time`, `update_time`, `create_by`, `update_by`)
values (1, 1, 1, 'admin', '111111', 'yongoe',
        'https://img.xjh.me/random_img.php', '121887765@qq.com', '10086',
        '无', '127.0.0.1', '2023-01-01 00:00:00', 1,
        '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe');


DROP TABLE IF EXISTS `sys_user_auths`;

CREATE TABLE `sys_user_auths`
(
    `id`           bigint                                                        NOT NULL AUTO_INCREMENT,
    `user_id`      bigint                                                        NOT NULL COMMENT '用户id',
    `login_type`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '登陆类型',
    `openid`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标识',
    `access_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '凭据',
    `create_time`  datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time`  datetime                                                      NOT NULL COMMENT '修改时间',
    `create_by`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '创建人',
    `update_by`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`          bigint                                                       NOT NULL AUTO_INCREMENT,
    `user_id`     bigint                                                       NOT NULL COMMENT '用户id',
    `role_id`     bigint                                                       NOT NULL COMMENT '角色id',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


insert into `sys_user_role`(`id`, `user_id`, `role_id`, `create_time`, `update_time`, `create_by`, `update_by`)
values (1, 1, 1, '2023-01-01 00:00:00', '2023-01-01 00:00:00', 'yongoe', 'yongoe');

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '用户',
    `type`        varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '类型',
    `title`       varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
    `details`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '详情',
    `create_time` datetime                               NOT NULL COMMENT '创建时间',
    `update_time` datetime                               NOT NULL COMMENT '修改时间',
    `create_by`   varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
    `update_by`   varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
