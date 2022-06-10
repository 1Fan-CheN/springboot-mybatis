package com.example.service;

import com.example.entity.vo.BaseProductMultiIdVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductOneIdVo;

public interface ProductBaseService {

    BaseProductResp getIdList(BaseProductMultiIdVo baseProductMultiIdVo);

    BaseProductResp getProductInfoById(BaseProductMultiIdVo baseProductMultiIdVo);

    BaseProductResp createProduct(BaseProductOneIdVo baseProductVo);

    BaseProductResp updateProduct(BaseProductOneIdVo baseProductVo);

    BaseProductResp offlineProduct(BaseProductOneIdVo baseProductOneIdVo);

    BaseProductResp deleteProduct(BaseProductOneIdVo baseProductOneIdVo);

}
