package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.ProductMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

// 定義返回 JSON Controller
@RestController
// 統一的路由
@RequestMapping("/product")
public class ProductController {

    /*
     *   通過這個註解 可以把 Mapper 引入到 Controller
     *   正常要寫一個 service，Controller 要引入 service，service引入Mapper
     */
    @Resource
    ProductMapper productMapper;

    // 新增
    @PostMapping
    public Result<?> save(@RequestBody Product product) {
        // 需要定義 前端回傳的 JSON - > entity
        // @RequestBody 把傳過來的 JSON 轉成JAVA對象
        // 需要連接資料庫 -> mapper
        // 問號表示任何一種泛型
        product.setCreateTime(new Date());
        product.setProductStatus(1);
        productMapper.insert(product);
        return Result.success();

    }

    // 修改
    @PutMapping
    public Result<?> update(@RequestBody Product product) {
        product.setUpdateTime(new Date());
        productMapper.updateById(product);
        return Result.success();

    }

    // 購買
    @PutMapping("/tobuy")
    public Result<?> toBuy(@RequestBody Product product) {
        Product resStatus = productMapper.selectOne(
                Wrappers.<Product>lambdaQuery()
                        .eq(Product::getProductId, product.getProductId())
                        .ne(Product::getProductStatus, 1));
        if (resStatus != null) {
            return Result.error("-1", "此商品已下架");
        }
        product.setUpdateTime(new Date());
        productMapper.updateById(product);
        return Result.success();

    }

    // 刪除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        // {} 要用 PathVariable
        productMapper.deleteById(id);
        return Result.success();

    }

    // ID查詢
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(productMapper.selectById(id));
    }

    // 查詢全部
    @GetMapping("/all")
    public Result<?> findAll() {
        return Result.success(productMapper.selectList(null));
    }


    // 查詢上架商品
    @GetMapping("/shelf")
    public Result<?> findTest(@RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Product> wrapper = Wrappers.<Product>lambdaQuery();
        wrapper.eq(Product::getProductStatus, 1);
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Product::getProductName, search)
                    .or().like(Product::getProductContent, search)
                    .or().like(Product::getSellerName, search)
                    .eq(Product::getProductStatus, 1);
        }

        List<Product> res = productMapper.selectList(wrapper);


        return Result.success(res);
    }


    // 分頁查詢
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize,
                              @RequestParam(defaultValue = "") String sellerId,
                              @RequestParam(defaultValue = "") String search) {
        // 查詢 用GET ，分頁查詢
        LambdaQueryWrapper<Product> wrapper = Wrappers.<Product>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            // 判斷 search 不為空
            wrapper.like(Product::getProductName, search);
            // 避免 search 是 null
        }
        if (StrUtil.isNotBlank(sellerId)) {
            // 判斷 sellerId 不為空
            wrapper.eq(Product::getSellerId, sellerId);
            // 避免 sellerId 是 null
        }
        Page<Product> ProductPage = productMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(ProductPage);

    }

}
