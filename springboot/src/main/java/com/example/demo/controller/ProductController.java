package com.example.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.ProductMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        // 如果商品狀態不正常 => 不能買
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

    /**
     * Excel導出
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Product> all = productMapper.selectList(null);
        for (Product product : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("商品ID", product.getProductId());
            row1.put("商品名稱", product.getProductName());
            row1.put("分類", product.getCategoryId());
            row1.put("價格", product.getProductPrice());
            row1.put("新舊程度", product.getProductCondition());
            row1.put("交易方式", product.getProductTrade());
            row1.put("上架時間", product.getCreateTime());
            list.add(row1);
        }

        // 2. 寫 excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("商品資料", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}
