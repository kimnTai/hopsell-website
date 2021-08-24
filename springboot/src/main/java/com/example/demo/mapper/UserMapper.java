package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;

public interface UserMapper extends BaseMapper<User> {
    // 繼承 BaseMapper 傳入泛行就是 User
}
