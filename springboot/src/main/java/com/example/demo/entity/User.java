package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data               // 通過註解，幫你生成 get set
public class User {

    @TableId(type = IdType.AUTO)    // 資料庫 table 名稱不同的話 要補 value
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
    private Integer role;
}
