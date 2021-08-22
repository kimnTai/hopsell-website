package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/book")
public class BookController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    BookMapper bookMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody Book Book) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型

        bookMapper.insert(Book);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Book Book) {
        bookMapper.updateById(Book);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        bookMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(bookMapper.selectById(id));
    }

    /**
     * 注意：这个方法使用的是Mybatis sql的方式做的多表联合查询，大家可以点开，参考下怎么写多表查询
     *
     * @param userId
     * @return
     */
//    @GetMapping("/{userId}")
//    public Result<?> getByUserId(@PathVariable Integer userId) {
//        return Result.success(bookMapper.findByUserId(userId));
//    }

    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            // 判斷 search 不為空
            wrapper.like(Book::getName, search);
            // 避免 search 是 null
        }
        Page<Book> BookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(BookPage);

    }

}
