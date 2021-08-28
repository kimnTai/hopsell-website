-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2021-08-28 15:52:41
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
-- 資料表結構 `comm`
--

CREATE TABLE `comm` (
  `comm_id` int(11) NOT NULL COMMENT '評價_主鍵',
  `product_id` int(11) DEFAULT NULL COMMENT '商品_主鍵',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品_名稱',
  `seller_id` int(11) DEFAULT NULL COMMENT '賣家 id',
  `buyer_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '買家 id',
  `buyer_img` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comm_level` int(11) DEFAULT NULL COMMENT '評價等級',
  `comm_content` text COLLATE utf8mb4_unicode_ci COMMENT '評價內容',
  `comm_img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '評價_附圖',
  `comm_time` datetime DEFAULT NULL COMMENT '評價時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `comm`
--

INSERT INTO `comm` (`comm_id`, `product_id`, `product_name`, `seller_id`, `buyer_name`, `buyer_img`, `comm_level`, `comm_content`, `comm_img`, `comm_time`) VALUES
(1, 1, 'Nautica 長谷川昭雄 刺繡 黑色 燈芯絨 老帽 NT$800', 1, '@nxuan07', 'https://media.karousell.com/media/photos/profiles/default.png', 5, '出貨超快 5星好賣家🤩', NULL, '2021-08-23 16:04:01'),
(2, 2, '🎌JP 日本 Carnival Joy 日系 古著 淺灰x黑色 魔鬼粘 山脈 運動涼鞋 休閒涼鞋 LL', 1, '@mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', 5, '買家超讚東西超讚出貨超快💕', NULL, '2021-08-24 14:12:48'),
(3, 3, 'U型枕（U型按摩枕）NT$300', 1, ' @twitter', 'https://media.karousell.com/media/photos/profiles/2015/12/12/jayhaa_1449886293.jpg', 5, '值得推薦的好賣家！', NULL, '2021-08-25 22:33:39');

-- --------------------------------------------------------

--
-- 資料表結構 `ordertable`
--

CREATE TABLE `ordertable` (
  `order_id` int(11) NOT NULL COMMENT '訂單_id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品 id',
  `buyer_id` int(11) DEFAULT NULL COMMENT '買家 id',
  `comm_id` int(11) DEFAULT NULL COMMENT '評價 id',
  `order_time` datetime DEFAULT NULL COMMENT '購買時間',
  `complete_time` datetime DEFAULT NULL COMMENT '完成時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL COMMENT '商品_主鍵',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品_名稱',
  `seller_id` int(11) DEFAULT NULL COMMENT '賣家 id',
  `seller_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `seller_img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分類',
  `product_content` text COLLATE utf8mb4_unicode_ci COMMENT '商品_內容',
  `product_trade` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '購買方式: 711、 郵寄、宅配',
  `product_condition` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '新舊程度',
  `product_price` int(11) DEFAULT NULL COMMENT '商品價格',
  `product_img_a` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片A',
  `product_img_b` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片B',
  `product_img_c` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品圖片C',
  `product_status` int(11) DEFAULT '1' COMMENT '商品狀態:正常 1 ，刪除 -1，0 下架',
  `create_time` datetime DEFAULT NULL COMMENT '創建時間',
  `update_time` datetime DEFAULT NULL COMMENT '更新時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 傾印資料表的資料 `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `seller_id`, `seller_name`, `seller_img`, `category_id`, `product_content`, `product_trade`, `product_condition`, `product_price`, `product_img_a`, `product_img_b`, `product_img_c`, `product_status`, `create_time`, `update_time`) VALUES
