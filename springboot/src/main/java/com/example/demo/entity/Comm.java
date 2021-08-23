package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("comm")
@Data               // 通過註解，幫你生成 get set
public class Comm {

    @TableId(type = IdType.AUTO)       // 資料庫 table 名稱不同的話 要補 value
    private Integer commId;            // 評價_主鍵
    private Integer productId;          // 商品_主鍵
    private String productName;       // 商品_名稱
    private Integer sellerId;          // 賣家 id
    private Integer buyerId;            // 買家 id
    private Integer commLevel;         // 評價等級
    private String commContent;       // 評價內容
    private String commImgs;          // 評價_附圖
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date commTime;             // 評價時間


}
