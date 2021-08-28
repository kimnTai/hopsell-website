package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/user")
public class UserController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    UserMapper userMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody User user) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();

    }

    // 登入
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getUserAccount, user.getUserAccount())
                        .eq(User::getPassword, user.getPassword()));
        if (res == null) {
            return Result.error("-1", "用戶名或密碼錯誤");
        }
        user.setLoginTime(new Date());
        userMapper.insert(user);
        return Result.success(res);
    }

    // 註冊
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(
                Wrappers.<User>lambdaQuery()
                        .eq(User::getUserAccount, user.getUserAccount()));
        if (res != null) {
            return Result.error("-1", "用戶名重複");
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        user.setRegisterTime(new Date());
        userMapper.insert(user);
        return Result.success();
    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        userMapper.deleteById(id);
        return Result.success();

    }

    // 單筆查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(userMapper.selectById(id));
    }

    // 查詢全部
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(userMapper.selectList(null));
    }


    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            // 判斷 search 不為空
            wrapper.like(User::getUserAccount, search);
            // 避免 search 是 null
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);

    }

}
