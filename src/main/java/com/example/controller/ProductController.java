package com.example.controller;

import com.example.entity.vo.ProductMultiIdVo;
import com.example.entity.vo.ProductOneIdVo;
import com.example.entity.vo.BaseProductResp;
import com.example.service.ProductBaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Resource
    private ProductBaseService productBaseService;

    @PostMapping("show_list")
    public BaseProductResp listProductIds(@RequestBody ProductMultiIdVo productMultiIdVo) {
        return productBaseService.listIds(productMultiIdVo);
    }

    @PostMapping("get_info")
    public BaseProductResp getProductInfoById(@RequestBody ProductMultiIdVo productMultiIdVo) {
        return productBaseService.getProductInfoById(productMultiIdVo);
    }

    @PostMapping("create")
    public BaseProductResp createProduct(@RequestBody ProductOneIdVo baseProductVo) {
        return productBaseService.createProduct(baseProductVo);
    }

    @PostMapping("update")
    public BaseProductResp updateProduct(@RequestBody ProductOneIdVo baseProductVo) {
        return productBaseService.updateProduct(baseProductVo);
    }

    @PostMapping("offline")
    public BaseProductResp offlineProduct(@RequestBody ProductOneIdVo productOneIdVo) {
        return productBaseService.offlineProduct(productOneIdVo);
    }

    @PostMapping("delete")
    public BaseProductResp deleteProduct(@RequestBody ProductOneIdVo productOneIdVo) {
        return productBaseService.deleteProduct(productOneIdVo);

    }

    @GetMapping("connect_test")
    public BaseProductResp Test() {
        return new BaseProductResp().success("data");
    }

}
