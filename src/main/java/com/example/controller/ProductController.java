package com.example.controller;

import com.example.entity.vo.BaseProductMultiIdVo;
import com.example.entity.vo.BaseProductOneIdVo;
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
    public BaseProductResp getProductIdList(@RequestBody BaseProductMultiIdVo baseProductMultiIdVo) {
        return productBaseService.listIds(baseProductMultiIdVo);
    }

    @PostMapping("get_info")
    public BaseProductResp getProductInfoById(@RequestBody BaseProductMultiIdVo baseProductMultiIdVo) {
        return productBaseService.getProductInfoById(baseProductMultiIdVo);
    }

    @PostMapping("create")
    public BaseProductResp createProduct(@RequestBody BaseProductOneIdVo baseProductVo) {
        return productBaseService.createProduct(baseProductVo);
    }

    @PostMapping("update")
    public BaseProductResp updateProduct(@RequestBody BaseProductOneIdVo baseProductVo) {
        return productBaseService.updateProduct(baseProductVo);
    }

    @PostMapping("offline")
    public BaseProductResp offlineProduct(@RequestBody BaseProductOneIdVo baseProductOneIdVo) {
        return productBaseService.offlineProduct(baseProductOneIdVo);
    }

    @PostMapping("delete")
    public BaseProductResp deleteProduct(@RequestBody BaseProductOneIdVo baseProductOneIdVo) {
        return productBaseService.deleteProduct(baseProductOneIdVo);

    }

    @GetMapping("connect_test")
    public BaseProductResp Test() {
        return new BaseProductResp().success("data");
    }

}
