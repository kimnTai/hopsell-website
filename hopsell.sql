-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2021-08-23 09:33:07
-- 伺服器版本： 5.7.24
-- PHP 版本： 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `hopsell`
--

-- --------------------------------------------------------

--
-- 資料表結構 `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '出版日期',
  `cover` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面地址',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- 傾印資料表的資料 `book`
--

INSERT INTO `book` (`id`, `name`, `price`, `author`, `create_time`, `cover`, `user_id`) VALUES
(1, '明朝那些事', '60.00', 'xxx', '2010-07-01 00:00:00', 'http://localhost:9090/files/fbe66a2571d2499886b306801f484f9e', NULL),
(2, '衣服', '70.00', 'xxx', '2021-08-21 00:00:00', 'http://localhost:9090/files/7caab0dabb7c4229a4d84e8055c7600d', NULL),
(3, '庫子', '50.00', '60', '2021-08-11 00:00:00', 'http://localhost:9090/files/04f179db2710406d852557be5add9157', NULL),
(4, '測試', '123.00', '123', '2021-08-01 00:00:00', 'http://localhost:9090/files/9e9745cd22bc49acbe2725c133b330a9', NULL),
(5, 'ddddddd', '90.00', 'dddd', '2021-08-04 00:00:00', 'http://localhost:9090/files/0cf42e5443f44d3cb731b71512ed261a', NULL),
(6, '6666', '6666.00', '55555', '2021-08-04 00:00:00', 'http://localhost:9090/files/35be384bbbc64079b7657705e21f6b44', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `comm`
--

CREATE TABLE `comm` (
  `comm_id` int(11) NOT NULL COMMENT '評價_主鍵',
  `product_id` int(11) DEFAULT NULL COMMENT '商品_主鍵',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品_名稱',
  `seller_id` int(11) DEFAULT NULL COMMENT '賣家 id',
  `buyer_id` int(11) DEFAULT NULL COMMENT '買家 id',
  `comm_level` int(11) DEFAULT NULL COMMENT '評價等級',
  `comm_content` text COLLATE utf8mb4_unicode_ci COMMENT '評價內容',
  `comm_imgs` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '評價_附圖',
  `comm_time` datetime DEFAULT NULL COMMENT '評價時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `comm`
--

INSERT INTO `comm` (`comm_id`, `product_id`, `product_name`, `seller_id`, `buyer_id`, `comm_level`, `comm_content`, `comm_imgs`, `comm_time`) VALUES
(1, 1, '我的鞋子', 1, 2, 5, '非常滿意', NULL, '2021-08-23 16:04:01');

-- --------------------------------------------------------

--
-- 資料表結構 `news`
--

CREATE TABLE `news` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `time` datetime DEFAULT NULL COMMENT '发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- 傾印資料表的資料 `news`
--

INSERT INTO `news` (`id`, `title`, `content`, `author`, `time`) VALUES
(4, '4444', '<p>444444</p><p><br/></p><p>44444</p><p><br/></p><p><br/></p><p>555555<img src=\"https://img1.baidu.com/it/u=4110196045,3829597861&amp;fm=26&amp;fmt=auto&amp;gp=0.jpg\" contenteditable=\"false\" style=\"font-size: 14px; max-width: 100%;\"/></p>', '管理员', '2021-07-17 18:55:27'),
(5, '77777', '<p>7777</p><p><br/></p><p><img src=\"http://localhost:9090/files/c536f6f2f0e94983951240d73d740601\" style=\"max-width:100%;\" contenteditable=\"false\" width=\"388\" height=\"388\"/><br/></p><p>8888</p><p><br/></p><p><img src=\"http://localhost:9090/files/b344314319f047cf9192ce64ca454674\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', '管理员', '2021-07-17 19:14:14'),
(8, '66666', '444444', NULL, NULL),
(9, '編輯測試', '<h1 id=\"hrjvv\"><b style=\"background-color: rgb(70, 172, 200);\"><i>編輯測事</i></b></h1><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>111111</th></tr><tr><td>1111111</td></tr></tbody></table><ul class=\"w-e-todo\"><li><span contenteditable=\"false\"><input type=\"checkbox\" checked=\"true\"/></span>真的很爛</li></ul>', NULL, NULL),
(10, '時間測試', '<p>時間測試啦</p>', '管理员', '2021-08-22 15:08:30'),
(11, '新增測事', '<p>新增<img src=\"http://localhost:9090/files/b0241c2405bd4ef6a2df8fc307f672bb\" contenteditable=\"false\" style=\"color: var(--el-text-color-regular); font-size: var(--el-dialog-content-font-size); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); max-width: 100%;\" width=\"100%\"/></p><p><br/></p><p>111111111111111</p>', '123', '2021-08-22 17:12:04');

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL COMMENT '商品_主鍵',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品_名稱',
  `seller_id` int(11) DEFAULT NULL COMMENT '賣家 id',
  `category_id` int(11) DEFAULT NULL COMMENT '分類 id',
  `product_content` text COLLATE utf8mb4_unicode_ci COMMENT '商品_內容',
  `product_trade` int(11) DEFAULT NULL COMMENT '購買方式:0 - 711，2 - 郵寄，3 - 宅配',
  `product_condition` int(11) DEFAULT NULL COMMENT '新舊程度',
  `product_price` int(11) DEFAULT NULL COMMENT '商品價格',
  `product_img_a` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片A',
  `product_img_b` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片B',
  `product_img_c` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片C',
  `product_status` int(11) DEFAULT NULL COMMENT '商品狀態:正常 1 ，刪除 -1，0 下架',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `update_time` datetime DEFAULT NULL COMMENT '更新時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `seller_id`, `category_id`, `product_content`, `product_trade`, `product_condition`, `product_price`, `product_img_a`, `product_img_b`, `product_img_c`, `product_status`, `create_time`, `update_time`) VALUES
(3, '我的衣服', 1, 4, '我的衣服郵寄，狀況尚可，100元', 2, 3, 100, 'http://localhost:9090/files/13a73a3193a14a4894a416320f6c1a2e', NULL, NULL, NULL, NULL, NULL),
(4, '我的手機', 1, 3, '我的手機500元', 3, 4, 500, 'https://media.karousell.com/media/photos/products/2021/8/23/bear_two__1629710111_15080e6f_progressive_thumbnail.jpg', NULL, NULL, NULL, NULL, NULL),
(5, '我的皮夾', 1, 1, '我的皮夾7-11付款、它的時尚、全新、500元', 1, 1, 500, 'https://media.karousell.com/media/photos/products/2021/8/3/_jp__kraft_______t_t_xl_1627963190_d4de3ab1_progressive_thumbnail.jpg', NULL, NULL, NULL, '2021-08-22 23:09:14', NULL),
(6, '我的大頭貼', 1, 3, '我的手機平板', 3, 4, 500, 'https://media.karousell.com/media/photos/products/2021/8/22/t_1629643463_c3b62581_progressive_thumbnail.jpg', NULL, NULL, NULL, '2021-08-23 11:18:07', NULL),
(7, '測試上傳', 1, 2, '上傳測試', 1, 1, 150, 'http://localhost:9090/files/15a9af2ad7f74e89ba3860fa6b197793', NULL, NULL, NULL, '2021-08-23 17:14:58', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `role` int(11) DEFAULT NULL COMMENT '角色，1：管理员，2：普通用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表' ROW_FORMAT=DYNAMIC;

--
-- 傾印資料表的資料 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nick_name`, `age`, `sex`, `address`, `role`) VALUES
(1, 'admin', 'admin', '管理员', 32, '男', '火星', 1),
(13, 'zhang', '123', '张三', 20, '男', '木星', 2),
(14, 'kimn', NULL, '編輯測試', 11, '男', '會關閉彈窗 刷新', NULL),
(16, 'test', NULL, '2', 1, '未知', '1', NULL),
(17, '頁碼', '123456', '頁碼', 1, '男', '1', NULL),
(20, 'kimn1', '123', '123', 11, '未知', 'person 測試', NULL);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 資料表索引 `comm`
--
ALTER TABLE `comm`
  ADD PRIMARY KEY (`comm_id`);

--
-- 資料表索引 `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `comm`
--
ALTER TABLE `comm`
  MODIFY `comm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '評價_主鍵', AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `news`
--
ALTER TABLE `news`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=12;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品_主鍵', AUTO_INCREMENT=8;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
