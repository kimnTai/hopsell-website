package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/news")
public class NewsController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    NewsMapper newsMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody News news) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        newsMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(newsMapper.selectById(id));
    }

    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            // 判斷 search 不為空
            wrapper.like(News::getTitle, search);
            // 避免 search 是 null
        }
        Page<News> NewsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(NewsPage);

    }

}
