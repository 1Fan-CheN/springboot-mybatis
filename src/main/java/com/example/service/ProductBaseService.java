package com.example.service;

import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.ProductMultiIdVo;
import com.example.entity.vo.ProductOneIdVo;

public interface ProductBaseService {

    boolean checkProductPermission(String sessionId, int Id);

    boolean productNameRepeat(String name);

    int countUndeletedModules(int productId);

    int getProductStatus(int productId);

    void updateProductStatus(int productId, int status);

    BaseProductResp listIds(ProductMultiIdVo productMultiIdVo);

    BaseProductResp getProductInfoById(ProductMultiIdVo productMultiIdVo);

    BaseProductResp createProduct(ProductOneIdVo baseProductVo);

    BaseProductResp updateProduct(ProductOneIdVo baseProductVo);

    BaseProductResp offlineProduct(ProductOneIdVo productOneIdVo);

    BaseProductResp deleteProduct(ProductOneIdVo productOneIdVo);

}
