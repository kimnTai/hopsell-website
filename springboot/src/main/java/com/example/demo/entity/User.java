package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data               // 通過註解，幫你生成 get set
public class User {

    @TableId(type = IdType.AUTO)    // 資料庫 table 名稱不同的話 要補 value
    private Integer userId;
    private String userAccount;
    private String password;
    private String userName;
    private Integer userEmail;
    private String userAddress;
    private String userIntroduce;
    private Integer role;
}
