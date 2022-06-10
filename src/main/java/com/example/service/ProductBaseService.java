package com.example.service;

import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.BaseProductInfoVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductVo;

import java.util.List;

public interface ProductBaseService {

    BaseProductResp getIdList(BaseProductInfoVo baseProductInfoVo);

    BaseProductResp getProductInfoById(BaseProductInfoVo baseProductInfoVo);

    BaseProductResp createProduct(BaseProductVo baseProductVo);

    BaseProductResp updateProduct(BaseProductVo baseProductVo);

}
