package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductMapper extends BaseMapper<Product> {
    // 根據用戶id查詢圖書信息
    List<Product> findBySellerId(@Param("sellerId") Integer sellerId);
}
