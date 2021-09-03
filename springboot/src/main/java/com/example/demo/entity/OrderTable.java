package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("ordertable")
@Data               // 通過註解，幫你生成 get set
public class OrderTable {

    @TableId(type = IdType.AUTO)       // 資料庫 table 名稱不同的話 要補 value
    private Integer orderId;
    private Integer productId;
    private String productName;
    private String productImgA;
    private String categoryId;
    private Integer productPrice;
    private Integer sellerId;
    private String sellerName;
    private Integer buyerId;
    private String orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date completeTime;

}
