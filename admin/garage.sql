-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2018-12-07 14:06:26
-- 服务器版本： 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `garage`
--

-- --------------------------------------------------------

--
-- 表的结构 `all_garage_status`
--

CREATE TABLE IF NOT EXISTS `all_garage_status` (
`id` int(10) NOT NULL,
  `total_num` int(10) NOT NULL,
  `used_num` int(10) NOT NULL,
  `not_used_num` int(10) NOT NULL,
  `fault_num` int(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `all_garage_status`
--

INSERT INTO `all_garage_status` (`id`, `total_num`, `used_num`, `not_used_num`, `fault_num`) VALUES
(1, 4, 1, 3, 0);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `entity_type` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `content`, `user_id`, `username`, `entity_id`, `entity_type`, `created_date`, `status`) VALUES
(1, 'https://wenku.baidu.com/view/bbf095a6f524ccbff12184ec.html', 12, '', 11, 1, '2018-07-30 16:15:29', 0),
(2, 'https://wenku.baidu.com/view/bbf095a6f524ccbff12184ec.html', 12, '', 11, 1, '2018-07-30 16:15:37', 0),
(3, 'iloioli', 12, '', 13, 1, '2018-07-31 14:53:29', 0),
(4, 'werwer', 3, '', 12, 1, '2018-07-31 14:56:32', 0),
(5, '', 12, '', 13, 1, '2018-09-02 13:12:41', 0),
(6, 'gbhfghdg', 12, '', 14, 1, '2018-09-18 11:18:51', 0),
(7, '梵蒂冈梵蒂冈', 12, '', 15, 1, '2018-09-26 10:42:05', 0),
(8, '123456', 10, '', 18, 1, '2018-09-30 13:15:19', 0),
(9, 'dsfsdf', 10, 'wan', 18, 1, '2018-09-30 13:27:49', 0),
(10, '12321312', 10, 'wan', 18, 1, '2018-09-30 13:32:41', 0),
(11, 'gffsffg', 10, 'wan', 18, 1, '2018-09-30 13:33:21', 0),
(12, 'dsfadsa', 10, 'wan', 18, 1, '2018-09-30 13:34:49', 0),
(13, 'sadfsf', 10, 'wan', 18, 1, '2018-09-30 13:37:25', 0),
(14, '第三方士大夫', 13, 'user1', 20, 1, '2018-10-15 15:43:39', 0),
(15, '法师打发士大夫胜多负少', 10, 'user1', 16, 1, '2018-10-18 13:45:12', 0),
(16, '水电费水电费水电费是的', 10, 'user1', 23, 1, '2018-10-18 13:47:23', 0),
(17, '发的规范的非官方大哥', 10, 'user1', 23, 1, '2018-10-18 13:53:46', 0),
(18, '发给撒大哥是的范德萨', 10, 'user1', 23, 1, '2018-10-18 14:37:22', 0),
(19, '发打发士大夫士大夫撒', 10, 'user1', 23, 1, '2018-10-18 14:37:31', 0),
(20, '规范化规范化', 9, 'li', 22, 1, '2018-11-28 15:51:19', 0),
(21, '水电费水电费', 9, 'li', 11, 1, '2018-12-07 17:26:31', 0),
(22, '范德萨范德萨', 9, 'li', 9, 1, '2018-12-07 17:27:59', 0),
(23, '双方的公司大', 9, 'li', 9, 1, '2018-12-07 17:31:42', 0);

-- --------------------------------------------------------

--
-- 表的结构 `fault_history`
--

CREATE TABLE IF NOT EXISTS `fault_history` (
`id` int(10) NOT NULL,
  `garage_id` int(10) NOT NULL,
  `address` varchar(20) COLLATE utf8_bin NOT NULL,
  `jiechu_state` int(10) NOT NULL,
  `fault_type` varchar(20) COLLATE utf8_bin NOT NULL,
  `jianxiu_state` int(1) NOT NULL,
  `submit_time` datetime NOT NULL,
  `jiechu_time` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `fault_history`
--

INSERT INTO `fault_history` (`id`, `garage_id`, `address`, `jiechu_state`, `fault_type`, `jianxiu_state`, `submit_time`, `jiechu_time`) VALUES
(1, 2, '江苏省南京市2号车库', 0, '急停报警', 0, '2018-09-01 11:14:15', '2018-09-06 11:16:13'),
(2, 2, '江苏省南京市2号车库', 0, '防松链报警', 1, '2018-09-02 11:14:15', '2018-09-06 11:16:13'),
(3, 4, '江苏省南京市4号车库', 0, '光电报警', 1, '2018-09-03 11:14:15', NULL),
(4, 3, '江苏省南京市3号车库', 0, '防松链报警', 1, '2018-09-04 11:14:15', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `fault_warning`
--

CREATE TABLE IF NOT EXISTS `fault_warning` (
`id` int(11) NOT NULL,
  `ji_ting` int(11) NOT NULL,
  `guang_dian` int(11) NOT NULL,
  `re_ji_guo_zai` int(1) NOT NULL,
  `duan_dian` int(11) NOT NULL,
  `fang_song_lian` int(11) NOT NULL,
  `ji_xian` int(11) NOT NULL,
  `gua_gou` int(11) NOT NULL,
  `xiang_xu` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `fault_warning`
--

INSERT INTO `fault_warning` (`id`, `ji_ting`, `guang_dian`, `re_ji_guo_zai`, `duan_dian`, `fang_song_lian`, `ji_xian`, `gua_gou`, `xiang_xu`) VALUES
(1, 0, 1, 1, 1, 1, 1, 1, 1),
(2, 0, 0, 1, 0, 0, 0, 0, 0),
(3, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `feed`
--

CREATE TABLE IF NOT EXISTS `feed` (
`id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `data` tinytext,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `feed`
--

INSERT INTO `feed` (`id`, `created_date`, `user_id`, `data`, `type`) VALUES
(1, '2018-11-29 17:19:05', 8, '{"userHead":"http://images.nowcoder.com/head/435t.png","questionId":"10","questionTitle":"TITLE{9}","userName":"USER8","userId":"8"}', 4),
(2, '2018-12-07 17:31:42', 5, '{"questionId":"9","questionTitle":"TITLE{8}","userName":"wangwu","userId":"5"}', 6);

-- --------------------------------------------------------

--
-- 表的结构 `garage_info`
--

CREATE TABLE IF NOT EXISTS `garage_info` (
`id` int(10) NOT NULL,
  `latitude` float NOT NULL,
  `longtitude` float NOT NULL,
  `address` varchar(25) COLLATE utf8_bin NOT NULL,
  `total_num` int(10) NOT NULL,
  `free_num` int(10) NOT NULL,
  `price_per_hour` float NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `garage_info`
--

INSERT INTO `garage_info` (`id`, `latitude`, `longtitude`, `address`, `total_num`, `free_num`, `price_per_hour`) VALUES
(1, 31.0429, 120.82, '江苏省苏州市吴江区聚力机械', 13, 8, 10.23),
(2, 32.0576, 118.932, '江苏省南京市2号车库', 100, 39, 8),
(3, 32.076, 118.856, '江苏省南京市3号车库', 80, 16, 6),
(4, 32.0875, 118.766, '江苏省南京市1号车库', 60, 35, 9);

-- --------------------------------------------------------

--
-- 表的结构 `garage_manager`
--

CREATE TABLE IF NOT EXISTS `garage_manager` (
`id` int(11) NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `password` varchar(128) COLLATE utf8_bin NOT NULL,
  `salt` varchar(32) COLLATE utf8_bin NOT NULL,
  `authority` int(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `garage_manager`
--

INSERT INTO `garage_manager` (`id`, `username`, `password`, `salt`, `authority`) VALUES
(1, 'admin', 'B9A040999507FDAA484F96F44852042B', '49be7', 0),
(2, 'lifan', '71CD0C2BD94DED16C8E46982E1E11D28', '44a58', 1),
(3, 'zhangsan', 'CDD91045DB75C3281E73A48307CF51A9', '90983', 2),
(4, 'lisi', '0727E2A4CC5C7DC8F840E29F744DA07D', 'a499e', 3),
(5, 'wangwu', '80754E300E45208539A379CAA1AA6A5B', '9ac4b', 4),
(6, 'liu', 'F58098954A8A48654A009D40BE5747D1', 'a822e', -1),
(7, 'wan', '80754E300E45208539A379CAA1AA6A5B', '9ac4b', -1),
(8, 'fan', '80754E300E45208539A379CAA1AA6A5B', '9ac4b', -1),
(9, 'li', '8815AF10B45BE2B2B40DD567EE8D0823', '764d5', -1),
(10, 'user1', '1B5BF0DB4193214FE45E014B0D91DBBF', '9bb08', -1);

-- --------------------------------------------------------

--
-- 表的结构 `history_solution`
--

CREATE TABLE IF NOT EXISTS `history_solution` (
`id` int(10) NOT NULL,
  `garage_id` int(10) NOT NULL,
  `fault_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `fault_content` varchar(20) COLLATE utf8_bin NOT NULL,
  `deal_time` datetime NOT NULL,
  `deal_manager` varchar(20) COLLATE utf8_bin NOT NULL,
  `solution` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `history_solution`
--

INSERT INTO `history_solution` (`id`, `garage_id`, `fault_type`, `fault_content`, `deal_time`, `deal_manager`, `solution`) VALUES
(1, 1, '急停报警', 'sad范德萨发的说法第三方', '2018-10-01 09:17:18', 'admin', '集体那是范德萨范德萨范德萨范德萨范德萨发大水发的撒范德萨范德萨发'),
(2, 1, '防松链报警', '大师的范德萨发斯蒂芬', '2018-10-02 09:17:18', 'admin', '集体那是范德萨范德萨范德萨范德萨范德萨发大水发的撒范德萨范德萨发');

-- --------------------------------------------------------

--
-- 表的结构 `late_order`
--

CREATE TABLE IF NOT EXISTS `late_order` (
`id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `car_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `action_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `cancel_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `order_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `start_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `money` float NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=130 ;

--
-- 转存表中的数据 `late_order`
--

INSERT INTO `late_order` (`id`, `username`, `car_num`, `action_time`, `cancel_time`, `order_time`, `start_time`, `money`) VALUES
(129, '18959204245', '豫FSQ818', '2018-06-05 08:50', '', '2018-06-05 10:12', '', 67.6667);

-- --------------------------------------------------------

--
-- 表的结构 `life_manage`
--

CREATE TABLE IF NOT EXISTS `life_manage` (
`id` int(10) NOT NULL,
  `garage_id` int(10) NOT NULL,
  `address` varchar(20) COLLATE utf8_bin NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `life_manage`
--

INSERT INTO `life_manage` (`id`, `garage_id`, `address`, `start_time`, `end_time`) VALUES
(1, 1, '江苏省南京市1号车库', '2018-06-01 00:00:00', NULL),
(2, 2, '江苏省南京市2号车库', '2018-06-01 00:00:00', NULL),
(3, 3, '江苏省南京市3号车库', '2018-06-01 00:00:00', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `login_ticket`
--

CREATE TABLE IF NOT EXISTS `login_ticket` (
`id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=232 ;

--
-- 转存表中的数据 `login_ticket`
--

INSERT INTO `login_ticket` (`id`, `user_id`, `ticket`, `expired`, `status`) VALUES
(140, 5, '56ed3f1a8f7545faa003333bab309969', '2018-09-28 15:23:02', 0),
(141, 10, '7097c828f14d4302bcaa5fadbf3855ab', '2018-09-30 11:32:38', 0),
(142, 10, '6a4180faf6524c6ab9485b9adbe96a93', '2018-09-30 11:53:02', 0),
(143, 10, '6f60b6f32a3144798fbee02efdf079ac', '2018-09-30 13:08:49', 0),
(144, 10, 'c66ddf189ec745d4ad49fa169c6a3e83', '2018-10-01 12:58:05', 1),
(145, 11, '8dac7941ce024d0a8f5f39d5f8828c08', '2018-10-01 15:38:32', 0),
(146, 10, '61858f0198314043b4d073918c676ee1', '2018-10-01 16:15:53', 1),
(147, 11, 'b725a1f6683a46db989eab68933a2355', '2018-10-01 17:01:40', 1),
(148, 10, '89b468013f43450d83d5f57252822517', '2018-10-01 18:51:28', 1),
(149, 5, '7cb56add68f2413d98fc8889e4fdbf71', '2018-10-01 18:51:48', 1),
(150, 12, '5b31c97d550a4d498114b523e28da392', '2018-10-01 18:52:17', 0),
(151, 10, 'ee644f89ea6446af8e07b0d7fe3e7038', '2018-10-01 19:19:37', 0),
(152, 10, 'b00a538928d9498b94d2c21ff46267ed', '2018-10-02 11:03:44', 0),
(153, 5, '231bf8eaf92c44cba7ed51cd5eb869b8', '2018-10-09 15:29:06', 0),
(154, 5, '52dfb6f75d374ca2b13bbe3f469e34c7', '2018-10-10 13:47:43', 0),
(155, 5, 'b40fb342ece647f9a53e52ae27f0f5c0', '2018-10-10 15:48:11', 0),
(156, 5, 'db81b5f28aab4d6eb30d479615a66559', '2018-10-10 15:50:31', 0),
(157, 5, '6135887bbcbf426b912a93ae6e2c5ce5', '2018-10-13 20:12:39', 0),
(158, 5, 'a4d1d53103aa4d2083ddc62a842400f3', '2018-10-14 11:26:39', 0),
(159, 5, 'a257c51d50c747488fc66af4f33f3ee9', '2018-10-14 11:29:08', 0),
(160, 5, 'ba0581b1778f441b94a9670c895edc6d', '2018-10-14 12:02:35', 0),
(161, 5, '94048ec31df348868f705ecc2a9a8dcb', '2018-10-14 12:23:17', 0),
(162, 5, '65d601bc334f4b2eae34977aa5ec0147', '2018-10-14 12:38:22', 0),
(163, 5, '206a68b14003423ea8838e3019ff04b1', '2018-10-14 12:38:51', 0),
(164, 5, 'a59d72b77eae42c3b2a28a4481f53095', '2018-10-16 15:31:46', 1),
(165, 13, '711849f1e7894ca396bf4255dd80d4fd', '2018-10-16 15:32:22', 1),
(166, 5, '0170b1f994604df3aa24ab0404dc9d01', '2018-10-16 15:35:31', 1),
(167, 5, 'b9753304c634464d8a64e282394e6d2e', '2018-10-16 15:39:54', 1),
(168, 13, '2aac59f11adb4ff4880bfecb138b9e6f', '2018-10-16 15:40:21', 1),
(169, 5, 'd6463e5f6ff14b9da94671f976450e24', '2018-10-16 16:46:01', 1),
(170, 13, '2556d724730046858f44a364e99de546', '2018-10-16 16:46:20', 0),
(171, 5, '13d560749cf0467a8aff1bcc848e92bb', '2018-10-18 11:50:50', 1),
(172, 10, 'e539168c27ab443387bbdc50877fc06d', '2018-10-18 11:51:17', 1),
(173, 5, 'f5c22028d7494846b6194e88828b3ac9', '2018-10-18 12:06:52', 1),
(174, 10, '1ca5a364c3304887a0312bb2d966bb8d', '2018-10-18 12:07:04', 1),
(175, 5, '63db3d769b99439995fa454d9cb8f57d', '2018-10-18 12:13:15', 1),
(176, 10, '1141e0fa01a746bf95cfee91bca3412d', '2018-10-18 12:13:29', 1),
(177, 10, '4771c0a9593e4ef8b0e279633b907045', '2018-10-18 12:15:23', 0),
(178, 10, '74a76846cc0048f4bf88f576a407b77d', '2018-10-18 12:16:17', 1),
(179, 12, 'c6aa742b6b4348bd92d1bb9d91bcf6a5', '2018-10-18 12:20:35', 0),
(180, 12, '4c655e47a8324f3195c4601587d3a1e4', '2018-10-18 12:49:14', 1),
(181, 5, 'efd017b2172c4566bc854aac3860ade2', '2018-10-18 13:10:32', 1),
(182, 12, '3155b7084bdd4f8faad169c78a612f54', '2018-10-18 13:11:07', 0),
(183, 12, '8359ec9e8c264f9d99265a3eaf3e818b', '2018-10-18 13:42:01', 1),
(184, 5, '7128e2ca6a8c41238ca31bbfad7f7a7f', '2018-10-18 13:43:23', 1),
(185, 12, '07267f23bc9b45529d45871a90058c5f', '2018-10-18 13:44:24', 0),
(186, 12, '78444b16739447c68c69e87f4b38f091', '2018-10-18 13:53:45', 1),
(187, 12, '9a087a7f83224ad4ad0716d085bd6c16', '2018-10-18 15:47:46', 0),
(188, 12, 'bf6caff5e58242f9aeffd125c01701d5', '2018-10-19 13:36:11', 1),
(189, 13, 'a398acd399a24dbdb24a654c865191d5', '2018-10-19 13:37:52', 0),
(190, 1, 'd7aac5c5a6194626a7b59bbce48c6fc5', '2018-11-08 15:55:20', 0),
(191, 2, 'da61851673fa445bbf9110cfc8be9adb', '2018-11-29 15:42:38', 0),
(192, 9, '01312b4dcaa145a09decf00840f34f93', '2018-11-29 15:50:46', 1),
(193, 1, 'baeb46ecec5b4a1cb2ff75a7cf12c4a2', '2018-11-29 15:56:08', 1),
(194, 9, '4fb779e5dd554a96bc2103c470876854', '2018-11-29 15:58:50', 0),
(195, 12, '3d7de3be13a34d9797217b8d7bb653f4', '2018-11-30 17:07:20', 0),
(196, 9, 'cd3a6414236b4fa4ae25af39cb033c98', '2018-11-30 17:22:03', 1),
(197, 8, '1b3628993be546ce9ffccb08c1826fee', '2018-11-30 17:23:52', 0),
(198, 9, '94ff680e8a0643d5a5530fa08f3c689c', '2018-12-02 13:32:16', 0),
(199, 8, '2d5fe2eaa11243aba74b6da5a22176de', '2018-12-03 13:05:14', 0),
(200, 8, '9356de687bb84b80a96b079f276d4ec7', '2018-12-03 15:22:50', 1),
(201, 8, '85f1f70dc14f4a14859da7bd9169307d', '2018-12-03 17:14:08', 1),
(202, 8, 'a42a649432314f1b9029c2583c81179d', '2018-12-03 17:17:06', 0),
(203, 8, '891d9ce149d448329ee5fb6592ca9f6e', '2018-12-03 17:17:27', 1),
(204, 8, 'd7e5792a721d435683cc391ebe691f7b', '2018-12-03 17:18:03', 1),
(205, 9, '0ca2e47c4a2d434e8347eb2e54f01233', '2018-12-03 17:18:46', 1),
(206, 8, '71bfb5d88d0e4a2bb2125fb4195056a9', '2018-12-03 17:19:19', 0),
(207, 8, '4e6f2d08f08943049e5398d86b0d12fa', '2018-12-03 19:33:49', 1),
(208, 9, '1993a53f286f46afa0b3202e936cd8cd', '2018-12-03 19:34:28', 1),
(209, 9, '37f3b6d9c1974e738fb3e804ec705c52', '2018-12-03 19:36:32', 1),
(210, 9, 'f6a0802d6adf4fd18e79cfadb2eb2421', '2018-12-03 20:27:19', 1),
(211, 9, 'eeaa209bc4ca4b109a6de7fb9eb35d2d', '2018-12-03 20:49:18', 1),
(212, 9, '053ebd72520b46fda2f063239349fd40', '2018-12-03 20:52:57', 0),
(213, 9, '4d70e648910e49efa8f27558d8217c4e', '2018-12-03 20:54:44', 1),
(214, 8, '26d7ddfeb5754575babb4c39d2e5c69d', '2018-12-03 20:56:08', 1),
(215, 8, 'b3bafabbcaaa477ba4f3dbb8cab1c754', '2018-12-03 20:56:23', 0),
(216, 9, 'd1957bb794e44dc395150abe29a5bcd7', '2018-12-03 21:25:37', 0),
(217, 9, '65d22aafaa6e44179ab15658e7a9829c', '2018-12-03 21:37:45', 0),
(218, 9, '7d1b38d344f64b0ea4355c8953b5c06d', '2018-12-03 21:50:49', 0),
(219, 9, 'd8707e31d1364d6d8fa344fb808abb9c', '2018-12-03 22:02:01', 1),
(220, 8, '2c84a8705b5e4336999000b9e5750f06', '2018-12-03 22:11:37', 0),
(221, 8, '7bf99e8a43e448a4a0e9757fa4da0848', '2018-12-03 22:14:25', 1),
(222, 9, '84480be0fffe40f29f3821a46522348f', '2018-12-04 13:45:42', 1),
(223, 8, '6b05d7a5ad2e476298e5a2bf8ce4c7d3', '2018-12-04 13:46:00', 1),
(224, 1, '75a5e3d54f6844449bd9d7ea038c423e', '2018-12-04 16:01:39', 0),
(225, 1, '34da5005122046dd96dfd089cafb8a1a', '2018-12-04 16:07:52', 0),
(226, 1, '8b31fb1f1a1a40d9a1ea98b3b8f40c16', '2018-12-05 14:45:07', 0),
(227, 1, '19cc7f3fb5154af69ffa0cdbb4ad99ff', '2018-12-05 15:22:44', 0),
(228, 1, 'ced3cad447504e14b099c015d0d25837', '2018-12-06 11:46:41', 0),
(229, 1, '4bf80c8577db46208199da4f3aec78ec', '2018-12-06 16:34:30', 0),
(230, 1, 'b847272647ab4644a9fc38972f95ad21', '2018-12-08 13:01:44', 0),
(231, 9, '91a0e094734a420caf7b19ea04456da4', '2018-12-08 17:26:21', 0);

-- --------------------------------------------------------

--
-- 表的结构 `member`
--

CREATE TABLE IF NOT EXISTS `member` (
`id` int(20) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `car_num` varchar(20) CHARACTER SET utf8 NOT NULL,
  `car_type` varchar(20) CHARACTER SET utf8 NOT NULL,
  `status` int(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=28 ;

--
-- 转存表中的数据 `member`
--

INSERT INTO `member` (`id`, `username`, `password`, `car_num`, `car_type`, `status`) VALUES
(1, '18959204245', '123456', '赣F16712', 'Aventador LP700-4', 1),
(26, '18795968928', '123456', '粤CKC236', '白色保时捷', 1),
(27, '18362903376', '123456', '豫FSQ818', '黑色丰田', 1);

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE IF NOT EXISTS `message` (
`id` int(11) NOT NULL,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `content` text,
  `created_date` datetime DEFAULT NULL,
  `has_read` int(11) DEFAULT NULL,
  `conversation_id` varchar(45) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=72 ;

--
-- 转存表中的数据 `message`
--

INSERT INTO `message` (`id`, `from_id`, `to_id`, `content`, `created_date`, `has_read`, `conversation_id`) VALUES
(1, 10, 10, '梵蒂冈梵蒂冈丰东股份', '2018-10-18 13:45:01', 1, '10_10'),
(2, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:45:19', 1, '1_10'),
(3, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:47:47', 1, '1_10'),
(4, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:03', 1, '1_10'),
(5, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:04', 1, '1_10'),
(6, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:05', 1, '1_10'),
(7, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:06', 1, '1_10'),
(8, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:08', 1, '1_10'),
(9, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:09', 1, '1_10'),
(10, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:10', 1, '1_10'),
(11, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:12', 1, '1_10'),
(12, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:14', 1, '1_10'),
(13, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 13:54:16', 1, '1_10'),
(14, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:12:14', 0, '1_10'),
(15, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:12:15', 0, '1_10'),
(16, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:12:16', 0, '1_10'),
(17, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:40:30', 0, '1_10'),
(18, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:40:31', 0, '1_10'),
(19, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:41:44', 0, '1_10'),
(20, 1, 10, '用户user1赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-10-18 14:48:49', 0, '1_10'),
(21, 1, 1, '用户admin赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-11-07 15:55:31', 0, '1_1'),
(22, 1, 1, '用户admin赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-11-07 16:01:01', 0, '1_1'),
(23, 1, 2, '用户admin回复了你', '2018-11-07 16:17:35', 0, '1_2'),
(24, 4, 12, '用户USER9关注了你,http://127.0.0.1:8080/user/10', '2018-11-29 17:07:27', 0, '4_12'),
(25, 1, 12, '用户fan赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-12-02 17:12:44', 0, '1_12'),
(26, 1, 12, '用户fan赞了你的评论,http://127.0.0.1:8080/center_my_index.html', '2018-12-02 17:12:46', 0, '1_12'),
(27, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:33:56', 0, '1_8'),
(28, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:00', 1, '1_8'),
(29, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:06', 1, '1_8'),
(30, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:16', 1, '1_8'),
(31, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:17', 1, '1_8'),
(32, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:18', 1, '1_8'),
(33, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:19', 1, '1_8'),
(34, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 19:34:19', 1, '1_8'),
(35, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 19:34:30', 1, '1_9'),
(36, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 19:36:15', 1, '1_9'),
(37, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 19:36:36', 1, '1_9'),
(38, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:30:07', 1, '1_9'),
(39, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:30:18', 0, '1_9'),
(40, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:30:23', 0, '1_9'),
(41, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:30:34', 0, '1_9'),
(42, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:30:34', 0, '1_9'),
(43, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:31:38', 0, '1_9'),
(44, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:31:39', 0, '1_9'),
(45, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:34:56', 0, '1_9'),
(46, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:34:58', 0, '1_9'),
(47, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:34:59', 0, '1_9'),
(48, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:34:59', 0, '1_9'),
(49, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:54:52', 0, '1_9'),
(50, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:54:59', 0, '1_9'),
(51, 1, 9, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 20:54:59', 0, '1_9'),
(52, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:02:50', 1, '1_8'),
(53, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:07:18', 1, '1_8'),
(54, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:07:23', 1, '1_8'),
(55, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:01', 1, '1_8'),
(56, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:03', 1, '1_8'),
(57, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:04', 1, '1_8'),
(58, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:05', 1, '1_8'),
(59, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:06', 1, '1_8'),
(60, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:09:21', 1, '1_8'),
(61, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:10:35', 1, '1_8'),
(62, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:11:03', 1, '1_8'),
(63, 1, 8, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-02 21:14:19', 1, '1_8'),
(65, 1, 8, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 21:55:49', 1, '1_8'),
(66, 1, 6, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 21:56:41', 0, '1_6'),
(67, 1, 10, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-02 21:58:03', 0, '1_10'),
(69, 1, 8, '用户li关注了你,http://127.0.0.1:8080/user/9', '2018-12-03 13:45:47', 0, '1_8'),
(70, 1, 9, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-03 13:46:04', 0, '1_9'),
(71, 1, 7, '用户fan关注了你,http://127.0.0.1:8080/user/8', '2018-12-03 14:33:01', 0, '1_7');

-- --------------------------------------------------------

--
-- 表的结构 `no1_car_place`
--

CREATE TABLE IF NOT EXISTS `no1_car_place` (
`id` int(20) NOT NULL,
  `cp_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `status` int(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=14 ;

--
-- 转存表中的数据 `no1_car_place`
--

INSERT INTO `no1_car_place` (`id`, `cp_num`, `status`) VALUES
(1, '101', 1),
(2, '102', 1),
(3, '201', 1),
(4, '202', 1),
(5, '301', 1),
(6, '302', 0),
(7, '401', 0),
(8, '402', 0),
(9, '501', 1),
(10, '502', 1),
(11, '601', 1),
(12, '602', 1),
(13, '603', 1);

-- --------------------------------------------------------

--
-- 表的结构 `no1_floor_1`
--

CREATE TABLE IF NOT EXISTS `no1_floor_1` (
`id` int(10) NOT NULL,
  `high_floor_cp_num` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `no1_floor_1`
--

INSERT INTO `no1_floor_1` (`id`, `high_floor_cp_num`) VALUES
(1, '201');

-- --------------------------------------------------------

--
-- 表的结构 `order_history`
--

CREATE TABLE IF NOT EXISTS `order_history` (
`id` int(20) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `car_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `garage_num` int(10) NOT NULL,
  `cp_num` int(10) NOT NULL,
  `start_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `leave_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `money` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `order_info`
--

CREATE TABLE IF NOT EXISTS `order_info` (
`order_id` int(10) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `car_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `garage_num` int(11) NOT NULL,
  `action_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `start_time` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=229 ;

--
-- 转存表中的数据 `order_info`
--

INSERT INTO `order_info` (`order_id`, `username`, `car_num`, `garage_num`, `action_time`, `start_time`) VALUES
(224, '18795968928', '粤CKC236', 1, '2018-06-08 18:50', '2018-06-28 18:07'),
(227, '18959204245', '赣F16712', 3, '2018-06-07 09:15', '2018-06-07 09:15'),
(228, '18959204245', '赣F16712', 2, '2018-06-07 10:24', '2018-06-07 10:26');

-- --------------------------------------------------------

--
-- 表的结构 `parking_info`
--

CREATE TABLE IF NOT EXISTS `parking_info` (
`parking_id` int(20) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `car_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `garage_num` int(20) NOT NULL,
  `cp_num` varchar(20) COLLATE utf8_bin NOT NULL,
  `finish_parking` int(10) NOT NULL,
  `confirm_parking` int(10) NOT NULL,
  `action_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `order_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `start_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `leave_time` varchar(20) COLLATE utf8_bin NOT NULL,
  `money` float NOT NULL,
  `pay_status` int(20) NOT NULL,
  `confirm_out` int(10) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=363 ;

--
-- 转存表中的数据 `parking_info`
--

INSERT INTO `parking_info` (`parking_id`, `username`, `car_num`, `garage_num`, `cp_num`, `finish_parking`, `confirm_parking`, `action_time`, `order_time`, `start_time`, `leave_time`, `money`, `pay_status`, `confirm_out`) VALUES
(362, '18959204245', '赣F16712', 2, '201', 1, 0, '2018-05-11 09:57', '2018-05-30 10:56', '2018-04-28 18:07', '2018-06-07 10:25', 0.01, 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `problem`
--

CREATE TABLE IF NOT EXISTS `problem` (
`id` int(11) NOT NULL,
  `garage_id` int(10) NOT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `created_date` datetime NOT NULL,
  `status` varchar(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=29 ;

--
-- 转存表中的数据 `problem`
--

INSERT INTO `problem` (`id`, `garage_id`, `title`, `content`, `username`, `created_date`, `status`) VALUES
(26, 3, '发生大幅度、、', '地方', 'lifan', '2018-09-20 13:00:07', '0'),
(27, 1, '3', '第三方', 'admin', '2018-09-27 13:50:30', '已受理'),
(28, 1, 'www', '第三方第三方', 'admin', '2018-09-27 20:00:14', '已受理');

-- --------------------------------------------------------

--
-- 表的结构 `question`
--

CREATE TABLE IF NOT EXISTS `question` (
`id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin,
  `user_id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `created_date` datetime NOT NULL,
  `comment_count` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`id`, `title`, `content`, `user_id`, `username`, `created_date`, `comment_count`) VALUES
(1, 'TITLE{0}', 'Balaababalalalal Content 0', 1, 'admin', '2018-07-29 22:30:37', 0),
(2, 'TITLE{1}', 'Balaababalalalal Content 1', 2, 'lifan', '2018-07-30 03:30:37', 1),
(3, 'TITLE{2}', 'Balaababalalalal Content 2', 3, 'zhangsan', '2018-07-30 08:30:37', 2),
(4, 'TITLE{3}', 'Balaababalalalal Content 3', 4, 'lisi', '2018-07-30 13:30:38', 3),
(5, 'TITLE{4}', 'Balaababalalalal Content 4', 5, 'wangwu', '2018-07-30 18:30:38', 4),
(6, 'TITLE{5}', 'Balaababalalalal Content 5', 6, 'liu', '2018-07-30 23:30:38', 5),
(7, 'TITLE{6}', 'Balaababalalalal Content 6', 7, 'wan', '2018-07-31 04:30:38', 6),
(8, 'TITLE{7}', 'Balaababalalalal Content 7', 8, 'fan', '2018-07-31 09:30:38', 7),
(9, 'TITLE{8}', 'Balaababalalalal Content 8', 9, 'li', '2018-07-31 14:30:38', 1),
(10, 'TITLE{9}', 'Balaababalalalal Content 9', 10, 'user1', '2018-07-31 19:30:38', 9),
(11, 'TITLE{10}', 'Balaababalalalal Content 10', 10, 'user1', '2018-08-01 00:30:38', 2);

-- --------------------------------------------------------

--
-- 表的结构 `reply`
--

CREATE TABLE IF NOT EXISTS `reply` (
`id` int(11) NOT NULL,
  `manager` varchar(20) NOT NULL,
  `authority` int(10) NOT NULL,
  `to_name` varchar(20) NOT NULL,
  `question_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `created_date` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=29 ;

--
-- 转存表中的数据 `reply`
--

INSERT INTO `reply` (`id`, `manager`, `authority`, `to_name`, `question_id`, `content`, `created_date`, `status`) VALUES
(19, 'admin', 0, 'lifan', 26, '今天是你的生日', '2018-09-26 15:55:40', 1),
(22, 'lifan', 1, 'admin', 26, '好的谢谢啊', '2018-09-26 16:04:14', 1),
(23, 'admin', 0, 'lifan', 26, '会差行号', '2018-09-26 16:52:20', 1),
(24, 'admin', 0, 'lifan', 26, '都是法师打发第三方', '2018-09-26 16:58:17', 1),
(25, 'admin', 0, 'admin', 29, '非官方大哥法规', '2018-09-27 20:10:16', 1),
(26, 'admin', 0, 'admin', 28, '太热太热问题人体吧', '2018-11-07 15:55:31', 1),
(27, 'admin', 0, 'admin', 28, '燕塘乳业忍野忍', '2018-11-07 16:01:00', 1),
(28, 'admin', 0, 'lifan', 26, '发斯蒂芬', '2018-11-07 16:17:35', 0);

-- --------------------------------------------------------

--
-- 表的结构 `result`
--

CREATE TABLE IF NOT EXISTS `result` (
`id` int(10) NOT NULL,
  `accuracy` float NOT NULL,
  `errorrate` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `running_data`
--

CREATE TABLE IF NOT EXISTS `running_data` (
`id` int(10) NOT NULL,
  `status` int(10) NOT NULL,
  `left_w` float NOT NULL,
  `left_n` float NOT NULL,
  `left_i` float NOT NULL,
  `right_w` float NOT NULL,
  `right_n` float NOT NULL,
  `right_i` int(11) NOT NULL,
  `horizontal_w` float NOT NULL,
  `horizontal_n` float NOT NULL,
  `horizontal_i` float NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `running_data`
--

INSERT INTO `running_data` (`id`, `status`, `left_w`, `left_n`, `left_i`, `right_w`, `right_n`, `right_i`, `horizontal_w`, `horizontal_n`, `horizontal_i`) VALUES
(1, 1, 2200, 1420, 5.6, 3700, 1420, 8, 200, 1400, 0.75),
(2, 0, 2112, 1397, 5.72, 3771, 1389, 8, 208, 1376, 0.71),
(3, -1, 2166, 1286, 5.56, 3624, 1432, 8, 204, 1414, 0.72),
(4, 0, 2182, 1452, 5.47, 3714, 1375, 8, 213, 1446, 0.79),
(5, 0, 2182, 1452, 5.47, 3714, 1375, 8, 213, 1446, 0.79);

-- --------------------------------------------------------

--
-- 表的结构 `screen_status`
--

CREATE TABLE IF NOT EXISTS `screen_status` (
`id` int(10) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `screen_status`
--

INSERT INTO `screen_status` (`id`, `status`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) unsigned NOT NULL,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  `head_url` varchar(256) NOT NULL DEFAULT ''
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `salt`, `head_url`) VALUES
(2, 'USER1', 'newpassword', '', 'http://images.nowcoder.com/head/147t.png'),
(3, 'USER2', 'newpassword', '', 'http://images.nowcoder.com/head/39t.png'),
(4, 'USER3', 'newpassword', '', 'http://images.nowcoder.com/head/426t.png'),
(5, 'USER4', 'newpassword', '', 'http://images.nowcoder.com/head/416t.png'),
(6, 'USER5', 'newpassword', '', 'http://images.nowcoder.com/head/318t.png'),
(7, 'USER6', 'newpassword', '', 'http://images.nowcoder.com/head/835t.png'),
(8, 'USER7', 'newpassword', '', 'http://images.nowcoder.com/head/666t.png'),
(9, 'USER8', 'newpassword', '', 'http://images.nowcoder.com/head/846t.png'),
(10, 'USER9', 'newpassword', '', 'http://images.nowcoder.com/head/209t.png'),
(11, 'USER10', 'newpassword', '', 'http://images.nowcoder.com/head/422t.png'),
(12, '123', '351058FB6E32289885917513ABB16092', 'bf38a', 'http://images.nowcoder.com/head/901t.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `all_garage_status`
--
ALTER TABLE `all_garage_status`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `entity_index` (`entity_id`,`entity_type`);

--
-- Indexes for table `fault_history`
--
ALTER TABLE `fault_history`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fault_warning`
--
ALTER TABLE `fault_warning`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feed`
--
ALTER TABLE `feed`
 ADD PRIMARY KEY (`id`), ADD KEY `user_index` (`user_id`);

--
-- Indexes for table `garage_info`
--
ALTER TABLE `garage_info`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `garage_manager`
--
ALTER TABLE `garage_manager`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `history_solution`
--
ALTER TABLE `history_solution`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `late_order`
--
ALTER TABLE `late_order`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `life_manage`
--
ALTER TABLE `life_manage`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login_ticket`
--
ALTER TABLE `login_ticket`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `ticket_UNIQUE` (`ticket`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`id`), ADD KEY `conversation_index` (`conversation_id`), ADD KEY `created_date` (`created_date`);

--
-- Indexes for table `no1_car_place`
--
ALTER TABLE `no1_car_place`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `no1_floor_1`
--
ALTER TABLE `no1_floor_1`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_history`
--
ALTER TABLE `order_history`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_info`
--
ALTER TABLE `order_info`
 ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `parking_info`
--
ALTER TABLE `parking_info`
 ADD PRIMARY KEY (`parking_id`);

--
-- Indexes for table `problem`
--
ALTER TABLE `problem`
 ADD PRIMARY KEY (`id`), ADD KEY `date_index` (`created_date`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`id`), ADD KEY `date_index` (`created_date`);

--
-- Indexes for table `reply`
--
ALTER TABLE `reply`
 ADD PRIMARY KEY (`id`), ADD KEY `entity_index` (`question_id`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `running_data`
--
ALTER TABLE `running_data`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `screen_status`
--
ALTER TABLE `screen_status`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `all_garage_status`
--
ALTER TABLE `all_garage_status`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `fault_history`
--
ALTER TABLE `fault_history`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `fault_warning`
--
ALTER TABLE `fault_warning`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `feed`
--
ALTER TABLE `feed`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `garage_info`
--
ALTER TABLE `garage_info`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `garage_manager`
--
ALTER TABLE `garage_manager`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `history_solution`
--
ALTER TABLE `history_solution`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `late_order`
--
ALTER TABLE `late_order`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=130;
--
-- AUTO_INCREMENT for table `life_manage`
--
ALTER TABLE `life_manage`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `login_ticket`
--
ALTER TABLE `login_ticket`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=232;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
MODIFY `id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `no1_car_place`
--
ALTER TABLE `no1_car_place`
MODIFY `id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `no1_floor_1`
--
ALTER TABLE `no1_floor_1`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `order_history`
--
ALTER TABLE `order_history`
MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `order_info`
--
ALTER TABLE `order_info`
MODIFY `order_id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=229;
--
-- AUTO_INCREMENT for table `parking_info`
--
ALTER TABLE `parking_info`
MODIFY `parking_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=363;
--
-- AUTO_INCREMENT for table `problem`
--
ALTER TABLE `problem`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `reply`
--
ALTER TABLE `reply`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `running_data`
--
ALTER TABLE `running_data`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `screen_status`
--
ALTER TABLE `screen_status`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
