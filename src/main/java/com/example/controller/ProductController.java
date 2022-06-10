package com.example.controller;

import com.example.entity.vo.BaseProductMultiIdVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductOneIdVo;
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
    public BaseProductResp getProductIdList(@RequestBody BaseProductMultiIdVo baseProductMultiIdVo) {
        log.info("getting product id list");
        return productBaseService.getIdList(baseProductMultiIdVo);
    }

    @PostMapping("get_info")
    public BaseProductResp getProductInfoById(@RequestBody BaseProductMultiIdVo baseProductMultiIdVo) {
        log.info("getting product info list by id");
        return productBaseService.getProductInfoById(baseProductMultiIdVo);
    }

    @PostMapping("create")
    public BaseProductResp createProduct(@RequestBody BaseProductOneIdVo baseProductVo) {
        log.info("start create product");
        return productBaseService.createProduct(baseProductVo);
    }

    @PostMapping("update")
    public BaseProductResp updateProduct(@RequestBody BaseProductOneIdVo baseProductVo) {
        log.info("start update product");
        return productBaseService.updateProduct(baseProductVo);
    }

    @PostMapping("offline")
    public BaseProductResp offlineProduct(@RequestBody BaseProductOneIdVo baseProductOneIdVo) {
        log.info("start offline product");
        return productBaseService.offlineProduct(baseProductOneIdVo);
    }

    @PostMapping("delete")
    public BaseProductResp deleteProduct(@RequestBody BaseProductOneIdVo baseProductOneIdVo) {
        log.info("start delete product");
        return productBaseService.deleteProduct(baseProductOneIdVo);

    }

    @PostMapping("connect_test")
    public BaseProductResp Test(@RequestBody BaseProductOneIdVo baseProductVo) {
        log.info("connection OK");
        return new BaseProductResp().success("data");
    }

}
