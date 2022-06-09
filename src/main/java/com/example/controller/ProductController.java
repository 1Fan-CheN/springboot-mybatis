package com.example.controller;

import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductVo;
import com.example.service.PermissionService;
import com.example.service.ProductBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Resource
    private PermissionService permissionService;

    @Resource
    private ProductBaseService productBaseService;

    @PostMapping("create")
    public BaseProductResp CreateModel(@RequestBody BaseProductVo baseProductVo) {
        // 权限检查
        if (!permissionService.checkProductPermission(baseProductVo)) {
            return new BaseProductResp().fail(2, "no permission", null);
        }
        // 重名校验
        if (productBaseService.productNameRepeat(baseProductVo.getName())) {
            return new BaseProductResp().fail(3, "product name is not unique", null);
        }

        log.info("start create product");
        try {
            String id = productBaseService.createProduct(baseProductVo);
            log.info(String.format("create product successfully, new product id: %s", id));
            return new BaseProductResp().success(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db insert error", e.toString());
        }

    }

    @PostMapping("offline")
    public BaseProductResp OfflineModel(@RequestBody BaseProductVo baseProductVo) {
        // 权限检查
        if (permissionService.checkProductPermission(baseProductVo)) {
            return new BaseProductResp().fail(2, "no permission", null);
        }
        // 检查所有子模块是否下线
        if (productBaseService.hasUndeletedModules(baseProductVo.getId())) {
            return new BaseProductResp().fail(1, "not all modules offline", null);
        }

        log.info("start offline product");
        try {
            String id = productBaseService.createProduct(baseProductVo);
            log.info(String.format("create product successfully, new product id: %s", id));
            return new BaseProductResp().success(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db insert error", e.toString());
        }
    }

    @PostMapping("delete")
    public BaseProductResp DeleteModel(@RequestBody BaseProductVo baseProductVo) {
        if (permissionService.checkProductPermission(baseProductVo)) {
            return new BaseProductResp().fail(2, "no permission", null);
        }
        log.info("start delete product");
        return new BaseProductResp().success(null);

    }

}
