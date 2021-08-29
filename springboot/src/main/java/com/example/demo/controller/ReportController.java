package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Report;
import com.example.demo.mapper.ReportMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/report")
public class ReportController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    ReportMapper reportMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody Report report) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        report.setReportTime(new Date());
        reportMapper.insert(report);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Report report) {
        reportMapper.updateById(report);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        reportMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(reportMapper.selectById(id));
    }

    // 查詢全部
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(reportMapper.selectList(null));
    }


    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String productId,
                              @RequestParam(defaultValue = "") String productName) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<Report> wrapper = Wrappers.<Report>lambdaQuery();
        if (StrUtil.isNotBlank(productName)) {
            // 判斷 search 不為空
            wrapper.like(Report::getProductName, productName);
            // 避免 search 是 null
        }
        if (StrUtil.isNotBlank(productId)) {
            // 判斷 search 不為空
            wrapper.eq(Report::getProductId, productId);
            // 避免 search 是 null
        }
        Page<Report> ReportPage = reportMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(ReportPage);

    }

}
