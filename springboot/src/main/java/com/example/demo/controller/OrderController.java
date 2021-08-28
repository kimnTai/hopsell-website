package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.OrderTable;
import com.example.demo.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/order")
public class OrderController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    OrderMapper orderMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody OrderTable orderTable) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        orderTable.setCompleteTime(new Date());
        orderMapper.insert(orderTable);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody OrderTable orderTable) {
        orderMapper.updateById(orderTable);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        orderMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(orderMapper.selectById(id));
    }

    // 查詢全部
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(orderMapper.selectList(null));
    }


    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String productId,
                              @RequestParam(defaultValue = "") String buyerId) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<OrderTable> wrapper = Wrappers.<OrderTable>lambdaQuery();
        if (StrUtil.isNotBlank(productId)) {
            // 判斷 search 不為空
            wrapper.eq(OrderTable::getProductId, productId);
            // 避免 search 是 null
        }
        if (StrUtil.isNotBlank(buyerId)) {
            // 判斷 search 不為空
            wrapper.eq(OrderTable::getBuyerId, buyerId);
            // 避免 search 是 null
        }
        Page<OrderTable> OrderPage = orderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(OrderPage);

    }

}
