package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data               // 通過註解，幫你生成 get set
public class User {

    @TableId(type = IdType.AUTO)    // 資料庫 table 名稱不同的話 要補 value
    private Integer userId;
    private String userAccount;
    private String password;
    private String userImg;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userIntroduce;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date registerTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      // 時間
    private Date loginTime;
    private Integer role;
}
