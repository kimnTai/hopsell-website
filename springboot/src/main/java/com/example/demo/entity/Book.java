package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("book")
@Data               // 通過註解，幫你生成 get set
public class Book {

    @TableId(type = IdType.AUTO)    // 資料庫 table 名稱不同的話 要補 value
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")      // 時間
    private Date createTime	;
    private String cover;
    private Integer userId;
}
