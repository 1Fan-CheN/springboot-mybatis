package com.example.controller;

import com.example.entity.vo.BaseProductInfoVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductVo;
import com.example.service.ProductBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Resource
    private ProductBaseService productBaseService;

    @PostMapping("show_list")
    public BaseProductResp getProductIdList(@RequestBody BaseProductInfoVo baseProductInfoVo) {
        log.info("getting product id list");
        return productBaseService.getIdList(baseProductInfoVo);
    }

    @PostMapping("get_info")
    public BaseProductResp getProductInfoById(@RequestBody BaseProductInfoVo baseProductInfoVo) {
        log.info("getting product info list by id");
        return productBaseService.getProductInfoById(baseProductInfoVo);
    }

    @PostMapping("create")
    public BaseProductResp createProduct(@RequestBody BaseProductVo baseProductVo) {
        log.info("start create product");
        return productBaseService.createProduct(baseProductVo);
    }

    @PostMapping("update")
    public BaseProductResp updateProduct(@RequestBody BaseProductVo baseProductVo) {
        log.info("start update product");
        return productBaseService.updateProduct(baseProductVo);
    }

    @PostMapping("offline")
    public BaseProductResp offlineProduct(@RequestBody BaseProductVo baseProductVo) {
        log.info("start offline product");
        return productBaseService.updateProduct(baseProductVo);
    }

    @PostMapping("delete")
    public BaseProductResp deleteProduct(@RequestBody BaseProductVo baseProductVo) {
        log.info("start delete product");
        return new BaseProductResp().success(null);

    }

    @PostMapping("connect_test")
    public BaseProductResp Test(@RequestBody BaseProductVo baseProductVo) {
        log.info("connection OK");
        return new BaseProductResp().success("data");
    }

}
