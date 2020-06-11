/*
Navicat MySQL Data Transfer

Source Server         : 123.57.180.154
Source Server Version : 50726
Source Host           : 123.57.180.154:3306
Source Database       : DB_Info_server

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-06-11 12:23:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态评论的ID编号唯一',
  `moving_id` int(11) NOT NULL COMMENT '评论对应动态的ID',
  `comment_content` varchar(255) NOT NULL COMMENT '评论主题内容',
  `comment_like` int(11) DEFAULT NULL COMMENT '评论点赞数量',
  `comment_like_user` varchar(255) DEFAULT NULL COMMENT '评论点赞用户ID（多个用户之间使用“，”分割）',
  `comment_status` int(11) NOT NULL COMMENT '评论的状态（0：正常；1：违规；2：删除）',
  `comment_uid` int(11) NOT NULL COMMENT '写评论的作者（编号）',
  `comment_author` varchar(25) NOT NULL COMMENT '写评论的作者（名称）',
  `reply_moving_uid` int(11) DEFAULT NULL COMMENT '所评论动态用户的ID',
  `reply_moving_uname` varchar(25) DEFAULT NULL COMMENT '所评论动态用户的用户名',
  `comment_parent` int(11) DEFAULT NULL COMMENT '评论的父ID关联本表中comment_id（评论的对象，为NULL指对动态的评论）',
  `create_time` timestamp NOT NULL COMMENT '评论创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论修改时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '想赚钱了！', '13', '22,38', '0', '1', '宝文理宋仲基', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-04-15 12:02:54');
INSERT INTO `comment` VALUES ('2', '1', '真的吗？', '0', null, '0', '2', 'Wise man', '3', '大宝Mongo', null, '2020-03-06 16:11:09', '2020-03-06 16:11:09');
INSERT INTO `comment` VALUES ('3', '1', '请问靠谱吗', '1', '38', '0', '1', '宝文理宋仲基', '3', '大宝Mongo', null, '2020-03-06 16:11:07', '2020-04-15 16:44:58');
INSERT INTO `comment` VALUES ('4', '1', '想去试一试', '1', null, '0', '2', 'Wise man', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('5', '1', '什么时间可以去面试呢', '3', null, '0', '21', '路人甲', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('6', '1', '学的不好可以去吗？', '0', null, '0', '22', '路人乙', '3', '大宝Mongo', null, '2020-03-06 16:11:15', '2020-03-06 16:11:15');
INSERT INTO `comment` VALUES ('7', '3', '说的太对了', '0', null, '0', '1', '宝文理宋仲基', '3', '大宝Mongo', null, '2020-03-06 16:11:17', '2020-03-06 16:11:17');
INSERT INTO `comment` VALUES ('8', '3', '支持你', '10', null, '0', '2', 'Wise man', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('9', '3', '恶性牟利', '3', null, '0', '27', '李梓然', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('10', '3', '赶紧解决吧！', '10', null, '0', '2', 'Wise man', '3', '大宝Mongo', null, '2020-03-06 16:11:27', '2020-03-06 16:11:27');
INSERT INTO `comment` VALUES ('11', '3', '我吃不起饭了，怎么办', '12', null, '0', '21', '路人甲', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('12', '3', '好希望可以光合作用！', '33', null, '0', '26', '抹茶奶绿', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('13', '3', '学校可以关心一下我们的感受吗', '4', null, '0', '28', '李白三剑', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('14', '3', '我早就想说了。。。', '12', null, '0', '21', '路人甲', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('15', '3', '估计竹篮打水一场空~', '22', null, '0', '31', '田小娥', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('16', '10', '520', '4', null, '0', '21', '路人甲', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('17', '10', '柠檬', '23', null, '0', '31', '田小娥', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('18', '10', '我....', '5', null, '0', '24', '薛之谦', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('19', '10', '羡慕了！！！', '1', null, '0', '25', '三人游', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('20', '10', '一辈子吧。。', '1', '38', '0', '32', 'bad girl', '3', '大宝Mongo', null, '2020-03-06 16:11:51', '2020-04-15 16:43:20');
INSERT INTO `comment` VALUES ('21', '10', '我的520在哪？', '55', null, '0', '29', '小团团', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('22', '16', '同求！', '10', null, '0', '21', '路人甲', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('23', '16', 'M', '23', null, '0', '31', '田小娥', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('24', '16', 'm', '5', null, '0', '24', '薛之谦', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('25', '16', '想骑马~', '2', null, '0', '25', '三人游', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('26', '16', '皇家马场。', '0', null, '0', '32', 'bad girl', '3', '大宝Mongo', null, '2020-03-06 16:12:03', '2020-03-06 16:12:03');
INSERT INTO `comment` VALUES ('27', '16', '我去过。。', '55', null, '0', '29', '小团团', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('28', '16', '野马跑起来', '1', null, '0', '30', '李家仙女er', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('29', '16', '一起呗，我想去来着！', '9', null, '0', '33', 'lemon tree', '3', '大宝Mongo', null, '2020-03-06 16:12:08', '2020-03-06 16:12:08');
INSERT INTO `comment` VALUES ('30', '16', '去蒙古草原，看野马奔腾。。。', '23', null, '0', '34', 'Vitamin-D', '3', '大宝Mongo', null, '2020-03-06 16:13:38', '2020-03-06 16:13:38');
INSERT INTO `comment` VALUES ('31', '1', '我也是耶，想好好赚一笔！', '2', null, '0', '24', '薛之谦', '3', '大宝Mongo', '1', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('32', '1', '一起呗', '1', null, '0', '2', 'Wise man', '3', '大宝Mongo', '1', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('33', '1', '走起，同学', '5', '38', '0', '24', '薛之谦', '3', '大宝Mongo', '1', '2020-03-06 18:47:50', '2020-04-15 09:25:23');
INSERT INTO `comment` VALUES ('34', '1', '应该不会有假吧？', '1', null, '0', '3', '大宝Mongo', '3', '大宝Mongo', '2', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('35', '1', '我也想问呢', '0', null, '0', '23', '易烊千玺fans', '3', '大宝Mongo', '3', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('36', '1', '同求', '2', '38,24', '0', '25', '三人游', '3', '大宝Mongo', '5', '2020-03-06 18:47:50', '2020-04-15 18:28:32');
INSERT INTO `comment` VALUES ('37', '1', '三人行，必有我师焉', '10', '38', '0', '25', '三人游', '3', '大宝Mongo', '6', '2020-03-06 18:47:50', '2020-04-15 11:55:57');
INSERT INTO `comment` VALUES ('38', '3', '不排除哟....', '1', null, '0', '25', '三人游', '3', '大宝Mongo', '9', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('39', '3', '做它的honey【坏笑】', '55', null, '0', '26', '抹茶奶绿', '3', '大宝Mongo', '13', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('40', '10', '我我我我什么呀？', '7', null, '0', '28', '李白三剑', '3', '大宝Mongo', '18', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('41', '10', '你也想要甜甜的爱情吗？', '11', null, '0', '29', '小团团', '3', '大宝Mongo', '18', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('42', '10', '你想挖墙脚？', '99', null, '0', '31', '田小娥', '3', '大宝Mongo', '18', '2020-03-06 18:47:50', '2020-03-06 18:47:50');
INSERT INTO `comment` VALUES ('43', '10', '别有坏想法呀？', '23', null, '0', '32', 'bad girl', '3', '大宝Mongo', '18', '2020-03-06 18:47:50', '2020-03-29 16:08:12');
INSERT INTO `comment` VALUES ('44', '10', '估计不会有', '1', '38', '0', '29', '小团团', '3', '大宝Mongo', '43', '2020-03-29 16:52:32', '2020-04-15 16:44:17');
INSERT INTO `comment` VALUES ('45', '10', '你在说什么？', '0', null, '0', '26', '抹茶奶绿', '3', '大宝Mongo', '44', '2020-03-29 17:06:43', '2020-03-29 17:06:43');
INSERT INTO `comment` VALUES ('46', '10', 'hahahha', '1', '38', '0', '31', '田小娥', '3', '大宝Mongo', '44', '2020-03-29 17:25:34', '2020-04-15 16:44:15');
INSERT INTO `comment` VALUES ('47', '10', 'hello', '0', null, '0', '31', '田小娥', '3', '大宝Mongo', '45', '2020-03-29 17:39:45', '2020-03-29 17:39:45');
INSERT INTO `comment` VALUES ('48', '10', '怎么还结巴了呢，哈哈哈', '1', '38', '0', '3', 'lemon tree', '3', '大宝Mongo', '40', '2020-03-29 22:04:59', '2020-04-15 16:44:11');
INSERT INTO `comment` VALUES ('49', '10', '可能不敢说出心里话', '2', '38', '0', '23', '易烊千玺fans', '3', '大宝Mongo', '48', '2020-03-29 22:16:11', '2020-04-15 16:49:27');
INSERT INTO `comment` VALUES ('50', '16', '可能不敢说出心里话', '0', null, '0', '21', '路人甲', '3', '大宝Mongo', '26', '2020-03-29 22:50:16', '2020-03-29 22:50:16');
INSERT INTO `comment` VALUES ('51', '16', '乱说吧', '0', null, '0', '22', '路人乙', '3', '大宝Mongo', '26', '2020-03-29 23:25:37', '2020-03-29 23:25:37');
INSERT INTO `comment` VALUES ('52', '6', '@heo 你觉得呢？', '0', null, '0', '24', '薛之谦', '3', '大宝Mongo', null, '2020-04-15 22:20:52', '2020-04-15 22:20:52');
INSERT INTO `comment` VALUES ('53', '6', '为啥我的消息不可以', '1', '37', '0', '38', '五二七', '3', '大宝Mongo', null, '2020-04-16 13:42:21', '2020-04-16 16:27:28');
INSERT INTO `comment` VALUES ('54', '11', '哈哈哈', '0', null, '0', '38', '五二七', '3', '大宝Mongo', null, '2020-04-16 14:32:37', '2020-04-16 14:32:37');
INSERT INTO `comment` VALUES ('55', '8', '会有的', '0', null, '0', '37', 'heo', '3', '大宝Mongo', null, '2020-04-16 16:06:56', '2020-04-16 16:06:56');
INSERT INTO `comment` VALUES ('56', '6', '你没有正常操作', '0', null, '0', '37', 'heo', '3', '大宝Mongo', '53', '2020-04-16 16:27:21', '2020-04-16 16:27:21');
INSERT INTO `comment` VALUES ('57', '39', '表白去吧你，别怂！', '0', null, '0', '37', 'heo', '37', 'heo', null, '2020-04-20 21:37:41', '2020-04-20 21:37:41');
INSERT INTO `comment` VALUES ('58', '39', '我又来了，惊喜不？', '0', null, '0', '37', 'heo', '37', 'heo', null, '2020-04-21 11:43:13', '2020-04-21 11:43:13');
INSERT INTO `comment` VALUES ('59', '40', '表白失败了呢？咋办', '1', '38', '0', '38', '五二七', '38', '五二七', null, '2020-04-21 11:55:35', '2020-04-21 12:52:20');
INSERT INTO `comment` VALUES ('60', '40', '999', '1', '38', '0', '38', '五二七', '38', '五二七', null, '2020-04-21 11:58:57', '2020-04-21 14:49:51');
INSERT INTO `comment` VALUES ('61', '39', '为什么就出不来呢？', '0', null, '0', '37', 'heo', '37', 'heo', null, '2020-04-21 12:14:08', '2020-04-21 12:14:08');
INSERT INTO `comment` VALUES ('62', '39', '好烦呀我', '1', '37', '0', '37', 'heo', '37', 'heo', null, '2020-04-21 12:30:30', '2020-04-21 13:05:04');
INSERT INTO `comment` VALUES ('63', '38', '加油喔⊙ω⊙', '1', '38', '0', '38', '五二七', '38', '五二七', null, '2020-04-21 13:09:07', '2020-04-21 14:50:07');
INSERT INTO `comment` VALUES ('64', '38', '不想学习了，该怎么办呀？', '1', '38', '0', '38', '五二七', '38', '五二七', null, '2020-04-21 13:36:21', '2020-04-21 14:50:29');
INSERT INTO `comment` VALUES ('65', '38', '我就想成功一次，有这么难吗？', '1', '38', '0', '38', '五二七', '38', '五二七', null, '2020-04-21 13:52:19', '2020-04-21 14:09:13');
INSERT INTO `comment` VALUES ('66', '39', '怕冷你了，快点吧', '1', '37', '0', '37', 'heo', '37', 'heo', null, '2020-04-21 14:01:26', '2020-04-21 14:20:04');
INSERT INTO `comment` VALUES ('67', '39', '你喜欢她？', '1', '37', '0', '37', 'heo', '37', 'heo', null, '2020-04-21 14:03:20', '2020-04-21 14:16:55');
INSERT INTO `comment` VALUES ('68', '41', 'hhhh', '1', '39', '0', '39', '菠萝', '37', 'heo', null, '2020-04-24 17:01:07', '2020-04-24 17:01:10');
INSERT INTO `comment` VALUES ('69', '42', '呼呼呼', '1', '4', '0', '39', '菠萝', '39', '菠萝', null, '2020-04-26 12:10:29', '2020-04-26 12:14:45');
INSERT INTO `comment` VALUES ('70', '42', '为啥我发不了，我带了图片的！', '0', null, '0', '4', '胡族威', '39', '菠萝', null, '2020-04-26 12:14:41', '2020-04-26 12:14:41');
INSERT INTO `comment` VALUES ('71', '42', '哈哈哈哈哈', '0', null, '0', '39', '菠萝', '39', '菠萝', null, '2020-04-26 12:28:36', '2020-04-26 12:28:36');
INSERT INTO `comment` VALUES ('72', '42', '咦咦咦', '0', null, '0', '39', '菠萝', '39', '菠萝', '70', '2020-04-26 12:28:45', '2020-04-26 12:28:45');
INSERT INTO `comment` VALUES ('73', '43', '凄凄切切钱', '0', null, '0', '39', '菠萝', '39', '菠萝', null, '2020-04-26 12:30:05', '2020-04-26 12:30:05');
INSERT INTO `comment` VALUES ('74', '45', '@胡族威 看着蛮好吃的样子呀', '0', null, '0', '4', '胡族威', '4', '胡族威', null, '2020-04-28 16:15:37', '2020-04-28 16:15:37');
INSERT INTO `comment` VALUES ('75', '49', '约起来好不好，哈哈哈', '0', null, '0', '38', '五二七', '38', '五二七', null, '2020-05-01 10:30:40', '2020-05-01 10:30:40');
INSERT INTO `comment` VALUES ('76', '6', '@好孩子 你觉得怎么样？', '1', '43', '0', '43', '好孩子', '3', '大宝Mongo', null, '2020-05-14 17:56:27', '2020-05-14 17:56:42');
INSERT INTO `comment` VALUES ('77', '54', '@五月丁香 加油', '1', '46', '0', '46', '五月丁香', '46', '五月丁香', null, '2020-05-15 09:14:37', '2020-05-15 09:14:48');
INSERT INTO `comment` VALUES ('78', '56', '@五月 对呀', '1', '48', '0', '48', '五月', '48', '五月', null, '2020-05-15 10:17:13', '2020-05-15 10:17:21');
INSERT INTO `comment` VALUES ('79', '57', '@五月 没错呀', '1', '49', '0', '49', '五月', '49', '五月', null, '2020-05-15 10:48:43', '2020-05-15 10:48:48');
INSERT INTO `comment` VALUES ('80', '58', '@五月 加油', '1', '50', '0', '50', '五月', '50', '五月', null, '2020-05-16 09:31:14', '2020-05-16 09:31:25');

-- ----------------------------
-- Table structure for faculty
-- ----------------------------
DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty` (
  `faculty_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '院系ID编号唯一',
  `faculty_name` varchar(50) NOT NULL COMMENT '院系名称',
  `faculty_english` varchar(255) NOT NULL COMMENT '院系英文全称',
  `english_abbreviations` varchar(255) NOT NULL COMMENT '院系英文名称缩写',
  `faculty_status` int(11) NOT NULL COMMENT '院系状态（0：正常，1：删除）',
  `faculty_desc` varchar(255) DEFAULT NULL COMMENT '院系简介',
  `create_time` timestamp NOT NULL COMMENT '院系数据创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '院系信息更新时间',
  `operator_name` varchar(25) NOT NULL COMMENT '操作员名称',
  PRIMARY KEY (`faculty_id`),
  UNIQUE KEY `faculty_name_uq` (`faculty_name`) USING BTREE COMMENT '院系名称唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of faculty
-- ----------------------------
INSERT INTO `faculty` VALUES ('1', '计算机学院', 'School of Computer', 'SC', '0', '计算机学院成立于2001年7月，其前身是原宝鸡师范学院计算机教研室，1992年与原宝鸡大学合并后设有“计算机应用（专科）”专业，1998年设置“计算机科学与技术（本科）”专业。2001年成立计算机科学系，2010年、2012年分别设置“软件工程”与“物联网工程”专业，2014年更名为计算机学院', '2020-03-04 12:57:46', '2020-03-04 12:57:46', '胡族威');
INSERT INTO `faculty` VALUES ('2', '物理与光电技术学院', 'School of Physics and Optoelectronic Technology', 'POT', '0', ' 物理与光电技术学院前身为1975年成立的陕西师范大学宝鸡分校物理系，1978年在陕西师范大学宝鸡分校的基础上成立了宝鸡师范学院并设有物理系。伴随着学院与1958建校的宝鸡大学合并、专业设置增加、办学层次提升和学科实力增强，先后更名为物理与信息技术系和物理与光电技术学院。', '2020-03-04 12:59:53', '2020-03-04 12:59:53', '胡族威');
INSERT INTO `faculty` VALUES ('3', '电子电气工程学院', 'School of Electronic and Electrical Engineering', 'EEE', '0', '电子电气工程学院始建于1984年，前身是宝鸡大学电气工程系。1992年，宝鸡大学与宝鸡师范学院合并为宝鸡文理学院，成立了电子电气工程系。2014年根据学校人才培养和学科建设工作需要，电子电气工程系更名为电子电气工程学院。目前学院涵盖电气工程、控制科学与工程、电子科学与技术、信息与通信工程四个一级学科。具有电气工程及其自动化、电子信息工程、自动化、通信工程和轨道交通信号与控制五个专业，全日制在校学生1400余人。', '2020-03-04 13:01:53', '2020-03-04 13:01:53', '胡族威');
INSERT INTO `faculty` VALUES ('4', '机械工程学院', 'School of Mechanical Engineering', 'ME', '0', '宝鸡文理学院机械工程学院前身为1984年成立的宝鸡大学机械工程系（本111科），1992年宝鸡大学与宝鸡师范学院合并后，更名为机电工程系，2014年更名为机械工程学院', '2020-03-04 13:02:00', '2020-03-04 13:02:00', '胡族威');
INSERT INTO `faculty` VALUES ('5', '化学化工学院', 'College of Chemistry and Chemical Engineering', 'CCE', '0', '化学化工学院', '2020-03-04 13:03:56', '2020-03-04 13:03:56', '胡族威');
INSERT INTO `faculty` VALUES ('6', '数学与信息科学学院', 'School of Mathematics and Information Science', 'MIS', '0', '数学与信息科学学院是宝鸡文理学院建立较早的院系之一，其前身是1975年宝鸡师范学院建立的数学系，2014年10月更名为数学与信息科学学院。2010年数学学科获批为学校申硕支撑学科立项建设项目，2015年学院获批数学教育专业硕士授权点。2018年获批数学一级学科学术型硕士学位授权点。2007年被陕西省人事厅、教育厅授予“陕西省教育系统先进集体”和“精神文明建设最佳单位”。', '2020-03-04 13:03:48', '2020-03-04 13:03:48', '胡族威');
INSERT INTO `faculty` VALUES ('7', '文学与新闻传播学院', 'literature and broadcast Institute', 'LBI', '0', '文学与新闻传播学院', '2020-03-04 13:06:34', '2020-03-04 13:06:34', '胡族威');
INSERT INTO `faculty` VALUES ('8', '历史文化与旅游学院', 'School of History, Culture and Tourism', 'HCT', '0', '历史文化与旅游学院创办于1985年，原名历史系，1988年开始招收历史学专业本科生，2004年增设旅游管理专业，2009年改名历史文化与旅游系，2013年新增文化产业管理专业，2015年开始学科教学（历史）教育硕士招生，2017年更名为历史文化与旅游学院，2019年开始旅游管理硕士招生。', '2020-03-04 13:06:45', '2020-03-04 13:06:45', '胡族威');
INSERT INTO `faculty` VALUES ('9', '外国语学院', 'Foreign Language School', 'FLS', '0', '承担学校的本科教学工作及全校英语课程', '2020-03-04 13:08:49', '2020-03-04 13:08:49', '胡族威');
INSERT INTO `faculty` VALUES ('10', '政法学院', 'Institute of Politics', 'IP', '0', '宝鸡文理学院政法学院的前身是成立于1975年的陕西师范大学宝鸡分校政教系，1993年更名为宝鸡文理学院政法系，2014年再次更名为政法学院，是我校建系最早且办学特色鲜明的院系之一', '2020-03-04 13:12:33', '2020-03-04 13:12:33', '胡族威');
INSERT INTO `faculty` VALUES ('11', '教育学院', 'College of Education', 'CE', '0', '教育学院是一个以教师教育为特色的学院，以“知行合一，超越平凡”为院风，以立德树人为根本，以教学科研为中心，以学科建设为龙头，以加强师资队伍建设为关键，以深化综合改革为动力，不断加强内涵建设，突出质量发展，彰显优势特色，增强服务能力，提升学生综合素质，为基础教育培养了大批高质量的师资', '2020-03-04 13:14:23', '2020-03-04 13:14:23', '胡族威');
INSERT INTO `faculty` VALUES ('12', '美术学院', 'Art school', 'AS', '0', '宝鸡文理学院美术学院是学校下设的艺术类二级学院，其前身是1988年创办的宝鸡师范学院教育系美术教研室，1989年开始招收美术教育专业专科生，1993年成立宝鸡文理学院艺术系。2006年成立美术系，2014年更名为美术学院。', '2020-03-04 13:13:10', '2020-03-04 13:13:10', '胡族威');
INSERT INTO `faculty` VALUES ('13', '音乐学院', 'music Academy', 'MA', '0', '音乐学院', '2020-03-04 13:14:53', '2020-03-04 13:14:53', '胡族威');
INSERT INTO `faculty` VALUES ('14', '地理与环境学院', 'School of Geography and Environment', 'GE', '0', '地理与环境学院肇始于1988年5月，前身为宝鸡师范学院地理系，于2012年9月更名为宝鸡文理学院地理与环境学院。学院目前有地理科学、测绘工程、环境工程、给排水科学与工程、自然地理与资源环境、人文地理与城乡规划等6个本科专业', '2020-03-04 13:15:15', '2020-03-04 13:15:15', '胡族威');
INSERT INTO `faculty` VALUES ('15', '经济管理学院', 'School of Economics and Management', 'EM', '0', '经济管理学院是1993年在原宝鸡文理学院职业教育部经济管理教研室基础上成立经济管理系，2014年10月更名为经济管理学院。现设有人力资源管理、工商管理、市场营销、经济学、会计学5个本科专业，其中工商管理为省级特色专业，现有应用经济、工商管理2个校级重点学科', '2020-03-04 13:15:37', '2020-03-04 13:15:37', '胡族威');
INSERT INTO `faculty` VALUES ('16', '体育学院', 'College of Physical Education', 'CPE', '0', '体育学院，本科体育专业教学及全校体育教学任务', '2020-03-04 13:14:20', '2020-03-04 13:14:20', '胡族威');
INSERT INTO `faculty` VALUES ('17', '马克思主义学院', 'academy of Marxism', 'AM', '1', '马克思主义学院', '2020-03-04 13:16:12', '2020-03-04 13:16:12', '胡族威');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块ID唯一',
  `module_name` varchar(30) NOT NULL COMMENT '模块名称',
  `module_status` int(11) NOT NULL COMMENT '模块状态（0：启用；1：禁用；2：删除）',
  `module_desc` varchar(255) DEFAULT NULL COMMENT '模块描述',
  `create_time` timestamp NOT NULL COMMENT '模块创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '模块更新时间',
  `operator_name` varchar(25) NOT NULL COMMENT '操作员名称（最后一次操作人）',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', '表白墙', '0', '用于校内同学之间互相传达个人情感的模块', '2020-03-03 20:21:51', '2020-03-03 20:21:59', '胡族威');
INSERT INTO `module` VALUES ('2', '万能墙', '0', '万能什么都可以干，什么都可以在这里得到答案和解决', '2020-03-03 20:23:29', '2020-03-03 20:23:34', '胡族威');
INSERT INTO `module` VALUES ('3', '谏言贴', '0', '在这里，帮我们建设更好的宝大一起谏言献策，说出你心中所想', '2020-03-03 20:24:42', '2020-03-03 20:24:47', '胡族威');
INSERT INTO `module` VALUES ('4', '兼职汇', '0', '零花钱，私房钱，自理生活经济，请来这里看看', '2020-03-03 20:26:06', '2020-03-03 20:26:09', '胡族威');

-- ----------------------------
-- Table structure for moving
-- ----------------------------
DROP TABLE IF EXISTS `moving`;
CREATE TABLE `moving` (
  `moving_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态ID唯一',
  `moving_imgs` varchar(255) DEFAULT NULL COMMENT '动态的图片url（多个图片之间使用“，”分隔）',
  `moving_content` varchar(255) NOT NULL COMMENT '动态主题内容',
  `topics` varchar(255) DEFAULT NULL COMMENT '动态对应的话题（使用[英文]“,”分割的字符串）',
  `moving_type` int(11) NOT NULL COMMENT '动态类型见Module（例如：兼职招聘，表白墙，万能墙，学校建议等）',
  `moving_like` int(11) DEFAULT NULL COMMENT '动态点赞数量',
  `moving_like_user` varchar(255) DEFAULT NULL COMMENT '点赞用户ID（多个用户之间使用英文“，”分割）',
  `moving_browse` int(11) DEFAULT NULL COMMENT '动态浏览量',
  `moving_author` varchar(30) NOT NULL COMMENT '写动态的作者（名称）',
  `user_id` int(11) NOT NULL COMMENT '写动态的作者（编号）',
  `comment_count` int(11) DEFAULT NULL COMMENT '动态评论总数',
  `moving_status` int(11) NOT NULL COMMENT '动态发布的状态（0：发布，1：草稿，2：删除）',
  `publish_location` varchar(255) DEFAULT NULL COMMENT '发布动态的定位位置（经纬度或者地址字符串）',
  `create_time` timestamp NOT NULL COMMENT '动态创建时间',
  `update_time` timestamp NOT NULL COMMENT '动态更新时间',
  PRIMARY KEY (`moving_id`),
  KEY `moving_fk_user` (`user_id`),
  KEY `moving_fk_module` (`moving_type`),
  FULLTEXT KEY `moving_full_index` (`moving_content`,`topics`,`moving_author`),
  CONSTRAINT `moving_fk_module` FOREIGN KEY (`moving_type`) REFERENCES `module` (`module_id`),
  CONSTRAINT `moving_fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of moving
-- ----------------------------
INSERT INTO `moving` VALUES ('1', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '好消息！好消息！宝鸡XXX培训班，因为工作需要，急需招聘辅导老师（注：物理，化学，数学，英语，生物），日结80，2小时！地址：宝鸡清姜路20号二楼。联系电话：18900902213', null, '4', '18', '3,21,22,38,24', '48', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-06 23:10:25', '2020-03-06 23:10:25');
INSERT INTO `moving` VALUES ('2', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '好消息！好消息！宝鸡XXX培训班，因为工作需要，急需招聘辅导老师（注：物理，化学，数学，英语，生物），日结80，2小时！地址：宝鸡清姜路20号二楼。联系电话：18900902213', null, '4', '27', null, '12', '大宝Mongo', '3', '0', '1', '陕西省-商洛市-山阳县', '2020-03-06 21:10:33', '2020-03-06 21:10:33');
INSERT INTO `moving` VALUES ('3', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '为了学校学生的生活水平能够得到提升，大家的生活成本可以降低，强烈建议：学校后勤集团将老校区食堂的饭价向下调整1~3左右！因为饭价一直再提升，每学期出现新价位', null, '3', '15', '38', '32', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-06 21:10:36', '2020-03-06 21:10:36');
INSERT INTO `moving` VALUES ('4', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '零花钱在这里等你来拿，经二路开元商城一楼KFC照片兼职，月结，按小时计服（11RMB/h），有想法的同学可去餐厅前台询问直接面试入职！', null, '4', '11', '38', '34', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('5', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '宝鸡女子驾校找校园代理，主要负责宣传并推荐想学车的学生，推荐有惊喜！联系电话：16777899650', null, '4', '12', '38', '8', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('6', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '希望学校能给宿舍按个空调，夏天太热了', null, '3', '158', '38,43', '358', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('7', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '食堂能否换换菜种类，一直那些吃的好腻呀', null, '3', '30', null, '12', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('8', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '校园卡能不能支持一下扫码付款呀？', null, '3', '45', null, '24', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('9', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '学前小姐姐我稀罕你，能否了解一下呢？', null, '1', '6', '38', '32', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('10', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '今天是我们认识的第520天，希望以后的每个520天都有你。一起走下去吧~~~', null, '1', '35', '38', '76', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('11', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '昨天晚上九点左右，穿着运动黑色短裤，黑T恤在操场慢跑的小姐姐，我想和你聊聊可以吗？', null, '1', '54', null, '125', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('12', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '爱上了能歌善舞的你，我想做你的唯一观众！李**，你听见了吗', null, '1', '32', null, '78', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('13', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '每次上课我不上课的我，不是为了做优等生，而是希望你在我的视线中时，我不想错过。', null, '1', '22', null, '36', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('14', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '转手李永乐的考研数学，有意者留言！', null, '2', '10', null, '21', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('15', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '周一早上第一节课后，我将耳机忘在了主教楼702第二排中间座位中，希望好心人可以联系我！', null, '2', '6', null, '10', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('16', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '有没有去过关上牧场的同学呀，怎么去？需要几天？准备些什么呀？', null, '2', '14', null, '23', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('17', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '找私人家教，要求女同学，有耐心，负责人。欢迎英语能力的同学联系我！联系电话：18125263214', null, '4', '31', null, '40', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('18', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '有没有会PS的同学呀，急需帮助！', null, '2', '11', '38', '18', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:10:51', '2020-03-04 21:10:51');
INSERT INTO `moving` VALUES ('19', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '表白墙测试数据-1', null, '1', '12', '38', '29', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:24:08', '2020-03-04 21:24:08');
INSERT INTO `moving` VALUES ('20', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '万能墙测试数据-1', null, '2', '12', null, '17', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:58', '2020-03-04 21:23:58');
INSERT INTO `moving` VALUES ('21', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '表白墙测试数据-2', null, '1', '16', null, '22', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:53', '2020-03-04 21:23:53');
INSERT INTO `moving` VALUES ('22', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '万能墙测试数据-2', null, '2', '22', null, '27', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:48', '2020-03-04 21:23:48');
INSERT INTO `moving` VALUES ('23', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '表白墙测试数据-3', null, '1', '21', null, '32', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:40', '2020-03-04 21:23:40');
INSERT INTO `moving` VALUES ('24', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '万能墙测试数据-3', null, '2', '24', null, '29', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:36', '2020-03-04 21:23:36');
INSERT INTO `moving` VALUES ('25', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '表白墙测试数据-4', null, '1', '9', null, '13', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:31', '2020-03-04 21:23:31');
INSERT INTO `moving` VALUES ('26', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '万能墙测试数据-4', null, '2', '11', null, '19', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:21:32', '2020-03-04 21:21:32');
INSERT INTO `moving` VALUES ('27', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '谏言贴测试数据-1', null, '3', '3', null, '7', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:26', '2020-03-04 21:23:26');
INSERT INTO `moving` VALUES ('28', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '谏言贴测试数据-2', null, '3', '5', null, '8', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:21', '2020-03-04 21:23:21');
INSERT INTO `moving` VALUES ('29', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '谏言贴测试数据-3', null, '3', '8', null, '9', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:18', '2020-03-04 21:23:18');
INSERT INTO `moving` VALUES ('30', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '谏言贴测试数据-4', null, '3', '13', null, '16', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:14', '2020-03-04 21:23:14');
INSERT INTO `moving` VALUES ('31', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '谏言贴测试数据-5', null, '3', '10', null, '17', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:10', '2020-03-04 21:23:10');
INSERT INTO `moving` VALUES ('32', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-1', null, '4', '17', null, '26', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:22:06', '2020-03-04 21:22:06');
INSERT INTO `moving` VALUES ('33', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-2', null, '4', '36', null, '43', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:23:04', '2020-03-04 21:23:04');
INSERT INTO `moving` VALUES ('34', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-3', null, '4', '39', null, '51', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:22:59', '2020-03-04 21:22:59');
INSERT INTO `moving` VALUES ('35', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-4', null, '4', '32', null, '37', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:22:54', '2020-03-04 21:22:54');
INSERT INTO `moving` VALUES ('36', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-5', null, '4', '1', null, '7', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:22:44', '2020-03-04 21:22:44');
INSERT INTO `moving` VALUES ('37', '/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg,/static/avatars/default_avatar.jpg', '兼职汇测试数据-6', null, '4', '5', null, '9', '大宝Mongo', '3', '0', '0', '陕西省-商洛市-山阳县', '2020-03-04 21:22:50', '2020-03-04 21:22:50');
INSERT INTO `moving` VALUES ('38', null, '找个一起2021考研的战友，租的房子还差一位主人，哪位可以合租呀，一起呀！互相监督，共同进步', '找研友,求合租,玩伴', '2', '1', '38', '10', '五二七', '38', '0', '0', '陕西省-西安市-未央区', '2020-04-17 13:32:08', '2020-04-17 13:32:08');
INSERT INTO `moving` VALUES ('39', '/images/moving/717ae08e-1d7c-468f-ade8-fe7ab0cdfa90.jpg,/images/moving/1ee6ccd5-429c-40fd-8e94-9705ae3221fa.jpg,/images/moving/8086e911-c563-47df-aecc-14e27c580f47.jpg', '那位淡黄色长裙，蓬松的头发的小姑娘，我稀罕你。', '仙女姐姐,卡哇伊', '1', '1', '37', '25', 'heo', '37', '0', '0', '陕西省-西安市-未央区', '2020-04-17 13:53:02', '2020-04-17 13:53:02');
INSERT INTO `moving` VALUES ('40', '/images/moving/43a97094-b478-4149-9bff-772085d568a0.jpg,/images/moving/60d688b9-15b2-4628-9848-922c1eb8fb09.jpg,/images/moving/03a5c697-3258-4cde-bd01-1303767616cf.jpg', '这里可以表白，可以仰慕，可以说心里话，也可以秀你们的恩爱情话。在这里情侣说了算，别人说的都没用。', '秀恩爱,女神,男神,女友,男友', '1', '1', '37', '27', '五二七', '38', '0', '0', '陕西省-西安市-未央区', '2020-04-17 15:22:59', '2020-04-17 15:22:59');
INSERT INTO `moving` VALUES ('41', '/images/moving/6fbf0d83-35b2-44c9-ba87-ee5dbd8ed035.jpg', '找个可以一起进步，一起成长的有缘人', '玩伴', '2', '2', '37,39', '7', 'heo', '37', '0', '0', '陕西省-西安市-未央区', '2020-04-24 14:37:11', '2020-04-24 14:37:11');
INSERT INTO `moving` VALUES ('42', null, '哈哈哈哈', '女神', '1', '1', '39', '38', '菠萝', '39', '0', '0', '陕西省-西安市-灞桥区', '2020-04-26 12:10:08', '2020-04-26 12:10:08');
INSERT INTO `moving` VALUES ('43', '/images/moving/e0eae33c-5ce8-443d-96da-a78fb2be08aa.jpg', '冒泡泡', '旅游攻略', '2', '2', '39,4', '4', '菠萝', '39', '0', '0', '陕西省-西安市-灞桥区', '2020-04-26 12:29:45', '2020-04-26 12:29:45');
INSERT INTO `moving` VALUES ('44', null, '天气太热了', '致歉', '2', '0', null, '1', '胡族威', '4', '0', '0', '陕西省-西安市-未央区', '2020-04-28 16:13:20', '2020-04-28 16:13:20');
INSERT INTO `moving` VALUES ('45', '/images/moving/ae9fea50-ba85-4ecf-a0f6-c5534e649a88.jpg', '有谁知道这个是啥吗？哈哈哈，黄金豆', '吐槽,美食推荐', '2', '1', '4', '3', '胡族威', '4', '0', '2', '陕西省-西安市-未央区', '2020-04-28 16:14:07', '2020-05-05 13:45:07');
INSERT INTO `moving` VALUES ('46', '/images/moving/7905fcb2-2bab-4236-a78c-bdaa1c4fa838.jpg', '有谁知道这个是啥吗？哈哈哈，黄金豆', '吐槽,美食推荐', '2', '0', null, '0', '胡族威', '4', '0', '2', '陕西省-西安市-未央区', '2020-04-28 16:14:19', '2020-05-05 13:44:58');
INSERT INTO `moving` VALUES ('47', '/images/moving/e80ae3f1-3463-41bd-966f-8be8b389e425.jpg', '有谁知道这个是啥吗？哈哈哈，黄金豆', '吐槽,美食推荐', '2', '0', null, '0', '胡族威', '4', '0', '2', '陕西省-西安市-未央区', '2020-04-28 16:14:21', '2020-05-05 13:44:38');
INSERT INTO `moving` VALUES ('48', '/images/moving/a9a71ef3-818b-4bc2-9b74-70ae294639bd.jpg', '有谁知道这个是啥吗？哈哈哈，黄金豆', '吐槽,美食推荐', '2', '0', null, '3', '胡族威', '4', '0', '0', '陕西省-西安市-未央区', '2020-04-28 16:14:21', '2020-04-28 16:14:21');
INSERT INTO `moving` VALUES ('49', '/images/moving/6a53b714-ce4e-4a66-85e6-a0de5562f907.jpg', '五一兼职来咯，找个女友陪我过五一，有意评论我加你哦。。', '小时工', '4', '0', null, '6', '五二七', '38', '0', '0', '陕西省-西安市-未央区', '2020-05-01 10:29:48', '2020-05-01 10:29:48');
INSERT INTO `moving` VALUES ('50', '/images/moving/ccce1b49-708a-4cd7-bd2e-b2fe49c777ec.jpg', '学校做决定的时候能不能顾及一下学生呀？', '教学提议', '3', '0', null, '6', '胡族威', '4', '0', '0', '陕西省-西安市-未央区', '2020-05-05 13:48:27', '2020-05-05 14:18:17');
INSERT INTO `moving` VALUES ('51', '/images/moving/1c8455aa-3155-4d31-9a2a-64747c3a560d.jpg,/images/moving/b23756c0-d027-457d-b904-33f4cb30688e.jpg', '爱上一个不爱自己的人，怎么办？她给的感觉真的是理想中的样子呀？', '仙女姐姐,小宝贝,女神', '1', '0', null, '0', '胡族威', '4', '0', '1', '陕西省-西安市-未央区', '2020-05-05 14:19:57', '2020-05-05 14:19:57');
INSERT INTO `moving` VALUES ('52', null, '墙墙你能帮我吗，我要怎样才可以追到她。好担心呀', '气死了', '2', '0', null, '0', '胡族威', '4', '0', '1', '陕西省-西安市-未央区', '2020-05-05 14:25:33', '2020-05-05 14:25:33');
INSERT INTO `moving` VALUES ('53', '/images/moving/c68213e4-5923-4b1c-bb1c-5a2aebb09e41.jpg,/images/moving/6e85f73e-bc30-4dc3-84e5-4b43b14a2584.jpg', '心情还不错。', '吐槽', '2', '1', '43', '2', '好孩子', '43', '0', '0', '陕西省-西安市-未央区', '2020-05-14 17:55:10', '2020-05-14 17:57:15');
INSERT INTO `moving` VALUES ('54', '/images/moving/9d65bf1a-ba33-46a5-a603-5e7160c868d0.jpg', '五月答辩，一切顺利！', '找研友,吐槽,美食推荐,音乐分享', '2', '1', '46', '5', '五月丁香', '46', '0', '0', '陕西省-西安市-未央区', '2020-05-15 09:12:05', '2020-05-15 09:13:42');
INSERT INTO `moving` VALUES ('55', '/images/moving/288fd82e-d064-4030-bc25-b26523255a23.jpg', '对你表白', '女神,仙女姐姐,女友,秀恩爱', '1', '0', null, '0', '五月丁香', '47', '0', '0', '陕西省-西安市-未央区', '2020-05-15 09:27:22', '2020-05-15 09:27:33');
INSERT INTO `moving` VALUES ('56', '/images/moving/96b7e85b-deb2-4eb0-b063-be03fe3716d7.jpg', '既然喜欢，就要说出来，不是吗？', '女神,卡哇伊,小宝贝,女友', '1', '1', '48', '5', '五月', '48', '0', '0', '陕西省-西安市-未央区', '2020-05-15 10:15:52', '2020-05-15 10:16:34');
INSERT INTO `moving` VALUES ('57', '/images/moving/359d8239-f3fd-4665-99b5-260c02efb862.jpg,/images/moving/2463aac6-71b4-4a97-9d66-e402d388f201.jpg', '爱我的故乡', '女神,男神,仙女姐姐,小宝贝', '1', '1', '49', '6', '五月', '49', '0', '0', '陕西省-西安市-未央区', '2020-05-15 10:47:06', '2020-05-15 10:47:51');
INSERT INTO `moving` VALUES ('58', '/images/moving/580b3ba0-9fdc-4ff5-ba35-40e9e7819642.jpg', '哈哈哈哈', '女神,卡哇伊,仙女姐姐,小宝贝,女友', '1', '1', '50', '6', '五月', '50', '0', '0', '陕西省-西安市-未央区', '2020-05-16 09:28:39', '2020-05-16 09:29:39');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复编号（主键自增）',
  `reply_type` int(11) NOT NULL COMMENT '回复类型（决定对评论的回复还是回复内容的回复，1：comment；2：reply）',
  `reply_content` varchar(255) NOT NULL COMMENT '回复主要内容',
  `reply_like` int(11) DEFAULT NULL,
  `reply_like_user` varchar(255) DEFAULT NULL COMMENT '回复点赞用户ID（多个用户之间使用“，”分割）',
  `reply_subject` int(11) NOT NULL COMMENT '回复主体ID编号（reply_type相关的ID）',
  `reply_sponsor` int(11) NOT NULL COMMENT '回复发起方ID',
  `reply_receiver` int(11) NOT NULL COMMENT '回复接收方ID',
  `reply_status` int(11) NOT NULL COMMENT '回复信息状态（0：正常；1：违规；2：删除）',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号唯一',
  `role_name` varchar(25) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名称',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色的描述信息',
  `role_status` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000' COMMENT '角色的启用状态（0：启用，1：禁用，2：删除）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通用户', '使用BJU的普通用户（即同学身份）', '00000000000');
INSERT INTO `role` VALUES ('2', '管理员', 'BJU的管理端，同样具备普通用户的身份', '00000000000');

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty` (
  `specialty_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业ID唯一',
  `specialty_code` varchar(8) NOT NULL COMMENT '专业代码',
  `specialty_name` varchar(50) NOT NULL COMMENT '专业名称',
  `specialty_status` int(11) NOT NULL COMMENT '专业状态（0：正常，1：禁用，2：删除）',
  `specialty_desc` varchar(255) DEFAULT NULL COMMENT '专业描述',
  `faculty_id` int(11) NOT NULL COMMENT '专业对应的院系',
  `create_time` timestamp NOT NULL COMMENT '专业信息创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '专业信息更新时间',
  `operator_name` varchar(25) NOT NULL COMMENT '操作员',
  PRIMARY KEY (`specialty_id`),
  KEY `faculty_id` (`faculty_id`),
  CONSTRAINT `specialty_ibfk_1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of specialty
-- ----------------------------
INSERT INTO `specialty` VALUES ('1', '080902', '软件工程', '0', '计算机网络系统和信息安全领域进行管理和服务的高级专业工程技术人才', '1', '2020-03-04 14:24:29', '2020-03-04 14:24:29', '胡族威');
INSERT INTO `specialty` VALUES ('2', '080901', '计算机科学与技术', '0', '主要在软件企业、国家机关以及各个大、中型企、事业单位等单位从事软件工程领域的技术开发工作', '1', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('3', '080905', '物联网工程', '0', '计算机网络系统和信息安全领域进行管理和服务的高级专业工程技术人才', '1', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('4', '070201', '物理学', '0', '该专业的学生毕业后可到基础教育学校、教学辅导机构从事教学工作，或是考取硕士研究生，进而到研究所从事理论研究、实验研究和技术开发与应用工作', '2', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('5', '080702', '电子科学与技术', '0', '毕业生能够从事电子信息类相关领域中电子器件、电子设备、计算机信息系统的研究、设计、开发、制造、应用、维护等工作，或是考取硕士研究生', '2', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('6', '080402', '材料物理', '0', '本专业的毕业生能够到企事业单位从事与材料相关的研发或管理工作，到科研机构、高等学校从事科研和教学工作，或继续攻读材料物理与化学及相关的工程学科、交叉学科的硕士学位', '2', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('7', '080601', '电气工程及其自动化', '0', '全国电力系统、电气类企业、技术研发单位、事业单位等；学生亦可报考国家电网、研究生或公务员', '3', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('8', '080701', '电子信息工程', '0', '主要在电子技术，新能源，计算机软、硬件设计等行业工作', '3', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('9', '080703', '通信工程', '0', '主要在涉及通信运营、现代通信设备制造、电子信息类科研院所、高新技术科技产业公司、相关企事业单位从事技术研发等工作；亦可报考研究生或公务员', '3', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('10', '080801', '自动化', '0', '在控制科学与工程、运动控制、过程控制、检测与自动化仪表、信息处理、管理与决策等相关方面', '3', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('11', '080802T', '轨道交通信号与控制', '0', '进入全国18个铁路局、工程局、通信信号公司、地铁（城轨）运营公司、铁路类和自动化类企业与技术研发单位、事业单位等，亦可报考研究生或公务员', '3', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('12', '080202', '机械设计制造及其自动化', '0', '在国家有关部门、科研院所、高等院校、企业（高级技术公司）中应用CAD及分析软件，从事各种机电产品及机电自动控制系统及设备的研究、设计、制造等', '4', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('13', '080203', '材料成型及控制工程', '0', '毕业后可在机械、材料、模具、电子、汽车、能源、交通、航空航天等行业内从事材料和产品的研究与开发、模具设计与制造、材料热加工等技术服务、管理工作', '4', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('14', '080204', '机械电子工程', '0', '本专业的毕业生具有良好的就业前景，可到各类机械设计与制造企业、电子及电器企业及其他生产部门、公司、科研与教学部门从事机电品的设计、制造、管理、教育教学、开发、销售及技术服务等工作', '4', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('15', '080205', '工业设计', '0', '毕业生可胜任产品整合创新设计以及相关研究、策划、教育和管理等岗位，也可从事平面设计的相关工作，亦可继续攻读本专业高层次学位', '4', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('16', '070301', '化学', '0', '与化学相关的领域从事教学与管理工作，继续深造从事与化学学科相关的科学研究工作', '5', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('17', '070302', '应用化学', '0', '化工企业、科研机构等从事精细化工产品的生产与技术开发、产品检测、生产管理、科研和技术服务等', '5', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('18', '081301', '化学工程与工艺', '0', '与化工相关的领域从事科研或生产管理工作，化工企业、化工设计院、科研和服务等', '5', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('19', '081302', '制药工程', '0', '本专业培养具备制药工程方面的知识，能在医药、农药、精细化工和生物化工等领域从事产品的设计、生产、开发、应用研究和经营管理等工作的高级工程技术人才', '5', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('20', '080403', '材料化学', '0', '进入电子、化工、材料、能源等行业从事工艺研发、材料制备、生产管理等；考取国内重点大学研究生继续深造；进入中学等学校从事教育工作等', '5', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('21', '070101', '数学与应用数学', '0', '主要到中小学从事数学教学与管理工作，或从事科学研究、经济金融运行等相关工作，或攻读研究生，进一步从事数学学习与研究工作', '6', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('22', '070102', '信息与计算科学', '0', '主要到科技、教育和经济部门从事数学与计算机研究、教学、应用开发和管理工作', '6', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('23', '071201', '统计学', '0', '各类企业统计、经济分析师、会计、数据分析师岗位，政府统计调查系统岗位，教育系统专业教师，研究单位研究员岗位，或继续深造', '6', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('24', '050101', '汉语言文学', '0', '本专业培养热爱中学教育教学工作，主动适应当前基础教育改革，思想品德优、专业基础厚、实践能力强（即“能说、善写、会教”）、综合素质高、发展后劲足，能在西部地区城乡中学等教育机构，从事语文学科教学与管理工作的优秀师资人才', '7', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('25', '050303', '广告学', '0', '本专业培养具有扎实的广告学理论与技能，能够在新闻媒介广告部门、广告公司、市场调查及信息咨询行业以及企事业单位从事广告经营与管理、广告策划和设计制作、市场营销策划等工作的高级专门人才', '7', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('26', '050301', '新闻学', '0', '培养可在在各级新闻媒体、传媒公司、企事业单位宣传部门工作的专业人才', '7', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('27', '130309', '播音与主持', '0', '培养可在在各级电视台、广播电台、影视公司、网络媒体、文艺团体、企事业单位宣传部门工作的专业人才', '7', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('28', '130305', '广播电视编导', '0', '本专业培养具有较高的思想政治水平、理论修养和艺术鉴赏能力，能在各级电视台、广播电台、影视公司、网络媒体、企事业单位宣传部门等机构从事创意策划、撰稿编剧、节目编导、摄制编排的高级应用人才', '7', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('29', '060101', '历史学', '0', '本专业培养具有历史学基本理论和基础知识，能够从事中等学校历史学教学和科学研究，以及其他教育的工作者', '8', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('30', '120901K', '旅游管理', '0', '本专业主要培养具有旅游管理专业知识，能在各级旅游行政管理部门、旅游企事业单位及相关部门从事旅游管理工作的专门人才', '8', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('31', '120210', '文化产业管理', '0', '培养主要在文化传播公司从事文化产品制作与传播工作。部分学生或在新兴游戏产业公司从事相关产品制作与营销工作，或在基层政府机构、文化事业单位从事文化服务与管理工作', '8', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('32', '050201', '英语', '0', '培养适应我国社会政治、经济、文化、科技发展所需要的，具有扎实的英语语言基础，广博的文化知识，较高的文化素养，较强的英语语言应用能力和英语教育教学研究能力的英语人才', '9', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('33', '050261', '翻译', '0', '本专业旨在培养德才兼备、文化素养良好、语言技能过硬，具有创新意识与国际视野的应用型翻译人才', '9', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('34', '030503', '思想政治教育', '0', '本专业培养具备较高的政治理论素质、思想道德素质，以及能够在党政机关、企事业单位的思想政治工作、社会工作、企业管理工作等方面的应用型人才', '10', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('35', '120401', '公共事业管理', '0', '在文教、体育、卫生、环保、社会保险等公共事业单位的行政管理部门从事管理等工作', '10', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('36', '030101K', '法学', '0', '本专业培养具有良好的思想政治素质，具有扎实的法学理论功底，系统掌握法律知识，熟悉法律法规，具有独立精神和职业操守的职业人', '10', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('37', '120402', '行政管理', '0', '本专业学生应系统学习马克思列宁主义、毛泽东思想、中国特色社会主义理论体系，掌握行政管理学、管理学、政治学、经济学、法学等基础理论和专业知识', '10', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('38', '010101', '哲学', '0', '本专业培养适应经济社会发展所需要的、具有一定哲学理论素养和系统专业知识，有进一步培养潜质的哲学专门人才，具有较高的综合素质和多种技能', '10', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('39', '040101', '教育学', '0', '培养德智体美劳全面发展、具有高度的社会责任感和创新精神、扎实的教育理论基础知识和良好的教育专业素养，实践能力强，能在中学和教育服务机构等领域从事教育管理，教学和咨询服务等工作的应用型人才', '11', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('40', '040106', '学前教育', '0', '本专业培养德、智、体、美、劳等方面全面发展，热爱学前教育事业，富有爱心和正确的儿童观，具备扎实的学前教育专业知识和初步的保教能力', '11', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('41', '040104', '教育技术', '0', '本专业培养具有扎实的教育技术学基础理论、基本知识，具有较强的创新精神、实践能力和应用技能，符合基础教育和各类中等教育要求', '11', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('42', '071102', '应用心理学', '0', '培养中学心理健康教育教师、心理咨询师、社会服务机构心理辅导人员、企业人力资源管理或测评人员', '11', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('43', '130401', '美术学', '0', '本专业培养德、智、体、美全面发展，系统掌握美术学的基本理论、基础知识和基本技能，具有较强的实践能力及创新精神，具备师范技能与方法，熟悉初高中美术课程标准和教材教法的应用型人才', '12', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('44', '130502', '视觉传达设计', '0', '本专业培养德、智、体、美全面发展，系统掌握视觉传达设计艺术的基本理论、基本知识和基本技能，具有较强的实践能力及创新精神，适应现代社会发展的需要的应用型人才', '12', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('45', '130503', '环境设计', '0', '可在装饰公司、建筑房地产企业、园林景观工程公司、教育单位或培训机构等企事业单位从事环境艺术设计、环境艺术项目预算、环境艺术项目组织和管理、环境艺术教学及培训等工作', '12', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('46', '130505', '服装与服饰设计', '0', '可在轻工、纺织、商业、外贸等部门的服装工厂或服装设计单位从事服装、服饰设计、研究等工作，亦可从事服装、服饰设计的教学等工作', '12', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('47', '130202', '音乐学', '0', '本专业培养掌握音乐学科基本理论、基础知识与基本技能，具备在高等和中等学校进行音乐教学和教学研究能力的教师、教学研究人员及其他教育工作者', '13', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('48', '130205', '舞蹈学', '0', '本专业培养掌握舞蹈学科基本理论、基础知识和基本技能，具备在高等和中等学校进行舞蹈教育、教学研究的教师以及在企事业单位从事舞蹈创作与编导的专门人才和舞蹈教育行政管理人员', '13', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('49', '070501', '地理科学', '0', '中学教师', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('50', '070502', '自然地理与资源环境', '0', '为适应目前社会经济发展趋势对专业人才的需求，本专业设置了三个方向课程模块', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('51', '070503', '人文地理与城乡规划', '0', '机关事业单位主要有行政机关、参军、事业单位；企业主要包括国土规划类企业、房地产评估咨询企业、城乡规划设计企业、测绘公司等；亦可考研深造', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('52', '082502', '环境工程', '0', '政府部门、规划部门、经济管理部门、环保部门、设计单位、工矿企业、科研院所、学校等从事规划、设计、管理、教学和研发等工作', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('53', '081003', '给排水科学与工程', '0', '本专业培养从事城镇、工矿企业、建筑工程等给排水工程的规划、设计、施工、运行管理和科学研究的技术人才', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('54', '081201', '测绘工程', '0', '各类土建工程建设单位、国家基础测绘、各类测绘地理信息单位、国土资源管理、城市规划及市政工程管理部门及灾害监测管理等部门', '14', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('55', '120201K', '工商管理', '0', '本专业培养具备经济、管理、法律及企业管理等方面知识和能力的应用型专业人才', '15', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('56', '120206', '人力资源管理', '0', '本专业培养具备经济、管理、法律及人力资源管理等方面知识和能力的应用型专业人才', '15', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('57', '120202', '市场营销', '0', '本专业培养具备经济、管理、法律、市场营销等方面知识和能力的应用型专业人才', '15', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('58', '120203K', '会计学', '0', '本专业培养具备经济、管理、法律和会计学等方面知识和能力的应用型专业人才', '15', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('59', '020101', '经济学', '0', '本专业培养具备扎实的经济理论知识，能够熟练地掌握现代经济学分析方法，知识面宽，具有向经济学相关领域扩展参透能力的应用型专业人才', '15', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');
INSERT INTO `specialty` VALUES ('60', '040201', '体育教育', '0', '可到各类学校从事体育的教学及管理工作，或到企事业单位从事群众体育的指导工作、体育运动训练和体育科学研究等工作', '16', '2020-03-04 14:42:15', '2020-03-04 14:42:15', '胡族威');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话题id唯一',
  `module_id` int(11) NOT NULL COMMENT '对应的模块ID',
  `topic_name` varchar(30) NOT NULL COMMENT '话题名称',
  `topic_desc` varchar(255) DEFAULT NULL COMMENT '话题简介',
  `topic_status` int(11) NOT NULL COMMENT '话题状态（0：正常；1：禁用；2：删除）',
  `create_time` timestamp NOT NULL COMMENT '话题创建时间',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '话题更新时间',
  `operator_name` varchar(25) NOT NULL COMMENT '主题创建操作人（只记录最后一个操作人）',
  PRIMARY KEY (`topic_id`),
  KEY `topic_fk_module` (`module_id`),
  CONSTRAINT `topic_fk_module` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '1', '女神', '好美的女生系列', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('2', '1', '男神', '好美的男生系列', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('3', '1', '仙女姐姐', '温柔的小女生', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('4', '1', '欧巴', '韩范男生称呼', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('5', '1', '霸道总裁', '能力与占有欲共存', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('6', '1', '卡哇伊', '日系可爱', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('7', '1', '憨憨', '新型流行语，老实憨厚', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('8', '1', '小宝贝', '专属小甜心', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('9', '1', '女友', '女朋友', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('10', '1', '男友', '女朋友', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('11', '1', '秀恩爱', '情侣秀一秀', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('12', '2', '找研友', '考研合伙人', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('13', '2', '求合租', '租房伴侣', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('14', '2', '求答案', '对提出的问题希望得到答案', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('15', '2', '玩伴', '找到一起玩同一种运动的伙伴', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('16', '2', '物品转让', '转出物品给他人', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('17', '2', '致歉', '道歉型', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('18', '2', '气死了', '发泄心中的不满', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('19', '2', '失物招领', '物品找主人', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('20', '2', '吐槽', '吐出心中所想', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('21', '2', '旅游攻略', '旅游建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('22', '2', '美食推荐', '好吃美食分享', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('23', '2', '背影杀', '好看的背影系列分享', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('24', '2', '音乐分享', '分享好听的音乐', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('25', '3', '娱乐设施', '学校设施的提议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('26', '3', '教学提议', '教学方式上的建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('27', '3', '实习优化', '对专业实习的建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('28', '3', '就业', '与就业工作相关建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('29', '3', '食堂类', '对食堂的各种提议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('30', '3', '医疗', '校园医保实施建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('31', '3', '图书馆', '加强图书馆的管理和环境', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('32', '3', '校园社团', '促进社团的发展和提高', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('33', '4', '辅导老师', '培训班辅导老师', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('34', '4', '发传单', '发传单类型的工作', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('35', '4', 'KFC', '肯德基小时工', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('36', '4', '就业', '与就业工作相关建议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('37', '4', '小时工', '对食堂的各种提议', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('38', '4', '销售', '兼职销售人员', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('39', '4', '美术音乐', '艺术培训机构找老师', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('40', '4', '扮人偶', '扮人偶类型', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('41', '4', '家教', '个人家庭找老师', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');
INSERT INTO `topic` VALUES ('42', '4', '校园代理', '企业找校园代理，协助开展业务', '0', '2020-03-04 12:22:28', '2020-03-04 12:22:28', '胡族威');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID唯一',
  `role_id` int(11) NOT NULL COMMENT '用户角色',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户头像',
  `user_nickname` varchar(25) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `user_password` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户登录密码',
  `user_mobile` varchar(11) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户联系电话',
  `user_borth` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户生日',
  `user_address` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户所在地址：例如陕西宝鸡',
  `user_hobby` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户爱好（多个爱好使用“,”分割）',
  `user_motto` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户座右铭',
  `faculty_name` varchar(255) DEFAULT NULL COMMENT '院系名称',
  `faculty_id` int(11) NOT NULL COMMENT '所属院系id唯一',
  `specialty_name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `specialty_id` int(11) NOT NULL COMMENT '所属专业',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_mobile_uq` (`user_mobile`) USING BTREE COMMENT '手机号码唯一索引',
  UNIQUE KEY `user_nickname_uq` (`user_nickname`) USING BTREE COMMENT '用户昵称唯一索引',
  KEY `role_id_fk` (`role_id`) USING BTREE COMMENT '角色ID外键',
  KEY `faculty_id_fk` (`faculty_id`) USING BTREE COMMENT '院系ID外键',
  KEY `specialty_id_fk` (`specialty_id`) USING BTREE COMMENT '专业外键',
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`),
  CONSTRAINT `user_ibfk_3` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '/static/avatars/default_avatar.jpg', '宝文理宋仲基', 'E10ADC3949BA59ABBE56E057F20F883E', '18800201236', '1997-02-21', '陕西省宝鸡市', '摄影,旅游,阅读', '宝大是我家，一起建设它！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('2', '1', '/static/avatars/default_avatar.jpg', 'Wise man', 'E10ADC3949BA59ABBE56E057F20F883E', '17898890091', '1998-09-23', '陕西省西安市', '打王者,吃鸡', '实在不知道说些什么，那就什么都说！', '地理与环境学院', '14', '给排水科学与工程', '53');
INSERT INTO `user` VALUES ('3', '1', '/static/avatars/default_avatar.jpg', '大宝Mongo', 'E10ADC3949BA59ABBE56E057F20F883E', '17800908997', '1998-11-23', '陕西省宝鸡市', '吃饭,睡觉,打豆豆', 'coding is my life!', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('4', '2', '/images/avatars/361dab61-3755-460a-8a88-1af7d2799abe.jpg', '胡族威', '36E1A5072C78359066ED7715F5FF3DA8', '18700950156', '1996-05-27', '陕西省宝鸡市', '敲代码', '努力做好自己想做的事', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('5', '1', '/static/avatars/default_avatar.jpg', 'hzuwei', 'E10ADC3949BA59ABBE56E057F20F883E', '18000980789', '1997-09-21', '陕西省宝鸡市', '摄影,旅游,阅读', '宝大是我家，一起建设它！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('21', '1', '/static/avatars/default_avatar.jpg', '路人甲', 'E10ADC3949BA59ABBE56E057F20F883E', '18000981111', '1997-09-21', '陕西省宝鸡市', '摄影,旅游,阅读', '此处略去一万字~~', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('22', '1', '/static/avatars/default_avatar.jpg', '路人乙', 'E10ADC3949BA59ABBE56E057F20F883E', '18000981212', '1997-09-21', '陕西省渭南市', '摄影,旅游,阅读', '此处略去一万字~~', '政法学院', '10', '行政管理', '37');
INSERT INTO `user` VALUES ('23', '1', '/static/avatars/default_avatar.jpg', '易烊千玺fans', 'E10ADC3949BA59ABBE56E057F20F883E', '18123980129', '1997-09-21', '陕西省宝鸡市', '摄影,旅游,阅读', '此处略去一万字~~', '外国语学院', '9', '翻译', '33');
INSERT INTO `user` VALUES ('24', '1', '/static/avatars/default_avatar.jpg', '薛之谦', 'E10ADC3949BA59ABBE56E057F20F883E', '18020945789', '1997-09-21', '陕西省延安市', '摄影,旅游', '超级爱唱歌的我，丑八怪～～', '外国语学院', '9', '英语', '32');
INSERT INTO `user` VALUES ('25', '1', '/static/avatars/default_avatar.jpg', '三人游', 'E10ADC3949BA59ABBE56E057F20F883E', '18300998309', '1997-09-21', '陕西省宝鸡市', '摄影,旅游,阅读', '此处略去一万字~~', '化学化工学院', '5', '制药工程', '19');
INSERT INTO `user` VALUES ('26', '1', '/static/avatars/default_avatar.jpg', '抹茶奶绿', 'E10ADC3949BA59ABBE56E057F20F883E', '18645750789', '1997-09-21', '陕西省宝鸡市', '摄影,旅游,阅读', '此处略去一万字~~', '化学化工学院', '5', '化学', '16');
INSERT INTO `user` VALUES ('27', '1', '/static/avatars/default_avatar.jpg', '李梓然', 'E10ADC3949BA59ABBE56E057F20F883E', '18192650789', '1997-09-21', '四川省广元市', '摄影,旅游,阅读', '此处略去一万字~~', '政法学院', '10', '行政管理', '37');
INSERT INTO `user` VALUES ('28', '1', '/static/avatars/default_avatar.jpg', '李白三剑', 'E10ADC3949BA59ABBE56E057F20F883E', '18502653489', '1997-09-21', '江西省抚州市', '摄影,旅游,阅读', '此处略去一万字~~', '机械工程学院', '4', '工业设计', '15');
INSERT INTO `user` VALUES ('29', '1', '/static/avatars/default_avatar.jpg', '小团团', 'E10ADC3949BA59ABBE56E057F20F883E', '18302650000', '1997-09-21', '湖北省武汉市', '摄影,旅游,阅读', '此处略去一万字~~', '文学与新闻传播学院', '7', '广告学', '25');
INSERT INTO `user` VALUES ('30', '1', '/static/avatars/default_avatar.jpg', '李家仙女er', 'E10ADC3949BA59ABBE56E057F20F883E', '18902123789', '1997-09-21', '陕西省咸阳市', '摄影,旅游,阅读', '此处略去一万字~~', '文学与新闻传播学院', '7', '汉语言文学', '24');
INSERT INTO `user` VALUES ('31', '1', '/static/avatars/default_avatar.jpg', '田小娥', 'E10ADC3949BA59ABBE56E057F20F883E', '18802650789', '1997-09-21', '陕西省西安市', '摄影,旅游,阅读', '此处略去一万字~~', '电子电气工程学院', '3', '电气工程及其自动化', '7');
INSERT INTO `user` VALUES ('32', '1', '/static/avatars/default_avatar.jpg', 'bad girl', 'E10ADC3949BA59ABBE56E057F20F883E', '18800000789', '1997-09-21', '甘肃省庆阳市', '摄影,旅游,阅读', '此处略去一万字~~', '体育学院', '16', '体育教学', '60');
INSERT INTO `user` VALUES ('33', '1', '/static/avatars/default_avatar.jpg', 'lemon tree', 'E10ADC3949BA59ABBE56E057F20F883E', '18102611119', '1997-09-21', '辽宁省大连市', '摄影,旅游,阅读', '此处略去一万字~~', '历史文化与旅游学院', '8', '历史学', '29');
INSERT INTO `user` VALUES ('34', '1', '/static/avatars/default_avatar.jpg', 'Vitamin-D', 'E10ADC3949BA59ABBE56E057F20F883E', '18103445789', '1997-09-21', '陕西省汉中市', '摄影,旅游,阅读', '此处略去一万字~~', '历史文化与旅游学院', '8', '旅游管理', '30');
INSERT INTO `user` VALUES ('35', '1', '/static/avatars/default_avatar.jpg', 'oxygen', 'E10ADC3949BA59ABBE56E057F20F883E', '18302659999', '1997-09-21', '陕西省西安市', '摄影,旅游,阅读', '此处略去一万字~~', '地理与环境学院', '14', '给排水科学与工程', '53');
INSERT INTO `user` VALUES ('37', '1', '/static/avatars/default_avatar.jpg', 'heo', 'E10ADC3949BA59ABBE56E057F20F883E', '18800090201', '2020-04-14', '陕西省宝鸡市', '睡觉', '我爱宝鸡文理学院！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('38', '1', '/images/avatars/d33b7ea0-7a61-443c-b378-fe06191ae566.jpg', '五二七', 'E10ADC3949BA59ABBE56E057F20F883E', '18700980516', '2010-04-14', '陕西省商洛市', '打游戏', '快快乐乐', '电子电气工程学院', '3', '电气工程及其自动化', '7');
INSERT INTO `user` VALUES ('39', '1', '/images/avatars/d242538b-6b71-4658-9187-d288343e9d6d.jpg', '菠萝', '18C49ACFFFFD23A897E515EF3395D313', '18591939279', '1997-09-27', '陕西省商洛市', '睡觉，睡觉', '我爱宝鸡文理学院！hhhh', '计算机学院', '1', '计算机科学与技术', '2');
INSERT INTO `user` VALUES ('40', '1', '/static/avatars/default_avatar.jpg', '叫啥呀', '1C88D37BE4E1D375F341D906F58288F4', '18740505134', '2020-04-26', '陕西省宝鸡市', '睡觉', '我爱宝鸡文理学院！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('41', '1', '/static/avatars/default_avatar.jpg', '痞子史', 'E10ADC3949BA59ABBE56E057F20F883E', '17629156689', '2020-04-26', '陕西省宝鸡市', '睡觉', '我爱宝鸡文理学院！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('42', '1', '/static/avatars/default_avatar.jpg', '小鹿乱撞', 'E10ADC3949BA59ABBE56E057F20F883E', '18700950517', '2020-05-11', '陕西省宝鸡市', '睡觉', '我爱宝鸡文理学院！', '计算机学院', '1', '软件工程', '1');
INSERT INTO `user` VALUES ('43', '1', '/images/avatars/576b10a1-2196-4e90-9c6a-ceed4c275312.jpg', '李佳琪', '6C44E5CD17F0019C64B042E4A745412A', '18700960516', '2008-05-14', '陕西省渭南市', '打游戏', '爱学习的好孩子', '电子电气工程学院', '3', '电气工程及其自动化', '7');
INSERT INTO `user` VALUES ('46', '1', '/images/avatars/f3be70bf-9edd-4e6b-9ef3-5115703d22b8.jpg', '孙尚香', '6C44E5CD17F0019C64B042E4A745412A', '18700940516', '2012-05-15', '青海省海东市', '看电影', '万事开头难', '化学化工学院', '5', '化学', '16');
INSERT INTO `user` VALUES ('47', '1', '/images/avatars/cbeac238-2c44-4213-93a0-d015d687a5bf.jpg', '钟馗', 'E10ADC3949BA59ABBE56E057F20F883E', '18700990516', '2020-05-15', '甘肃省金昌市', '嘿嘿', '1234567', '外国语学院', '9', '英语', '32');
INSERT INTO `user` VALUES ('48', '1', '/images/avatars/8408066a-44c5-4732-9035-6cb452dc738c.jpg', '大乔', 'E10ADC3949BA59ABBE56E057F20F883E', '18700910516', '2020-05-15', '贵州省贵阳市', '吃', '我爱宝鸡', '政法学院', '10', '思想政治教育', '34');
INSERT INTO `user` VALUES ('49', '1', '/images/avatars/a9c991e1-0a09-49f0-b0c3-4adea534a6bd.jpg', '小乔', 'E10ADC3949BA59ABBE56E057F20F883E', '18700950518', '2020-05-15', '陕西省渭南市', '玩', '我爱学习', '电子电气工程学院', '3', '自动化', '10');
INSERT INTO `user` VALUES ('50', '1', '/images/avatars/96a2ff85-bc26-4b6e-a5a0-2f6557a491d6.jpg', '五月', 'E10ADC3949BA59ABBE56E057F20F883E', '18700950516', '1990-05-16', '陕西省商洛市', '玩游戏', '答辩了，希望一切顺利', '计算机学院', '1', '物联网工程', '3');
