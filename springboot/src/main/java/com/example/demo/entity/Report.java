package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("report")
@Data               // 通過註解，幫你生成 get set
public class Report {

    @TableId(type = IdType.AUTO)       // 資料庫 table 名稱不同的話 要補 value
    private Integer reportId;
    private Integer productId;
    private String productName;
    private String productImgA;
    private String reportReason;
    private String reportStatus;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date reportTime;


}
