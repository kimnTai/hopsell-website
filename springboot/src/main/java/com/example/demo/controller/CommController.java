package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Comm;
import com.example.demo.mapper.CommMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/comm")
public class CommController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    CommMapper commMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody Comm comm) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        comm.setCommTime(new Date());
        commMapper.insert(comm);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Comm comm) {
        commMapper.updateById(comm);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        commMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(commMapper.selectById(id));
    }

    // 查詢全部
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(commMapper.selectList(null));
    }


    /**
     * 注意：這個方法使用的是Mybatis sql的方式做的多多表聯合查詢，大家可以點開，參考下怎麼寫多表查詢
     * <p>
     * //     * @param sellerId
     * //     * @return
     */
//    @GetMapping("/seller/{sellerId}")
//    public Result<?> getBysellerId(@PathVariable Integer sellerId) {
//        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("sellerId", sellerId);
//
//        return Result.success(queryWrapper);
//    }

    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String sellerId,
                              @RequestParam(defaultValue = "") String buyerName) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<Comm> wrapper = Wrappers.<Comm>lambdaQuery();
        if (StrUtil.isNotBlank(buyerName)) {
            // 判斷 search 不為空
            wrapper.like(Comm::getBuyerName, buyerName);
            // 避免 search 是 null
        }
        if (StrUtil.isNotBlank(sellerId)) {
            // 判斷 search 不為空
            wrapper.like(Comm::getSellerId, sellerId);
            // 避免 search 是 null
        }
        Page<Comm> CommPage = commMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(CommPage);

    }

}
