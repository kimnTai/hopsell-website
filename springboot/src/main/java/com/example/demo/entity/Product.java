package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("product")
@Data               // 通過註解，幫你生成 get set
public class Product {

    @TableId(type = IdType.AUTO)        // 資料庫 table 名稱不同的話 要補 value
    private Integer productId;          // 商品_主鍵
    private String productName;         // 商品_名稱
    private Integer sellerId;           // 賣家 id
    private String sellerName;           // 賣家 名稱
    private String sellerImg;           // 賣家 頭像
    private String categoryId;         // 分類 id
    private String productContent;      // 商品_內容
    private String productTrade;       // 購買方式: 711、郵寄、宅配
    private String productCondition;   // 新舊程度: 全新、幾乎全新、狀況良好、狀況尚可
    private Integer productPrice;       // 商品價格
    private String productImgA;         // 商品圖片A
    private String productImgB;
    private String productImgC;
    private Integer productStatus;         // 商品狀態:正常 1、刪除 -1、下架 0
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date updateTime;

}