(5, '更新皮夾', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '時尚衣裝', '我的皮夾7-11付款、它的時尚、全新、500元', '郵寄及宅配', '幾乎全新', 500, 'https://media.karousell.com/media/photos/products/2021/8/26/rodelo_1629975865_7fd484ba_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/26/rodelo_1629975864_31bd2049_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/26/rodelo_1629975864_75ab657b_progressive.jpg', -1, '2021-08-22 00:00:00', '2021-08-28 15:37:50'),
(6, '刪除很多G2000 襯衫 長袖 男生 衣服', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '娛樂休閒', 'G2000 襯衫 長袖 男生 衣服', '面交', '狀況良好', 500, 'http://localhost:9090/files/3ca5ba87f01944d6865ddeaff2c29464', 'https://media.karousell.com/media/photos/products/2021/8/23/g2000_____1629719161_44f7f1f6_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/23/g2000_____1629719161_70d5fffc_progressive.jpg', -1, '2021-08-23 00:00:00', '2021-08-28 01:06:34'),
(7, 'iPhone X 64G 銀 🔋100% 9成新', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '3C家電', '📱宇哥嚴選中古機📱\r\n\r\n📣歡迎貼換，舊機換新機\r\n📣下單前請先詢問有無現貨\r\n\r\niPhone X 64G 銀 $7500\r\n\r\n《外觀描述》\r\n✅外觀9成新\r\n✅全機功能正常\r\n✅健康度100%\r\n\r\n《配件》序號一致原盒\r\n🔺全新充電頭\r\n🔺全新充電線\r\n🔺全新9H鋼化玻璃貼\r\n🔺全新防摔空壓殼\r\n另有很多週邊配件可用優惠價格加購，歡迎詢問😉\r\n\r\n交易方式：\r\n✅雙北都可以預約面交\r\n✅可貨到付款(4%手續費自付)\r\n\r\n#iphone #iPhonex #ix #64g', '面交', '幾乎全新', 7500, 'https://media.karousell.com/media/photos/products/2021/8/24/iphone_x_64g__100_9_1629824664_23d7c83f_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/24/iphone_x_64g__100_9_1629824664_93a25ff4_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/24/iphone_x_64g__100_9_1629824664_830ece1e_progressive.jpg', 1, '2021-08-23 17:14:58', '2021-08-28 16:49:07'),
(8, '我的包包', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '她的時尚', '我的包包800元', '7-Eleven 取貨付款', '狀況尚可', 800, 'http://localhost:9090/files/f01b4a3173e043ec9693ce6d55836496', NULL, NULL, 1, '2021-08-23 17:48:59', NULL),
(9, 'Nike+ Jordan sticker together', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '名牌精品', '西洋棋一組\n塑膠製品\n附上說明書\n無原本的外包裝盒，用其他盒子代替', '7-Eleven 取貨付款', '幾乎全新', 210, 'https://media.karousell.com/media/photos/products/2021/8/22/9polo_ralph_lauren44t_1629661645_79bc84ad_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/24/nike_jordan_sticker_together_1629823928_cec7b3ae_progressive.jpg', 'https://media.karousell.com/media/photos/products/2021/8/24/nike_jordan_sticker_together_1629823928_daecfcd3_progressive.jpg', 1, '2021-08-25 00:55:41', NULL),
(10, '實拍現貨~高貴華爾緞短款修身顯瘦襯衫女氣質套裝裙高級感兩件套', 1, 'mianhuuu', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '時尚衣裝', '商品定價：代購約2500-3000\r\n                                    商品尺寸：S號 版型偏大\r\n                                    肩寬48胸寬56衣長72cm\r\n                                    商品狀況：95%\r\n\r\n                                    台灣沒有發售這個配色\r\n\r\n                                    商品照片皆為本人實體拍攝\r\n                                    提供商品實體測量數據參考\r\n                                    購買前可先詢問 或 參考賣場簡介✔️✔️✔️\r\n\r\n                                    喜歡的各位可點選本賣場首頁《追蹤》\r\n                                    才不會遺漏最新上架的商品', '郵寄及宅配', '全新', 860, 'https://upload.cc/i1/2021/08/13/IlYc4K.jpg', 'https://upload.cc/i1/2021/08/13/V7zYiM.jpg', 'https://upload.cc/i1/2021/08/13/gWvoPQ.jpg', 1, '2021-08-25 20:17:25', NULL),
(12, '實拍現貨~高貴華爾緞短款修身顯瘦襯衫女氣質套裝裙高級感兩件套', 20, 'RobertaColeman', 'https://randomuser.me/api/portraits/women/31.jpg', '時尚衣裝', '商品定價：代購約2500-3000\n                                    商品尺寸：S號 版型偏大\n                                    肩寬48胸寬56衣長72cm\n                                    商品狀況：95%\n                                    台灣沒有發售這個配色\n                                    商品照片皆為本人實體拍攝\n                                    提供商品實體測量數據參考\n                                    購買前可先詢問 或 參考賣場簡介✔️✔️✔️\n                                    喜歡的各位可點選本賣場首頁《追蹤》\n                                    才不會遺漏最新上架的商品', '郵寄及宅配', '全新', 860, 'https://media.karousell.com/media/photos/products/2021/8/26/porter_1629975912_42f075eb_progressive.jpg', 'https://upload.cc/i1/2021/08/13/V7zYiM.jpg', 'https://upload.cc/i1/2021/08/13/gWvoPQ.jpg', 1, '2021-08-26 10:50:21', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `report`
--

CREATE TABLE `report` (
  `report_id` int(11) NOT NULL COMMENT '訂單_id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品_id',
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_img_a` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `report_reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '檢舉原因',
  `report_time` datetime DEFAULT NULL COMMENT '檢舉時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL COMMENT 'ID',
  `user_account` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `user_img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '大頭貼',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `user_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Email',
  `user_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `user_introduce` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '個人簡介',
  `register_time` datetime DEFAULT NULL COMMENT '註冊時間',
  `login_time` datetime DEFAULT NULL,
  `role` int(11) DEFAULT NULL COMMENT '角色，1：管理员，2：普通用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表' ROW_FORMAT=DYNAMIC;

--
-- 傾印資料表的資料 `user`
--

INSERT INTO `user` (`user_id`, `user_account`, `password`, `user_img`, `user_name`, `user_email`, `user_address`, `user_introduce`, `register_time`, `login_time`, `role`) VALUES
(1, 'admin', 'admin', 'https://media.karousell.com/media/photos/profiles/2020/04/04/mianhuuu_1585929644.jpg', '管理员', 'kim.pierce@example.com', '中彰投', '', '2021-08-01 14:41:58', '2021-08-28 15:17:15', 1),
(13, 'zhang', '123', 'https://randomuser.me/api/portraits/women/47.jpg', 'Neil Perry', 'vera.holmes@example.com', '中彰投', '', '2021-08-03 14:42:07', NULL, 2),
(17, '頁碼', '123456', 'https://randomuser.me/api/portraits/men/28.jpg', 'Ann Medina', 'alexa.schmidt@example.com', '中彰投', '', '2021-08-09 14:42:10', NULL, 2),
(20, 'Roberta Coleman', '123', 'https://randomuser.me/api/portraits/women/31.jpg', 'Roberta Coleman', 'neil.perry@example.com', '中彰投', '', '2021-08-15 14:42:12', NULL, 2);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `comm`
--
ALTER TABLE `comm`
  ADD PRIMARY KEY (`comm_id`);

--
-- 資料表索引 `ordertable`
--
ALTER TABLE `ordertable`
  ADD PRIMARY KEY (`order_id`);

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- 資料表索引 `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`report_id`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`) USING BTREE;

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `comm`
--
ALTER TABLE `comm`
  MODIFY `comm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '評價_主鍵', AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `ordertable`
--
ALTER TABLE `ordertable`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '訂單_id';

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品_主鍵', AUTO_INCREMENT=15;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `report`
--
ALTER TABLE `report`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '訂單_id';

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID', AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
