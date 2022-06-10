package com.example.service;

import com.example.entity.vo.BaseProductMultiIdVo;
import com.example.entity.vo.BaseProductOneIdVo;
import com.example.entity.vo.BaseProductResp;

public interface ProductBaseService {

    boolean checkProductPermission(String sessionId, int Id);

    boolean productNameRepeat(String name);

    int countUndeletedModules(int productId);

    int getProductStatus(int productId);

    void updateProductStatus(int productId, int status);

    BaseProductResp listIds(BaseProductMultiIdVo baseProductMultiIdVo);

    BaseProductResp getProductInfoById(BaseProductMultiIdVo baseProductMultiIdVo);

    BaseProductResp createProduct(BaseProductOneIdVo baseProductVo);

    BaseProductResp updateProduct(BaseProductOneIdVo baseProductVo);

    BaseProductResp offlineProduct(BaseProductOneIdVo baseProductOneIdVo);

    BaseProductResp deleteProduct(BaseProductOneIdVo baseProductOneIdVo);

}
